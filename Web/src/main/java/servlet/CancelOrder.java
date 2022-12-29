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
 * Servlet implementation class CancelOrder
 */
@WebServlet("/cancel-order")
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		int idOrder = Integer.parseInt(request.getParameter("id-order")); 
		Orders orders = productDAO.getOrderById(idOrder);
		if(orders.getStatus().equals("CO")){
			request.setAttribute("message", "Huỷ đơn hàng thành công");
		}else {
			boolean isUpdate = productDAO.updateStatus(idOrder, "CO");
			if(isUpdate) {
				request.setAttribute("message", "Huỷ đơn hàng thành công");
				
			}else {
				request.setAttribute("message", "Huỷ đơn hàng thất bại");
			}
		}
		request.getRequestDispatcher("cancel-order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
