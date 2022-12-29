/**
 * 
 */
function loadImage() {
	var url = $("#url-image").val();
	$("#preview-image").attr("src", url);
}
function refeshInput() {
	$(".modal-title").text("Thêm sản phẩm");
	$("#name-product").val(null);
	$("#description").val(null);
	$("#price").val(null);
	$("#url-image").val(null);
	$("#preview-image").attr("src", null);
	$(".col.d-flex.justify-content-end").html("<button class='btn btn-primary' onclick='addProduct()'>Lưu thay đổi </button>");
};
function cssDefault() {
	$("#name-product").css("border", "1px solid #ced4da");
	$("#name-product").parent().children().get(2).style.display = "none";
	$("#description").css("border", "1px solid #ced4da");
	$("#description").parent().children().get(2).style.display = "none";
	$("#price").css("border", "1px solid #ced4da");
	$("#price").parent().children().get(3).style.display = "none";
	$("#url-image").css("border", "1px solid #ced4da");
	$("#url-image").parent().children().get(2).style.display = "none";
}
function validate(nameProduct, description, categoryId, price, image) {
	var isvalid = true;
	if (nameProduct == "") {
		$("#name-product").css("border-color", "#dc3545");
		$("#name-product").parent().children().get(2).style.display = "block";
		return false;
	} else {
		$("#name-product").css("border", "1px solid #ced4da");
		$("#name-product").parent().children().get(2).style.display = "none";

	}
	if (description == "") {
		$("#description").css("border-color", "#dc3545");
		$("#description").parent().children().get(2).style.display = "block";
		return false;
	} else {
		$("#description").css("border", "1px solid #ced4da");
		$("#description").parent().children().get(2).style.display = "none";

	}
	if (price == "") {
		$("#price").css("border-color", "#dc3545");
		$("#price").parent().children().get(3).style.display = "block";
		return false;
	} else {
		$("#price").css("border", "1px solid #ced4da");
		$("#price").parent().children().get(3).style.display = "none";

	}
	if (image == "") {
		$("#url-image").css("border-color", "#dc3545");
		$("#url-image").parent().children().get(2).style.display = "block";
		return false;
	}
	else {
		$("#url-image").css("border", "1px solid #ced4da");
		$("#url-image").parent().children().get(2).style.display = "none";

	}
	return true;
}
function addProduct() {
	var nameProduct = $("#name-product").val();
	var description = $("#description").val();
	var categoryId = $("#category-id").val();
	var price = $("#price").val();
	var image = $("#url-image").val();
	var isValid = validate(nameProduct, description, categoryId, price, image);
	console.log(isValid)
	if (isValid) {
		$.ajax({
			type: "GET",
			url: "./add-product",
			data: {
				nameProduct: nameProduct,
				description: description,
				categoryId: categoryId,
				price: price,
				image: image
			},
			success: function(response) {
				console.log(response)
				if (response == 'true') {
					showSwal('success-message', 'Thêm sản phẩm thành công');
					setTimeout(reload, 2000);
				} else {
					showSwal('error');
				}

			},
			error: function(request) {
				showSwal('error', '', 'Có lỗi xảy ra');
			}
		}

		)

	}

};
function reload() {
	location.reload();
}

function showSwal(type, text, messageError) {
	'use strict';
	if (type === 'success-message') {
		swal({
			title: 'Thành công!',
			text: text,
			type: 'success',
			button: {
				text: "Continue",
				value: true,
				visible: true,
				className: "btn btn-primary"
			}
		})

	} else {
		swal(messageError);
	}
}
function showProduct(id) {
	$(".modal-title").text("Sửa - #" + id);
	cssDefault();
	var infoName = $("#info-" + id + " #info-name").text();
	var infoDes = $("#info-" + id + " #info-description").val();
	var infoPrice = $("#info-" + id + " #info-price").text();
	var infoImage = $("#info-" + id + " #info-image").attr("src");
	$("#name-product").val(infoName);
	$("#description").val(infoDes);
	$("#price").val(infoPrice);
	$("#url-image").val(infoImage);
	loadImage();
	var cate = $("#info-" + id + " #info-category").text();
	setSelect(cate);
	$(".col.d-flex.justify-content-end").html("<button class='btn btn-primary' onclick='updateProduct(" + id + ")'>Lưu thay đổi </button>");
}
function setSelect(category) {
	var options = $("#category-id option");
	for (o of options) {
		var c = o.text;
		console.log(c + " " + category)
		if (c === category) {
			o.setAttribute("selected", "selected");
			break;
		}

	}
}
function isChange(infoName, infoDes, infoPrice, infoImage, cate) {
	if (infoName != $("#name-product").val()) {
		return true;
	}
	if (infoDes != $("#description").val()) {
		return true;
	}
	if (infoPrice != $("#price").val()) {
		return true;
	}
	if (infoImage != $("#url-image").val()) {
		return true;
	}
	if (cate != $('#category-id').find(":selected").text()) {
		return true;
	}
	return false;
}
function updateProduct(id) {
	var infoName = $("#info-" + id + " #info-name").text();
	var infoDes = $("#info-" + id + " #info-description").val();
	var infoPrice = $("#info-" + id + " #info-price").text();
	var infoImage = $("#info-" + id + " #info-image").attr("src");
	var cate = $("#info-" + id + " #info-category").text();
	var change = isChange(infoName, infoDes, infoPrice, infoImage, cate);
	if (change) {
		var nameProduct = $("#name-product").val();
		var description = $("#description").val();
		var categoryId = $("#category-id").val();
		var price = $("#price").val();
		var image = $("#url-image").val();
		$.ajax({
			type: "GET",
			url: "./update-product",
			data: {
				id: id,
				nameProduct: nameProduct,
				description: description,
				categoryId: categoryId,
				price: price,
				image: image
			},
			success: function(response) {
				console.log(response)
				if (response == 'true') {
					showSwal('success-message', 'Cập nhật thành công');
					setTimeout(reload, 4000);
				} else {
					showSwal('error', '', 'Có lỗi xảy ra');
				}
			},
			error: function(request) {

				showSwal('error', '', 'Có lỗi xảy ra');
			}
		})

	} else {
		showSwal('error', '', 'Không có thay đổi');
	}

}