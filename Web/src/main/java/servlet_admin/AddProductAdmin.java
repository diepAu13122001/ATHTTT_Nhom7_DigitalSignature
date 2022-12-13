package servlet_admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import cart.ShoppingCart;
import dao.HistoryUrl;
import dao.ProductDAO;
import model.Category;
import model.Customer;
import model.IDRandom;
import model.Product;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/admin/add-product")

public class AddProductAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * Extracts file name from HTTP header content-disposition
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		String nameProduct = request.getParameter("nameProduct");
		String description = request.getParameter("description");
		String cateString = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(cateString);
		double price = Double.parseDouble(request.getParameter("price"));
		String image = request.getParameter("image");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("userAdmin");
		if (customer != null) {
			boolean isInsert = productDAO.insertProduct(nameProduct, description, price, categoryId, image);
//	    request.getRequestDispatcher(urlLast).forward(request, response);         
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			// String json = new Gson().toJson(cart);
			System.out.println("Hello");
			response.getWriter().print(isInsert);
		}

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
