/**
 * 
 */
function addToCart(id) {
	$.ajax({
		type: "GET",
		url: "addcart",
		data: { id: id },
		success: function(responseJson) {
			console.log(responseJson)
			showSuccessToast();
			$('.badge').html(responseJson.numberOfItems);
			$('.cart-list').html('')
			let total = 0;
			$.each(responseJson.items, function(key, value) {
				total += value.item.price * value.quantity;
				$('.cart-list').append(
					'<li> <a href="" class="photo"><img src="' + value.item.image + '" class="cart-thumb" alt="" /></a>'
					+ '<h6><a href="">' + value.item.nameProduct + '</a></h6>' +
					'<p>' + value.quantity + 'x- <span class="price">' + formatCurrent(value.item.price) + '</span></p></li>'
				)
			});
			$('.cart-list').append('<li class="total"><a href="cart" class="btn btn-default hvr-hover btn-cart">Giỏ hàng</a>' +
				'<span class="float-right"><strong>Tổng :' + formatCurrent(total) + '</strong></span></li>'
			)
		}
	})
};
function generateKey(){
	var keySize = $("#keySize").val();
	$.ajax({
		type: "GET",
		url: "create-key",
		data: { keySize: keySize },
		success: function(responseJson) {
			showSuccessGenerateKey();
			console.log(responseJson);
			$("#publicKey").text(responseJson[0].keyString);
			$("#privateKey").text(responseJson[1].keyString);
			$("#download-privatekey").attr("href","./download?filename="+responseJson[1].urlDownload);
			$("#download-publickey").attr("href","./download?filename="+ responseJson[0].urlDownload);
			
		},
		 error: function (request, status, error) {
        	showErrorToast();
    	}
	})
	
}

function formatCurrent(value) {
	return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
}
function showSuccessToast() {
	toast({
		title: "Thành công!",
		message: "Bạn đã thêm sản phẩm vào giỏ hàng.",
		type: "success",
		duration: 3000
	});
}
function showSuccessGenerateKey() {
	toast({
		title: "Thành công!",
		message: "Tạo khoá thành công",
		type: "success",
		duration: 3000
	});
}
function showErrorToast() {
	toast({
		title: "Thất bại!",
		message: "Cố lỗi, tạo khoá thất bại",
		type: "error",
		duration: 3000
	});
}
function toast({ title = "", message = "", type = "info", duration = 3000 }) {
  const main = document.getElementById("toast");
  if (main) {
    const toast = document.createElement("div");

    // Auto remove toast
    const autoRemoveId = setTimeout(function () {
      main.removeChild(toast);
    }, duration + 1000);

    // Remove toast when clicked
    toast.onclick = function (e) {
      if (e.target.closest(".toast__close")) {
        main.removeChild(toast);
        clearTimeout(autoRemoveId);
      }
    };

    const icons = {
      success: "fas fa-check-circle",
      info: "fas fa-info-circle",
      warning: "fas fa-exclamation-circle",
      error: "fas fa-exclamation-circle"
    };
    const icon = icons[type];
    const delay = (duration / 1000).toFixed(2);

    toast.classList.add("toast", `toast--${type}`);
    toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

    toast.innerHTML = `
                    <div class="toast__icon">
                        <i class="${icon}"></i>
                    </div>
                    <div class="toast__body">
                        <h3 class="toast__title">${title}</h3>
                        <p class="toast__msg">${message}</p>
                    </div>
                    <div class="toast__close">
                        <i class="fas fa-times"></i>
                    </div>
                `;
    main.appendChild(toast);
  }
}
