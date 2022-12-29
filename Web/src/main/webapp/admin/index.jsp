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
				<div class="container-fluid px-4">
					<h1 class="mt-4">Bảng điều khiển</h1>
					
					<div class="row">
						<div class="col-lg-6 col-xl-3 mb-4">
							<div class="card bg-primary text-white h-100">
								<div class="card-body">
									<div class="d-flex justify-content-between align-items-center">
										<div class="me-3">
											<div class="text-white-75 small">Earnings (Monthly)</div>
											<div class="text-lg fw-bold">$40,000</div>
										</div>
										<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
											viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="2" stroke-linecap="round"
											stroke-linejoin="round"
											class="feather feather-calendar feather-xl text-white-50">
											<rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
											<line x1="16" y1="2" x2="16" y2="6"></line>
											<line x1="8" y1="2" x2="8" y2="6"></line>
											<line x1="3" y1="10" x2="21" y2="10"></line></svg>
									</div>
								</div>
								<div
									class="card-footer d-flex align-items-center justify-content-between small">
									<a class="text-white stretched-link" href="#!">View Report</a>
									<div class="text-white">
										<svg class="svg-inline--fa fa-angle-right" aria-hidden="true"
											focusable="false" data-prefix="fas" data-icon="angle-right"
											role="img" xmlns="http://www.w3.org/2000/svg"
											viewBox="0 0 256 512" data-fa-i2svg="">
											<path fill="currentColor"
												d="M64 448c-8.188 0-16.38-3.125-22.62-9.375c-12.5-12.5-12.5-32.75 0-45.25L178.8 256L41.38 118.6c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l160 160c12.5 12.5 12.5 32.75 0 45.25l-160 160C80.38 444.9 72.19 448 64 448z"></path></svg>
										<!-- <i class="fas fa-angle-right"></i> Font Awesome fontawesome.com -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-xl-3 mb-4">
							<div class="card bg-warning text-white h-100">
								<div class="card-body">
									<div class="d-flex justify-content-between align-items-center">
										<div class="me-3">
											<div class="text-white-75 small">Earnings (Annual)</div>
											<div class="text-lg fw-bold">$215,000</div>
										</div>
										<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
											viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="2" stroke-linecap="round"
											stroke-linejoin="round"
											class="feather feather-dollar-sign feather-xl text-white-50">
											<line x1="12" y1="1" x2="12" y2="23"></line>
											<path d="M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"></path></svg>
									</div>
								</div>
								<div
									class="card-footer d-flex align-items-center justify-content-between small">
									<a class="text-white stretched-link" href="#!">View Report</a>
									<div class="text-white">
										<svg class="svg-inline--fa fa-angle-right" aria-hidden="true"
											focusable="false" data-prefix="fas" data-icon="angle-right"
											role="img" xmlns="http://www.w3.org/2000/svg"
											viewBox="0 0 256 512" data-fa-i2svg="">
											<path fill="currentColor"
												d="M64 448c-8.188 0-16.38-3.125-22.62-9.375c-12.5-12.5-12.5-32.75 0-45.25L178.8 256L41.38 118.6c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l160 160c12.5 12.5 12.5 32.75 0 45.25l-160 160C80.38 444.9 72.19 448 64 448z"></path></svg>
										<!-- <i class="fas fa-angle-right"></i> Font Awesome fontawesome.com -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-xl-3 mb-4">
							<div class="card bg-success text-white h-100">
								<div class="card-body">
									<div class="d-flex justify-content-between align-items-center">
										<div class="me-3">
											<div class="text-white-75 small">Task Completion</div>
											<div class="text-lg fw-bold">24</div>
										</div>
										<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
											viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="2" stroke-linecap="round"
											stroke-linejoin="round"
											class="feather feather-check-square feather-xl text-white-50">
											<polyline points="9 11 12 14 22 4"></polyline>
											<path
												d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"></path></svg>
									</div>
								</div>
								<div
									class="card-footer d-flex align-items-center justify-content-between small">
									<a class="text-white stretched-link" href="#!">View Tasks</a>
									<div class="text-white">
										<svg class="svg-inline--fa fa-angle-right" aria-hidden="true"
											focusable="false" data-prefix="fas" data-icon="angle-right"
											role="img" xmlns="http://www.w3.org/2000/svg"
											viewBox="0 0 256 512" data-fa-i2svg="">
											<path fill="currentColor"
												d="M64 448c-8.188 0-16.38-3.125-22.62-9.375c-12.5-12.5-12.5-32.75 0-45.25L178.8 256L41.38 118.6c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l160 160c12.5 12.5 12.5 32.75 0 45.25l-160 160C80.38 444.9 72.19 448 64 448z"></path></svg>
										<!-- <i class="fas fa-angle-right"></i> Font Awesome fontawesome.com -->
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-6 col-xl-3 mb-4">
							<div class="card bg-danger text-white h-100">
								<div class="card-body">
									<div class="d-flex justify-content-between align-items-center">
										<div class="me-3">
											<div class="text-white-75 small">Pending Requests</div>
											<div class="text-lg fw-bold">17</div>
										</div>
										<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
											viewBox="0 0 24 24" fill="none" stroke="currentColor"
											stroke-width="2" stroke-linecap="round"
											stroke-linejoin="round"
											class="feather feather-message-circle feather-xl text-white-50">
											<path
												d="M21 11.5a8.38 8.38 0 0 1-.9 3.8 8.5 8.5 0 0 1-7.6 4.7 8.38 8.38 0 0 1-3.8-.9L3 21l1.9-5.7a8.38 8.38 0 0 1-.9-3.8 8.5 8.5 0 0 1 4.7-7.6 8.38 8.38 0 0 1 3.8-.9h.5a8.48 8.48 0 0 1 8 8v.5z"></path></svg>
									</div>
								</div>
								<div
									class="card-footer d-flex align-items-center justify-content-between small">
									<a class="text-white stretched-link" href="#!">View
										Requests</a>
									<div class="text-white">
										<svg class="svg-inline--fa fa-angle-right" aria-hidden="true"
											focusable="false" data-prefix="fas" data-icon="angle-right"
											role="img" xmlns="http://www.w3.org/2000/svg"
											viewBox="0 0 256 512" data-fa-i2svg="">
											<path fill="currentColor"
												d="M64 448c-8.188 0-16.38-3.125-22.62-9.375c-12.5-12.5-12.5-32.75 0-45.25L178.8 256L41.38 118.6c-12.5-12.5-12.5-32.75 0-45.25s32.75-12.5 45.25 0l160 160c12.5 12.5 12.5 32.75 0 45.25l-160 160C80.38 444.9 72.19 448 64 448z"></path></svg>
										<!-- <i class="fas fa-angle-right"></i> Font Awesome fontawesome.com -->
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xl-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-area me-1"></i> Area Chart Example
								</div>
								<div class="card-body">
									<canvas id="myAreaChart" width="100%" height="40"></canvas>
								</div>
							</div>
						</div>
						<div class="col-xl-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Bar Chart Example
								</div>
								<div class="card-body">
									<canvas id="myBarChart" width="100%" height="40"></canvas>
								</div>
							</div>
						</div>
					</div>
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
									<c:forEach begin="0" end="${orders.size()-1}" var="i">
										<tr onclick="orderDetail(${orders.get(i).id})"
											style="cursor: pointer;">
											<td>${orders.get(i).id}</td>
											<td>${orders.get(i).dateCreate}</td>
											<td>${orders.get(i).phoneNum}</td>
											<td>${orders.get(i).address}</td>
											<td><fmt:formatNumber type="number" groupingUsed="true"
													value="${orders.get(i).grandPrice}" /></td>
											<td>${orders.get(i).statusName}</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script>
	 function orderDetail(id) {
		window.location.href = "./order-detail?id="+id;
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