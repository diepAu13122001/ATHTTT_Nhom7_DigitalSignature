package digital_signature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DigitalSignatureDAO;

/**
 * Servlet implementation class DisableKey
 */
@WebServlet("/admin/DisableKey")
public class DisableKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisableKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		int userId =Integer.parseInt(request.getParameter("userId"));  
		int id =Integer.parseInt(request.getParameter("id"));  
		int active =Integer.parseInt(request.getParameter("active"));  
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		boolean isUpdate = digitalSignatureDAO.updateStatusPublicKey(userId, id,active);
		if(isUpdate) {
			response.getWriter().print("OK");
		}else {
			response.getWriter().print("FAIL");
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
