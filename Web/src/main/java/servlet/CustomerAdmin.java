package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.ProductDAO;
import model.Customer;
import model.Product;

/**
 * Servlet implementation class CustomerAdmin
 */
@WebServlet("/admin/customer")
public class CustomerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO customerDAO = (CustomerDAO)getServletContext().getAttribute("khachHangDAO");
		List<Customer> customers = customerDAO.getCustomers();
		
		ServletContext context = getServletContext();
		context.setAttribute("customers", customers);
		//context.setAttribute("productsCate", lisProductCategories);
		request.getRequestDispatcher("customer.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
