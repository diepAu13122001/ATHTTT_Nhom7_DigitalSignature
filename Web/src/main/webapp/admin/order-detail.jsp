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
<link href="../admin/css/mystyle.css" rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<style type="text/css">
.pb-1 {
	padding-bottom: 1rem !important;
}
.mg-li{
	margin: 10px 0;
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
					<h1 class="mt-4">Chi tiết đơn hàng</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="index.jsp">Bảng điều
								khiển</a></li>
						<li class="breadcrumb-item active">Đơn hàng/ Chi tiết đơn
							hàng</li>
					</ol>


					<form action="add-product" enctype="multipart/form-data"
						method="post">
						<nav class="navbar navbar-light bg-light ">
							<div class="container-fluid end-flex">
								<button type="submit" class="btn btn-success">
									<i class="fas fa-paper-plane"></i> Cập nhật
								</button>
							</div>
						</nav>
						<div class="row">
							<div class="col-8">
								<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Thông tin người
									nhận</h4>
								<div class="container bootdey">
									<div class="row invoice row-printable">
										<div class="col-md-10" style="width: 100%">
											<!-- col-lg-12 start here -->
											<div class="panel panel-default plain" id="dash_0">
												<!-- Start .panel -->
												<div class="panel-body p30">
													<div class="row">
														<!-- Start .row -->

														<!-- col-lg-6 end here -->
														<div class="col-lg-12">
															<!-- col-lg-12 start here -->
															<div class="invoice-details mt25">

																<div class="well">
																	<div class="mb-3">
																		<label for="name" class="form-label"><strong>Tên
																				người nhận</strong></label> <input type="text" class="form-control"
																			required="required" id="name" name="name-product"
																			value="${order.nameReceiver }">
																	</div>
																	<div class="mb-3">
																		<label for="name" class="form-label"><strong>Số
																				điện thoại người nhận</strong></label> <input type="number"
																			class="form-control" required="required" id="name"
																			name="name-product" value="${order.phoneNum }">
																	</div>
																	<div class="mb-3">
																		<label for="name" class="form-label"><strong>Email</strong></label>
																		<input type="text" class="form-control"
																			required="required" id="name" name="name-product"
																			value="${order.email }">
																	</div>
																	<div class="mb-3">
																		<label for="name" class="form-label"><strong>Địa
																				chỉ</strong></label> <input type="text" class="form-control"
																			required="required" id="name" name="name-product"
																			value="${order.address }">
																	</div>
																	<div class="mb-3">
																		<label for="name" class="form-label"><strong>Mô
																				tả địa chỉ</strong></label> <input type="text" class="form-control"
																			required="required" id="name" name="name-product"
																			value="${order.addressDetail}">
																	</div>
																</div>
															</div>

														</div>
														<!-- col-lg-12 end here -->
													</div>
													<!-- End .row -->
												</div>
											</div>
											<!-- End .panel -->
										</div>
										<!-- col-lg-12 end here -->
									</div>
								</div>
								<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Danh sách đơn
									hàng</h4>
								<div class="container bootdey">
									<div class="row invoice row-printable">
										<div class="col-md-10" style="width: 100%">
											<!-- col-lg-12 start here -->
											<div class="panel panel-default plain" id="dash_0">
												<!-- Start .panel -->
												<div class="panel-body p30">
													<div class="row">
														<!-- Start .row -->

														<!-- col-lg-6 end here -->
														<div class="col-lg-12">
															<!-- col-lg-12 start here -->
															<div class="invoice-items">
																<div class="table-responsive"
																	style="overflow: hidden; outline: none;" tabindex="0">
																	<table class="table table-bordered">
																		<thead>
																			<tr>
																				<th class="per70 text-center">Sản phẩm</th>
																				<th class="per5 text-center">Số lượng</th>
																				<th class="per25 text-center">Đơn giá(VND)</th>
																				<th class="per25 text-center">Thành tiền(VND)</th>
																			</tr>
																		</thead>
																		<tbody>

																			<c:forEach begin="0"
																				end="${order.orderDetails.size()-1}" var="i">
																				<tr
																					onclick="productDetail(${order.orderDetails.get(i).product.idProduct})"
																					style="cursor: pointer;">
																					<td>${order.orderDetails.get(i).product.nameProduct }</td>
																					<td class="text-center">${order.orderDetails.get(i).quantity}</td>
																					<td class="text-center"><fmt:formatNumber
																							type="number" groupingUsed="true"
																							value="${order.orderDetails.get(i).product.price}" /></td>
																					<td class="text-center"><fmt:formatNumber
																							type="number" groupingUsed="true"
																							value="${order.orderDetails.get(i).product.price*order.orderDetails.get(i).quantity}" />
																					</td>
																				</tr>
																			</c:forEach>
																		</tbody>
																		<tfoot>
																			<tr>
																				<th colspan="2" class="text-right">Tổng cộng:</th>
																				<th class="text-center"></th>
																				<th class="text-center"><fmt:formatNumber
																						type="number" groupingUsed="true"
																						value="${order.totalPrice()}" /></th>
																			</tr>
																			<tr>
																				<th colspan="2" class="text-right">Giảm giá:</th>
																				<th class="text-center"></th>
																				<th class="text-center"><fmt:formatNumber
																						type="number" groupingUsed="true"
																						value="${order.discount}" /></th>
																			</tr>
																			<tr>
																				<th colspan="2" class="text-right">Ship:</th>
																				<th class="text-center"></th>
																				<th class="text-center"><fmt:formatNumber
																						type="number" groupingUsed="true"
																						value="${order.shipping.price}" /></th>
																			</tr>
																			<tr>
																				<th colspan="2" class="text-right">Tổng thanh
																					toán:</th>
																				<th class="text-center"></th>
																				<th class="text-center"><fmt:formatNumber
																						type="number" groupingUsed="true"
																						value="${order.grandPrice}" /></th>
																			</tr>
																		</tfoot>
																	</table>
																</div>
															</div>
															<div class="invoice-footer mt25">
																<p class="text-center">
																	Generated on Monday, October 08th, 2015 <a href="#"
																		class="btn btn-default ml15"><i
																		class="fa fa-print mr5"></i> Print</a>
																</p>
															</div>
														</div>
														<!-- col-lg-12 end here -->
													</div>
													<!-- End .row -->
												</div>
											</div>
											<!-- End .panel -->
										</div>
										<!-- col-lg-12 end here -->
									</div>
								</div>
							</div>
							<div class="col-4">
								<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Tài khoản</h4>
								<div class="row" style="padding-top: 1rem">
									<div class="col-lg-6" style="width: 30%">
										<!-- col-lg-6 start here -->
										<div class="invoice-logo">

											<c:if test="${order.customer.accountGoogle == 0}">
												<img width="100"
													src="https://bootdey.com/img/Content/avatar/avatar7.png"
													alt="Invoice logo">
											</c:if>
											<c:if test="${order.customer.accountGoogle == 1}">
												<img width="100" src="../images/google.png"
													alt="Invoice logo">
											</c:if>

										</div>
									</div>
									<!-- col-lg-6 end here -->
									<div class="col-lg-6" style="width: 70%">
										<!-- col-lg-6 start here -->
										<div class="invoice-from">
											<ul class="list-unstyled text-right">
												<li><strong>ID: </strong>${order.customer.id}</li>
												<li><strong>Email: </strong>${order.customer.email}</li>
												<li><strong>Loại TK: </strong>
												<c:if test="${order.customer.accountGoogle == 0}">
													Thường
												</c:if> <c:if test="${order.customer.accountGoogle == 1}">
													Google
												</c:if></li>
												<li>
												<ul class="list-unstyled text-right">
													<li><strong>Trạng thái đơn hàng: </strong></li>
			
													 
													<li class="mg-li">
													${order.status()}
													</li>
												</ul>
												</li>
											</ul>
										</div>
									</div>
								</div>

							</div>
						</div>
					</form>

				</div>
			</main>
		</div>
	</div>
	<script>
		// Hien thi hinh anh
		var previews = document.querySelectorAll('.preview-image');
		var btns = document.querySelectorAll("input[type='file']");
		console.log(previews);
		console.log(btns);
		var loadFile = function(index, event) {

			previews[index].src = URL.createObjectURL(event.target.files[0]);
			btn[index].onload = function() {
				URL.revokeObjectURL(previews[index].src) // free memory
			}
		};
		function productDetail(id) {
			window.location.href = "../shopdetail?idProduct="+id;
		}
	</script>
	<script src="https://kit.fontawesome.com/c31e7889db.js"
		crossorigin="anonymous"></script>
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