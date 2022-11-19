<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../admin/css/styles.css" rel="stylesheet" />
         <link href="../admin/css/mystyle.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
	<jsp:include page="header.jsp"></jsp:include>
		 <div id="layoutSidenav">
		 <jsp:include page="sidebar-left.jsp"></jsp:include>
		    <div id="layoutSidenav_content">
		     <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Thêm sản phẩm</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="index.jsp">Bảng điều khiển</a></li>
                        <li class="breadcrumb-item active">Sản phẩm /Thêm sản phẩm</li>
                    </ol>
                    <c:if test="${isOK==true}">
					<div class="alert alert-success" role="alert">
 							Thêm sản phẩm thành công
					</div>
                    </c:if>

                    <form action="add-product" enctype="multipart/form-data" method="post">
                        <nav class="navbar navbar-light bg-light ">
                            <div class="container-fluid end-flex">
                                <button type="submit" class="btn btn-success"> <i class="fas fa-paper-plane"></i> Đăng</button>
                            </div>
                          </nav>
                        <div class="row">
                            <div class="col-8">
                                <div class="mb-3">
                                    <label for="name" class="form-label">Tên sản phẩm</label>
                                    <input type="text" class="form-control" required="required" id="name"  name="name-product" placeholder="Nhập tên sản phẩm">
                                  </div>
                                  <div class="mb-3">
                                    <label for="des" class="form-label">Mô tả</label>
                                    <textarea class="form-control" required="required" id="des" name="des-product" rows="3"></textarea>
                                  </div> 
                                  <div class="mb-3">
                                    <label for="category" class="form-label">Loại sản phẩm</label>
                                    <select class="form-select" aria-label="Default select example" name="category-product">
               						<c:forEach var="cate" items="${categories}">
               							<option value="${cate.idCate}">${cate.nameCate}</option>
               						</c:forEach>
                                      </select>
                                  </div>
                                  <div class="mb-3">
                                    <label for="price" class="form-label">Giá</label>
                                    <div class="input-group">
                                        <input type="number" required="required" class="form-control" name="price-product" aria-label="VND amount (with dot and two decimal places)">
                                        <span class="input-group-text">VND</span>
                                        <span class="input-group-text">0.00</span>
                                      </div>
                                  </div>
                                  
                            </div>
                            <div class="col-4">
                                <div class="mb-3">
                                        <label for="formFile" class="form-label">Ảnh đại diện</label>
                                        <input class="form-control" required="required" name="file" type="file" id="formFile"  accept="image/*" onchange="loadFile(0,event)">
                                        <img alt="" src="" class="preview-image" style="width: 100%">
                                      
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="mb-3">
                                            <label for="formFile1" class="form-label">Ảnh bổ sung 1</label>
                                            <input class="form-control" required="required" name="file" type="file" id="formFile1" accept="image/*" onchange="loadFile(1,event)">
                                            <img alt="" src="" class="preview-image" style="width: 100%">
                                    </div>
                                    </div>
                                    <div class="col">
                                        <div class="mb-3">
                                            <label for="formFile2"  class="form-label">Ảnh bỏ sung 2</label>
                                            <input class="form-control" required="required" name="file" type="file" id="formFile2"  accept="image/*" onchange="loadFile(2,event)">
                                            <img alt="" src="" class="preview-image" style="width: 100%">
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
		        var loadFile = function (index, event) {
		
		            previews[index].src = URL.createObjectURL(event.target.files[0]);
		            btn[index].onload = function () {
		                URL.revokeObjectURL(previews[index].src) // free memory
		            }
		        };
    </script>
  <script src="https://kit.fontawesome.com/c31e7889db.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../admin/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../admin/assets/demo/chart-area-demo.js"></script>
        <script src="../admin/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../admin/js/datatables-simple-demo.js"></script>
</body>

</html>