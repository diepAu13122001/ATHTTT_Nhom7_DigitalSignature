package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.Category;
import model.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/admin/updateproduct")
public class RenderPageUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenderPageUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		ServletContext context = getServletContext();
		List<Category> categories =  productDAO.getListCategories();
		context.setAttribute("categories", categories);
		String id = request.getParameter("idProduct");
		String nameproduct = request.getParameter("nameProduct");
		String description = request.getParameter("description");
		String nameCategory = request.getParameter("nameCategory");
		double price = Double.parseDouble(request.getParameter("price")); 
		System.out.println(price);
		String linkImage = request.getParameter("linkImage");
		String linkList = request.getParameter("linkList");
		String[] linkLists = linkList.split(" ");
		String link1 = linkLists[0];
		String link2 = linkLists[1];
		HttpSession session = request.getSession();
		session.setAttribute("idProduct", id);
		session.setAttribute("nameProduct", nameproduct);
		session.setAttribute("description", description);
		session.setAttribute("price", price);
		session.setAttribute("nameCategory", nameCategory);
		session.setAttribute("linkImage", linkImage);
		session.setAttribute("link1", link1);
		session.setAttribute("link2", link2);

		request.getRequestDispatcher("update-product.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
