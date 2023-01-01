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

</head>

<body>
	<!-- Start Main Top -->
	<jsp:include page="header.jsp"></jsp:include>

	<div class=" shop-detail-box-main invoice">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-6 mb-3">
					<div class="title-left">
						<h2 style="font-weight: 500">Thông tin đơn hàng</h2>
					</div>
					<iframe src="showPdf?invoice=${invoice}" width="100%"
						height="600px"> </iframe>

				</div>
				<div class="col-sm-6 col-lg-6 mb-3">
					<div class="title-left">
						<h2 style="font-weight: 500">Xác thực</h2>
					</div>
					<c:if test="${status!=null}">
						<div class="alert alert-danger" role="alert">${status}</div>
					</c:if>

					<form id="upload-form" class="needs-validation"
						action="handle-authentication" enctype="multipart/form-data"
						method="post">
						<div class="mb-3">
							<label for="signature">Chữ ký (Base64)</label>
							<div class="input-group">
								<textarea class="form-control" id="signature" name="signature"
									required rows="4"></textarea>
							</div>
						</div>
						<input type="hidden" name="invoice" value="${invoice}">
						<div class="mb-3">
							<label for="file2">Public key <select
								class="form-select form-select-sm"
								aria-label=".form-select-sm example" id="publickey-select"
								onchange="switchType('${pk}')">
									<option value="0" selected>Base 64</option>
									<option value="1">File</option>

							</select>

							</label>
							<c:if test="${ errorKey!=null}">
								<label id="pass-validate" class="mb-0"
									style="color: red; display: block">${errorKey}</label>
							</c:if>

							<div class="input-group" id="pk-box">

								<input type="text" class="form-control" id="public-key"
									name="pk-base64" placeholder="" required value="${pk}">

							</div>
						</div>
						<div class="col-12 d-flex shopping-box">
							<button type="submit" class="ml-auto btn hvr-hover">Xác
								thực</button>
						</div>
					</form>
					<div class="ml-3 mt-3">
					<div class="row"><strong>Hướng dẫn xác thực</strong></div>
					<div class="row ml-2"><ul>
							<li>1.  Tải hoá đơn về</li>
								<li>2. Tạo chữ ký bằng tool (Tải tool tại <a  href="#">đây </a>)</li>
									<li>3. Upload chữ ký và public key lên  (Nếu chưa có khoá tạo tại  <a  href="createKey">đây </a>)</li>
										<li>4.  Xác nhận và chờ BQT xử lý trong 24h</li>
										<li>5.  Theo dõi trạng thái đơn hàng tại  <a  href="orders">đây </a> </li>
						</ul></div>
						
					</div>
						<div class="row ml-2"><p style="color:red">#Lưu ý: Sau 24h nếu không xác thực thì đơn hàng sẽ bị huỷ</p></div>
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
		function switchType(pk) {
			var type = $("#publickey-select").val();
			if (type == '0') {
				console.log('dmm');
				$("#pk-box")
						.html(
								"<input type='text' class='form-control' id='public-key' name='pk-base64' placeholder='' required value='"+pk+"'>");
			} else {
				console.log('sv');
				$("#pk-box")
						.html(
								"<input type='file' class='form-control' id='public-key'  name='file-encrypt' placeholder='' required>");
			}

		}
	</script>

</body>

</html>