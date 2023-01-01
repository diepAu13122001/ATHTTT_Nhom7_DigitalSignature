package digital_signature;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import cart.ShoppingCart;
import dao.DigitalSignatureDAO;
import dao.HistoryUrl;
import dao.ProductDAO;
import io.WritePDF;
import model.Customer;
import model.Key;
import model.Product;

/**
 * Servlet implementation class CreateKey
 */
@WebServlet("/create-key")
public class CreateKey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateKey() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HistoryUrl historyUrl = (HistoryUrl) getServletContext().getAttribute("urlDAO");
		DigitalSignatureDAO digitalSignatureDAO = (DigitalSignatureDAO) getServletContext()
				.getAttribute("digitalSignatureDAO");
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("user");

		historyUrl.saveHistoryUrl(request);
		String keySize = request.getParameter("keySize");
		System.out.println("Key Size: " + keySize);
		RSACipher rsaCipher = new RSACipher();
		rsaCipher.setKeySize(Integer.parseInt(keySize));
		try {
			rsaCipher.createKey();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encodePublicKey = Base64.getEncoder().encodeToString(rsaCipher.getPublicKey().getEncoded());
		String encodePrivate = Base64.getEncoder().encodeToString(rsaCipher.getPrivateKey().getEncoded());
		String pathPublicKey = "public-key.key";
		String pathPrivateKey = "private-key.key";

		Key privaKey = new Key();
		privaKey.setKeyString(encodePrivate);
		privaKey.setUrlDownload(pathPrivateKey);

		Key publicKey = new Key();
		publicKey.setKeyString(encodePublicKey);
		publicKey.setUrlDownload(pathPublicKey);

		WritePDF.writeByte(pathPublicKey, rsaCipher.getPublicKey().getEncoded());
		WritePDF.writeByte(pathPrivateKey, rsaCipher.getPrivateKey().getEncoded());

		List<Key> responseJsons = new ArrayList<>();
		responseJsons.add(publicKey);
		responseJsons.add(privaKey);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		if (digitalSignatureDAO.checkExistKey(customer.getId())) {
			digitalSignatureDAO.updateActive(customer.getId());
		}
		boolean isInsert = digitalSignatureDAO.inserKeyUser(customer.getId(), encodePublicKey);
		if (isInsert) {
			String json = new Gson().toJson(responseJsons);
			response.getWriter().write(json);
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
