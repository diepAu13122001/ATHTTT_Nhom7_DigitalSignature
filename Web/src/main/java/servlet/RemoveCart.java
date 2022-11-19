package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cart.ShoppingCart;
import dao.UrlDAO;
import model.Product;

/**
 * Servlet implementation class RemoveCart
 */
@WebServlet("/remove")
public class RemoveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productID = request.getParameter("id");
		String removeAll = request.getParameter("removeAll");
		String quantity = request.getParameter("quantity");
		HttpSession session = request.getSession();
		ShoppingCart<String, Product> cart = (ShoppingCart<String, Product>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart<String, Product>();
			session.setAttribute("cart", cart);
		}
		if (removeAll != null) {
			if (removeAll.equals("clear")) {
				cart.removeAll();
			}
		}
		System.out.println(productID + "--------hello");
		if (productID != null) {
			for (int i = 0; i < Math.abs(Integer.parseInt(quantity)); i++) {
				cart.remove(productID);
			}
			System.out.println("Remove cart OK!");
		}

		UrlDAO urlDAO = (UrlDAO) getServletContext().getAttribute("urlDAO");
		String urlLast = urlDAO.getUrlLast();
		if (urlLast == null) {
			urlLast = "index.jsp";
		} else {
			urlLast = urlLast.substring(1);
		}
		response.sendRedirect(urlLast);

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
