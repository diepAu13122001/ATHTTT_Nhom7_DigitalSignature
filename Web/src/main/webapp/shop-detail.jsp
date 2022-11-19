<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="en">
<!-- Basic -->

<head>

<link rel="stylesheet" href="css/start-reviews2.css">
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	 <meta name="google-signin-client_id" content="1036870259189-5q0j11me86k3v4ni4eng6v02v2pe7jql.apps.googleusercontent.com">
	<link rel="stylesheet" href="css/header.css">
    <!-- Site Metas -->
    <title>Freshshop</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
	<link rel="stylesheet" href="fonts/themify-icons-font/themify-icons/themify-icons.css">
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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->



<style>
#ratings{border:none;float:right;}
#ratings>input{display:none;}/*ẩn input radio - vì chúng ta đã có label là GUI*/
#ratings>label:before{margin:5px;font-size:1.25em;font-family:FontAwesome;display:inline-block;content:"\f005";}/*1 ngôi sao*/
#ratings>.half:before{content:"\f089";position:absolute;}/*0.5 ngôi sao*/
#ratings>label{color:#ddd; cursor: pointer; float: right;}
#ratings>input:checked~label,
#ratings:not(:checked)>label:hover, 
#ratings:not(:checked)>label:hover~label{color:#FFD700;}
/* Hover vào các sao phía trước ngôi sao đã chọn*/
#ratings>input:checked+label:hover,
#ratings>input:checked~label:hover,
#ratings>label:hover~input:checked~label,
#ratings>input:checked~label:hover~label{color:#FFED85;}

.img-fluid{
	border: 2px solid #b0b435;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<!-- Start All Title Box -->

	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>Sản phẩm</h2>
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="#">Cửa hàng</a></li>
						<li class="breadcrumb-item active">Sản phẩm</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

	<!-- Start Shop Detail  -->
	<div class="shop-detail-box-main">
		<c:set var="product" value="${product}"></c:set>
	

		<div class="container">
			<div class="row">
				<div class="col-xl-5 col-lg-5 col-md-6">
					<div id="carousel-example-1"
						class="single-product-slider carousel slide" data-ride="carousel">
						<div class="carousel-inner" role="listbox">
							<div class="carousel-item active">
								<img class="d-block w-100"
									src="${product.image}"
									alt="First slide">
							</div>

							<%-- <div class="carousel-item">
								<img class="d-block w-100" src="images/image-product/${link1 }"
									alt="Second slide">
							</div>
							<div class="carousel-item">
								<img class="d-block w-100" src="images/image-product/${link2}"
									alt="Third slide">
							</div> --%>
						</div>
						<a class="carousel-control-prev" href="#carousel-example-1"
							role="button" data-slide="prev"> <i class="fa fa-angle-left"
							aria-hidden="true"></i> <span class="sr-only">Previous</span>
						</a> <a class="carousel-control-next" href="#carousel-example-1"
							role="button" data-slide="next"> <i class="fa fa-angle-right"
							aria-hidden="true"></i> <span class="sr-only">Next</span>
						</a>
						<ol class="carousel-indicators" style="background: unset;margin-top:20px">
							<li data-target="#carousel-example-1" data-slide-to="0"
								class="active"><img class="d-block w-100 img-fluid"
								src="${product.image}" alt="" /></li>
							<%-- <li data-target="#carousel-example-1" data-slide-to="1"><img
								class="d-block w-100 img-fluid"
								src="images/image-product/${link1 }" alt="" /></li>
							<li data-target="#carousel-example-1" data-slide-to="2"><img
								class="d-block w-100 img-fluid"
								src="images/image-product/${link2 }" alt="" /></li> --%>
						</ol>
					</div>
				</div>
				<div class="col-xl-7 col-lg-7 col-md-6">
					<div class="single-product-details">
						<h2>${product.nameProduct}</h2>
						<h5>
							<fmt:formatNumber type="number" maxFractionDigits="3"
								value="${product.price}" />
							₫
						</h5>
						<p class="available-stock">
							<span> More than 20 available / <a href="#">8 sold </a></span>
						<p>
						<h4>Mô tả:</h4>
						<p>${product.description}</p>
						<ul>
							<li>
								<div class="form-group quantity-box">
									<label class="control-label">Số lượng</label> <input id="quantity"
										class="form-control" value="1" min="1" max="20" type="number">
								</div>
							</li>
						</ul>

						<div class="price-box-bar">
							<div class="cart-and-bay-btn">
								<a class="btn hvr-hover" data-fancybox-close="" onclick="buyNow('${product.idProduct}')">Mua
									ngay</a>
								<c:url var="url" value="/addcart">
									<c:param name="add" value="${product.idProduct }"></c:param>
			
								</c:url>
								<a class="btn hvr-hover" data-fancybox-close="" href="${url}">Thêm
									vào giỏ hàng</a>

							</div>
						</div>

						<div class="add-to-btn">
							<!-- 							<div class="add-comp">
								<a class="btn hvr-hover" href="#"><i class="fas fa-heart"></i> Add to wishlist</a>
								<a class="btn hvr-hover" href="#"><i class="fas fa-sync-alt"></i> Add to Compare</a>
							</div> -->
							<div class="share-bar">
								<a class="btn hvr-hover" href="#"><i class="fab fa-facebook"
									aria-hidden="true"></i></a> <a class="btn hvr-hover" href="#"><i
									class="fab fa-google-plus" aria-hidden="true"></i></a> <a
									class="btn hvr-hover" href="#"><i class="fab fa-twitter"
									aria-hidden="true"></i></a> <a class="btn hvr-hover" href="#"><i
									class="fab fa-pinterest-p" aria-hidden="true"></i></a> <a
									class="btn hvr-hover" href="#"><i class="fab fa-whatsapp"
									aria-hidden="true"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row my-5">
				<div class="card card-outline-secondary my-4" style="width: 100%">
					<div class="card-header">
						<h2>Đánh giá</h2>
					</div>
					<div class="card-body">
					<c:forEach var="review" items="${listReviews}">
						<div class="media mb-3">
							<div class="mr-2">
								<img class="rounded-circle border p-1" style="width: 56px"
									src="images/image-user/avatar.jpg"
									alt="Generic placeholder image">
							</div>
							<div class="media-body">
								<h4><strong>${review.getNameCustomer()}</strong> 
							<div id="ratings" >
							<c:if test="${review.rating.equals('5')}">
										<input type="radio" id="stars1" value="1" checked="checked" /> <label
										class="full" for="stars1" title="Rất tốt - 5 star"></label>
										<input
										type="radio" id="stars2" value="2" /> <label
										class="full" for="stars2" title="Tốt - 4 stars"></label>
													<input type="radio"  value="3" id="stars3" /> <label
										class="full" for="stars3" title="Bình thường - 3 stars"></label> 
												 <input
										type="radio"  value="4"  id="stars4"/> <label
										class="full" for="stars4" title="Không ổn - 2 stars"></label>
										<input type="radio" value="5"  id="stars5"/> <label
										class="full" for="stars5" title="Tệ - 1 stars"></label>
								</c:if>	
															<c:if test="${review.rating.equals('4')}">
										<input type="radio" id="stars1" value="1"  /> <label
										class="full" for="stars1" title="Rất tốt - 5 star"></label>
										<input
										type="radio" id="stars2" value="2" checked="checked"/> <label
										class="full" for="stars2" title="Tốt - 4 stars"></label>
													<input type="radio"  value="3" id="stars3" /> <label
										class="full" for="stars3" title="Bình thường - 3 stars"></label> 
												 <input
										type="radio"  value="4"  id="stars4"/> <label
										class="full" for="stars4" title="Không ổn - 2 stars"></label>
										<input type="radio" value="5"  id="stars5"/> <label
										class="full" for="stars5" title="Tệ - 1 stars"></label>
								</c:if>	
															<c:if test="${review.rating.equals('3')}">
										<input type="radio" id="stars1" value="1"  /> <label
										class="full" for="stars1" title="Rất tốt - 5 star"></label>
										<input
										type="radio" id="stars2" value="2" /> <label
										class="full" for="stars2" title="Tốt - 4 stars"></label>
													<input type="radio"  value="3" id="stars3" checked="checked" /> <label
										class="full" for="stars3" title="Bình thường - 3 stars"></label> 
												 <input
										type="radio"  value="4"  id="stars4"/> <label
										class="full" for="stars4" title="Không ổn - 2 stars"></label>
										<input type="radio" value="5"  id="stars5"/> <label
										class="full" for="stars5" title="Tệ - 1 stars"></label>
								</c:if>	
															<c:if test="${review.rating.equals('2')}">
										<input type="radio" id="stars1" value="1"  /> <label
										class="full" for="stars1" title="Rất tốt - 5 star"></label>
										<input
										type="radio" id="stars2" value="2" /> <label
										class="full" for="stars2" title="Tốt - 4 stars"></label>
													<input type="radio"  value="3" id="stars3" /> <label
										class="full" for="stars3" title="Bình thường - 3 stars"></label> 
												 <input
										type="radio"  value="4"  id="stars4" checked="checked"/> <label
										class="full" for="stars4" title="Không ổn - 2 stars"></label>
										<input type="radio" value="5"  id="stars5"/> <label
										class="full" for="stars5" title="Tệ - 1 stars"></label>
								</c:if>	
															<c:if test="${review.rating.equals('1')}">
										<input type="radio" id="stars1" value="1"  /> <label
										class="full" for="stars1" title="Rất tốt - 5 star"></label>
										<input
										type="radio" id="stars2" value="2" /> <label
										class="full" for="stars2" title="Tốt - 4 stars"></label>
													<input type="radio"  value="3" id="stars3" /> <label
										class="full" for="stars3" title="Bình thường - 3 stars"></label> 
												 <input
										type="radio"  value="4"  id="stars4"/> <label
										class="full" for="stars4" title="Không ổn - 2 stars"></label>
										<input type="radio" value="5"  id="stars5" checked="checked"/> <label
										class="full" for="stars5" title="Tệ - 1 stars"></label>
								</c:if>		
								</div> 
								
								</h4>
								<p>${review.getContent()}</p>
								<small class="text-muted">${review.getDateReview() }</small>
							</div>
						</div>
						<hr>
					</c:forEach> 
						<div class="contact-form-right">
							<h2>Đánh giá của bạn</h2>
							<c:if test="${user.email!=null }">
							<form id="contactForms" action="addreview"
								style="display: flex; flex-direction: column" >
								<input type="hidden" name="idProduct" value="${product.idProduct}">
								<div id="rating">
										<input type="radio" id="star1" name="rating" value="5" checked="checked" /> <label
										class="full" for="star1" title="Rất tốt - 5 star"></label>
										<input
										type="radio" id="star2" name="rating" value="4" /> <label
										class="full" for="star2" title="Tốt - 4 stars"></label>
													<input type="radio" id="star3" name="rating" value="3"  /> <label
										class="full" for="star3" title="Bình thường - 3 stars"></label> 
												 <input
										type="radio" id="star4" name="rating" value="2" /> <label
										class="full" for="star4" title="Không ổn - 2 stars"></label>
										<input type="radio" id="star5" name="rating" value="1" /> <label
										class="full" for="star5" title="Tệ - 1 stars"></label>
								</div>
								<div class="row">

									<div class="col-md-12">
										<div class="form-group">
											<textarea class="form-control" id="message"
												placeholder="Nhận xét của bạn" rows="4"
												data-error="Write your message" name="content" required></textarea>
											<div class="help-block with-errors"></div>
										</div>
										<div class="submit-button text-center">
										
											<button class="btn hvr-hover" id="submit" type="submit">Gửi
												đánh giá</button>
											
											<div id="msgSubmits" class="h3 text-center hidden"></div>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</form>
							</c:if>
							<c:if test="${user.email==null }">
							<div class="login-box2">
                         		<a class="btn hvr-hover" style="width: 130px" data-fancybox-close="" href="login.jsp">Đăng nhập</a>
                         		<a class="btn hvr-hover" style="width: 130px" data-fancybox-close="" href="register.jsp">Đăng ký</a>
								</div>
							</c:if>	
							<div>
							
							</div>
						
						</div>

					</div>
				</div>
			</div>

			<div class="row my-5">
				<div class="col-lg-12">
					<div class="title-all text-center">
						<h1>Featured Products</h1>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Sed sit amet lacus enim.</p>
					</div>
					<div class="featured-products-box owl-carousel owl-theme">
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-01.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-02.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-03.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-04.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-01.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-02.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-03.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
						<div class="item">
							<div class="products-single fix">
								<div class="box-img-hover">
									<img src="images/img-pro-04.jpg" class="img-fluid" alt="Image">
									<div class="mask-icon">
										<ul>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="View"><i
													class="fas fa-eye"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Compare"><i
													class="fas fa-sync-alt"></i></a></li>
											<li><a href="#" data-toggle="tooltip"
												data-placement="right" title="Add to Wishlist"><i
													class="far fa-heart"></i></a></li>
										</ul>
										<a class="cart" href="#">Add to Cart</a>
									</div>
								</div>
								<div class="why-text">
									<h4>Lorem ipsum dolor sit amet</h4>
									<h5>$9.79</h5>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- End Cart -->




	<!-- Start Footer  -->
	<jsp:include page="footer.jsp"></jsp:include>

	<script type="text/javascript">
	function loginFirst() {
		  alert("Bạn phải đăng nhập trước");
		}
	function buyNow(id){
		var quantity = document.querySelector("#quantity").value;
		window.location.href="BuyNow?idProduct="+id+"&quantity="+quantity;
	}

	</script>
	 <!-- ALL JS FILES -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- ALL PLUGINS -->
    <script src="./js/bootsnav.js"></script>
    <script src="js/jquery.superslides.min.js"></script>
    <script src="js/bootstrap-select.js"></script>
    <script src="js/inewsticker.js"></script>
    <script src="js/images-loded.min.js"></script>
    <script src="js/isotope.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/baguetteBox.min.js"></script>
    <script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>
</body>

</html>