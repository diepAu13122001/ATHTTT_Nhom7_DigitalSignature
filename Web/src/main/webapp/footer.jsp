<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

    <!-- Start Instagram Feed  -->
  
    <!-- End Instagram Feed  -->


    
    <footer>
        <div class="footer-main py-5">
        </div>
    </footer>
    <!-- End Footer  -->

    <!-- Start copyright  -->
    <div class="footer-copyright">
        <p class="footer-company">All Rights Reserved. &copy; 2018 <a href="#">ThewayShop</a> Design By :
            <a href="https://html.design/">html design</a></p>
    </div>
    <!-- End copyright  -->

    <a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>
	<script>
	function addToCart(id) {
		  $.ajax({
            type: "GET",
            url : "addcart",
            data: {id: id},
            success: function(responseJson){
          	  console.log(responseJson)
          	  alert("Thêm giỏ hàng thành công");         	 
					$('.badge').html(responseJson.numberOfItems);	
					$('.cart-list').html('')
				let total = 0;
				   $.each(responseJson.items, function(key, value){
					   total +=  value.item.price *value.quantity;
					   $('.cart-list').append(
								 '<li> <a href="" class="photo"><img src="'+value.item.image+'" class="cart-thumb" alt="" /></a>'
									+'<h6><a href="">'+value.item.nameProduct+'</a></h6>'+
									'<p>'+value.quantity+'x- <span class="price">'+formatCurrent(value.item.price)+'</span></p></li>'	   
					   )
				   });
					$('.cart-list').append('<li class="total"><a href="cart" class="btn btn-default hvr-hover btn-cart">Giỏ hàng</a>'+
											'<span class="float-right"><strong>Tổng :'+formatCurrent(total)+'</strong></span></li>'
							)
		  }
	})
	}
	 function formatCurrent(value){
		 return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value/10);
	 }
	</script>
   
