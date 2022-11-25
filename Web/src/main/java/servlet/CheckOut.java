package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import io.WritePDF;
import model.Product;

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
		String shipping = request.getParameter("shipping-option");
		String desAddres = request.getParameter("des-address");
		HttpSession session = request.getSession();
		ShoppingCart<String, Product> cart = (ShoppingCart<String, Product>) session.getAttribute("cart");
		String address = ward + " - " + district + " - " + city;
		WritePDF.makeInvoicePDF(name, phoneNum, email, address, desAddres, shipping, ward, "25-11-2022",
				cart.getCartItems());
		request.getRequestDispatcher("authentication.jsp").forward(request, response);

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
