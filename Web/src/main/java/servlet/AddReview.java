package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dao.HistoryUrl;
import model.Customer;
import model.IDRandom;
import model.Review;



/**
 * Servlet implementation class AddReview
 */
@WebServlet("/addreview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		String id = request.getParameter("idProduct");
		String rating = request.getParameter("rating");
	    String content = request.getParameter("content");
	    System.out.println(content);
	    System.out.println(id+" id sp");
	    
	    HttpSession session = request.getSession();
	    Customer customer ;
	    customer = (Customer)session.getAttribute("user");
	    LocalDateTime dateTime = LocalDateTime.now();
	    java.sql.Date dateReview = java.sql.Date.valueOf(dateTime.toLocalDate());
	    productDAO.insertReview(content,Integer.parseInt(rating),dateReview,Integer.parseInt(id), customer.getId());
//	    request.getRequestDispatcher("shopdetail").forward(request, response);
		HistoryUrl historyUrl = (HistoryUrl)getServletContext().getAttribute("urlDAO");
		String urlLast = historyUrl.getUrlLast();
		if(urlLast==null) {
			urlLast="index.jsp";
		}else {
			urlLast=urlLast.substring(1);
		}
		request.getRequestDispatcher(urlLast).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
