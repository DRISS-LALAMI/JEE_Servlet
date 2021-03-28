package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CommanderUnDisque
 */
public class CommanderUnDisque extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommanderUnDisque() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = null;
		int nbreProduit = 0;
		Cookie[] cookies = request.getCookies();
		HttpSession session= request.getSession();
		nom = Identification.chercheNom(cookies);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	    Enumeration<String> names = session.getAttributeNames();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> votre commande </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<h3>" + "Bonjour "+ nom + " voici votre commande" + "</h3>");
		// Nombre de disques dans la session
				int nbDisques = Collections.list(names).size();
				// S'il ya des disques dans la session (panier)
				out.println("<ol>");
				if (nbDisques != 0) {
					for (int i = 1; i <= nbDisques; i++) {
						String nomDisque = (String) session.getAttribute("disque" + i);
						String[] infosDisque = Stock.chercheNom(nomDisque);
						out.println("<li>Disque" + " Nom=" + infosDisque[0] + " Prix=" + infosDisque[1] + "</li>");
					}
				}
				// si parametre ordre == ajouter affichage du disque à ajouter au panier
				try {
					if (request.getParameter("ordre").equals("ajouter")) {
						String code = request.getParameter("code");
						String[] disque = Stock.chercheCode(code);
						out.println("<li>Disque" + " Nom=" + disque[0] + " Prix=" + disque[1] + "</li>");
						// Incrementer le nombre de disques
						nbDisques++;
						session.setAttribute("disque" + nbDisques, disque[0]);
					}
				} catch (Exception e) {
				}
				out.println("</ol>");
		out.println("<A HREF=achat> Vous pouvez commandez un autre disque </A><br> ");
		out.println("<A HREF=enregistre> Vous pouvez enregistrer votre commande </A><br> ");
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
