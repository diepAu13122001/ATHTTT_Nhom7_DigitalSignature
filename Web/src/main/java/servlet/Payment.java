package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Orders;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/payments")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
			int parent = Integer.parseInt(request.getParameter("id"));
			Orders orders =  productDAO.getOrderByParentAndStatusActive(parent, "public");
			if(orders.getStatus().equals("NP")) {
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("payment.jsp").forward(request, response);
			}else {
				response.sendRedirect("./orders");
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
