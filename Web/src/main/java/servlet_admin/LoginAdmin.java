package servlet_admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import model.Customer;
import utitls.Role;

/**
 * Servlet implementation class LoginAdmin
 */
@WebServlet("/login-admin")
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(300);
		String username = request.getParameter("userName");
		String pass = request.getParameter("password");
		CustomerDAO khd = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");
		Customer user = khd.findByEmail(username,0);
		String role =user!=null?khd.getRole(user.getId()):"";
		boolean isLogSuccess = false;
		boolean isAuthor = role.equals(Role.ADMIN) || role.equals(Role.EMPLOYEE);
		if (khd.checkLogin(username, pass) && isAuthor)   {
				session.setAttribute("userAdmin", user);
				isLogSuccess = true;
		}
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     response.getWriter().print(isLogSuccess);
		
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
