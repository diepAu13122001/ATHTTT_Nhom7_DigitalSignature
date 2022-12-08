package digital_signature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import dao.DigitalSignatureDAO;
import model.Customer;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/handle-authentication")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static byte[] byteEncrypt;
	private static byte[] bytePubliKey;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
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
		String filePublicKey = request.getParameter("public-key");
		for (Part part : request.getParts()) {		
			InputStream inputStream = part.getInputStream();
			String fileName = extractFileName(part);
			
			if (!fileName.isEmpty()) {
				byteEncrypt = inputStream.readAllBytes();
			}
		}

		try {
			RSACipher rsaCipher = new RSACipher();
			PublicKey publicKey;
			publicKey = rsaCipher.publicKeyType(filePublicKey);
			String decryptHash = rsaCipher.decrypt(byteEncrypt, publicKey);
			
			DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO)getServletContext().getAttribute("digitalSignatureDAO");
			HttpSession session = request.getSession();
			Customer customer = (Customer)session.getAttribute("user");
			int idOrder = (int)session.getAttribute("idOrder");
			String hashString = digitalSignatureDAO.getHashString(customer.getId(), idOrder);
			System.out.println("Hash: "+hashString);
			if(hashString.equals(decryptHash)) {
				response.getWriter().append("Xác thực thành công").append(request.getContextPath());
			}else {
				response.getWriter().append("Xác thực thất bại").append(request.getContextPath());
			}
		} catch (InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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