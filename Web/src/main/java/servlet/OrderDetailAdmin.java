package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.ProductDAO;
import model.Customer;
import model.OrderDetail;
import model.Orders;

/**
 * Servlet implementation class OrderDetailAdmin
 */
@WebServlet("/admin/order-detail")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		CustomerDAO customerDAO = (CustomerDAO)getServletContext().getAttribute("khachHangDAO");
		int orderId = Integer.parseInt(request.getParameter("id"));
		Orders orders = productDAO.getOrderById(orderId);
		Customer customer = customerDAO.getCustomerById(orders.getUserId());
		orders.setCustomer(customer);
		List<OrderDetail> orderDetails = productDAO.getOrderDetails(orderId);
		orders.setOrderDetails(orderDetails);
		
		HttpSession context = request.getSession();
		context.setAttribute("order", orders);
		
		request.getRequestDispatcher("order-detail.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
