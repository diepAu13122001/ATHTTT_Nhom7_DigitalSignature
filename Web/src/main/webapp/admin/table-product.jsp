<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../admin/css/styles.css" rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">


<style>
<
style type ="text /css ">#datatablesSimple td:nth-child(6) a {
	display: block;
}

.badge {
	color: #9e9e9e;
}
</style>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="header.jsp"></jsp:include>
	<div id="layoutSidenav">
		<jsp:include page="sidebar-left.jsp"></jsp:include>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Sản phẩm</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="index.jsp">Bảng điều
								khiển</a></li>
						<li class="breadcrumb-item active">Sản phẩm /Bảng dữ liệu</li>
					</ol>

					<div class="container">
						<div class="row flex-lg-nowrap">

							<div class="col">
								<div class="e-tabs mb-3 px-3">
									<ul class="nav nav-tabs">
										<li class="nav-item"></li>
									</ul>
								</div>

								<div class="row flex-lg-nowrap">
									<div class="col mb-3">
										<div class="e-panel card">
											<div class="card-body">
												<div class="card-title">
													<h6 class="mr-2">
														<span>Users</span><small class="px-1">Be a wise
															leader</small>
													</h6>
												</div>
												<div class="e-table">
													<div class="table-responsive table-lg mt-3">
														<table class="table table-bordered">
															<thead>
																<tr>
																	<th class="align-top">
																		<div
																			class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0">
																			<input type="checkbox" class="custom-control-input"
																				id="all-items"> <label
																				class="custom-control-label" for="all-items"></label>
																		</div>
																	</th>
																	<th>Mã SP</th>
																	<th class="max-width">Tên SP</th>
																	<th class="sortable">Loại</th>
																	<th class="sortable">Ảnh</th>
																	<th>Giá (VNĐ)</th>
																	<th>Hành động</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${products}" var="product">
																	<tr id="info-${product.idProduct}">
																	
																		<td class="align-middle">
																			<div
																				class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0 align-top">
																				<input type="checkbox" class="custom-control-input"
																					id="item-1"> <label
																					class="custom-control-label" for="item-1"></label>
																			</div>
																		</td>

																		<td class="text-center align-middle" >${product.idProduct}</td>

																		<td class="text-wrap align-middle" id="info-name">${product.nameProduct}</td>
																		<td class="text-nowrap align-middle" id="info-category">${product.category_name}</td>
																		<td class="align-middle text-center">
																			<div
																				class="bg-light d-inline-flex justify-content-center align-items-center align-top"
																				style="width: 35px; height: 35px; border-radius: 3px;">
																				<img src="${product.image }" id="info-image" style="width: 100%">
																			</div>
																		</td>
																		<td class="text-center align-middle" id="info-price"><fmt:formatNumber
																				type="number" maxFractionDigits="3"
																				value="${product.price}" /></td>
																		<td class="text-center align-middle">
																			<div class="btn-group align-top">
																				<input type="hidden" value="${product.description}" id="info-description">
																				<button
																					class="btn btn-sm btn-outline-secondary badge"
																					type="button" data-toggle="modal"
																					data-target="#user-form-modal" onclick="showProduct(${product.idProduct})">Edit</button>
																				<button
																					class="btn btn-sm btn-outline-secondary badge"
																					type="button" onclick="deleteProduct(${product.idProduct})">
																					<i class="fa fa-trash"></i>
																				</button>
																			</div>
																		</td>
																	</tr>
																</c:forEach>

															</tbody>
														</table>
													</div>
													<div class="d-flex justify-content-center">
														<ul class="pagination mt-3 mb-0">
															<li class="disabled page-item"><a href="#"
																class="page-link">‹</a></li>
															<c:forEach var="i" begin="1" end="${totalPage}">
																<c:if test="${pageCurrent.equals(i)}">
																	<li class="active page-item"><a
																		href="./dataproduct?page=${i}" class="page-link">${i}</a></li>
																</c:if>
																<c:if test="${!pageCurrent.equals(i)}">
																	<li class="page-item"><a
																		href="./dataproduct?page=${i}" class="page-link">${i}</a></li>
																</c:if>
															</c:forEach>


															<li class="page-item"><a href="#" class="page-link">»</a></li>
														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-12 col-lg-3 mb-3">
										<div class="card">
											<div class="card-body">
												<div class="text-center px-xl-3">
													<button class="btn btn-success btn-block" type="button"
														data-toggle="modal" data-target="#user-form-modal"
														onclick="refeshInput()">Thêm sản phẩm mới</button>
												</div>
												<hr class="my-3">
												<div class="e-navlist e-navlist--active-bold">
													<ul class="nav">
														<li class="nav-item active"><a href=""
															class="nav-link"><span>All</span>&nbsp;<small>/&nbsp;32</small></a></li>
														<li class="nav-item"><a href="" class="nav-link"><span>Active</span>&nbsp;<small>/&nbsp;16</small></a></li>
														<li class="nav-item"><a href="" class="nav-link"><span>Selected</span>&nbsp;<small>/&nbsp;0</small></a></li>
													</ul>
												</div>
												<hr class="my-3">
												<div>
													<div class="form-group">
														<label>Date from - to:</label>
														<div>
															<input id="dates-range"
																class="form-control flatpickr-input"
																placeholder="01 Dec 17 - 27 Jan 18" type="text"
																readonly="readonly">
														</div>
													</div>
													<div class="form-group">
														<label>Search by Name:</label>
														<div>
															<input class="form-control w-100" type="text"
																placeholder="Name" value="">
														</div>
													</div>
												</div>
												<hr class="my-3">
												<div class="">
													<label>Status:</label>
													<div class="px-2">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																name="user-status" id="users-status-disabled"> <label
																class="custom-control-label" for="users-status-disabled">Disabled</label>
														</div>
													</div>
													<div class="px-2">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																name="user-status" id="users-status-active"> <label
																class="custom-control-label" for="users-status-active">Active</label>
														</div>
													</div>
													<div class="px-2">
														<div class="custom-control custom-radio">
															<input type="radio" class="custom-control-input"
																name="user-status" id="users-status-any" checked="">
															<label class="custom-control-label"
																for="users-status-any">Any</label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- User Form Modal -->
								<div class="modal fade" role="dialog" tabindex="-1"
									id="user-form-modal">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">Thêm sản phẩm</h5>
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">×</span>
												</button>
											</div>
											<div class="modal-body">
												<div class="py-1">



													<div class="row">
														<div class="col-8">
															<div class="mb-3">
																<label for="name-product" class="form-label">Tên
																	sản phẩm</label> <input type="text" class="form-control"
																	required="required" id="name-product" 
																	placeholder="Nhập tên sản phẩm">
																<div class="invalid-feedback" id>Vui lòng nhập tên sản phẩm</div>
															</div>
															<div class="mb-3">
																<label for="description" class="form-label">Mô
																	tả</label>
																<textarea class="form-control" required="required" 
																	id="description" rows="3"></textarea>
																	<div class="invalid-feedback" id>Vui lòng nhập mô tả</div>
															</div>
															<div class="mb-3">
																<label for=category-id class="form-label">Loại
																	sản phẩm</label> <select class="form-select"
																	aria-label="Default select example" id="category-id" >
																	<c:forEach var="cate" items="${categories}">
																		<option value="${cate.idCate}" >${cate.nameCate}</option>
																	</c:forEach>
																</select>
															</div>
															<div class="mb-3">
																<label for="price" class="form-label">Giá</label>
																<div class="input-group">
																	<input type="number" required="required"
																		class="form-control" id="price" 
																		aria-label="VND amount (with dot and two decimal places)">
																	<span class="input-group-text">VND</span> <span
																		class="input-group-text">0.00</span>
																		<div class="invalid-feedback" id>Vui lòng nhập giá</div>
																</div>
															</div>

														</div>
														<div class="col-4">
															<div class="mb-3">
																<label for="formFile" class="form-label">Ảnh đại
																	diện</label> <input class="form-control" required="required"
																	name="image" type="text" id="url-image" 
																	onchange="loadImage()">
																		<div class="invalid-feedback" id>Vui lòng url ảnh</div>
																	 <img alt="" src=""
																	id="preview-image"
																	style="width: 100%; margin-top: 10px; border: 1px solid #ced4da;">

															</div>

														</div>
													</div>
													<div class="row">
														<div class="col d-flex justify-content-end">
															<button class="btn btn-primary" onclick="addProduct()">Thêm
															</button>
														</div>
													</div>


												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script type="text/javascript">
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
			$("#name-product").css("border","1px solid #ced4da");
			$("#name-product").parent().children().get( 2 ).style.display = "none";
			$("#description").css("border","1px solid #ced4da");
			$("#description").parent().children().get( 2 ).style.display = "none";
			$("#price").css("border","1px solid #ced4da");
			$("#price").parent().children().get( 3 ).style.display = "none";
			$("#url-image").css("border","1px solid #ced4da");
			$("#url-image").parent().children().get( 2 ).style.display = "none";
		}
		function validate(nameProduct,description,categoryId,price,image) {			
			var isvalid = true;
			if(nameProduct==""){
				$("#name-product").css("border-color","#dc3545");
				$("#name-product").parent().children().get( 2 ).style.display = "block";
				return false;
			}else{			
				$("#name-product").css("border","1px solid #ced4da");
				$("#name-product").parent().children().get( 2 ).style.display = "none";
				
			}
			if(description==""){
				$("#description").css("border-color","#dc3545");
				$("#description").parent().children().get( 2 ).style.display = "block";
				return false;
			}else{
				$("#description").css("border","1px solid #ced4da");
				$("#description").parent().children().get( 2 ).style.display = "none";
			
			}
			if(price==""){
				$("#price").css("border-color","#dc3545");
				$("#price").parent().children().get( 3 ).style.display = "block";
				return false;
			}else{
				$("#price").css("border","1px solid #ced4da");
				$("#price").parent().children().get( 3 ).style.display = "none";
				
			}
			if(image==""){
				$("#url-image").css("border-color","#dc3545");
				$("#url-image").parent().children().get( 2 ).style.display = "block";
				return false;
			}
			else{
				$("#url-image").css("border","1px solid #ced4da");
				$("#url-image").parent().children().get( 2 ).style.display = "none";
				
			}
			return true;
		}
		function addProduct() {	
			var nameProduct = $("#name-product").val();
			var description = $("#description").val();
			var categoryId = $("#category-id").val();
			var price = $("#price").val();
			var image = $("#url-image").val();
			var isValid = validate(nameProduct,description,categoryId,price,image);
			console.log(isValid)
			if(isValid){
				$.ajax({
					type : "GET",
					url : "./add-product",
					data : {
						nameProduct : nameProduct,
						description : description,
						categoryId : categoryId,
						price : price,
						image : image
					},
					success : function(response) {
						console.log(response)
						if (response == 'true') {
							showSwal('success-message','Thêm sản phẩm thành công');
							setTimeout(reload, 2000);
						} else {
							showSwal('error');
						}

					},
					error : function(request) {
						showSwal('error','','Có lỗi xảy ra');
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
					title : 'Thành công!',
					text : text,
					type : 'success',
					button : {
						text : "Continue",
						value : true,
						visible : true,
						className : "btn btn-primary"
					}
				})

			} else {
				swal(messageError);
			}
		}
		function showProduct(id) {
			$(".modal-title").text("Sửa - #"+id);
			cssDefault();
			var infoName = $("#info-"+id+" #info-name").text();
			var infoDes = $("#info-"+id+" #info-description").val();
			var infoPrice = $("#info-"+id+" #info-price").text();
			var infoImage = $("#info-"+id+" #info-image").attr("src");
			$("#name-product").val(infoName);
			$("#description").val(infoDes);
			$("#price").val(infoPrice);
			$("#url-image").val(infoImage);
			loadImage();
			var cate =  $("#info-"+id+" #info-category").text();
			setSelect(cate);
			$(".col.d-flex.justify-content-end").html("<button class='btn btn-primary' onclick='updateProduct("+id+")'>Lưu thay đổi </button>");
		}
		function setSelect(category){
			var options = $("#category-id option");
			for(o of options){
				var c = o.text;
				console.log(c +" "+ category)
				if(c === category){
					o.setAttribute("selected","selected");
					break;
				}
				
			}
		}
		function isChange(infoName,infoDes,infoPrice,infoImage,cate) {
			if(infoName != $("#name-product").val()){
				return true;
			}
			if(infoDes !=$("#description").val()){
				return true;
			}
			if(infoPrice != $("#price").val()){
				return true;
			}
			if(infoImage != $("#url-image").val()){
				return true;
			}
			if(cate !=  $('#category-id').find(":selected").text()){
				return true;
			}
			return false;
		}
		function updateProduct(id) {
			var infoName = $("#info-"+id+" #info-name").text();
			var infoDes = $("#info-"+id+" #info-description").val();
			var infoPrice = $("#info-"+id+" #info-price").text();
			var infoImage = $("#info-"+id+" #info-image").attr("src");
			var cate =  $("#info-"+id+" #info-category").text();
			var change =  isChange(infoName,infoDes,infoPrice,infoImage,cate);
			if(change){
				var nameProduct = $("#name-product").val();
				var description = $("#description").val();
				var categoryId = $("#category-id").val();
				var price = $("#price").val();
				var image = $("#url-image").val();
				$.ajax({
					type : "GET",
					url : "./update-product",
					data : {
						id: id,
						nameProduct : nameProduct,
						description : description,
						categoryId : categoryId,
						price : price,
						image : image
					},
					success : function(response) {
						console.log(response)
						if (response == 'true') {
							showSwal('success-message','Cập nhật thành công');
							setTimeout(reload, 4000);
						} else {
							showSwal('error','','Có lỗi xảy ra');
						}
					},
					error : function(request) {
						showSwal('error','','Có lỗi xảy ra');
					}
				})						
			}else{
				showSwal('error','','Không có thay đổi');
			}
		
		}
		function deleteProduct(id) {
			$.confirm({
			    title: 'Xác nhận!',
			    content: 'Bạn có muốn xoá sản phẩm này không ?',
			    buttons: {
			        confirm: function () {			        	
			        	requestDelete(id);
			        },
			        cancel: function () {
			           
			        },
			    }
			});
					
		}
		function requestDelete(id) {
			$.ajax({
				type : "GET",
				url : "./detele-product",
				data : {
					id: id,				
				},
				success : function(response) {
					console.log(response)
					if (response == 'true') {
						showSwal('success-message','Xoá thành công');
						setTimeout(reload, 4000);
					} else {
						showSwal('error','','Có lỗi xảy ra');
					}
				},
				error : function(request) {
					showSwal('error','','Có lỗi xảy ra');
				}
			})
		}
	</script>
	<script src="js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/c31e7889db.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="../admin/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="../admin/assets/demo/chart-area-demo.js"></script>
	<script src="../admin/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="../admin/js/datatables-simple-demo.js"></script>
	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script src="../js/popper.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
</body>
</html>