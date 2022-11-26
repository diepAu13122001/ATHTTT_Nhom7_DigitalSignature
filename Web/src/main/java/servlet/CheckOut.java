package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import dao.ProductDAO;
import io.WritePDF;
import model.Customer;
import model.Product;
import model.Shipping;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/checkout")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/pdf; charset=utf-8");
		String name = request.getParameter("name");
		String phoneNum = request.getParameter("phone-num");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String ward = request.getParameter("ward");
		String shippingOption = request.getParameter("shipping-option");
		String desAddres = request.getParameter("des-address");
		HttpSession session = request.getSession();
		ShoppingCart<String, Product> cart = (ShoppingCart<String, Product>) session.getAttribute("cart");
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		String address = ward + " - " + district + " - " + city;
		LocalDateTime dateIssue = LocalDateTime.now();
		Shipping shipping = productDAO.getShippingById(Integer.parseInt(shippingOption));
		double subTotal = cart.getTotal();
		double discount = 0;
		double ship =shipping.getPrice();
		double grandTotal = subTotal + discount+ ship;
		String shippingType = shipping.getType();  

		
		Customer customer = (Customer)session.getAttribute("user");
		boolean isInsert = productDAO.insertOrders(customer.getId(), name, phoneNum, email, address, desAddres, 0, dateIssue, cart.getCartItems(), discount, Integer.parseInt(shippingOption), grandTotal);
		if(isInsert) {
			WritePDF.makeInvoicePDF(name, phoneNum, email, address, desAddres, shippingType, ward, dateIssue,
					cart.getCartItems(),subTotal,discount,ship,grandTotal);
			response.sendRedirect("authentication.jsp");
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
