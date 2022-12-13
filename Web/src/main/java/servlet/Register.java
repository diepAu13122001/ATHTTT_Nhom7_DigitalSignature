package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import dao.CustomerDAO;
import model.Customer;
import model.IDRandom;



/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		CustomerDAO khd = (CustomerDAO)getServletContext().getAttribute("khachHangDAO");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		if (firstName != null || lastName != null || email != null || pass != null) {
			Customer kh = new Customer();
			kh.setFirstName(firstName);
			kh.setLastName(lastName);
			kh.setEmail(email);
			kh.setPassword(pass);
			boolean isExitsEmail = false;
			boolean isSuccess = false;	
				if(khd.isEmailExist(email)) {
					isExitsEmail= true;
					request.setAttribute("isExitsEmail", isExitsEmail);
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}else {
					
					if(khd.insertCustomer(kh,0)) {
						Customer customer = khd.findByEmail(email,0);
						khd.insertRole("USER", customer.getId());
						isSuccess=true;
						request.setAttribute("isSuccess", isSuccess);
						request.getRequestDispatcher("login.jsp").forward(request, response);		
					}
					
				}
				
			}
		request.getRequestDispatcher("register.jsp").forward(request, response);
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
