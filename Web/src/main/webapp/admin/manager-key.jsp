<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Dashboard - SB Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../admin/css/styles.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
	<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
.btn {
	font-size: 0.875rem;
}
</style>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="header.jsp"></jsp:include>
	<div id="layoutSidenav">
		<jsp:include page="sidebar-left.jsp"></jsp:include>
		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4 mb-5">
					<h1 class="mt-4">Public Key</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="index.jsp">Khách
								hàng</a></li>
						<li class="breadcrumb-item active">Public Key</li>
					</ol>
					<div class="container">
						<div class="main-body">
							<div class="row">
								<div class="col-sm-12">
									<div class="card mb-4">
										<div class="card-header">
											<i class="fas fa-table me-1"></i>
										</div>
										<div class="card-body">
											<table id="datatablesSimple">
												<thead>
													<tr>
														<th>Mã khoá</th>
														<th>Mã khách hàng</th>
														<th>Public Key (Base64)</th>
														<th>Ngày tạo</th>
														<th>Trạng thái</th>
														<th>Bật /tắt</th>

													</tr>
												</thead>
												<tfoot>
													<tr>
														<th>Mã khoá</th>
														<th>Mã khách hàng</th>
														<th>Public Key (Base64)</th>
														<th>Ngày tạo</th>
														<th>Trạng thái</th>
														<th>Bật /tắt</th>


													</tr>
												</tfoot>
												<tbody>
													<c:forEach items="${publicKeys}" var="publicKey">
														<tr style="cursor: pointer;">
															<td>${publicKey.id}</td>
															<td>${publicKey.idUser}</td>
															<td><input type="text" class="form-control"
																value=" ${publicKey.publicKeyBase64}"></td>
															<td>${publicKey.date_create}</td>
															<td><c:if test="${publicKey.active ==1}">
																Đang sử dụng
															</c:if> <c:if test="${publicKey.active ==0}">
																Không sử dụng
															</c:if></td>
															<td>
															<c:if test="${publicKey.active ==1}">
																<a style="cursor: pointer;" onclick="submitDisable(${publicKey.idUser},${publicKey.id},0)"><i class="fas fa-lock"></i></a>
															</c:if> <c:if test="${publicKey.active ==0}">
																<a style="cursor: pointer;" onclick="submitDisable(${publicKey.idUser},${publicKey.id},1)"><i class="fas fa-unlock"></i></a>
															</c:if>
															</td>

														</tr>
													</c:forEach>
												</tbody>
											</table>

										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
		</div>
		</main>
	</div>
	</div>
	<script src="../js/jquery-3.2.1.min.js"></script>
	<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="../js/addcart.js"></script>
	<script>
		function orderDetail(id) {
			window.location.href = "./order-detail-ad?id=" + id;

		}
		function submitDisable(userId, id,active) {
			$.confirm({
				title : 'Xác nhận!',
				content : 'Bạn muốn bật/tắt khoá không ?',
				buttons : {
					confirm : function() {
						disableKey(userId, id,active);
					},
					cancel : function() {
						
					},
				}
			});
			}
		
		function disableKey(userId, id, active) {
			$.ajax({
				type : "GET",
				url : "./DisableKey",
				data:{
					userId: userId,
					id: id,
					active: active
				},
				success: function(response) {
					if (response == 'OK') {
						if(active == 1){
							showSwal('success-message', 'Bật khoá thành công');
						}else{
							showSwal('success-message', 'Tắt khoá thành công');
						}
						
						setTimeout(function(){location.reload()}, 1500);
					} else {
						showSwal('error', '', 'Thất bại')
					}
				},
				error: function() {
					showSwal('error', '', 'Thất bại')
				}
			})
		}
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="../admin/js/scripts.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
		crossorigin="anonymous"></script>
	<script src="../admin/assets/demo/chart-area-demo.js"></script>
	<script src="../admin/assets/demo/chart-bar-demo.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
		crossorigin="anonymous"></script>
	<script src="../admin/js/datatables-simple-demo.js"></script>
</body>
</html>