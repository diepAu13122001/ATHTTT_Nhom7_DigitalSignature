package servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import model.Product;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/admin/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateProduct extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		// Lay danh sach loai san pham

		ArrayList<String> fileNames = new ArrayList<String>();
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
					fileNames.add(fileName);
					part.write(savePath + File.separator + fileName);
				}else {
					
				}
			}
		} catch (Exception i) {
			System.out.println(i);
		}
		// Cap nhat du lieu

		String id = request.getParameter("idProduct");
		String name = request.getParameter("name-product");
		String des = request.getParameter("des-product");
		String price = request.getParameter("price-product");
		String category = request.getParameter("category-product");
		String linkI = "";
		String linkL = "";

//		if (fileNames.size() == 3) {
//			linkI = fileNames.get(0);
//			linkL = fileNames.get(1) + " " + fileNames.get(2);
//		} else {
//				Product p = productDAO.findById(id);
//				linkI = p.getLinkImage();
//				linkL = p.getLinkList();
////		
//			}
		
		productDAO.udpateProduct(id, name, des, price, linkI, category, linkL);

		response.sendRedirect("dataproduct"); 
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
