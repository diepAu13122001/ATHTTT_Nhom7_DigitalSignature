package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import dao.ProductDAO;
import dao.UrlDAO;
import model.Product;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/addcart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		HttpSession session = request.getSession();
		ShoppingCart<String, Product> cart = (ShoppingCart<String, Product>)session.getAttribute("cart");
	
		if (cart == null) {
			cart = new ShoppingCart<String, Product>();
			session.setAttribute("cart", cart);
		}

		String productID = request.getParameter("add");
	
		
		if(productID!=null) {
			Product product = productDAO.getProductById(Integer.parseInt(productID));
			cart.add(productID, product);
			//request.setAttribute("addProduct", product);
		}
		UrlDAO urlDAO = (UrlDAO)getServletContext().getAttribute("urlDAO");
		String urlLast = urlDAO.getUrlLast();
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
