package Main;

import Controleur.AvanceeRoute;
import Controleur.AvanceeTemps;
import Controleur.ControlMoto;
import Modele.Moto;
import Modele.Route;
import Vue.AffichageJeu;

public class Main {

	public static void main(String [] args) {
		
		Moto moto = new Moto();
		Route route = new Route(moto);
		
		AvanceeTemps temps = new AvanceeTemps(route);
		temps.start();
		AvanceeRoute ar = new AvanceeRoute(route);
		ar.start();
		
		AffichageJeu affichageJeu = new AffichageJeu(moto, route, temps, ar);
		
		ControlMoto controlMoto = new ControlMoto(moto, temps, ar);
		affichageJeu.addKeyListener(controlMoto);
		

	}
	
}
