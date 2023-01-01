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
					<h1 class="mt-4">Thông tin khách hàng</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="index.jsp">Khách
								hàng</a></li>
						<li class="breadcrumb-item active">Thông tin khách hàng</li>
					</ol>
					<div class="container">
						<div class="main-body">
							<div class="row " style="min-height: 320px">
								<div class="col-lg-4">
									<div class="card">
										<div class="card-body">
											<div
												class="d-flex flex-column align-items-center text-center">
												<c:if test="${customer.accountGoogle == 0}">
													<img
														src="https://bootdey.com/img/Content/avatar/avatar7.png"
														alt="Admin" class="rounded-circle p-1" width="110">
												</c:if>
												<c:if test="${customer.accountGoogle == 1}">
													<img src="../images/google.png" alt="Admin"
														class="rounded-circle p-1" width="110">
												</c:if>
												<div class="mt-3">
													<p class="text-secondary mb-1">ID: ${customer.id}</p>
													<c:if test="${customer.accountGoogle==1}">
														<h4>TK Google</h4>
													</c:if>
													<c:if test="${customer.accountGoogle==0}">
														<h4>TK Thường</h4>
													</c:if>
													<button class="btn btn-primary">${customer.roleName}</button>
												</div>
											</div>
											<hr class="my-4">
											<ul class="list-group list-group-flush">
												<li
													class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
													<h6 class="mb-0">Số đơn hàng</h6> <span
													class="text-secondary">${customer.numberOrders}</span>
												</li>
												<li
													class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
													<h6 class="mb-0">Số bình luận</h6> <span
													class="text-secondary">${customer.numberReviews}</span>
												</li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-lg-8">
									<div class="card">
										<div class="card-body">
											<div class="row mb-3">
												<div class="col-sm-3">
													<h6 class="mb-0">Họ</h6>
												</div>
												<div class="col-sm-9 text-secondary">
													<input type="text" class="form-control"
														value="${customer.lastName}">
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<h6 class="mb-0">Tên</h6>
												</div>
												<div class="col-sm-9 text-secondary">
													<input type="text" class="form-control"
														value="${customer.firstName}">
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<h6 class="mb-0">Email</h6>
												</div>
												<div class="col-sm-9 text-secondary">
													<input type="text" class="form-control"
														value="${customer.email}">
												</div>
											</div>
											<div class="row mb-3">
												<div class="col-sm-3">
													<h6 class="mb-0">SĐT</h6>
												</div>
												<div class="col-sm-9 text-secondary">
													<input type="text" class="form-control"
														value="${customer.phoneNum }">
												</div>
											</div>
											<div class="row">
												<div class="col-sm-3"></div>
												<div class="col-sm-9 text-secondary">
													<input type="button" class="btn btn-primary px-4"
														value="Cập nhật">
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="card mb-4">
										<div class="card-header">
											<i class="fas fa-table me-1"></i> Đơn hàng
										</div>
										<div class="card-body">
											<table id="datatablesSimple">
												<thead>
													<tr>
														<th>Mã hoá đơn</th>
														<th>Ngày đặt</th>
														<th>SĐT</th>
														<th>Địa chỉ</th>
														<th>Tổng giá trị(VND)</th>
														<th>Trạng thái</th>

													</tr>
												</thead>
												<tfoot>
													<tr>
														<th>Mã hoá đơn</th>
														<th>Ngày đặt</th>
														<th>SĐT</th>
														<th>Địa chỉ</th>
														<th>Tổng giá trị(VND)</th>
														<th>Trạng thái</th>

													</tr>
												</tfoot>
												<tbody>
													<c:forEach items="${orders}" var="order">
														<tr onclick="orderDetail(${order.id})"
															style="cursor: pointer;">
															<td>${order.parent}</td>
															<td>${order.dateCreate}</td>
															<td>${order.phoneNum}</td>
															<td>${order.address}</td>
															<td><fmt:formatNumber type="number"
																	groupingUsed="true" value="${order.grandPrice}" /></td>
															<td>${order.statusName}</td>

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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="../js/addcart.js"></script>
	<script>
	 function orderDetail(id) {	
			window.location.href = "./order-detail-ad?id="+id;
					 
		 }
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