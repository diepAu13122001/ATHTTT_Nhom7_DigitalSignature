package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cart.ShoppingCart;
import dao.ProductDAO;
import dao.HistoryUrl;
import model.Category;
import model.Product;
import model.SortPopular;
import model.SortPriceASC;
import model.SortPriceDESC;


/**
 * Servlet implementation class Shop
 */
@WebServlet("/shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get list product
		int productOfPage = 15;
		String argument = "";
		String params ="";
		String search = request.getParameter("search");
		String cateString =request.getParameter("category_id") ;
		int categoryId = cateString!=null ? Integer.parseInt(cateString) : -1;
		// Lay khoang gia
		String minString = request.getParameter("min-price");
		String maxString = request.getParameter("max-price");
		double minPrice = minString!=null?Double.parseDouble(minString):-1;
		double maxPrice = maxString!=null?Double.parseDouble(maxString):-1;
		//
		String pageString = request.getParameter("page");
		int page = pageString!=null?Integer.parseInt(pageString):1;

		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		System.out.println(search+" "+categoryId+ " "+ minPrice+ " "+maxPrice);
		List<Product> results = productDAO.filterProduct(page-1, productOfPage, search, categoryId, minPrice, maxPrice);
		List<Category> categories = productDAO.getListCategories();
		int totalProduct = productDAO.getTotalProduct(search, categoryId, minPrice, maxPrice);
		String sortBy = request.getParameter("sort");
		String sort = "";
		System.out.println(sortBy);
		if(sortBy==null) {
			sortBy="random";
			sort="Không sắp xếp";
		}
		if(sortBy.equals("random")) {
			sort="Không sắp xếp";
		}if(sortBy.equals("high-to-low")) {
			Collections.sort(results, new SortPriceDESC());
			sort="Giá cao → Giá thấp";
		
		}if(sortBy.equals("low-to-high")) {
			Collections.sort(results, new SortPriceASC());
			sort="Giá thấp → Giá cao";
		}
		if(sortBy.equals("popularty")) {
			Collections.sort(results, new SortPopular());
			sort="Phổ biến nhất";
		}
		if(cateString!=null) {
			params = "category_id";
			argument = cateString;
		}else {
			if(search!=null) {
				params = "search";
				argument = search;
			}else {
				if(minString!= null && maxString!=null) {
					params = "min-price="+minString+"&max-price="+maxString;
				}
			}
		}
		//Lay duong dan cuoi cung
		HistoryUrl historyUrl = (HistoryUrl)getServletContext().getAttribute("urlDAO");
	    historyUrl.saveHistoryUrl(request);
//	    System.out.println(urlDAO.getUrlLast());
		
		request.setAttribute("sortTitle", sort);
		request.setAttribute("argument", argument);
		request.setAttribute("params", params);
		request.setAttribute("categories", categories);
		request.setAttribute("response", results);
		request.setAttribute("pageCurrent", page);
		request.setAttribute("numberOfPage", (int) Math.floor(totalProduct/productOfPage)+1);
		request.getRequestDispatcher("shop.jsp").forward(request, response);
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
