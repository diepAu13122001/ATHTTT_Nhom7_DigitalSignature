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
import java.util.Base64;

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
import dao.ProductDAO;
import dao.HistoryUrl;
import model.Customer;
import model.DigitalSignature;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/handle-authentication")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static byte[][] byteUpload = new byte[2][];

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
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		HttpSession session = request.getSession();
		String filePublicKey = request.getParameter("pk-base64");
		String invoice = request.getParameter("invoice");
		String digitalSignature = request.getParameter("signature");
		int index = 0;
		for (Part part : request.getParts()) {
			InputStream inputStream = part.getInputStream();
			String fileName = extractFileName(part);
			if (!fileName.isEmpty()) {
				byteUpload[index] = inputStream.readAllBytes();
				index++;
			}
		}

		try {
			RSACipher rsaCipher = new RSACipher();
			PublicKey publicKey;
			if (filePublicKey != null) {
				publicKey = rsaCipher.publicKeyType(filePublicKey);
			} else {
				publicKey = rsaCipher.publicKeyFile(byteUpload[0]);
			}
			String publickeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());

			Customer customer = (Customer) session.getAttribute("user");
			String split[] = invoice.split("_");
			int idOrder = Integer.parseInt(split[split.length - 1]);
			DigitalSignature ds = digitalSignatureDAO.getDigitalSignature(customer.getId(), idOrder);
			boolean isUpdate = false;
			if (digitalSignatureDAO.checkExistPublicKey(customer.getId(), publickeyStr)) {
				if (ds != null) {
					isUpdate = digitalSignatureDAO.updateDigitalSignature(customer.getId(), idOrder, digitalSignature,
							publickeyStr);
				} else {
					isUpdate = digitalSignatureDAO.inserDigitalSignature(customer.getId(), idOrder, digitalSignature,
							publickeyStr);
				}

				if (isUpdate) {
					productDAO.updateStatusOrder(idOrder, "public", "PR");
					response.sendRedirect("process-order.jsp");
				}

			} else {
				request.setAttribute("status", "Public Key kh??ng t???n t???i");
				request.getRequestDispatcher("authentication.jsp?invoice=" + invoice).forward(request, response);
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalArgumentException e) {
			e.printStackTrace();
			request.setAttribute("status", "Ch??? k?? ho???c public key kh??ng h???p l???");
			request.getRequestDispatcher("authentication.jsp?invoice=" + invoice).forward(request, response);
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
