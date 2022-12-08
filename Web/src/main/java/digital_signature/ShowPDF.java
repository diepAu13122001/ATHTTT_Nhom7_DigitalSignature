package digital_signature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.WritePDF;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/showPdf")
public class ShowPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String fileName = (String)context.getAttribute("fileNameInvoice");
		if(fileName !=null) {
			File file = new File(WritePDF.PATH+fileName);
		    response.setHeader("Content-Type",    getServletContext().getMimeType(file.getName()));
		    response.setHeader("Content-Length", String.valueOf(file.length()));
		    response.setHeader("Content-Disposition", "inline; filename=\""+fileName+"\"");
		    Files.copy(file.toPath(), response.getOutputStream());
		}
			
		
	  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
