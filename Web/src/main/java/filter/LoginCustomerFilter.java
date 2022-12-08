package filter;

import java.io.IOException;
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

import model.Customer;

/**
 * Servlet Filter implementation class LoginCustomerFilter
 */
public class LoginCustomerFilter  implements Filter {
       
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String url = httpRequest.getRequestURI();
		String[] split = url.split("/");
		if(split[split.length-1].equals("checkout.jsp")||split[split.length-1].equals("authentication.jsp")) {
			HttpSession session = httpRequest.getSession(true);
			Customer customer = (Customer) session.getAttribute("user");
			if(customer == null) {
				
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");
			}
			else {
				chain.doFilter(httpRequest, httpResponse);
			}
			
		}else {
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
