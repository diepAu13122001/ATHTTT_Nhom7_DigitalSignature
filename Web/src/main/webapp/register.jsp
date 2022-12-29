<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="${param.lang}">
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
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
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
					<h2>Đăng ký</h2>
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href=".">Trang chủ</a></li>
						<li class="breadcrumb-item active">Đăng ký</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

	<!-- Start Cart  -->
	<div class="cart-box-main">
		<div class="container">
			<div class="row new-account-login" style="justify-content: center">
				<div class="col-sm-6 col-lg-6 mb-3"
					style="flex: 0 0 70%; max-width: 70%;">
					<div class="title-left">
						<h3>Tạo tài khoản</h3>
					</div>



					<!--               <h5><a data-toggle="collapse" href="#formRegister" role="button" aria-expanded="true">Click here to Register</a></h5> -->
					<div class="mt-3 review-form-box" id="formRegister">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="lastname" class="mb-0">Họ </label> <input
									type="text" name="lastname" required class="form-control"
									id="lastname" placeholder="Họ"> <label
									id="lastname-validate" class="mb-0"
									style="color: red; display: none">* Không được để trống
									Họ</label>
							</div>
							<div class="form-group col-md-6">
								<label for="firstname" class="mb-0">Tên</label> <input
									type="text" name="firstname" required class="form-control"
									id="firstname" placeholder="Tên"> <label
									id="firstname-validate" class="mb-0"
									style="color: red; display: none">* Không được để trống
									Tên</label>
							</div>
							<div class="form-group col-md-6">
								<label for="email" class="mb-0">Email</label> <input
									type="email" name="email" required class="form-control"
									id="email" placeholder="Email"> <label
									id="email-validate" class="mb-0"
									style="color: red; display: none">* Không được để trống
									email</label>
							</div>
							<div class="form-group col-md-6">
								<label for="pass" class="mb-0">Mật khẩu</label> <input
									type="password" name="pass" required class="form-control"
									id="pass" onchange="hanldePassFiled();" placeholder="Mật khẩu" />
								<label id="pass-validate" class="mb-0"
									style="color: red; display: none">* Mật khẩu phải có
									tối đa 6 ký tự</label> <label id="pass-validate-blank" class="mb-0"
									style="color: red; display: none">* Không được để trống
									mật khẩu</label>
							</div>

						</div>
						<button class="btn hvr-hover" onclick="checkMail()">Đăng
							ký</button>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal" id="showModal"
		data-target="#exampleModal" style="display: none"></button>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Xác nhận email</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="code" class="mb-0" id="title-email"></label> <input
							type="text" name="code" required class="form-control"
							id="code" placeholder="Nhập mã xác nhận">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="verifyMail()">Xác nhận</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End Cart -->


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
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
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- ALL JS FILES -->
	<script src="js/addcart.js"></script>
	<script>
		function hanldePassFiled() {
			var pass = document.querySelector('#pass');
			var passVadilate = document.querySelector('#pass-validate');
			if (pass.value.length < 6) {
				pass.style.border = '1px solid red';
				passVadilate.style.display = 'block';
				return false;
			} else {
				pass.style.border = '1px solid #e8e8e8';
				passVadilate.style.display = 'none';
				return true;
			}
		}
		function validate(lastname, firstname, email, pass) {
			var isvalid = true;
			if (lastname == "") {
				$("#lastname").css("border-color", "#dc3545");
				$("#lastname-validate").css('display', 'block');
				return false;
			} else {
				$("#lastname").css("border", "1px solid #ced4da");
				$("#lastname-validate").css('display', 'none')
			}
			if (firstname == "") {
				$("#firstname").css("border-color", "#dc3545");
				$("#firstname-validate").css('display', 'block');
				return false;
			} else {
				$("#firstname").css("border", "1px solid #ced4da");
				$("#firstname-validate").css('display', 'none');
			}
			if (email == "") {
				$("#email").css("border-color", "#dc3545");
				$("#email-validate").css('display', 'block');
				return false;
			} else {
				$("#email").css("border", "1px solid #ced4da");
				$("#email-validate").css('display', 'none');
			}
			if (pass == "") {
				$("#pass").css("border-color", "#dc3545");
				$("#pass-validate-blank").css('display', 'block');
				return false;
			} else {
				$("#pass").css("border", "1px solid #ced4da");
				$("#pass-validate-blank").css('display', 'none');
			}
			return true;
		}

		function message() {
			var email = $('#email').val();
			$('#title-email').html(
					"Chúng tôi đã gửi mã xác nhận qua email: " + email);
		}
		function verifyMail() {
			var code = $('#code').val();

				$.ajax({
					type : "GET",
					url : "verify-mail",
					data : {
						code : code,
					},
					success : function(responseJson) {
						if (responseJson == 'NOT') {
							showSwal('error', '', 'Mã xác nhận không đúng');
						}else{
							register();
						}						
						
					},
					error : function() {
						showSwal('error', '', 'Có lỗi xảy ra');
					}
				})

		}
		function register() {
			var firstname = $('#firstname').val();
			var lastname = $('#lastname').val();
			var email = $('#email').val();
			var pass = $('#pass').val();
			if (validate(lastname, firstname, email, pass) && hanldePassFiled()) {
				$.ajax({
					type : "GET",
					url : "register",
					data : {
						firstname : firstname,
						lastname : lastname,
						email : email,
						pass : pass
					},
					success : function(responseJson) {
						console.log(responseJson);
						if (responseJson == 'RegisterOk') {
							showSwal('success-message','Đăng ký thành công');
							setTimeout(login, 2000);
						}						
					},
					error : function() {
						showSwal('error', '', 'Có lỗi xảy ra');
					}
				})

			}

		}
		function login() {
			window.location.href = './login'
		}
		function checkMail() {
			var firstname = $('#firstname').val();
			var lastname = $('#lastname').val();
			var email = $('#email').val();
			var pass = $('#pass').val();
			if (validate(lastname, firstname, email, pass) && hanldePassFiled()) {
				$.ajax({
					type : "GET",
					url : "SendCodeVerifyEmail",
					data : {
						email : email,		
					},
					success : function(responseJson) {
						if (responseJson == 'EmailExist') {
							showSwal('error', '', 'Email đã tồn tại');
						}
						if (responseJson == 'EmailNotExist') {
								message();
							 document.getElementById("showModal").click();
						}
						if (responseJson == 'EmailNotValid') {
							showSwal('error', '', 'Email không hợp lệ hoặc không tồn tại');
					}

					},
					error : function() {
						showSwal('error', '', 'Có lỗi xảy ra');
					}
				})

			}

		}
	</script>
</body>

</html>
