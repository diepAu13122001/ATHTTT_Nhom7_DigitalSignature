package servlet_admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import dao.ProductDAO;
import model.Category;
import model.Customer;
import model.IDRandom;
import model.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/admin/update-product")

public class UpdateProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		int idProduct = Integer.parseInt(request.getParameter("id"));
		String nameProduct = request.getParameter("nameProduct");
		String description = request.getParameter("description");
		String cateString = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(cateString);
		double price = Double.parseDouble(request.getParameter("price"));
		String image = request.getParameter("image");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("userAdmin");
		if (customer != null) {
			boolean isUpdate = productDAO.updateProduct(nameProduct, description, price, categoryId, image,idProduct);
//	    request.getRequestDispatcher(urlLast).forward(request, response);         
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			// String json = new Gson().toJson(cart);
			System.out.println("Update successfully");
			response.getWriter().print(isUpdate);
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
