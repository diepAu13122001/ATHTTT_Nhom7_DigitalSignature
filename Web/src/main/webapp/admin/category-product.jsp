<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="vn">
<head>
<meta content="text/html;charset=UTF-8" />
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
<!-- Bootstrap CSS -->

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<style>
#datatablesSimple td:nth-child(3) {
	cursor: pointer;
}

.change-background {
	background-color: #dadada;
}

.hidden-btn {
	display: none;
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
					<h1 class="mt-4">Danh mục sản phẩm</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="index.jsp">Bảng điều
								khiển</a></li>
						<li class="breadcrumb-item active">Sản phẩm /Danh mục sản
							phẩm</li>
					</ol>
					<c:if test="${isOK==true }">
						<div class="alert alert-success">
							<strong>Thành công!</strong> Thêm danh mục thành công
						</div>
					</c:if>

					<div class="row">
						<div class="col-xl-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fa-solid fa-plus"></i> Thêm danh mục
								</div>
								<div class="mg-15">
									<form action="addcategory" method="post" id="send">
										<div class="form-group mt-2">
											<label for="name" class="form-label">Name:</label> <input
												type="text" class="form-control" id="name" required
												name="name-cate">
										</div>
										<div class="form-group mt-2">
											<button type="submit" class="btn btn-success">Đăng</button>

										</div>
									</form>
									<form action="update-category" method="get" class="hidden-btn"
										id="update">
										<div class="form-group mt-2">
											<label for="name" class="form-label">Name:</label> <input
												type="text" class="form-control" id="name2" required
												name="name-cate"> <input type="hidden"
												class="form-control" id="idCate" required name="idCate">
										</div>
										<div class="form-group mt-2">
											<button type="submit" class="btn btn-success ">Cập
												nhật</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-xl-6">
							<div class="card mb-4">
								<div class="card-header">
									<i class="fas fa-chart-bar me-1"></i> Bảng dữ liệu
								</div>
								<c:if test="${isOK == false}">
									<div class="alert alert-danger">
										<strong>Lỗi!</strong> Chưa chọn dòng để xóa
									</div>

								</c:if>
								<form action="RemoveCategory">
									<div class="card-body">
										<button type="submit" class="btn btn-danger btn-del">Xóa</button>
										<table id="datatablesSimple">

											<thead>
												<tr>
													<th>Mã loại</th>
													<th>Tên loại</th>
													<th>Sửa</th>
													<th>Chọn</th>

												</tr>
											</thead>
											<tfoot>
												<tr>
													<th>Mã loại</th>
													<th>Tên loại</th>
													<th>Sửa</th>
													<th>Chọn</th>
												</tr>
											</tfoot>
											<tbody>

												<c:forEach var="cate" items="${categories}">
													<c:url var="url" value="/categoryProduct">
														<c:param name="idCategory" value="${cate.getIdCate()}"></c:param>
													</c:url>
													<tr class="tr-${cate.getIdCate()}">
														<td>${cate.idCate}</td>
														<td>${cate.nameCate}</td>
														<td
															onclick="edit('${cate. getNameCate()}','${cate.getIdCate()}')">
															<i class="fa-solid fa-pen"></i>
														</td>
														<td><input type="checkbox" class="form-check-input" style="margin-left: 0"
															value="${cate.idCate}" name="delete-cate"></td>
													</tr>

												</c:forEach>
											</tbody>
										</table>
									</div>
								</form>
							</div>
						</div>
					</div>

				</div>
			</main>
		</div>
	</div>
	<script type="text/javascript">
		    
		    function edit(name, id){
		    	  var nameUpdate = document.querySelector('#name2');
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