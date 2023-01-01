package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Orders;

/**
 * Servlet implementation class OrderDetail
 */
@WebServlet("/order-detail")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
			int idOrder = Integer.parseInt(request.getParameter("id"));
			Orders orders = productDAO.getOrderById(idOrder);
			List<model.OrderDetail> orderDetails = productDAO.getOrderDetails(idOrder);
			orders.setOrderDetails(orderDetails);
			request.setAttribute("orders", orders);
			if(orders.getStatus().equals("CO")||orders.getStatus().equals("HS")||orders.getStatus().equals("SP")) {
				request.getRequestDispatcher("not-found.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("order-detail.jsp").forward(request, response);
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
