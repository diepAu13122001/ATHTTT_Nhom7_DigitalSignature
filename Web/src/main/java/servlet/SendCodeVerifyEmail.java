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

import dao.CustomerDAO;
import email.Constants;
import email.EmailUtility;
import model.Customer;

/**
 * Servlet implementation class SendCodeVerifyEmail
 */
@WebServlet("/SendCodeVerifyEmail")
public class SendCodeVerifyEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCodeVerifyEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		CustomerDAO khd = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String status = "";
		if (email != null) {
			if (khd.isEmailExist(email)) {
				status = "EmailExist";
			} else {
				try {
					sendMail(session, email);
					status = "EmailNotExist";
				} catch (MessagingException e) {
					status = "EmailNotValid";
					
				}
				
			}
		}
		response.getWriter().print(status);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void sendMail(HttpSession session,String email)  throws MessagingException{
		SecureRandom random = new SecureRandom();
		String randomCode = new BigInteger(30, random).toString(32).toUpperCase();
		session.setAttribute("mailCode", randomCode);
		String subject = "Xác nhận email";
		String message = "Mã xác nhận của bạn là: "+randomCode;
		
			EmailUtility.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASS, email, subject, message);
		

}

}
