package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DigitalSignatureDAO;
import dao.HistoryUrl;
import model.Customer;

/**
 * Servlet implementation class RenderGUICreateKey
 */
@WebServlet("/createKey")
public class RenderGUICreateKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenderGUICreateKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HistoryUrl historyUrl = (HistoryUrl)getServletContext().getAttribute("urlDAO");
		historyUrl.saveHistoryUrl(request);
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO)getServletContext().getAttribute("digitalSignatureDAO");
		HttpSession session = request.getSession();
		Customer customer =(Customer) session.getAttribute("user");
		boolean isFirst = !digitalSignatureDAO.checkExistKey(customer.getId());
		request.setAttribute("isFirst", isFirst);
		System.out.println("Bạn đã từng tạo khoá chứa: "+ isFirst +", autho: "+ session.getAttribute("authCode"));
		request.getRequestDispatcher("create-key.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
