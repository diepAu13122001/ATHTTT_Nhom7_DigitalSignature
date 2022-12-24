package listeners;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import dao.DigitalSignatureDAO;
import dao.ProductDAO;
import dao.HistoryUrl;
import timer.TimerExample;

@WebListener
public final class ContextListener implements ServletContextListener {
	private ServletContext context = null;
	private ScheduledExecutorService scheduler;

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
//		  scheduler = Executors.newSingleThreadScheduledExecutor();
//		  Runnable commad = new TimerExample();
//		  long initialDelay = 10;

//		  long period = 2;
//		  scheduler.scheduleAtFixedRate(commad, initialDelay, period,unit);

//		scheduler = Executors.newSingleThreadScheduledExecutor();
//		Runnable commad = new TimerExample(context);
//		TimeUnit unit = TimeUnit.SECONDS;
//		scheduler.schedule(commad, 10, unit);

		

		try {

			CustomerDAO khachHangDAO = new CustomerDAO();
			context.setAttribute("khachHangDAO", khachHangDAO);
			ProductDAO productDAO = new ProductDAO();
			context.setAttribute("productDAO", productDAO);
			HistoryUrl historyUrl = new HistoryUrl();
			context.setAttribute("urlDAO", historyUrl);
			DigitalSignatureDAO digitalSignatureDAO = new DigitalSignatureDAO();
			context.setAttribute("digitalSignatureDAO", digitalSignatureDAO);

		} catch (Exception ex) {
			System.out.println("Couldn't create bookstore database bean: " + ex.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		context = event.getServletContext();

		CustomerDAO khachHangDAO = (CustomerDAO) context.getAttribute("khachHangDAO");

		context.removeAttribute("khachHangDAO");
		ProductDAO productDAO = (ProductDAO) context.getAttribute("productDAO");

		context.removeAttribute("productDAO");
		HistoryUrl historyUrl = (HistoryUrl) context.getAttribute("urlDAO");
		context.removeAttribute("urlDAO");
	}
}
