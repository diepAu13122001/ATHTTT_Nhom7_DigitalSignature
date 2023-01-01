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
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet">
<style>
@media ( min-width : 768px) {
	.col-md-10 {
		max-width: 100%
	}
}
</style>
</head>

<body>
	<!-- Start Main Top -->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="shop-box-inner">
		<div class="container">
			<div class="row flex-lg-nowrap">


				<div class="col">
					<div class="e-tabs mb-3 px-3">
						<ul class="nav nav-tabs">
							<li class="nav-item"><a class="nav-link active" href="#">Danh
									sách đơn hàng</a></li>
						</ul>
					</div>

					<div class="row flex-lg-nowrap">
						<div class="col mb-3">
							<div class="e-panel card">
								<div class="card-body">

									<div class="e-table">
										<div class="table-responsive table-lg mt-3">
											<table class="table table-bordered">
												<thead>
													<tr>

														<th>MHD</th>
														<th class="max-width">Ngày đặt</th>
														<th>SĐT</th>
														<th class="sortable">Địa chỉ</th>
														<th>Tổng giá trị (VND)</th>
														<th>Trạng thái</th>
														<th>Chi tiết</th>
														<th>Hoá đơn</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${orders}" var="order">
														<tr>
															<td class="align-middle">${order.parent}</td>
															<td class="align-middle text-center">
																${order.dateCreate }</td>
															<td class="text-nowrap align-middle">${order.phoneNum }</td>
															<td class="text-nowrap align-middle">${order.address}</td>
															<td class="text-center align-middle"><fmt:formatNumber
																	type="number" groupingUsed="true"
																	value="${order.grandPrice}" /></td>
															<td class="text-center align-middle">
																${order.statusName}</td>
															<td class="text-center align-middle">
																<div class="btn-group align-top">
																	<button class="btn btn-sm btn-outline-secondary badge"
																		type="button" data-toggle="modal"
																		data-target="#user-form-modal"
																		onclick="showOrderDetail(${order.id})">
																		<i class="fas fa-info-circle"></i>
																	</button>

																</div>
															</td>
															<td>
															<c:if test="${order.status.equals('HS')}">
																<div class="btn-group align-top">
																	<button class="btn btn-sm btn-outline-secondary badge" onclick="showInvoice('${order.fileInvoice}')"
																		type="button">
																		<i class="fas fa-file-alt"></i>
																	</button>
																</div>
															</c:if>
															</td>
														</tr>
													</c:forEach>


												</tbody>
											</table>
										</div>
										<div class="d-flex justify-content-center">
											<ul class="pagination mt-3 mb-0">
												<li class="disabled page-item"><a href="#"
													class="page-link">‹</a></li>
												<li class="active page-item"><a href="#"
													class="page-link">1</a></li>
												<li class="page-item"><a href="#" class="page-link">2</a></li>
												<li class="page-item"><a href="#" class="page-link">3</a></li>
												<li class="page-item"><a href="#" class="page-link">4</a></li>
												<li class="page-item"><a href="#" class="page-link">5</a></li>
												<li class="page-item"><a href="#" class="page-link">›</a></li>
												<li class="page-item"><a href="#" class="page-link">»</a></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="col-12 col-lg-3 mb-3">
							<div class="card">
								<div class="card-body">
									<div class="text-center px-xl-3">
										<button class="btn btn-success btn-block" type="button"
											data-toggle="modal" data-target="#user-form-modal">New
											User</button>
									</div>
									<hr class="my-3">
									<div class="e-navlist e-navlist--active-bold">
										<ul class="nav">
											<li class="nav-item active"><a href="" class="nav-link"><span>All</span>&nbsp;<small>/&nbsp;32</small></a></li>
											<li class="nav-item"><a href="" class="nav-link"><span>Active</span>&nbsp;<small>/&nbsp;16</small></a></li>
											<li class="nav-item"><a href="" class="nav-link"><span>Selected</span>&nbsp;<small>/&nbsp;0</small></a></li>
										</ul>
									</div>
									<hr class="my-3">
									<div>
										<div class="form-group">
											<label>Date from - to:</label>
											<div>
												<input id="dates-range" class="form-control flatpickr-input"
													placeholder="01 Dec 17 - 27 Jan 18" type="text"
													readonly="readonly">
											</div>
										</div>
										<div class="form-group">
											<label>Search by Name:</label>
											<div>
												<input class="form-control w-100" type="text"
													placeholder="Name" value="">
											</div>
										</div>
									</div>
									<hr class="my-3">
									<div class="">
										<label>Status:</label>
										<div class="px-2">
											<div class="custom-control custom-radio">
												<input type="radio" class="custom-control-input"
													name="user-status" id="users-status-disabled"> <label
													class="custom-control-label" for="users-status-disabled">Disabled</label>
											</div>
										</div>
										<div class="px-2">
											<div class="custom-control custom-radio">
												<input type="radio" class="custom-control-input"
													name="user-status" id="users-status-active"> <label
													class="custom-control-label" for="users-status-active">Active</label>
											</div>
										</div>
										<div class="px-2">
											<div class="custom-control custom-radio">
												<input type="radio" class="custom-control-input"
													name="user-status" id="users-status-any" checked="">
												<label class="custom-control-label" for="users-status-any">Any</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> -->
					</div>

					<!-- User Form Modal -->
					<div class="modal fade" role="dialog" tabindex="-1"
						id="user-form-modal">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title">
										<span></span>
									</h5>
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<div class="modal-body">
									<div class="py-1">
										<div id="layoutSidenav_content">
											<nav class="navbar navbar-light bg-light ">
												<div class="container-fluid end-flex"
													style="justify-content: space-between;">
													<div class="row">
														<a  style="color: #fff"
															class="btn btn-primary my-2 " id="update-order" href="">
															<i class="fas fa-pencil-alt"></i> Sửa đơn hàng
														</a>
													</div>
													<div class="row">
														<div class="col-sm" id="cancel-order"></div>
														<div class="col-sm" id="status-order"></div>
													</div>



												</div>
											</nav>

											<nav class="navbar navbar-light  ">
												<div class="row">
													<span style="font-weight: 500; color: #b0b435">Thông
														báo từ BQT: </span>
													<div>
														<p id="note" class="mx-2" style="color: red"></p>
													</div>
												</div>
												<div class="row">
													<div class="title-left">
														<h2 style="font-weight: 500">Thông tin người nhận</h2>
													</div>
													<div class="container bootdey" style="margin-bottom: 20px">
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
																					<ul class="list-unstyled mb0">
																						<li><strong>Tên người nhận: </strong></li>
																						<li><strong>SĐT: </strong></li>
																						<li><strong>Email: </strong></li>
																						<li><strong>Địa chỉ: </strong></li>
																						<li><strong>Mô tả địa chỉ: </strong></li>
																					</ul>
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
													<div class="title-left">
														<h2 style="font-weight: 500">Sản phẩm</h2>
													</div>
													<div class="container bootdey">

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
																					style="overflow: hidden; outline: none;"
																					tabindex="0">
																					<table class="table table-bordered">
																						<thead>
																							<tr>
																								<th class="per70 text-center">Sản phẩm</th>
																								<th class="per5 text-center">Số lượng</th>
																								<th class="per25 text-center">Đơn giá</th>
																								<th class="per25 text-center">Thành tiền</th>
																							</tr>
																						</thead>
																						<tbody>



																						</tbody>
																						<tfoot>
																							<tr>
																								<th colspan="2" class="text-right">Tổng
																									cộng:</th>
																								<th class="text-center"></th>
																								<th class="text-center" id="totalPrice"></th>
																							</tr>
																							<tr>
																								<th colspan="2" class="text-right">Giảm
																									giá:</th>
																								<th class="text-center"></th>
																								<th class="text-center" id="discount"></th>
																							</tr>
																							<tr>
																								<th colspan="2" class="text-right">Ship:</th>
																								<th class="text-center"></th>
																								<th class="text-center" id="ship"></th>
																							</tr>
																							<tr>
																								<th colspan="2" class="text-right">Tổng
																									thanh toán:</th>
																								<th class="text-center"></th>
																								<th class="text-center" id="grandPrice"></th>
																							</tr>
																						</tfoot>
																					</table>
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
											</nav>
										</div>

									</div>
								</div>
							</div>
						</div>

						<div class="modal fade modal-child" id="exampleModal"
							tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<input id="id-order" type="hidden" value="">
							<div class="modal-dialog" role="document"
								style="margin-top: 200px;">
								<div class="modal-content"
									style="box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
									<div class="modal-header">
										<h3 class="modal-title" id="exampleModalLabel">Thông báo</h3>

									</div>
									<div class="modal-body">
										<h2 class="modal-title" id="exampleModalLabel">Bạn có
											muốn huỷ đơn hàng không ?</h2>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											onclick="closeModalChild()">Không</button>
										<button type="button" class="btn btn-primary"
											onclick="cancelOrder()">Chắc chắn</button>
									</div>
								</div>
							</div>
						</div>
					</div>
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
	function productDetail(id) {
		window.location.href = "./shopdetail?idProduct="+id;
	}
	function showOrderDetail(id) {
		$.ajax({
			type: "GET",
			url: "orderDetail",
			data: { id: id },
			success: function(responseJson) {
				console.log(responseJson.note);
				if(responseJson.status =='CO' ||responseJson.status =='SP'||responseJson.status =='HS' ){
					$("#update-order").css('display','none')
				}else{
					$("#update-order").css('display','block')
				}
				$("#update-order").attr("href","./order-detail?id="+id);
				$(".modal-title span").html("#"+responseJson.dateCreate+"-"+responseJson.parent);
				if(responseJson.note==undefined){
					$('#note').html("");
				}else{
					$('#note').html(responseJson.note);
				}
	
				if(responseJson.status == "PR"){
					$('#user-form-modal #status-order').html("<a class='btn btn-labeled btn-warning' style='color: #fff'> "
					+"<span class='btn-label'><i class='fas fa-user-warning'></i></span>&nbsp;&nbsp;Đang xử lý</a>");
					
					$('#user-form-modal #cancel-order').html("<a class='btn btn-labeled btn-danger' style='color: #fff' onclick='showModalChild("+responseJson.id+")'> "
					+"<span class='btn-label'><i class='fa fa-trash'></i></span>&nbsp;&nbsp;Huỷ đơn</a>");
				}
				if(responseJson.status == "NA"){
					$('#user-form-modal #status-order').html("<a class='btn btn-labeled btn-warning' style='color: #fff'> "
					+"<span class='btn-label'><i class='fas fa-user-check'></i></span>&nbsp;&nbsp;Xác thực</a>");
					$('#user-form-modal #status-order a').attr("href","./authentication?invoice="+responseJson.fileInvoice);
					$('#user-form-modal #cancel-order').html("<a class='btn btn-labeled btn-danger' style='color: #fff' onclick='showModalChild("+responseJson.id+")'> "
					+"<span class='btn-label'><i class='fa fa-trash'></i></span>&nbsp;&nbsp;Huỷ đơn</a>");
				}
				if(responseJson.status == "NP"){
					$('#user-form-modal #status-order').html("<a class='btn btn-labeled btn-primary' style='color: #fff'> "
							+"<span class='btn-label'><i class='fa fa-credit-card' aria-hidden='true'></i></span>&nbsp;&nbsp;Thanh toán</a>");
							$('#user-form-modal #status-order a').attr("href","payments?id="+responseJson.parent);
							$('#user-form-modal #cancel-order').html("<a class='btn btn-labeled btn-danger' style='color: #fff' onclick='showModalChild("+responseJson.id+")'> "
							+"<span class='btn-label'><i class='fa fa-trash'></i></span>&nbsp;&nbsp;Huỷ đơn</a>");
				}
				
				if(responseJson.status == "CO"){
					$('#user-form-modal #cancel-order').html("");
					$('#user-form-modal #status-order').html("<a class='btn btn-labeled btn-danger' style='color: #fff'"
							+"<span class='btn-label'><i class='fa fa-ban'></i></span>&nbsp;&nbsp;Đã huỷ</a>");
				}
				if(responseJson.status == "SP"){
					$('#user-form-modal #cancel-order').html("");
					$('#user-form-modal #status-order').html("<a class='btn btn-labeled btn-info' style='color: #fff'"
							+"<span class='btn-label'><i class='fa fa-truck'></i></span>&nbsp;&nbsp;Đang giao hàng</a>");
				}
				if(responseJson.status == "HS"){
					$('#user-form-modal #cancel-order').html("");
					$('#user-form-modal #status-order').html("<a class='btn btn-labeled btn-success' style='color: #fff'"
							+"<span class='btn-label'><i class='fa fa-check'></i></span>&nbsp;&nbsp;Đã giao hàng</a>");
				}
				$(".list-unstyled").html("<li><strong>Tên người nhận: </strong> "+responseJson.nameReceiver+"</li>"+
						"<li><strong>SĐT: </strong> "+responseJson.phoneNum+"</li>"+
						"<li><strong>Email: </strong> "+responseJson.email+"</li>"+
						"<li><strong>Địa chỉ: </strong> "+responseJson.address+"</li>"+
						"<li><strong>Mô tả địa chỉ: </strong> "+responseJson.addressDetail+"</li>"
				);
				
				let total = 0;
				$('#user-form-modal .table-bordered tbody').html("");
				$.each(responseJson.orderDetails, function(key, value) {
					total += value.product.price*value.quantity;
				
					$('#user-form-modal .table-bordered tbody').append("<tr	style='cursor: pointer'>"+
							"<td>"+value.product.nameProduct+"</td>"+
							"<td class='text-center'>"+value.quantity+"</td>"+
							"<td class='text-center'>"+formatCurrent(value.product.price)+"</td>"+
							"<td class='text-center'>"+formatCurrent(value.product.price*value.quantity)+"</td>"+
						"</tr>"
						);
					
				});
				$("#totalPrice").html(formatCurrent(total));
				$("#discount").html(formatCurrent(responseJson.discount));
				$("#ship").html(formatCurrent(responseJson.shipping.price));
				$("#grandPrice").html(formatCurrent(responseJson.grandPrice));
				
			}
		})
	}
	function formatCurrent(value) {
		return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
	}
	function closeModalChild() {
		$('#exampleModal').css("opacity", 0);
		$('#exampleModal').css("display","none");
		//$('#exampleModal').css("display","none");
		//$('#exampleModal').attr("aria-hidden","true");
	}
	function showModalChild(id) {
		$('#exampleModal').css("opacity", 1);
		$('#exampleModal').css("display","block");
		$('body').removeClass("modal-open");
		$("#id-order").val(id);
		//$('#exampleModal').css("display","none");
		//$('#exampleModal').attr("aria-hidden","true");
	}
	function cancelOrder(){
		window.location.href= "./cancel-order?id-order="+	$("#id-order").val();
	}
	function showInvoice(invoice) {
		window.location.href='./showPdf?invoice='+invoice;
	}
	</script>

</body>

</html>