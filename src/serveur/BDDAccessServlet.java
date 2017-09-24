package serveur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

/**
 * Servlet implementation class BDDAccessServlet
 */

public class BDDAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BDDAccessServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String requestURL = request.getRemoteAddr();
		Date currentTime = new Date();

        String idProduit = request.getParameter("idProduit");
        Integer quantite = Integer.valueOf(request.getParameter("quantite"));
        String action = request.getParameter("action");
        
        String logLine = "--->> "+ currentTime.getHours()+":"+currentTime.getMinutes()+" "+ requestURL+"  : idProduit = "+idProduit+"; quantite = "+quantite+"; action = "+action;
        System.out.println(logLine);
        
        logLine += "\n";
        
        try {
            Files.write(Paths.get("D:/CaissePress2Play.log"), logLine.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        
    	DAO dao = new DAO(); 
    	dao.ouvrir();
    	if(action.equals("add")){
      	  	dao.updateProduct(idProduit, quantite, true);
    	}else{
      	  	dao.updateProduct(idProduit, quantite, false);
    	}

    	dao.fermer();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
