<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<meta name="google-signin-client_id"
	content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
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
<style>
* {
	margin: 0;
	padding: 0
}

*root { -
	-maincolor: #b0b435;
}

body {
	overflow-x: hidden;
	background: #eee
}

.btn-success {
	background-color: #b0b435;
	border-color: #b0b435;
}

.top-highlight, .active1 {
	color: #b0b435 !important;
}

#bg-div {
	margin: 0;
	margin-top: 100px;
	margin-bottom: 100px
}

#border-btm {
	padding-bottom: 20px;
	margin-bottom: 0px;
	box-shadow: 0px 35px 2px -35px lightgray
}

#test {
	margin-top: 0px;
	margin-bottom: 40px;
	border: 1px solid #FFE082;
	border-radius: 0.25rem;
	width: 60px;
	height: 30px;
	background-color: #FFECB3
}

.active1 {
	font-weight: bold
}

.bar4 {
	width: 35px;
	height: 5px;
	background-color: #ffffff;
	margin: 6px 0
}

.list-group .tabs {
	color: #000000
}

#menu-toggle {
	height: 50px
}

#new-label {
	padding: 2px;
	font-size: 10px;
	font-weight: bold;
	background-color: red;
	color: #ffffff;
	border-radius: 5px;
	margin-left: 5px
}

#sidebar-wrapper {
	min-height: 100vh;
	margin-left: -15rem;
	-webkit-transition: margin .25s ease-out;
	-moz-transition: margin .25s ease-out;
	-o-transition: margin .25s ease-out;
	transition: margin .25s ease-out
}

#sidebar-wrapper .sidebar-heading {
	padding: 0.875rem 1.25rem;
	font-size: 1.2rem
}

#sidebar-wrapper .list-group {
	width: 15rem
}

#page-content-wrapper {
	min-width: 100vw;
	padding-left: 20px;
	padding-right: 20px
}

#wrapper.toggled #sidebar-wrapper {
	margin-left: 0
}

.list-group-item.active {
	z-index: 2;
	color: #fff;
	background-color: #fff !important;
	border-color: #fff !important
}

@media ( min-width : 768px) {
	#sidebar-wrapper {
		margin-left: 0
	}
	#page-content-wrapper {
		min-width: 0;
		width: 100%
	}
	#wrapper.toggled #sidebar-wrapper {
		margin-left: -15rem;
		display: none
	}
}

.card0 {
	margin-top: 10px;
	margin-bottom: 10px
}

.top-highlight {
	color: #00C853;
	font-weight: bold;
	font-size: 20px
}

.form-card input, .form-card textarea {
	padding: 10px 15px 5px 15px;
	border: none;
	border: 1px solid lightgrey;
	border-radius: 6px;
	margin-bottom: 25px;
	margin-top: 2px;
	width: 100%;
	box-sizing: border-box;
	font-family: arial;
	color: #2C3E50;
	font-size: 14px;
	letter-spacing: 1px
}

.form-card input:focus, .form-card textarea:focus {
	-moz-box-shadow: 0px 0px 0px 1.5px skyblue !important;
	-webkit-box-shadow: 0px 0px 0px 1.5px skyblue !important;
	box-shadow: 0px 0px 0px 1.5px skyblue !important;
	font-weight: bold;
	border: 1px solid skyblue;
	outline-width: 0
}

input.btn-success {
	height: 50px;
	color: #ffffff;
	opacity: 0.9
}

#below-btn a {
	font-weight: bold;
	color: #000000
}

.input-group {
	position: relative;
	width: 100%;
	overflow: hidden
}

.input-group input {
	position: relative;
	height: 90px;
	margin-left: 1px;
	margin-right: 1px;
	border-radius: 6px;
	padding-top: 30px;
	padding-left: 25px
}

.input-group label {
	position: absolute;
	height: 24px;
	background: none;
	border-radius: 6px;
	line-height: 48px;
	font-size: 15px;
	color: gray;
	width: 100%;
	font-weight: 100;
	padding-left: 25px
}

input:focus+label {
	color: #1E88E5
}

#qr {
	margin-bottom: 150px;
	margin-top: 50px
}
</style>
</head>

<body>
	<!-- Start Main Top -->
	<jsp:include page="header.jsp"></jsp:include>


	<div class="row justify-content-center">
		
		<div class="col-lg-9 col-12">
		<c:if test="${authenticateSuccess!=null}">
			<div class="alert alert-success" role="alert" style="text-align: center; font-size: 22px;">
				${authenticateSuccess}</div>
		</c:if>
			<div class="card card0">
				<div class="d-flex" id="wrapper">
					<!-- Sidebar -->
					<div class="bg-light border-right" id="sidebar-wrapper">
						<div class="sidebar-heading pt-5 pb-4">
							<strong>PAY WITH</strong>
						</div>
						<div class="list-group list-group-flush">
							<a data-toggle="tab" href="#menu1" id="tab1"
								class="tabs list-group-item bg-light">
								<div class="list-div my-2">
									<div class="fa fa-home"></div>
									&nbsp;&nbsp; Bank
								</div>
							</a> <a data-toggle="tab" href="#menu2" id="tab2"
								class="tabs list-group-item active1">
								<div class="list-div my-2">
									<div class="fa fa-credit-card"></div>
									&nbsp;&nbsp; Card
								</div>
							</a> <a data-toggle="tab" href="#menu3" id="tab3"
								class="tabs list-group-item bg-light">
								<div class="list-div my-2">
									<div class="fa fa-qrcode"></div>
									&nbsp;&nbsp;&nbsp; Visa QR <span id="new-label">NEW</span>
								</div>
							</a>
						</div>
					</div>
					<!-- Page Content -->
					<div id="page-content-wrapper">
						<div class="row pt-3" id="border-btm">
							<div class="col-4">
								<button class="btn btn-success mt-4 ml-3 mb-3" id="menu-toggle">
									<div class="bar4"></div>
									<div class="bar4"></div>
									<div class="bar4"></div>
								</button>
							</div>
							<div class="col-8">
								<div class="row justify-content-right">
									<div class="col-12">
										<p class="mb-0 mr-4 mt-4 text-right">customer@email.com</p>
									</div>
								</div>
								<div class="row justify-content-right">
									<div class="col-12">
										<p class="mb-0 mr-4 text-right">
											Pay <span class="top-highlight">$ 100</span>
										</p>
									</div>
								</div>
							</div>
						</div>
						<div class="row justify-content-center">
							<div class="text-center" id="test">Pay</div>
						</div>
						<div class="tab-content">
							<div id="menu1" class="tab-pane">
								<div class="row justify-content-center">
									<div class="col-11">
										<div class="form-card">
											<h3 class="mt-0 mb-4 text-center">Enter bank details to
												pay</h3>
											<form onsubmit="event.preventDefault()">
												<div class="row">
													<div class="col-12">
														<div class="input-group">
															<input type="text" id="bk_nm" placeholder="BBB Bank">
															<label>BANK NAME</label>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-12">
														<div class="input-group">
															<input type="text" name="ben_nm" id="ben-nm"
																placeholder="John Smith"> <label>BENEFICIARY
																NAME</label>
														</div>
													</div>
													<div class="col-12">
														<div class="input-group">
															<input type="text" name="scode" placeholder="ABCDAB1S"
																class="placeicon" minlength="8" maxlength="11">
															<label>SWIFT CODE</label>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<input type="submit" value="Pay $ 100"
															class="btn btn-success placeicon">
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<p class="text-center mb-5" id="below-btn">
															<a href="#">Use a test card</a>
														</p>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div id="menu2" class="tab-pane in active">
								<div class="row justify-content-center">
									<div class="col-11">
										<div class="form-card">
											<h3 class="mt-0 mb-4 text-center">Enter your card
												details to pay</h3>
											<form onsubmit="event.preventDefault()">
												<div class="row">
													<div class="col-12">
														<div class="input-group">
															<input type="text" id="cr_no"
																placeholder="0000 0000 0000 0000" minlength="19"
																maxlength="19"> <label>CARD NUMBER</label>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-6">
														<div class="input-group">
															<input type="text" name="exp" id="exp"
																placeholder="MM/YY" minlength="5" maxlength="5">
															<label>CARD EXPIRY</label>
														</div>
													</div>
													<div class="col-6">
														<div class="input-group">
															<input type="password" name="cvcpwd"
																placeholder="&#9679;&#9679;&#9679;" class="placeicon"
																minlength="3" maxlength="3"> <label>CVV</label>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<input type="submit" value="Pay $ 100"
															class="btn btn-success placeicon">
													</div>
												</div>
												<div class="row">
													<div class="col-md-12">
														<p class="text-center mb-5" id="below-btn">
															<a href="#">Use a test card</a>
														</p>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<div id="menu3" class="tab-pane">
								<div class="row justify-content-center">
									<div class="col-11">
										<h3 class="mt-0 mb-4 text-center">Scan the QR code to pay</h3>
										<div class="row justify-content-center">
											<div id="qr">
												<img src="https://i.imgur.com/DD4Npfw.jpg" width="200px"
													height="200px">
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
	<script src="https://apis.google.com/js/platform.js?onload=onLoad"
		async defer></script>
	<script>
		$(document).ready(function() {
			//Menu Toggle Script
			$("#menu-toggle").click(function(e) {
				e.preventDefault();
				$("#wrapper").toggleClass("toggled");
			});

			// For highlighting activated tabs
			$("#tab1").click(function() {
				$(".tabs").removeClass("active1");
				$(".tabs").addClass("bg-light");
				$("#tab1").addClass("active1");
				$("#tab1").removeClass("bg-light");
			});
			$("#tab2").click(function() {
				$(".tabs").removeClass("active1");
				$(".tabs").addClass("bg-light");
				$("#tab2").addClass("active1");
				$("#tab2").removeClass("bg-light");
			});
			$("#tab3").click(function() {
				$(".tabs").removeClass("active1");
				$(".tabs").addClass("bg-light");
				$("#tab3").addClass("active1");
				$("#tab3").removeClass("bg-light");
			});
		})
	</script>

</body>

</html>