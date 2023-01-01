package servlet_admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.DigitalSignatureDAO;
import dao.ProductDAO;
import model.Customer;
import model.DigitalSignature;
import model.OrderDetail;
import model.Orders;
import utitls.Role;

/**
 * Servlet implementation class OrderDetailAdmin
 */
@WebServlet("/admin/order-detail-ad")
public class OrderDetailAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderDetailAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		CustomerDAO customerDAO = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		HttpSession session = request.getSession();
		Customer admin = (Customer) session.getAttribute("userAdmin");
		int orderId = Integer.parseInt(request.getParameter("id"));

		Orders orders = productDAO.getOrderById(orderId);
		Customer customer = customerDAO.getCustomerById(orders.getUserId());
		orders.setCustomer(customer);
		List<OrderDetail> orderDetails = productDAO.getOrderDetails(orderId);
		orders.setOrderDetails(orderDetails);
		DigitalSignature digitalSignature = digitalSignatureDAO.getDigitalSignature(orders.getUserId(),
				orders.getParent());
		if (digitalSignature != null) {
			request.setAttribute("statusDS", digitalSignature.getStatus());
			request.setAttribute("signature", digitalSignature.getSignture());
			request.setAttribute("publickey", digitalSignature.getPublicKey());
		}

		Map<String, String> map = productDAO.getStutusOrder();
		String status = orders.getStatus();
		if (status.equals("SP")) {
			map.remove("NA");
			map.remove("NP");
		}
		if (status.equals("CO")) {
			map.remove("NA");
			map.remove("NP");
			map.remove("SP");
			map.remove("HS");
			map.remove("PR");
		}
		if (status.equals("HS")) {
			map.remove("NA");
			map.remove("NP");
			map.remove("SP");
			map.remove("CO");
		}
		if (status.equals("NP")) {
			map.remove("NA");
			map.remove("PR");
		}
		if (status.equals("NA")) {
			map.remove("NP");
			map.remove("SP");
			map.remove("HS");
			map.remove("PR");
		}
		if (status.equals("PR")) {
			map.remove("HS");

		}
		if (status.equals("SP")) {
			map.remove("NP");
			map.remove("NA");
			map.remove("PR");
		}

		HttpSession context = request.getSession();
		request.setAttribute("order", orders);
		request.setAttribute("statusOrdes", map);

		if (admin.getRoleName().equals(Role.ADMIN)) {
			request.setAttribute("role", admin.getRoleName());
		}
		request.getRequestDispatcher("order-detail.jsp").forward(request, response);
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

	protected void check(String status) {

	}
}
