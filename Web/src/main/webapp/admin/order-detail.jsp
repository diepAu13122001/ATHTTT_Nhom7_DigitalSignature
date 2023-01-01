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
<title>Trang quản trị</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link href="../admin/css/styles.css" rel="stylesheet" />
<link href="../admin/css/mystyle.css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style type="text/css">
.pb-1 {
	padding-bottom: 1rem !important;
}

.btn-label {
	position: relative;
	left: -12px;
	display: inline-block;
	padding: 6px 12px;
	background: rgba(0, 0, 0, 0.15);
	border-radius: 3px 0 0 3px;
}

.btn-labeled {
	padding-top: 0;
	padding-bottom: 0;
}

.btn {
	
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

					<c:if test="${udpateSuccess==true}">
						<div class="alert alert-success" role="alert">Cập nhật thành
							công</div>
					</c:if>
					<c:if test="${udpateSuccess==false}">
						<div class="alert alert-danger" role="alert">Cập nhật thất
							bại</div>
					</c:if>
					<form action="update-order" method="get">
						<input type="hidden" value="${order.id}" name="id-order"
							id="id-order"> <input type="hidden"
							value="${order.parent}" name="parent" id="parent"> <input
							type="hidden" value="${order.userId}" name="userId" id="userId">
						<nav class="navbar navbar-light bg-light ">
							<div class="container-fluid end-flex">
								<div class="row mx-2">
									<ul class="list-unstyled text-right mb-0">
										<li class="mg-li"><select class="form-select"
											id="status-order" name="status-order">
												<c:forEach items="${statusOrdes.entrySet()}" var="entry">
													<c:if test="${ order.status.equals(entry.getKey())}">
														<option value="${entry.getKey()}" selected="selected">${entry.getValue()}</option>
													</c:if>
													<c:if test="${!order.status.equals(entry.getKey())}">
														<option value="${entry.getKey()}">${entry.getValue()}</option>
													</c:if>
												</c:forEach>
										</select></li>
									</ul>
								</div>
								<a class="btn btn-success" onclick="updateStatus()"> <i
									class="fas fa-paper-plane"></i> Cập nhật trạng thái
								</a>
							</div>
						</nav>
						<div class="row">
							<div class="col-8">
								<div class="row ">
									<div class="col">
										<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Thông tin người
											gửi</h4>
									</div>
									<div class="col">
										<button style="display: none" type="submit"
											class="btn btn-primary my-2 " id="update-order"
											style="float: right; margin-right: 25px">
											<i class="fas fa-pencil-alt"></i> Cập nhật hoá đơn
										</button>
										<c:if test="${order.status.equals('PR') || order.status.equals('NA') || order.status.equals('NP')}">
										<a class="btn btn-primary my-2 " onclick="submitUpdate()"
											style="float: right; margin-right: 25px"> <i
											class="fas fa-pencil-alt"></i> Cập nhật hoá đơn
										</a>
										</c:if>
										
									</div>

								</div>

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
																			required="required" id="name" name="name-receiver"
																			required value="${order.nameReceiver }">
																	</div>
																	<div class="mb-3">
																		<label for="phone" class="form-label"><strong>Số
																				điện thoại người nhận</strong></label> <input type="number"
																			class="form-control" required="required" id="phone"
																			name="phone-num" value="${order.phoneNum }">
																	</div>
																	<div class="mb-3">
																		<label for="email" class="form-label"><strong>Email</strong></label>
																		<input type="text" class="form-control"
																			required="required" id="email" name="email"
																			value="${order.email }">
																	</div>
																	<div class="mb-3">
																		<label for="address" class="form-label"><strong>Địa
																				chỉ</strong></label> <input type="text" class="form-control"
																			required="required" id="address" name="address"
																			value="${order.address }">
																	</div>
																	<div class="mb-3">
																		<label for="address-detail" class="form-label"><strong>Mô
																				tả địa chỉ</strong></label> <input type="text" class="form-control"
																			required="required" id="address-detail"
																			name="address-detail" value="${order.addressDetail}">
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
																				<tr style="cursor: pointer;">

																					<td><a
																						href="../shopdetail?idProduct=${order.orderDetails.get(i).product.idProduct}"
																						style="text-decoration: none">
																							${order.orderDetails.get(i).product.nameProduct }</a></td>
																					<td class="text-center"><input type="hidden"
																						name="id-product"
																						value="${order.orderDetails.get(i).product.idProduct}"><input
																						type="number" class="form-control" name="quantity"
																						<c:if test="${!role.equals('ADMIN')}">disabled="disabled"</c:if>
																						value="${order.orderDetails.get(i).quantity}"></td>
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
							<div class="col-4 ">
								<div class="row flex-column ">
									<div class="col border-bottom">
										<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Tài khoản</h4>
										<div class="row mx-2" style="padding-top: 1rem">
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
														<li><strong>Loại TK: </strong> <c:if
																test="${order.customer.accountGoogle == 0}">
													Thường
												</c:if> <c:if test="${order.customer.accountGoogle == 1}">
													Google
												</c:if></li>
														<li></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
									<div class="col mt-2 ">
										<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap">Xác thực</h4>
										<div class="row border-bottom">
											<div class="form-group mb-3">
												<label for="exampleFormControlTextarea1">Chữ ký</label>
												<textarea class="form-control" id="signature" rows="3">${signature}</textarea>
											</div>

											<div class="form-group mb-3">
												<label for="exampleFormControlTextarea1">Public key</label>
												<textarea class="form-control" id="publickey" rows="3">${publickey}</textarea>
											</div>
											<div class="form-group mb-3">
												<c:if
													test="${statusDS.equals('SUCCESS')}">
													<div class="alert alert-success" role="alert">Xác
														thực thành công</div>
												</c:if>
												<c:if
													test="${!statusDS.equals('SUCCESS')}">
													<a class="btn btn-primary" onclick="checkSignature()">Kiểm
														tra</a>
												</c:if>

											</div>


										</div>
										<div class="col mt-2 border-bottom">
											<h4 class="pt-sm-2 pb-1 mb-0 text-nowrap ">Thông báo</h4>
											<div class="row ">
												<div class="form-group mb-3">
													<label for="exampleFormControlTextarea1">Nội dung</label>
													<textarea class="form-control" id="message" rows="3">${order.note}</textarea>
												</div>

												<div class="form-group mb-3">
												<c:if test="${order.status.equals('NA') || order.status.equals('NP') || order.status.equals('PR')}">
												<a class="btn btn-primary" onclick="sendMessage()">Gửi</a>
												</c:if>
													
												</div>

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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="../js/addcart.js"></script>
</body>
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
		window.location.href = "../shopdetail?idProduct=" + id;
	}
	function enableType() {
		$('#name').attr('disabled', 'disabled')
	}
	function submitUpdate() {
		$.confirm({
			title : 'Xác nhận!',
			content : 'Bạn có cập nhật không ?',
			buttons : {
				confirm : function() {
					document.querySelector('#update-order').click();
				},
				cancel : function() {

				},
			}
		});
	}
	function updateStatus() {
		var idOrder = $('#id-order').val();
		var statusOrder = $('#status-order').val();
		$.ajax({
			type : "GET",
			url : "./UpdateStatusOrder",
			data : {
				idOrder : idOrder,
				statusOrder : statusOrder
			},
			success : function(responseJson) {
				if (responseJson == 'updateOK') {
					showSwal('success-message', 'Cập nhật thành công');
					setTimeout(function(){location.reload()}, 1500);
				} else {
					showSwal('error', '', 'Cập nhật lỗi')
				}

			},
			error : function(request, status, error) {
				showSwal('error', '', 'Cập nhật lỗi')
			}
		})
	}
	function checkSignature() {
		var parent = $('#parent').val();
		var userId = $('#userId').val();
		$.ajax({
			type : "GET",
			url : "../CheckSignature",
			data : {
				parent : parent,
				userId : userId
			},
			success : function(responseJson) {
				if (responseJson == 'OK') {
					showSwal('success-message', 'Xác thực thành công');
				} else {
					showSwal('error', '', 'Xác thực thất bại')
				}

			},
			error : function(request, status, error) {
				showSwal('error', '', 'Lỗi')
			}
		})
	}
	function sendMessage() {
		var message = $('#message').val();
		var idOrder = $('#id-order').val();
		if(message == ''){
			showSwal('error', '', 'Chưa nhập nội dung')
		}else{
			$.ajax({
				type : "GET",
				url : "./SendMessage",
				data : {
					message : message,
					idOrder : idOrder
				},
				success : function(response) {
					if (response == 'OK') {
						showSwal('success-message', 'Gửi thành công');
					} else {
						showSwal('error', '', 'Gửi thất bại')
					}
				}
			})
		}
	
	}
</script>
<script src="../js/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
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