<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"  %>
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
	 <meta name="google-signin-client_id" content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
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

</head>

<body>
    <!-- Start Main Top -->
   <jsp:include page="header.jsp"></jsp:include>
 <div id="toast"></div>
    <!-- Start All Title Box -->
    <div class="all-title-box">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2>Cart</h2>
                    <ul class="breadcrumb">
                        <li class="breadcrumb-item"><a href=".">Tranh chủ</a></li>
                        <li class="breadcrumb-item active">Giỏ hàng</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- End All Title Box -->

    <!-- Start Cart  -->
    <div class="cart-box-main">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="table-main table-responsive">
                   <div class="col-12 d-flex shopping-box">
                    <c:url var="urls" value="/remove">
												<c:param name="removeAll" value="clear"></c:param>
											</c:url>                           
                   <a style="margin-right: -15px; margin-bottom: 15px"  href="${urls}" class="ml-auto btn hvr-hover">
                   <i class="fa fa-trash" aria-hidden="true"></i> &nbsp;Xóa tất cả </a> </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Ảnh</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Tổng giá</th>
                                    <th>Xóa</th>
                                </tr>
                            </thead>
                       
                            <tbody>
                           
                            <c:if test="${cart!=null && cart.getCartItems().size()>0}">
                            <c:forEach  begin="0" end="${cart.getCartItems().size()-1}" var="index">
                            <c:url var="url" value="/shopdetail">
												<c:param name="idProduct" value="${cart.getCartItems().get(index).getItem().idProduct}"></c:param>
											</c:url>
                             <tr>
                                    <td class="thumbnail-img">
                                        <a href="#">
									<img class="img-fluid" src="${cart.getCartItems().get(index).getItem().image}" alt="" />
								</a>
                                    </td>
                                    <td class="name-pr">
                                        <a href="${url}">
									${cart.getCartItems().get(index).getItem().nameProduct}
								</a>
                                    </td>
                                    <td class="price-pr">
                                        <p class="price"><fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getCartItems().get(index).getItem().price}" /> ₫</p>
                                    </td>
                                    <c:set var="quantity" value="${ cart.getCartItems().get(index).getQuantity()}" scope="session"></c:set>
                                    <td class="quantity-box"><input type="number" size="4" id="quantily-${index }" value="${cart.getCartItems().get(index).getQuantity()}"
                                     min="1" step="1" class="c-input-text qty text" onchange="addCart('${cart.getCartItems().get(index).getItem().idProduct}',${index });"></td>
                                    <td class="total-pr">                                
                                        <p class="total-price-${index} "><fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getCartItems().get(index).totalPrice()}" /> ₫</p>
                                    </td>
                                    <td class="remove-pr">
                                      <c:url var="url" value="/remove">
												<c:param name="remove" value="${cart.getCartItems().get(index).getItem().idProduct}"></c:param>
											</c:url>
                                        <a onclick="removeCart('${cart.getCartItems().get(index).getItem().idProduct}',${index });">
									<i class="fas fa-times"></i>
								</a>
                                    </td>
                                </tr>
                           
                           	 </c:forEach>
                             </c:if>                     
                            </tbody>
	
                        </table>
                         <c:if test="${cart==null || cart.getCartItems().size()==0 }">
                              <h2 style="text-align: center">Giỏ hàng trống</h2>
                                 <p style="text-align: center; color: #fff"><a class="btn hvr-hover" href="shop" style="color: #fff">Cửa hàng</a></p>
   
                          </c:if>
                    </div>
                </div>
            </div>

            <div class="row my-5">
                <div class="col-lg-6 col-sm-6">
                    <div class="coupon-box">
                        <div class="input-group input-group-sm">
                            <input class="form-control" placeholder="Enter your coupon code" aria-label="Coupon code" type="text">
                            <div class="input-group-append">
                                <button class="btn btn-theme" type="button" onclick=" showErrorToast();">Sử dụng voucher</button>
                            </div>
                        </div>
                    </div>
                </div>
   <!--              <div class="col-lg-6 col-sm-6">
                    <div class="update-box">
                        <input value="Update Cart" type="submit">
                    </div>
                </div> -->
            </div>

            <div class="row my-5">
                <div class="col-lg-8 col-sm-12"></div>
                <div class="col-lg-4 col-sm-12">
                    <div class="order-box">
                    	
                        <h3>Thanh toán</h3>
                        <div class="d-flex">
                            <h4>Tổng giá</h4>
                            <c:set var="totalGrand" value="${cart.getTotal()}" scope="session"></c:set>
                            <div class="ml-auto font-weight-bold grand-total" > <fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getTotal()}" /> VND</div>
                        </div>
                        
                        <div class="d-flex">
                            <h4>Giảm giá</h4>
                            <div class="ml-auto font-weight-bold"> 0 VND </div>
                        </div>
                            <div class="d-flex">
                            <h4>Thuế</h4>
                            <div class="ml-auto font-weight-bold"> <fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getTax(0.05)}" /> VND </div>
                        </div>
                        <hr>
                        <div class="d-flex gr-total">
                            <h5>Thành tiền</h5>
                            <div class="ml-auto h5 grand-total"> <fmt:formatNumber type = "number" 
         														maxFractionDigits = "3" value = "${cart.getTotal()+cart.getTax(0.05)}" /> VND </div>
                        </div>
                        <hr> </div>
                </div>
                <div class="col-12 d-flex shopping-box"><a href="checkout" class="ml-auto btn hvr-hover">Checkout</a> </div>
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
      <script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
    <script type="text/javascript">
    function calTotal(price, index, length){
       var q = document.querySelector('#quantily-'+index).value;
       var total = document.querySelector('.total-price-'+index);     
       var total2 = document.querySelector('.total-price2-'+index);  
       var t = (Number(price*q)).toString();
       total2.innerHTML=t;
       total.innerHTML= t.replace(/\B(?=(\d{3})+(?!\d))/g, '.')+ ' ₫'; 
       grandToatal(length);
    
    }
    function addCart(id,index){
    	 var q = document.querySelector('#quantily-'+index).value;
    	
    	 if(Number(q)>=100){
    		 showErrorToast();
    	 }else{
    		 window.location="increase?id="+id+"&quantity="+q;
    	 }
    	 
    	
    }
    function removeCart(id,index){
   	 var q = document.querySelector('#quantily-'+index).value;
   	 window.location="remove?id="+id+"&quantity="+q;
   }
 	function grandToatal(length){
 		var sum =0;
 		for(var i =0;i<length;i++){
 		   var total = document.querySelector('.total-price2-'+i).innerHTML;        
 			sum+=Number(total);
 			
 		}
 		  var grandTotal = document.querySelectorAll('.grand-total');    
 		  for(item of grandTotal){
 			  item.innerHTML=sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')+' VND';
 		  }
 		
 	}
 	

    </script>
</body>

</html>