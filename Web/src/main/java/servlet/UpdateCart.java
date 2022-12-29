package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cart.ShoppingCart;
import dao.HistoryUrl;
import dao.ProductDAO;
import model.Product;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/udpate-cart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart<String, Product> cart = (ShoppingCart<String, Product>)session.getAttribute("cart");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		if (cart == null) {
			cart = new ShoppingCart<String, Product>();
			session.setAttribute("cart", cart);
		}
		String productID = request.getParameter("id");	
		if(productID!=null) {
			cart.updateQuantity(productID, quantity);
			//request.setAttribute("addProduct", product);
		}
		HistoryUrl historyUrl = (HistoryUrl)getServletContext().getAttribute("urlDAO");
		String urlLast = historyUrl.getUrlLast();
		if(urlLast==null) {
			urlLast="index.jsp";
		}else {
			urlLast=urlLast.substring(1);
		}
//	    request.getRequestDispatcher(urlLast).forward(request, response);         
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String json = new Gson().toJson(cart);
        response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
