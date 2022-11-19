package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import model.Customer;

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
		String username = request.getParameter("emailAdmin");
		String pass = request.getParameter("passAdmin");
		CustomerDAO khd = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");
		Customer user = khd.findByEmail(username,0);
		String role =user!=null?khd.getRole(user.getId()):"";
		if (khd.checkLogin(username, pass) && role.equals("ADMIN")) {
				session.setAttribute("userAdmin", user);
				request.setAttribute("emailAdmin", username);
				response.sendRedirect("admin/index.jsp");
		} else {
			request.setAttribute("emailAdmin", username);
			request.getRequestDispatcher("login-admin.jsp").forward(request, response);
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
