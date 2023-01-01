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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>

<style type="text/css">
#datatablesSimple td:nth-child(6) a {
	display: block;
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
					<h1 class="mt-4">Khách hàng</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="index.jsp">Bảng điều
								khiển</a></li>
						<li class="breadcrumb-item active">Sản phẩm /Bảng dữ liệu</li>
					</ol>
					<div class="card mb-4">
						<div class="card-body">
							<a href="../register.jsp" class="btn btn-success"><i
								class="fa-solid fa-plus"></i> Thêm khách hàng</a>
						</div>
					</div>
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table me-1"></i> Dữ liệu
						</div>
						<div class="card-body">
							<table id="datatablesSimple">
								<thead>
									<tr>
										<th>Họ</th>
										<th>Tên</th>
										<th>Email</th>
										<th>Số đơn hàng đã mua</th>
										<th>Số bình luận</th>
										<th>Sửa</th>
										<th>Public key</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>Họ</th>
										<th>Tên</th>
										<th>Email</th>
										<th>Số đơn hàng đã mua</th>
										<th>Số bình luận</th>
										<th>Sửa</th>
										<th>Public key</th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach items="${customers }" var="customer">
										<tr>

											<td>${customer.lastName}</td>
											<td>${customer.firstName}</td>
											<td>${customer.email}</td>
											<td>${customer.numberOrders}</td>
											<td>${customer.numberReviews}</td>
											<td><a style="cursor: pointer;"
												onclick="orderDetail(${customer.id},'${userAdmin.roleName}')"><i
													class="fa-solid fa-pen"></i></a></td>

											<td><a style="cursor: pointer;"
												onclick="publicKey(${customer.id},'${userAdmin.roleName}')"><i
													class="fas fa-key"></i></a></td>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="../js/addcart.js"></script>
	<script>
		 <script type="text/javascript">
		 var des = document.querySelectorAll('#datatablesSimple td:nth-child(2)');
		 for(var i of des){
			 var x = String(i.innerHTML);
			 if(x.length>80){
				 var newDes = x.substring(0,80);
				 i.innerHTML = newDes+"  ...";
			 }
		 }
		 </script>
	<script type="text/javascript">  
		  function orderDetail(id,role) {
			if(role =='ADMIN'){
				window.location.href = "./info-customer?id="+id;
			}else{
				showSwal('error','','Không có quyền truy cập');
			}
			
			
		}
		  function publicKey(id,role) {
				if(role =='ADMIN'){
					window.location.href = "./publickey?id="+id;
				}else{
					showSwal('error','','Không có quyền truy cập');
				}
				
				
			}
		    function edit(name, id){
		    	  var idUpdate = document.querySelector('#idCate');
		    	  var items = document.querySelector('#datatablesSimple .tr-'+id);
		    	  var allTr = document.querySelectorAll('#datatablesSimple tr');
		    	  var btnSend = document.querySelector('#send');
		    	  var btnUpdate = document.querySelector('#update');
		    	  btnUpdate.classList.remove("hidden-btn");
		    	  btnSend.classList.add("hidden-btn");
		    	  console.log(nameUpdate);
	    	 		 for(var a of allTr){
		    		  a.classList.remove("change-background");
		    		
		    	  } 
		    	  items.classList.add("change-background");
		    	  nameUpdate.value=name;
		    	  idUpdate.value=id;
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