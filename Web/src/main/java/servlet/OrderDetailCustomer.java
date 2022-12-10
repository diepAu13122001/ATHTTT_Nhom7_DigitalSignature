package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


import dao.ProductDAO;
import dao.UrlDAO;
import model.OrderDetail;
import model.Orders;
import model.Product;

/**
 * Servlet implementation class OrderDetail
 */
@WebServlet("/orderDetail")
public class OrderDetailCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		int orderId = Integer.parseInt(request.getParameter("id"));
		Orders orders = productDAO.getOrderById(orderId);
		List<OrderDetail> orderDetails = productDAO.getOrderDetails(orderId);
		orders.setOrderDetails(orderDetails);
//	    request.getRequestDispatcher(urlLast).forward(request, response);         
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(orders);
        response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
