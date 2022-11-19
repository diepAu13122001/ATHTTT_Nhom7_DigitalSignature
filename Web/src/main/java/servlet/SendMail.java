package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import email.Constants;
import email.EmailUtility;

/**
 * Servlet implementation class SendMail
 */
@WebServlet("/sendmail")
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recipient = request.getParameter("email");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		boolean isSendMailSuccess = false;
		try {
			EmailUtility.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASS, recipient, subject, message);
			System.out.println("Send mail success");
			isSendMailSuccess = true;
		
		} catch (Exception ex) {
			ex.printStackTrace();
			isSendMailSuccess = false;
			
		} finally {
			request.setAttribute("recipient", recipient);
			request.setAttribute("isSendMailSuccess", isSendMailSuccess);
			request.getRequestDispatcher("contact.jsp").forward(
	                    request, response);
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
