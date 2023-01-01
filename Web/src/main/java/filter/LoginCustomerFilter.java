package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.ShoppingCart;
import model.Customer;
import model.Product;
import servlet.Cart;

/**
 * Servlet Filter implementation class LoginCustomerFilter
 */
public class LoginCustomerFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public LoginCustomerFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURI();
		String[] urls = { "checkout.jsp", "authentication.jsp", "payment.jsp", "orders", "createKey","verificate-code","order-detail" };
		List<String> list = Arrays.asList(urls);
		String split[] = url.split("/");
		String last = split[split.length - 1];
		if (list.contains(last)) {
			HttpSession session = httpRequest.getSession(true);
			Customer customer = (Customer) session.getAttribute("user");
			if (customer == null) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			} else {
				chain.doFilter(httpRequest, httpResponse);
			}

		} else {
			chain.doFilter(httpRequest, httpResponse);
		}
		// pass the request along the filter chain

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
