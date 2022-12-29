package servlet_admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Category;
import model.Product;
import model.ProductCategory;

/**
 * Servlet implementation class DataProduct
 */
@WebServlet("/admin/dataproduct")
public class DataProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageString = request.getParameter("page");
		int page =pageString!=null?Integer.parseInt(pageString):1 ;
		ProductDAO productDAO = (ProductDAO)getServletContext().getAttribute("productDAO");
		int totalProduct = productDAO.getTotalProduct(null,-1,-1,-1);
		List<Product> listProducts = productDAO.pagdingProduct(page-1, 16);
		List<Category> categories = productDAO.getListCategories();
		
		request.setAttribute("categories", categories);
		request.setAttribute("pageCurrent", page);
		request.setAttribute("totalPage", totalProduct/16 +1);
		request.setAttribute("products", listProducts);
		//context.setAttribute("productsCate", lisProductCategories);
		request.getRequestDispatcher("table-product.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
