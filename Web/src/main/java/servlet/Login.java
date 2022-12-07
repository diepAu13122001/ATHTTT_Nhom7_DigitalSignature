package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import access.google.GooglePojo;
import access.google.GoogleUtils;
import dao.CustomerDAO;
import dao.UrlDAO;
import model.Customer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(1800);
		String username = request.getParameter("email");
		String pass = request.getParameter("pass");
		CustomerDAO khd = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");

		// Lay duong dan cuoi cung
		UrlDAO urlDAO = (UrlDAO)getServletContext().getAttribute("urlDAO");
		String urlLast = urlDAO.getUrlLast();
		if(urlLast==null) {
			urlLast="index.jsp";
		}else {
			urlLast=urlLast.substring(1);
		}
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			if (khd.checkLogin(username, pass)) {
				Customer kh = khd.findByEmail(username.trim(),0);
				System.out.println(kh);
				session.setAttribute("user", kh);
				request.setAttribute("email", username);
				request.getRequestDispatcher(urlLast).forward(request, response);
			} else {
				request.setAttribute("email", username);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else {
			String accessToken = GoogleUtils.getToken(code);
		    GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
		 //   Customer customer = new Customer(googlePojo.getFamily_name(), googlePojo.getGiven_name(), googlePojo.getEmail(),"0"); 
		    Customer customer = new Customer();
		    customer.setFirstName(googlePojo.getGiven_name());
		    customer.setLastName(googlePojo.getFamily_name());
		    customer.setEmail(googlePojo.getEmail());
		    customer.setPassword("0");
		    
		    if(!khd.isAccountGoogleExist(googlePojo.getEmail())) {
		    	 khd.insertCustomer(customer,1);
		    	 customer = khd.findByEmail(googlePojo.getEmail(),1);
		    	 khd.insertRole("USER", customer.getId());
		    }else {
		    	customer = khd.findByEmail(googlePojo.getEmail(),1);
		    }
		    session.setAttribute("user", customer);
		    request.getRequestDispatcher(urlLast).forward(request, response);
		}
//		request.getRequestDispatcher("shop-detail.jsp").forward(request, response);
		

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
