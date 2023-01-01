package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import io.WritePDF;
import model.Customer;
import model.OrderDetail;
import model.Orders;
import model.Product;

/**
 * Servlet implementation class UpdateOrderCustomer
 */
@WebServlet("/UpdateOrderCustomer")
public class UpdateOrderCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrderCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				int idOrder = Integer.parseInt(request.getParameter("id-order"));
				String status = request.getParameter("status-order");
				ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
				HttpSession session = request.getSession();
				Customer customer = (Customer) session.getAttribute("user");
				if (customer != null) {
					String nameReciver = request.getParameter("name-receiver");
					String phoneNum = request.getParameter("phone-num");
					String email = request.getParameter("email");
					String address = request.getParameter("address");
					String addressDetail = request.getParameter("address-detail");
					String[] quantity = request.getParameterValues("quantity")==null ?new String[0]:request.getParameterValues("quantity") ;
					String[] idProduct = request.getParameterValues("id-product");
					List<OrderDetail> orderDetails = new ArrayList<>();
					Orders orders = productDAO.getOrderById(idOrder);
					double grandTotal = 0;
					if (quantity.length>0) {
						for (int i = 0; i < quantity.length; i++) {
							OrderDetail orderDetail = new OrderDetail();
							Product product = productDAO.getProductById(Integer.parseInt(idProduct[i]));
							orderDetail.setProduct(product);
							orderDetail.setQuantity(Integer.parseInt(quantity[i]));
							orderDetails.add(orderDetail);
							grandTotal += product.getPrice() * Integer.parseInt(quantity[i]);
							
						}
						grandTotal += orders.getShipping().getPrice();
					}else {
						orderDetails =productDAO.getOrderDetails(idOrder);		
						grandTotal = orders.getGrandPrice();
					}
				
					int update = productDAO.updateOrders(orders.getUserId(), nameReciver, phoneNum, email, address,
							addressDetail, orders.getDateCreate(), orderDetails, orders.getDiscount(),
							orders.getShipping().getId(), grandTotal, orders.getStatus(), orders.getFileInvoice(),
							customer.getId(), orders.getParent(), idOrder,orders.getCancelTime(),orders.getNote());
					if (update > -1) {
						double subTotal = grandTotal - orders.getShipping().getPrice();
						productDAO.updateStatus(update, "NA");
						WritePDF.updateInvoicePDF(nameReciver, phoneNum, email, address, addressDetail,
								orders.getShipping().getType(), "", orders.getDateCreate(), orderDetails, subTotal,
								orders.getDiscount(), orders.getShipping().getPrice(), grandTotal,
								orders.getFileInvoice() + ".pdf");
						response.sendRedirect("order-detail?id=" + update);
					} else {
						response.sendRedirect("order-detail?id=" + update);
					}

				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
