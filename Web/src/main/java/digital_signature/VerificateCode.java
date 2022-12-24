package digital_signature;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;

/**
 * Servlet implementation class VerificateCode
 */
@WebServlet("/verificate-code")
public class VerificateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerificateCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60);
		Customer customer = (Customer) session.getAttribute("user");
		String authCode = (String) session.getAttribute("authCode");
		String code = authCode != null ? authCode : "";
		String userCode = request.getParameter("userCode");
		System.out.println("Giong nhau ko: "+authCode+" "+userCode);
		if (code.equals(userCode)) {
			response.sendRedirect("ReCreateKey");
		}else {
			request.setAttribute("email", customer.getEmail());
			request.setAttribute("verificateStatus", "Mã xác nhận không đúng");
			request.getRequestDispatcher("verificate-createkey.jsp").forward(request, response);
		}
		
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
