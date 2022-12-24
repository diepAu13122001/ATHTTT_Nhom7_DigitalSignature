package digital_signature;

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

import email.Constants;
import email.EmailUtility;
import model.Customer;

/**
 * Servlet implementation class EmailVerification
 */
@WebServlet("/sennd-code-verificate")
public class SendVerificateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendVerificateCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Customer customer =(Customer) session.getAttribute("user");
		if(customer!=null) {
			SecureRandom random = new SecureRandom();
			String randomCode = new BigInteger(30, random).toString(32).toUpperCase();
			session.setAttribute("authCode", randomCode);
			String email = customer.getEmail();
			String subject = "Xác nhận tạo khoá mới";
			String message = "Mã xác nhận của bạn là: "+randomCode;
			try {
				EmailUtility.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASS, email, subject, message);
				response.sendRedirect("verificate-code");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
