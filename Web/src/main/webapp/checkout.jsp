<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="en">
<!-- Basic -->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Site Metas -->
<title>Freshshop - Thực phẩm sạch cho mọi người</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<meta name="google-signin-client_id" content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
	<link rel="stylesheet" href="css/header.css">
<!-- Site Icons -->
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Site CSS -->
<link rel="stylesheet" href="css/style.css">
<!-- Responsive CSS -->
<link rel="stylesheet" href="css/responsive.css">
<!-- Custom CSS -->
<link rel="stylesheet" href="css/custom.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<!-- Start All Title Box -->
	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>Checkout</h2>
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href=".">Trang chủ</a></li>
						<li class="breadcrumb-item active">Checkout</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

	<!-- Start Cart  -->
	<div class="cart-box-main">
		<div class="container">
		
			<div class="row">
				<div class="col-sm-6 col-lg-6 mb-3">
					<div class="checkout-address">
						<div class="title-left">
							<h3>Thông tin thanh toán</h3>
						</div>
						<form class="needs-validation" action="checkout" >
							<div class="mb-3">
								<label for="username">Tên *</label>
								<div class="input-group">
									<input type="text" class="form-control" id="name" name="name"
										placeholder="" required>
								
								</div>
							</div>
							<div class="mb-3">
								<label for="phone-num">Số điện thoại *</label>
								<div class="input-group">
									<input type="text" class="form-control" id="phone-num" name="phone-num"
										placeholder="" required>
									<div class="invalid-feedback" style="width: 100%;">Your
										username is required.</div>
								</div>
							</div>
							<div class="mb-3">
								<label for="email">Địa chỉ Email *</label> <input type="email" name= "email"
									class="form-control" id="email" placeholder="">
								<div class="invalid-feedback">Please enter a valid email
									address for shipping updates.</div>
							</div>

							<div class="row">
								<div class="col-md-4 mb-3">
									<label for="country">Tỉnh/TP *</label>
									<!-- 									 <select
										class="wide w-100" id="country">
										<option value="Choose..." data-display="Select">Choose...</option>
										<option value="United States">United States</option>

									</select> -->
									<select name="city" class="wide w-100"
										id="country" required=""
										onchange="print_state('sate',this.selectedIndex);">
										<option value="">Chọn</option>
									</select>

									<div class="invalid-feedback">Hãy chọn Tỉnh/TP</div>
								</div>
								<div class="col-md-4 mb-3">
									<label for="district">Quận/Huyện *</label> <select
										name="district" class="wide w-100" id="district"
										required=""
										onchange="print_district('wards',this.selectedIndex);">
										<option value="">Chọn</option>
									</select>
									<div class="invalid-feedback">Hãy chọn Quận/Huyện</div>
								</div>
								<div class="col-md-4 mb-3">
									<label for="ward">Phường xã *</label> <select
										name="ward" class="wide w-100" id="ward"
										required="">
										<option value="">Chọn</option>
									</select>
									<div class="invalid-feedback">Hãy chọn Phường/Xã</div>
								</div>
								<input class="billing_address_1" name="" type="hidden" value="">
								<input class="billing_address_2" name="" type="hidden" value="">

							</div>
														<div class="mb-3">
								<label for="address">Mô tả địa chỉ *</label> <input type="text"
									class="form-control" id="address" placeholder="" required name="des-address">
								<div class="invalid-feedback">Vui lòng nhập mô tả địa chỉ</div>
							</div>
				
							<hr class="mb-4">
							
					<div class="mb-3">
								<label for="ship">Giao hàng</label> 
								<div class="mb-4">
									<div class="custom-control custom-radio">
										<input id="shippingOption1" name="shipping-option"
											class="custom-control-input" checked="checked" type="radio" value="1">
										<label class="custom-control-label" for="shippingOption1">Giao
											hàng tiêu chuẩn</label>
											 <span class="float-right font-weight-bold">10.000
											VND</span>
									</div>
									<div class="ml-4 mb-2 small">(1-2 ngày)</div>
									<div class="custom-control custom-radio">
										<input id="shippingOption2" name="shipping-option"
											class="custom-control-input" type="radio"  value="2"> <label
											class="custom-control-label" for="shippingOption2">Giao
											hàng nhanh</label> <span class="float-right font-weight-bold">20.000
											VND</span>
									</div>
									<div class="ml-4 mb-2 small">(5-6 tiếng)</div>
									<div class="custom-control custom-radio">
										<input id="shippingOption3" name="shipping-option"
											class="custom-control-input" type="radio"  value="3"> <label
											class="custom-control-label" for="shippingOption3">Giao
											ngay lập tức</label> <span class="float-right font-weight-bold">30.000
											VND</span>
									</div>
									<div class="ml-4 mb-2 small">(20-30 phút)</div>
								</div>
							</div>
							<hr class="mb-4">
	<!-- 						<div class="title">
								<span>Payment</span>
							</div>
							<div class="d-block my-3">
								<div class="custom-control custom-radio">
									<input id="debit" name="paymentMethod" type="radio"
										class="custom-control-input" checked required> <label
										class="custom-control-label" for="debit">MoMo</label>
									
								</div>
								<div class="custom-control custom-radio">
									<input id="credit" name="paymentMethod" type="radio"
										class="custom-control-input"> <label
										class="custom-control-label" for="credit">Thẻ tín dụng/Thẻ ghi nợ</label>
								</div>

								<div class="custom-control custom-radio">
									<input id="paypal" name="paymentMethod" type="radio" 
										class="custom-control-input" required> <label
										class="custom-control-label" for="paypal">Tiền mặt</label>
								</div>
							</div> -->
	
				<!-- 				<button type="button" 
									data-toggle="collapse" data-target="#payment-cart">Simple
									collapsible</button> -->
			<!-- 				<div id="qr-code" >
								<div class="row">
									<img alt=""  style="width: 50%"src="images/payment-icon/momo-payment.png">
								</div>
							</div> -->
							<div id="payment-cart" class="collapse" >
								<div class="row">
									<div class="col-md-6 mb-3">
										<label for="cc-name">Name on card</label> <input type="text"
											class="form-control" id="cc-name" placeholder="" >
										<small class="text-muted">Full name as displayed on
											card</small>
										<div class="invalid-feedback">Name on card is required</div>
									</div>
									<div class="col-md-6 mb-3">
										<label for="cc-number">Credit card number</label> <input
											type="text" class="form-control" id="cc-number"
											placeholder="" >
										<div class="invalid-feedback">Credit card number is
											required</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-3 mb-3">
										<label for="cc-expiration">Expiration</label> <input
											type="text" class="form-control" id="cc-expiration"
											placeholder="" >
										<div class="invalid-feedback">Expiration date required</div>
									</div>
									<div class="col-md-3 mb-3">
										<label for="cc-expiration">CVV</label> <input type="text"
											class="form-control" id="cc-cvv" placeholder="" >
										<div class="invalid-feedback">Security code required</div>
									</div>
									<div class="col-md-6 mb-3">
										<div class="payment-icon">
											<ul>
												<li><img class="img-fluid"
													src="images/payment-icon/1.png" alt=""></li>
												<li><img class="img-fluid"
													src="images/payment-icon/2.png" alt=""></li>
												<li><img class="img-fluid"
													src="images/payment-icon/3.png" alt=""></li>
												<li><img class="img-fluid"
													src="images/payment-icon/5.png" alt=""></li>
												<li><img class="img-fluid"
													src="images/payment-icon/7.png" alt=""></li>
											</ul>
										</div>
									</div>
								</div>
							</div>

							<hr class="mb-1">
							<input type="submit" id="checkout-submit">
						</form>
					</div>
				</div>
				<div class="col-sm-6 col-lg-6 mb-3">
					<div class="row">
						<div class="col-md-12 col-lg-12">
							<div class="odr-box">
								<div class="title-left">
									<h3>Shopping cart</h3>
								</div>

								<div class="rounded p-2 bg-light">
									<c:forEach var="product" items="${cart.getCartItems()}">
										<div class="media mb-2 border-bottom">
											<div class="media-body">
												<c:url var="url" value="/shopdetail">
													<c:param name="idProduct"
														value="${product.getItem().idProduct}"></c:param>
												</c:url>
												<a href="${url}">${product.getItem().nameProduct}</a>
												<div class="small text-muted">
													Price:
													<fmt:formatNumber type="number" maxFractionDigits="3"
														value="${product.getItem().price}" />
													₫ <span class="mx-2">|</span> Qty:${ product.getQuantity()}
													<span class="mx-2">|</span> Subtotal:
													<fmt:formatNumber type="number" maxFractionDigits="3"
														value="${product.totalPrice()}" />
													₫
												</div>
											</div>
										</div>
									</c:forEach>



								</div>
							</div>
						</div>
						<div class="col-md-12 col-lg-12">
							<div class="order-box">
								<div class="title-left">
									<h3>Your order</h3>
								</div>
								<div class="d-flex">
									<div class="font-weight-bold">Product</div>
									<div class="ml-auto font-weight-bold">Total</div>
								</div>
								<hr class="my-1">
								<div class="d-flex">
									<h4>Tổng giá trị</h4>
									<div class="ml-auto font-weight-bold cast"> <fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getTotal()}" /> VND </div>
								</div>
								<div class="d-flex">
									<h4>Giảm giá</h4>
									<div class="ml-auto font-weight-bold cast">0 VND</div>
								</div>
								<hr class="my-1">
								<div class="d-flex">
									<h4>Mã giảm giá</h4>
									<div class="ml-auto font-weight-bold cast">0 VND</div>
								</div>
								<div class="d-flex">
									<h4>Thuế VAT (5%)</h4>
									<div class="ml-auto font-weight-bold cast"> <fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getTax(0.05)}" /> VND</div>
								</div>
								<div class="d-flex">
									<h4>Phí ship</h4>
									<div id ="shipcost" class="ml-auto font-weight-bold">10.000 VND</div>
								</div>
								<hr>
								<input type="text" hidden="" value="${cart.getTax(0.05)+cart.getTotal()}" id="subtotal">
								<div class="d-flex gr-total">
									<h5>Thành tiền</h5>
									<div id="grand-total" class="ml-auto h5"> <fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getTax(0.05)+cart.getTotal()+10000}" /> VND</div>
								</div>
								<hr>
							</div>
						</div>
						<div class="col-12 d-flex shopping-box">
							<a onclick="checkOut()" class="ml-auto btn hvr-hover">Xác thực</a>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- End Cart -->




	<!-- Start Footer  -->
	<jsp:include page="footer.jsp"></jsp:include>

	<!-- ALL JS FILES -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- ALL PLUGINS -->
	<script src="js/jquery.superslides.min.js"></script>
	<script src="js/bootstrap-select.js"></script>
	<script src="js/inewsticker.js"></script>
	<script src="js/bootsnav.js"></script>
	<script src="js/images-loded.min.js"></script>
	<script src="js/isotope.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/baguetteBox.min.js"></script>
	<script src="js/form-validator.min.js"></script>
	<script src="js/contact-form-script.js"></script>
	<script src="js/custom.js"></script>
	  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
	<script type="text/javascript">
  
	/* Calcutate grand total price */


	</script>
	
	<script type="text/javascript">
	/*  Select method ship */
		var shipOption1 = document.querySelector('input[id="shippingOption1"]');
		var shipOption2 = document.querySelector('input[id="shippingOption2"]');
		var shipOption3 = document.querySelector('input[id="shippingOption3"]');
		var shipcost = document.querySelector('#shipcost');
		var grandtotal = document.querySelector('#grand-total');
		var subtotal = document.querySelector('#subtotal');

	
		
		shipOption1.onclick= function(){
			shipcost.innerHTML = '<fmt:formatNumber type = "number" 
					maxFractionDigits = "3" value = "10000" /> VND'	;
						var total = (Number(subtotal.value)+10000).toString();
						var format = total.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
						grandtotal.innerHTML=format +" VND";
		}
		shipOption2.onclick= function(){
			shipcost.innerHTML = '<fmt:formatNumber type = "number" 
					maxFractionDigits = "3" value = "20000" /> VND'	;
						var total = (Number(subtotal.value)+20000).toString();
						var format = total.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
						grandtotal.innerHTML=format +" VND";
		}
		shipOption3.onclick= function(){
			shipcost.innerHTML = '<fmt:formatNumber type = "number" 
					maxFractionDigits = "3" value = "30000" /> VND'	;
						var total = (Number(subtotal.value)+30000).toString();
						var format = total.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
						grandtotal.innerHTML=format +" VND";
		}

	
	</script>
	<script type="text/javascript">
	/* change method payment */
		var paymentCredit = document.querySelector('input[id="credit"]');
		var paymentMomo = document.querySelector('input[id="debit"]');
		var paymentCast = document.querySelector('input[id="paypal"]');
		var paymentBox = document.querySelector('#payment-cart');
		var paymentBox2 = document.querySelector('#qr-code');
		paymentCredit.onclick= function(){
			paymentBox.classList.remove("collapse");
			paymentBox2.classList.add("collapse");
			
		}
		paymentMomo.onclick= function(){
			paymentBox2.classList.remove("collapse");
			paymentBox.classList.add("collapse");
		}
		paymentCast.onclick = function(){
			paymentBox2.classList.add("collapse");
			paymentBox.classList.add("collapse");
		}

	</script>
	<script>
/* 	load data about provinces, districts and wards in VietName */
			fetch("json/data.json").then(res => res.json())
		    .then(data => {
		        var option_str = document.getElementById('country');
		        option_str.length = 0;
		        option_str.options[0] = new Option('Chọn Tỉnh/TP', '');
		
		        option_str.selectedIndex = 0;
		        for (var i = 0; i < data.length; i++) {
		            option_str.options[option_str.length] = new Option(data[i].Name, data[i].Name);
		        }
		   
		    })
			function print_state(state_id, state_index) {
			    fetch("json/data.json").then(res => res.json())
			        .then(data => {
			        	 var option_str = document.querySelector('#district');
			            option_str.length = 0;
			            option_str.options[0] = new Option('Chọn Quận/Huyện', '');
			            option_str.selectedIndex = 0;
			            if (state_index > 0) {
			                for (var i = 0; i < data[state_index - 1].Districts.length; i++) {
			                    option_str.options[option_str.length] = new Option(data[state_index - 1].Districts[i].Name, data[state_index - 1].Districts[i].Name);
			                }
			            }
			
			        })
			}
				function print_district(district_id, district_index) {
				    fetch("json/data.json").then(res => res.json())
				        .then(data => {
				            var option_str = document.getElementById('ward');
				            option_str.length = 0;
				            option_str.options[0] = new Option('Chọn Phường/Xã', '');
				            option_str.selectedIndex = 0;
				            var state_index = document.getElementById('country').selectedIndex;
				            if (state_index > 0) {
				                state_index = state_index - 1;
				                if (district_index > 0) {
				                    for (var i = 0; i < data[state_index].Districts[district_index - 1].Wards.length; i++) {
				                        var index = data[state_index].Districts[district_index - 1].Wards[i];
				                        option_str.options[option_str.length] = new Option(index.Name, index.Name);
				                    }
				                }
				            }
				
				        })
		
		}
				function checkOut(){
					document.getElementById("checkout-submit").click();
				}
	</script>
</body>

</html>