package servlet_admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier;

import dao.ProductDAO;
import model.Customer;

/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/admin/detele-product")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		int idProduct = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("userAdmin");
		if (customer != null) {
			boolean isDelete = productDAO.deleteProduct(idProduct);
//	    request.getRequestDispatcher(urlLast).forward(request, response);         
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			// String json = new Gson().toJson(cart);
			System.out.println("Delete successfully");
			response.getWriter().print(isDelete);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
