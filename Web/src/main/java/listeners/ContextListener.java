package listeners;


import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.ProductDAO;
import dao.UrlDAO;



@WebListener
public final class ContextListener implements ServletContextListener {
	private ServletContext context = null;

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();

		try {
			CustomerDAO khachHangDAO = new CustomerDAO();
			context.setAttribute("khachHangDAO", khachHangDAO);
			ProductDAO productDAO = new ProductDAO();
			context.setAttribute("productDAO", productDAO);
			UrlDAO urlDAO = new UrlDAO();
			context.setAttribute("urlDAO", urlDAO);
	
		} catch (Exception ex) {
			System.out.println("Couldn't create bookstore database bean: "
					+ ex.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		context = event.getServletContext();

		CustomerDAO khachHangDAO = (CustomerDAO) context.getAttribute("khachHangDAO");

		context.removeAttribute("khachHangDAO");
		ProductDAO productDAO = (ProductDAO) context.getAttribute("productDAO");

		context.removeAttribute("productDAO");
		UrlDAO urlDAO = (UrlDAO)context.getAttribute("urlDAO");
		context.removeAttribute("urlDAO");
	}
}
