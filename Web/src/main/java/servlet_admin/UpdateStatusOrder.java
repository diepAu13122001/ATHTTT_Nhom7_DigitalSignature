package servlet_admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;

/**
 * Servlet implementation class UpdateStatusOrder
 */
@WebServlet("/admin/UpdateStatusOrder")
public class UpdateStatusOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStatusOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		int idOrder = Integer.parseInt(request.getParameter("idOrder"));
		String statuOrder = request.getParameter("statusOrder");
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		String status = "";
		boolean isUpdate = productDAO.updateStatus(idOrder, statuOrder);
		if (isUpdate) {
			status = "updateOK";
		}
		response.getWriter().print(status);

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
