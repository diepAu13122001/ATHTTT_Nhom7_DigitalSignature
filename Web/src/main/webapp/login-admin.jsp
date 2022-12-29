<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login Admin</title>
   <link rel="stylesheet" href="css/form-admin.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">

	</head>
	<body>
	
    <section class="container">
        <div class="login-container">
            <div class="circle circle-one"></div>
            <div class="form-container">
                <img src="https://raw.githubusercontent.com/hicodersofficial/glassmorphism-login-form/master/assets/illustration.png" alt="illustration" class="illustration" />
                <h1 class="opacity">ĐĂNG NHẬP</h1>
               <div class="form">
                    <input type="text" placeholder="USERNAME" required="required" id="user-name"/>
                    <input type="password" placeholder="PASSWORD" required="required" id="password"/>
                    <button class="opacity" onclick="login()">ĐĂNG NHẬP</button>
             </div>
                <div class="register-forget opacity" style="justify-content: center">
               
                   <!--  <a href="">REGISTER</a>
                    <a href="">FORGOT PASSWORD</a> -->
                </div>
            </div>
            <div class="circle circle-two"></div>
        </div>
        <div class="theme-btn-container"></div>
    </section>
    <script src="js/form-admin.js"></script>
    <script src ="js/jquery-3.2.1.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script >
    	function login() {
    		var userName = $("#user-name").val();
    		var password = $("#password").val();
			$.ajax({
				type: "GET",
				url: "login-admin",
				data: { userName: userName,
						password: password
				},
				success: function(response) {
					console.log(response)
					if(response ==false){
						showSwal('error');
					}else{
						window.location.href = './admin';
					}
					
					
				},
				 error: function (request, status, error) {
					 $.alert({
						    title: 'Thất bại!',
						    content: 'Thông tin đăng nhập sai!',
						});
		    	}
			})
		}
    	(function($) {
    		  showSwal = function(type) {
    		    'use strict';
    		     if (type === 'success-message') {
    		      swal({
    		        title: 'Congratulations!',
    		        text: 'You entered the correct answer',
    		        type: 'success',
    		        button: {
    		          text: "Continue",
    		          value: true,
    		          visible: true,
    		          className: "btn btn-primary"
    		        }
    		      })

    		    }else{
    		        swal("Đăng nhập thất bại !");
    		    } 
    		  }

    		})(jQuery);
    </script>
</body>
</html>