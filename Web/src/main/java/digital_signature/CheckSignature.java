package digital_signature;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DigitalSignatureDAO;
import dao.ProductDAO;
import io.WritePDF;
import model.DigitalSignature;
import model.Orders;

/**
 * Servlet implementation class CheckSignature
 */
@WebServlet("/CheckSignature")
public class CheckSignature extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckSignature() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		ProductDAO productDAO = (ProductDAO) getServletContext().getAttribute("productDAO");
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		int idUser = Integer.parseInt(request.getParameter("userId"));
		int parent = Integer.parseInt(request.getParameter("parent"));
		DigitalSignature digitalSignature = digitalSignatureDAO.getDigitalSignature(idUser, parent);
		Orders orders = productDAO.getOrderByParentAndStatusActive(parent, "public");
		String fileInvoice = orders.getFileInvoice() + ".pdf";
		try {
			RSACipher rsaCipher = new RSACipher();
			PublicKey publicKey = rsaCipher.publicKeyType(digitalSignature.getPublicKey());
			String hashInvoice = new Hash().hashFile(WritePDF.PATH + fileInvoice);
			String hashDecrypt = rsaCipher.decryptText(digitalSignature.getSignture(), publicKey);
			if (hashInvoice.equals(hashDecrypt)) {
				digitalSignatureDAO.updateStatus(idUser, parent, "SUCCESS");
				response.getWriter().print("OK");
			} else {
				digitalSignatureDAO.updateStatus(idUser, parent, "FAIL");
				response.getWriter().print("Fail");
			}
		} catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
				| InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
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
