<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html >
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
<meta name="google-signin-client_id" content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
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

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.abcRioButtonBlue, .abcRioButtonBlue:hover{
background-color: #4285f4!important;
}
.abcRioButtonBlue {
    border: none;
    color: #fff;
}
.abcRioButton {
    border-radius: 1px;
    box-shadow: 0 2px 4px 0 rgb(0 0 0 / 25%);
    -moz-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-transition: background-color .218s,border-color .218s,box-shadow .218s;
    transition: background-color .218s,border-color .218s,box-shadow .218s;
    -webkit-user-select: none;
    -webkit-appearance: none;
    background-color: #fff;
    background-image: none;

    cursor: pointer;
    outline: none;
    overflow: hidden;
    position: relative;
    text-align: center;
    vertical-align: middle;
    white-space: nowrap;
    width: auto;
}
.abcRioButtonBlue .abcRioButtonContentWrapper {
    border: 1px solid transparent;
}
.abcRioButtonContentWrapper {
    height: 100%;
    width: 100%;
}
.abcRioButtonBlue .abcRioButtonIcon {
    background-color: #fff;
    border-radius: 1px;
}
.abcRioButtonIcon {
    float: left;
}
.abcRioButtonContents {
    font-family: Roboto,arial,sans-serif;
    font-size: 14px;
    font-weight: 500;
    letter-spacing: .21px;
    margin-left: 6px;
    margin-right: 6px;
    vertical-align: top;
}
</style>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	<c:if test="${isSuccess==true}">
		<div id="toast"></div>
	</c:if>
	<!-- Start All Title Box -->
	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>
						Đăng nhập
					</h2>
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href=".">Trang chủ</a></li>
						<li class="breadcrumb-item active">Đăng nhập
					</ul>
				</div>
		</div>
	</div>
	</div>
	<!-- End All Title Box -->

	<!-- Start Cart  -->
	<div class="cart-box-main">
		<div class="container" style="margin-bottom: 70px">
			<div class="row new-account-login" style="justify-content: center">
				<div class="col-sm-6 col-lg-6 mb-3"
					style="flex: 0 0 70%; max-width: 70%;">
					<div class="title-left">
						<h3>
							Đăng nhập
						</h3>
					</div>

					<c:if test="${email!=null}">
						<div id="error">
							<div class="alert alert-danger d-flex align-items-center"
								role="alert" style="border-radius: unset;">
								<i class="fas fa-exclamation-circle"></i>&nbsp; &nbsp; &nbsp;
								<div>
									Tài khoản không đúng
								</div>
							</div>
						</div>
					</c:if>
					<form class="mt-3 review-form-box" id="formLogin" method="post" onsubmit="return loginValidate()"
						action="./login">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="InputEmail" class="mb-0">Email</label> <input type="email" required
									class="form-control" id="InputEmail" placeholder="Email"
									name="email">
							</div>
							<div class="form-group col-md-6">
								<label for="InputPassword" class="mb-0">Mật khẩu</label> <input type="password"
									required class="form-control" name="pass" id="InputPassword"
									placeholder="Mật khẩu">
									 <label id="pass-validate" class="mb-0" style="color: red; display: none">* Mật khẩu phải có tối đa 6 ký tự</label>
							</div>
						</div>

						<div style="text-align: center;">
						<button type="submit" class="btn hvr-hover">
							Đăng nhập
						</button>
						</div>

					</form>
					<hr>
					<div style="height: 50px; width: 100%;"
						class=" abcRioButtonBlue abcRioButton" onclick="googleAccess()">
						<div class="abcRioButtonContentWrapper">
							<div class="abcRioButtonIcon" style="padding: 15px">
								<div style="width: 18px; height: 18px;"
									class="abcRioButtonSvgImageWithFallback abcRioButtonIconImage abcRioButtonIconImage18">
									<svg version="1.1" xmlns="http://www.w3.org/2000/svg"
										width="18px" height="18px" viewBox="0 0 48 48"
										class="abcRioButtonSvg">
						<g>
						<path fill="#EA4335"
											d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path>
						<path fill="#4285F4"
											d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path>
						<path fill="#FBBC05"
											d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path>
						<path fill="#34A853"
											d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path>
						<path fill="none" d="M0 0h48v48H0z"></path></g></svg>
								</div>
							</div>
							<span style="font-size: 16px; line-height: 48px;"
								class="abcRioButtonContents"><span
								id="not_signed_inioyagfn9vomh">Sign in with Google</span><span
								id="connectedioyagfn9vomh" style="display: none">Signed
									in with Google</span></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End Cart -->
	</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- ALL JS FILES -->
		<script type="text/javascript">
			function googleAccess(){
				window.location ="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/Digital_Signature_Web/login&response_type=code&client_id=1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com&approval_prompt=force";
			}
			   function loginValidate(){
			   		var pass = document.querySelector('#InputPassword');
			   		var passVadilate = document.querySelector('#pass-validate');
			   		if(pass.value.length < 6){
			   			pass.style.border='2px solid red';
			   			passVadilate.style.display='block';
			   			return false;
			   		 }else{
			   		 	return true;
			   		 }
			   }
			
		</script>
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
		  <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>

</html>
