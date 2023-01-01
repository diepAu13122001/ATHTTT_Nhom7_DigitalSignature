package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import digital_signature.RSACipher;

/**
 * Servlet implementation class CompletePayment
 */
@WebServlet("/CompletePayment")
public class CompletePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletePayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
			int parent = Integer.parseInt(request.getParameter("parent"));
			boolean isUpdate= productDAO.updateStatusOrder(parent,"public","SP");
			if(isUpdate) {
				response.sendRedirect("./complete-order.jsp");
			}else {
				response.sendRedirect("./authentication-fail.jsp");
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
