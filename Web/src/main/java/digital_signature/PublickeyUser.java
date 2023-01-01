package digital_signature;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DigitalSignatureDAO;
import model.Customer;
import model.PublicKey;
import utitls.Role;

/**
 * Servlet implementation class PublickeyUser
 */
@WebServlet("/admin/publickey")
public class PublickeyUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublickeyUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		int userId = Integer.parseInt(request.getParameter("id")); 
		Customer customer = (Customer)request.getSession().getAttribute("userAdmin");
		if(customer.getRoleName().equals(Role.ADMIN)) {
			List<PublicKey> publicKeys = digitalSignatureDAO.getPublicKey(userId);
			request.setAttribute("publicKeys", publicKeys);
			request.getRequestDispatcher("manager-key.jsp").forward(request, response);
		}else {
			response.sendRedirect("./customer");
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
