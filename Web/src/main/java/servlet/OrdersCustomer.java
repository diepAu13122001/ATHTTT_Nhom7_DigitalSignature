package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HistoryUrl;
import dao.ProductDAO;
import model.Customer;
import model.OrderDetail;
import model.Orders;
import model.Product;

/**
 * Servlet implementation class OrdersCustomer
 */
@WebServlet("/orders")
public class OrdersCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		HistoryUrl historyUrl = (HistoryUrl) getServletContext().getAttribute("urlDAO");
		
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("user");
		List<Orders> orders = productDAO.getOrdersByUser(customer.getId());
		for(Orders order: orders) {
			List<OrderDetail> orderDetails = productDAO.getOrderDetails(order.getId());
			order.setOrderDetails(orderDetails);
		}
		request.setAttribute("orders", orders);
		historyUrl.saveHistoryUrl(request);
		request.getRequestDispatcher("bills.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
