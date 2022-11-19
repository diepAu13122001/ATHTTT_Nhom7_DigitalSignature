<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"  %>
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
	 <meta name="google-signin-client_id" content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
	<link rel="stylesheet" href="css/header.css">
    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="stylesheet" href="fonts/themify-icons-font/themify-icons/themify-icons.css">
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
                <div class="col-sm-6 col-lg-6 mb-3" style="flex:0 0 70%;max-width:70%;">
                    <div class="title-left">
                        <h3>Tạo tài khoản</h3>
                    </div>
                 
                    
                    <c:if test="${isExitsEmail}">
					<div id="error" >
						<div class="alert alert-danger d-flex align-items-center"
							role="alert" style="border-radius: unset;">
							<i class="fas fa-exclamation-circle"></i>&nbsp; &nbsp; &nbsp;
							<div>
								Email đã tồn tại
							</div>
						</div>
					</div>
					</c:if>
      <!--               <h5><a data-toggle="collapse" href="#formRegister" role="button" aria-expanded="true">Click here to Register</a></h5> -->
                    <form class="mt-3 review-form-box" id="formRegister" action="register" method="post" onsubmit=" return registerValidate()">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="InputName" class="mb-0">Họ </label>
                                <input type="text" name="lastname"required class="form-control" id="InputName" placeholder="Họ"> </div>
                            <div class="form-group col-md-6">
                                <label for="InputLastname" class="mb-0">Tên</label>
                                <input type="text" name="firstname"required class="form-control" id="InputLastname" placeholder="Tên"> </div>
                            <div class="form-group col-md-6">
                                <label for="InputEmail1" class="mb-0">Email</label>
                                <input type="email" name="email" required class="form-control" id="InputEmail1" placeholder="Email"> </div>
                            <div class="form-group col-md-6">
                                <label for="InputPassword1" class="mb-0">Mật khẩu</label>
                                <input type="password" name="pass"required class="form-control" id="InputPassword1" placeholder="Mật khẩu" />"> 
                                <label id="pass-validate" class="mb-0" style="color: red; display: none">* Mật khẩu phải có tối đa 6 ký tự</label>
                                </div>
                                
                        </div>
                        <button type="submit" class="btn hvr-hover">Đăng ký</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
    <!-- End Cart -->
	
  <jsp:include page="footer.jsp"></jsp:include> 
   <!-- ALL JS FILES -->
   <script type="text/javascript">
   function registerValidate(){
   		var pass = document.querySelector('#InputPassword1');
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
