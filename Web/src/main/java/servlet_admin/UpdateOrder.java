package servlet_admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/admin/update-order")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
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
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		boolean isUpdate = productDAO.updateOrder(idOrder,status);
		if(isUpdate) {
			request.setAttribute("udpateSuccess", true);
		}else {
			request.setAttribute("udpateSuccess", false);
		}
		
		request.getRequestDispatcher("order-detail?id="+idOrder).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
