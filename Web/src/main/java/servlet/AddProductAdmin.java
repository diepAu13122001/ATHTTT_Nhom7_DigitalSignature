package servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import dao.ProductDAO;
import model.Category;
import model.IDRandom;


/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/admin/add-product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddProductAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadFiles";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Upload file
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		//Lay danh sach loai san pham
		ServletContext context = getServletContext();
		List<Category> categories =  productDAO.getListCategories();
		context.setAttribute("categories", categories);
		String fileNames = "";
		// gets absolute path of the web application
		// constructs path of the directory to save uploaded file
		try {
		String savePath = "D:\\Workspace\\Java\\DoAn_CuoiKy_LTWeb\\src\\main\\webapp\\images\\image-product";
//		String savePath = appPath + File.separator + SAVE_DIR;
		// creates the save directory if it does not exists
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		for (Part part : request.getParts()) {
			if (part.getContentType() != null) {
				String fileName = extractFileName(part);
				fileNames += "," + fileName;
			part.write(savePath + File.separator + fileName);
			}
		}
		//Them san pham vao csdl
		IDRandom random = new IDRandom();
		String id = "SP"+random.getIDRandom();
		String name = request.getParameter("name-product");
		String des = request.getParameter("des-product");
		double price = Double.parseDouble(request.getParameter("price-product"));
		String category = request.getParameter("category-product");
//		
		String[] split = fileNames.trim().split(",");
		String linkI = split[1];
		String linkL = split[2] + " " + split[3];
//		System.out.println(fileNames);
//		System.out.println(name);
//		System.out.println(des);
//		System.out.println(price);
//		System.out.println(category);
//		System.out.println("Link Image: " + linkI);
//		System.out.println("Link List: " + linkL);
		productDAO.insertProduct(id, name, des, price, linkI, category, linkL);
		
		}catch(Exception i) {
			System.out.println("Error !!!");
		}
		request.getRequestDispatcher("addproduct.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
