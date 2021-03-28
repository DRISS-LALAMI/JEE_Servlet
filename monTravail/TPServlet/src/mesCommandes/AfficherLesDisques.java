package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AfficherLesDisques
 */
public class AfficherLesDisques extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherLesDisques() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String nom = null;
    	Cookie[] cookies = request.getCookies();
    	nom = Identification.chercheNom(cookies);
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	out.println("<html>");
    	out.println("<body>");
    	out.println("<head>");
    	out.println("<title> Commande de disques </title>");
    	out.println("</head>");
    	out.println("<body bgcolor=\"white\">");
    	out.println("<h3>" + "Bonjour " + nom + " vous pouvez commander un disque" + "</h3>");
    	Stock.vente(out);
    	out.println("</body>");
    	out.println("</html>");
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
