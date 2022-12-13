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
	.color-main{
		background-color: #b0b435;
		    border-color: #b0b435;
		    color:#fff!important;
	}
</style>
</head>

<body>
	<!-- Start Main Top -->
	<jsp:include page="header.jsp"></jsp:include>

	<div class=" shop-detail-box-main invoice">
		<div class="container">
			<div class="title-left">
				<h2 style="font-weight: 500">Tạo khoá</h2>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-8">

						<div class="row">
							<div class="col-md-12 col-sm-12 col-xs-12">
								<div class="row">
									<div class="col-md-12 col-sm-12 col-xs-12">
									
											<div class="form-group">
												<label for="keySize">Chọn kích thước khoá</label> <select
													class="form-control input-lg ng-valid ng-dirty ng-valid-parse ng-touched"
												 id="keySize" name="keySize">											
													<option value="1024">1024 bit</option>
													<option value="2048">2048 bit</option>							
												</select>
											</div>
											<div class="form-group">
												<button class="btn btn-primary color-main"  onclick="generateKey()"
													><i class="fa fa-key"></i> Tạo khoá</button>
											</div>
										
									</div>
								</div>
							</div>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<h3>Public Key</h3>
							
									<div class="form-group">
										<textarea id="publicKey"
											class="form-control input-lg ng-pristine ng-valid ng-touched"
											rows="5" name="publicKey" spellcheck="false"></textarea>
									</div>
									<div class="form-group">
										<a class="btn btn-primary color-main" id="download-publickey"  ><i class="fa fa-download"></i>  Tải xuống</a>
									</div>
							
							</div>
							<div class="col-md-6 col-sm-6 col-xs-12 vertical-line">
								<h3>Private Key</h3>

									<div class="form-group">
										<textarea id="privateKey"
											class="form-control input-lg ng-pristine ng-valid ng-touched"
											rows="5" name="privateKey" spellcheck="false"></textarea>
									</div>
									<div class="form-group">
										<a class="btn btn-primary color-main" id="download-privatekey"  ><i class="fa fa-download"></i>  Tải xuống</a>
									</div>

					
							</div>
						</div>

					</div>
					<div class="col-4">col-4</div>
				</div>
			</div>
		</div>
	</div>



	<jsp:include page="footer.jsp"></jsp:include>

	<!-- ALL JS FILES -->
		<script src="js/addcart.js"></script>
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
		
	</script>

</body>

</html>