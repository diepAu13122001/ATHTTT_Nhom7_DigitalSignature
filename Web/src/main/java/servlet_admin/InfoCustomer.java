package servlet_admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import dao.ProductDAO;
import model.Customer;
import model.Orders;
import utitls.Role;

/**
 * Servlet implementation class InfoCustomer
 */
@WebServlet("/admin/info-customer")
public class InfoCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO customerDAO = (CustomerDAO) getServletContext().getAttribute("khachHangDAO");
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		int id = Integer.parseInt(request.getParameter("id"));
		Customer customer = customerDAO.getInfo(id);
		Customer userAdmin = (Customer)request.getSession().getAttribute("userAdmin");
		List<Orders> orders = productDAO.getOrdersByUser(id);
		request.setAttribute("customer", customer);
		request.setAttribute("orders", orders);
		if(userAdmin.getRoleName().equals(Role.ADMIN)) {
			request.getRequestDispatcher("info-customer.jsp").forward(request, response);
		}else {
			response.sendRedirect("./customer");
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
