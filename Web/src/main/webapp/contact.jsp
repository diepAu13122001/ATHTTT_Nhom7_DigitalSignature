<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="${param.lang }">
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
	 <meta name="google-signin-client_id" content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
	<link rel="stylesheet" href="css/header.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>

</style>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>

	    <!-- Start All Title Box -->
    <div class="all-title-box">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>Liên lạc</h2>
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item"><a href=".">Trang chủ</a></li>
                        <li class="breadcrumb-item active"> Liên lạc </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- End All Title Box -->

    <!-- Start Contact Us  -->
    <div class="contact-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-sm-12">
                    <div class="contact-form-right">
                    					<c:if test="${isSendMailSuccess==true}">
						<div id="success">
							<div class="alert alert-success d-flex align-items-center"
								role="alert" style="border-radius: unset;">
								<i class="fas fa-exclamation-circle"></i>&nbsp; &nbsp; &nbsp;
								<div>
									Gửi mail tới ${recipient} thành công
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${isSendMailSuccess==false}">
						<div id="error">
							<div class="alert alert-danger d-flex align-items-center"
								role="alert" style="border-radius: unset;">
								<i class="fas fa-exclamation-circle"></i>&nbsp; &nbsp; &nbsp;
								<div>
									Gửi mail tới ${recipient} thất bại
									</div>
							</div>
						</div>
					</c:if>
      
                       
                        <form action="sendmail">
                            <div class="row">
<!--                                 <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Your Name" required data-error="Please enter your name">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div> -->
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" placeholder="Email"  class="form-control" name="email" required  value="khaihieupc2@gmail.com">
                                     
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control"  name="subject" placeholder="Chủ đề" required >
                              
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <textarea class="form-control" name="message" placeholder="Nội dung" rows="4"  required></textarea>
                                        
                                    </div>
                                    <div class="submit-button text-center">
                                        <button class="btn hvr-hover" type="submit">Gửi</button>
                                  <!--       <div id="msgSubmit" class="h3 text-center hidden"></div>
                                        <div class="clearfix"></div> -->
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
				<div class="col-lg-4 col-sm-12">
                    <div class="contact-info-left">
                        <h2>Thông tin liên lạc</h2>
                       
                        <ul>
                            <li>
                                <p><i class="fas fa-map-marker-alt"></i>Địa chỉ: Trường Đại học Nông Lâm TP HCM  </p>
                            </li>
                            <li>
                                <p><i class="fas fa-phone-square"></i>SĐT: <a href="tel:0343385406">0343385406</a></p>
                            </li>
                            <li>
                                <p><i class="fas fa-envelope"></i>Email: <a href="mailto: khaihieunlu@outlook.com">khaihieunlu@outlook.com</a></p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Cart -->

		<jsp:include page="footer.jsp"></jsp:include>
		<!-- ALL JS FILES -->

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
</body>

</html>
