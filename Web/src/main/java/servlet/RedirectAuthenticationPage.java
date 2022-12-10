package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dao.UrlDAO;
import io.WritePDF;
import model.Customer;
import model.Orders;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/authentication")
public class RedirectAuthenticationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RedirectAuthenticationPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = getServletContext();
		UrlDAO urlDAO = (UrlDAO) getServletContext().getAttribute("urlDAO");
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		StringBuilder requestURL = new StringBuilder(request.getServletPath());
		String queryString = request.getQueryString();
		if (queryString == null) {
			urlDAO.setUrlLast(requestURL.toString());
		} else {
			urlDAO.setUrlLast(requestURL.append('?').append(queryString).toString());
		}
//		String fileName = (String)context.getAttribute("fileNameInvoice");
		String invoice = request.getParameter("invoice");
		request.setAttribute("invoice", invoice);
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		if (customer != null) {
			try {
				String split[] = invoice.split("_");
				int idOrder = Integer.parseInt(split[split.length - 1]);
				Orders orders = productDAO.getOrderById(idOrder);
				if (orders.getStatus().equals("NA")) {
					request.getRequestDispatcher("authentication.jsp").forward(request, response);
				} else {
					if (orders.getStatus().equals("NP")) {
						request.getRequestDispatcher("payment.jsp").forward(request, response);
					}

					else {
						request.setAttribute("error", "Đơn hàng đã huỷ");
						request.getRequestDispatcher("notfound.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				request.getRequestDispatcher("notfound.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("notfound.jsp").forward(request, response);
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
