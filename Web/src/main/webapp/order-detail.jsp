<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<!-- Basic -->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Site Metas -->
<title>Freshshop</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<meta name="google-signin-client_id"
	content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
<link rel="stylesheet" href="css/header.css">
<!-- Site Icons -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
<link rel="stylesheet"
	href="fonts/themify-icons-font/themify-icons/themify-icons.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Site CSS -->
<link rel="stylesheet" href="css/style.css">
<!-- Responsive CSS -->
<link rel="stylesheet" href="css/responsive.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/custom.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<div class="page-wrap d-flex flex-row align-items-center">

		<div class="container my-5">
			<div class="title-left">
				<h3>Sửa đơn hàng</h3>
			</div>
			<form action="UpdateOrderCustomer" method="get">
			<div class="row">
				<div class="col">
					<div class="row">
						<div class="form needs-validation mx-3" style="width: 100%">
						<input type="hidden" value="${orders.id}" name="id-order">
							<div class="mb-3">
								<label for="username">Tên *</label>
								<div class="input-group">
									<input type="text" class="form-control" id="name" name="name-receiver"
										placeholder="" required value="${orders.nameReceiver}">

								</div>
							</div>
							<div class="mb-3">
								<label for="phone-num">Số điện thoại *</label>
								<div class="input-group">
									<input type="text" class="form-control" id="phone-num"
										name="phone-num" placeholder="" required
										value="${orders.phoneNum}">
									<div class="invalid-feedback" style="width: 100%;">Your
										username is required.</div>
								</div>
							</div>
							<div class="mb-3">
								<label for="email">Địa chỉ Email *</label> <input type="email"
									name="email" class="form-control" id="email" placeholder=""
									value="${orders.email}">
								<div class="invalid-feedback">Please enter a valid email
									address for shipping updates.</div>
							</div>
							<div class=" mb-3">
								<label for="address">Địa chỉ *</label> <input type="text"
									class="form-control" id="address" placeholder="" required
									name="address" value="${orders.address}">
								<div class="invalid-feedback">Vui lòng nhập mô tả địa chỉ</div>
							</div>
							<div class="mb-3">
								<label for="address">Mô tả địa chỉ *</label> <input type="text"
									class="form-control" id="address" placeholder="" required
									name="address-detail" value="${orders.addressDetail}">
								<div class="invalid-feedback">Vui lòng nhập mô tả địa chỉ</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="panel-body">
						<div class="row mt-3">
							<!-- Start .row -->
							<!-- col-lg-6 end here -->
							<div class="col-lg-12">
								<!-- col-lg-12 start here -->
								<div class="invoice-items">
									<div class="table-responsive"
										style="overflow: hidden; outline: none;" tabindex="0">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th class="per70 text-center">Sản phẩm</th>
													<th class="per5 text-center">Số lượng</th>
													<th class="per25 text-center">Đơn giá</th>
													<th class="per25 text-center">Thành tiền</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${orders.orderDetails}" var="order">
												<input type="hidden" value="${order.product.idProduct}" name = "id-product">
													<tr>
														<td>${order.product.nameProduct}</td>
														<td class='text-center'>
														<input value="${order.quantity}" type="number" onchange="checkQuantity(this,${order.quantity})" name="quantity">
														</td>
														<td class='text-center'><fmt:formatNumber
																type="number" groupingUsed="true"
																value="${order.product.price}" /></td>
														<td class='text-center'><fmt:formatNumber
																type="number" groupingUsed="true"
																value="${order.product.price*order.quantity}" /></td>
													</tr>
												</c:forEach>


											</tbody>
											<tfoot>
												<tr>
													<th colspan="2" class="text-right">Tổng cộng:</th>
													<th class="text-center"></th>
													<th class="text-center" id="totalPrice"><fmt:formatNumber
															type="number" groupingUsed="true"
															value="${orders.totalPrice()}" /></th>
												</tr>
												<tr>
													<th colspan="2" class="text-right">Giảm giá:</th>
													<th class="text-center"></th>
													<th class="text-center" id="discount"><fmt:formatNumber
															type="number" groupingUsed="true"
															value="${orders.discount}" /></th>
												</tr>
												<tr>
													<th colspan="2" class="text-right">Ship:</th>
													<th class="text-center"></th>
													<th class="text-center" id="ship"><fmt:formatNumber
															type="number" groupingUsed="true"
															value="${orders.shipping.price}" /></th>
												</tr>
												<tr>
													<th colspan="2" class="text-right">Tổng thanh toán:</th>
													<th class="text-center"></th>
													<th class="text-center" id="grandPrice"><fmt:formatNumber
															type="number" groupingUsed="true"
															value="${orders.grandPrice}" /></th>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>

							</div>
						</div>

					</div>
				</div>
				<div class="col-12 d-flex shopping-box">
							<button type="submit" class="ml-auto btn hvr-hover" id="update-order" style="display: none">Cập nhật</button>
							<a  class="ml-auto btn hvr-hover" onclick="submitUpdate()">Cập nhật</a>
						</div>
				<hr class="mb-4">
			</div>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<!-- ALL JS FILES -->
	<script src="js/address.js"></script>
		<script src="js/addcart.js"></script>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- ALL PLUGINS -->
	<script src="./js/bootsnav.js"></script>
	<script src="js/jquery.superslides.min.js"></script>
	<script src="js/bootstrap-select.js"></script>
	<script src="js/inewsticker.js"></script>
	<script src="js/images-loded.min.js"></script>
	<script src="js/isotope.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/baguetteBox.min.js"></script>
	<script src="js/form-validator.min.js"></script>
	<script src="js/contact-form-script.js"></script>
	<script src="js/custom.js"></script>
	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>
	<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>	
	<script type="text/javascript">
	function checkQuantity(e,valueOld) {
    	var maxQuantity = 50;
    	if(e.value>maxQuantity || e.value<1){
    		showErrorToast("Số lượng phải <= 50 và >=1");
    		e.value = valueOld;
    	}
    	
	}
	function submitUpdate() {
		$.confirm({
			title : 'Xác nhận!',
			content : 'Bạn có muốn cập nhật không ?',
			buttons : {
				confirm : function() {
					document.querySelector('#update-order').click();
				},
				cancel : function() {

				},
			}
		});
	}
	</script>	
</body>

</html>
