package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.mysql.cj.Session;

import dao.CustomerDAO;
import email.Constants;
import email.EmailUtility;
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
		response.setContentType("text/plain");
		CustomerDAO khd = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		String status = "";
		if (firstName != null || lastName != null || email != null || pass != null) {
			Customer kh = new Customer();
			kh.setFirstName(firstName);
			kh.setLastName(lastName);
			kh.setEmail(email);
			kh.setPassword(pass);

			if (khd.insertCustomer(kh, 0)) {
				status = "RegisterOk";
			} else {
				status = "RegisterFail";
			}
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
