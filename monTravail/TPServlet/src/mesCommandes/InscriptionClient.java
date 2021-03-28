package mesCommandes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InscriptionClient
 */
public class InscriptionClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InscriptionClient() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomRecu=null, motPasseRecu=null;
		String nomCookie=null, motPasseCookie=null;
		
		// initialisation cookies et paramètres
		nomRecu=request.getParameter("nom");
		motPasseRecu=request.getParameter("motdepasse");
		try {
			Cookie[] cookies = request.getCookies();
			nomCookie = Arrays.stream(cookies).filter(t -> "nom".equals(t.getName())).findFirst().get().getValue();
			motPasseCookie = Arrays.stream(cookies).filter(t -> "motdepasse".equals(t.getName())).findFirst().get()
					.getValue();
		} catch (Exception e) {	}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (nomCookie==null && nomRecu==null){
		// Cas 1 : cas où il n'y a ni de cookies ni de parametres
		formulaire(nomRecu, nomCookie,  motPasseRecu , motPasseCookie, out);		
		System.out.println("Cas1");
		}
		else if (nomCookie==null && nomRecu!=null){
			// Cas 2 : cas où il n'y a pas de cookies mais les paramètres nom et mot de passes sont présents :
			Cookie cookie1 = new Cookie("nom", nomRecu);
			Cookie cookie2= new Cookie("motdepasse", motPasseRecu);
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			formulaire(nomRecu, nomCookie,  motPasseRecu , motPasseCookie, out);
			System.out.println("Cas2");
			}
			else if (identique(nomRecu,nomCookie) && identique(motPasseRecu,motPasseCookie))	
			{
				// Cas 4 : cas où le nom et le mot passe sont correctes, appel à la servlet achat
				response.sendRedirect("achat");
				System.out.println("Cas4");
			}
			else {
				// Cas 3 : les cookies sont présents demande de s'identifier;
				formulaire(nomRecu, nomCookie,  motPasseRecu , motPasseCookie, out);
				System.out.println("Cas3");
			}
		}
	
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	boolean identique (String recu, String cookie) {
		return ((recu != null) && (recu.length() >3) && (cookie != null) && (recu.equals(cookie) ));
		}

	void formulaire(String nomRecu,String nomCookie, String motPasseRecu , String motPasseCookie, PrintWriter out) throws IOException {
		
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title> inscription d'un client </title>");
		out.println("</head>");
		out.println("<body bgcolor='white' >");
		out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
		out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
		out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
		out.print(" <form action='sinscrire' method='GET' > ");
		out.println("nom");
		out.println("<input type='text' size='20' name='nom' >");
		out.println("<br>");
		out.println("mot de passe");
		out.println("<input type='password' size='20' name='motdepasse'> <br>");
		out.println("<input type='submit' value='inscription'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	
	}
	}
