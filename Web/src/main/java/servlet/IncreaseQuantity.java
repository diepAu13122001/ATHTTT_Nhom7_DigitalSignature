package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import dao.ProductDAO;
import dao.HistoryUrl;
import model.Product;

/**
 * Servlet implementation class IncreaseQuantity
 */
@WebServlet("/increase")
public class IncreaseQuantity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreaseQuantity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		HttpSession session = request.getSession();
		String productID = request.getParameter("id");
		String quantity = request.getParameter("quantity");
		ShoppingCart<String, Product> cart = (ShoppingCart<String, Product>)session.getAttribute("cart");
		if(cart==null) {
			cart =new ShoppingCart<String, Product>();
		}
		System.out.println(quantity);
		System.out.println(productID);
		if(productID!=null) {
			Product product = productDAO.getProductById(Integer.parseInt(productID));
			ShoppingCartItem<Product> cartItem = cart.getCartItem(productID); 
			System.out.println(cartItem.getQuantity()+" cart1");
			session.setAttribute("quantityCurrent ", cartItem);
			int num = cartItem.getQuantity()- Integer.parseInt(quantity);
			if(num<0) {
				for(int i =0;i<Math.abs(num);i++) {
					cart.add(productID, product);
				}
			}else {
				for(int i =0;i<Math.abs(num);i++) {
					cart.remove(productID);
				}
				
			}
			request.setAttribute("addProduct", product);
			System.out.println("Add to cart OK!");
		}
		HistoryUrl historyUrl = (HistoryUrl)getServletContext().getAttribute("urlDAO");
		String urlLast = historyUrl.getUrlLast();
		if(urlLast==null) {
			urlLast="index.jsp";
		}else {
			urlLast=urlLast.substring(1);
		}
	    response.sendRedirect(urlLast);
	}
			
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
