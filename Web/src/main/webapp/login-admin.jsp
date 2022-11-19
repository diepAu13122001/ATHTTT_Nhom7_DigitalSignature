<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login Admin</title>
  <link href="admin/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Đăng nhập</h3></div>
                                    <c:if test="${emailAdmin!=null}">
                                     <div class="alert alert-danger">
										Sai thông tin đăng nhập! 
 										 </div>
                                    </c:if>
                                    <div class="card-body">
                                        <form action="login-admin" method="post" onsubmit="return loginValidate()">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" required type="email" name="emailAdmin" placeholder="name@example.com" />
                                                <label for="inputEmail">Email</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputPassword" required name="passAdmin" type="password" placeholder="Mật khẩu" />
                                                <label for="inputPassword">Mật khẩu</label>	
                                                 
                                            </div>
                                            <label id="pass-validate" class="mb-0" style="color: red; display: none">* Mật khẩu phải có tối đa 6 ký tự</label>
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />
                                                <label class="form-check-label" for="inputRememberPassword">Nhớ mật khẩu</label>
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="password-forget-admin.jsp">Quên mật khẩu?</a>
                                                <button type="submit" class="btn btn-primary">Đăng nhập</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                       <div class="small"><a href="register.jsp">Đăng ký!</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2021</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script type="text/javascript">
        function loginValidate(){
	   		var pass = document.querySelector('#inputPassword');
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="admin/js/scripts.js"></script>
    </body>
</html>