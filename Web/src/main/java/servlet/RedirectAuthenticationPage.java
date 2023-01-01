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
import dao.DigitalSignatureDAO;
import dao.HistoryUrl;
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
		HistoryUrl historyUrl = (HistoryUrl) getServletContext().getAttribute("urlDAO");
		historyUrl.saveHistoryUrl(request);
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
//		String fileName = (String)context.getAttribute("fileNameInvoice");
		String invoice = request.getParameter("invoice");
		request.setAttribute("invoice", invoice);
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");
		if (customer != null) {
			try {
				String split[] = invoice.split("_");
				int idOrder = Integer.parseInt(split[split.length - 1]);
				int userId = Integer.parseInt(split[split.length - 2]);
				Orders orders = productDAO.getOrderByParentAndStatusActive(idOrder, "public");
				if (orders.getStatus().equals("NA") && userId == customer.getId() ) {
					request.setAttribute("pk", digitalSignatureDAO.publicKeyBase64(customer.getId()));
					request.getRequestDispatcher("authentication.jsp").forward(request, response);
				} else {
					if (orders.getStatus().equals("NP")) {
						request.setAttribute("authenticateSuccess", "Xác thực thành công");
						request.setAttribute("id", idOrder);
						request.getRequestDispatcher("payments").forward(request, response);
					}

					else {
						request.getRequestDispatcher("notfound.jsp").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
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
