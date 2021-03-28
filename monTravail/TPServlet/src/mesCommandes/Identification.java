package mesCommandes;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;

public class Identification {
	static String chercheNom (Cookie [] cookies) {
	if(cookies.length>0) {
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals("nom")) {
				return cookies[i].getValue();
			}
		}
	}
		return "inconnu"; 
	}
}
	

