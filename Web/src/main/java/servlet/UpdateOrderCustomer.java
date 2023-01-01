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

import dao.DigitalSignatureDAO;
import dao.ProductDAO;
import io.WritePDF;
import model.Customer;
import model.OrderDetail;
import model.Orders;
import model.Product;
import utitls.Constant;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idOrder = Integer.parseInt(request.getParameter("id-order"));
		String status = request.getParameter("status-order");
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		Orders orders = productDAO.getOrderById(idOrder);
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		if (customer != null) {
			int numUpdate = productDAO.numberOfUpdateOrder(customer.getId(), orders.getParent());
			System.out.println("Số lần update :" + numUpdate);
			if (numUpdate < Constant.MAX_NUMBER_UPDATE) {
				String nameReciver = request.getParameter("name-receiver");
				String phoneNum = request.getParameter("phone-num");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				String addressDetail = request.getParameter("address-detail");
				String[] quantity = request.getParameterValues("quantity") == null ? new String[0]
						: request.getParameterValues("quantity");
				String[] idProduct = request.getParameterValues("id-product");
				List<OrderDetail> orderDetails = new ArrayList<>();
				double grandTotal = 0;
				if (quantity.length > 0) {
					for (int i = 0; i < quantity.length; i++) {
						OrderDetail orderDetail = new OrderDetail();
						Product product = productDAO.getProductById(Integer.parseInt(idProduct[i]));
						orderDetail.setProduct(product);
						orderDetail.setQuantity(Integer.parseInt(quantity[i]));
						orderDetails.add(orderDetail);
						grandTotal += product.getPrice() * Integer.parseInt(quantity[i]);

					}
					grandTotal += orders.getShipping().getPrice();
				} else {
					orderDetails = productDAO.getOrderDetails(idOrder);
					grandTotal = orders.getGrandPrice();
				}

				int update = productDAO.updateOrders(orders.getUserId(), nameReciver, phoneNum, email, address,
						addressDetail, orders.getDateCreate(), orderDetails, orders.getDiscount(),
						orders.getShipping().getId(), grandTotal, orders.getStatus(), orders.getFileInvoice(),
						customer.getId(), orders.getParent(), idOrder, orders.getCancelTime(), orders.getNote());
				if (update > -1) {
					double subTotal = grandTotal - orders.getShipping().getPrice();
					productDAO.updateStatus(update, "NA");
					digitalSignatureDAO.updateStatus(customer.getId(), orders.getParent(), "FAIL");
					WritePDF.updateInvoicePDF(nameReciver, phoneNum, email, address, addressDetail,
							orders.getShipping().getType(), "", orders.getDateCreate(), orderDetails, subTotal,
							orders.getDiscount(), orders.getShipping().getPrice(), grandTotal,
							orders.getFileInvoice() + ".pdf");
					response.sendRedirect("order-detail?id=" + update);
				} else {
					response.sendRedirect("order-detail?id=" + update);
				}

			} else {
				String message = "Mỗi đơn hàng chỉ được sửa tối đa " + Constant.MAX_NUMBER_UPDATE + " lần";
				request.setAttribute("message", message);
				request.getRequestDispatcher("order-detail.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
