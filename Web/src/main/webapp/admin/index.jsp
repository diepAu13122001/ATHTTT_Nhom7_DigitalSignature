<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <style>
        .btn{
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
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Bảng điều khiển</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body">Primary Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-warning text-white mb-4">
                                    <div class="card-body">Warning Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-success text-white mb-4">
                                    <div class="card-body">Success Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-3 col-md-6">
                                <div class="card bg-danger text-white mb-4">
                                    <div class="card-body">Danger Card</div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#">View Details</a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        Area Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        Bar Chart Example
                                    </div>
                                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                               Đơn hàng
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
                                            <th>Thanh toán</th>
                                             <th>Xác thực</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Mã hoá đơn</th>
                                            <th>Ngày đặt</th>
                                            <th>SĐT</th>
                                            <th>Địa chỉ</th>
                                            <th>Tổng giá trị(VND)</th>
                                            <th>Thanh toán</th>
                                             <th>Xác thực</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                         <c:forEach begin="0" end="${orders.size()-1}" var="i">
                                    		<tr onclick="orderDetail(${orders.get(i).id})" style="cursor: pointer;">                         
                                            <td>${orders.get(i).id}</td>
                                            <td>${orders.get(i).dateCreate}</td>
                                            <td>${orders.get(i).phoneNum}</td>
                                            <td>${orders.get(i).address}</td>
                                             <td><fmt:formatNumber type="number"  groupingUsed="true"
																value="${orders.get(i).grandPrice}" />  </td>
                                             <td>
                                             <c:if test="${orders.get(i).payment == 0}">
                                             	 <button type="button" class="btn btn-danger">Chưa thanh toán</button>
                                             </c:if>
                                              <c:if test="${orders.get(i).payment == 1}">
                                             	 <button type="button" class="btn btn-success">Đã thanh toán</button>
                                             </c:if>
                                            </td>
                                             <td>
                                             <c:if test="${orders.get(i).authentication == 0}">
                                             	 <button type="button" class="btn btn-danger">Chưa xác thực</button>
                                             </c:if>
                                              <c:if test="${orders.get(i).authentication == 1}">
                                             	 <button type="button" class="btn btn-success">Đã xác thực</button>
                                             </c:if>
                                             </td>                                     	
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
	  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../admin/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../admin/assets/demo/chart-area-demo.js"></script>
        <script src="../admin/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="../admin/js/datatables-simple-demo.js"></script>
</body>
</html>