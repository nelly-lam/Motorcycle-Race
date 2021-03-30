package Main;

import Controleur.ControlMoto;
import Modele.AvanceeRoute;
import Modele.AvanceeTemps;
import Modele.Moto;
import Modele.MyTimer;
import Modele.Route;
import Vue.AffichageJeu;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String [] args) {
		
		Moto moto = new Moto();
		Route route = new Route(moto);
		//MyTimer temps = new MyTimer(AcanceeTemps.tempsCheckPoint);
		AvanceeTemps temps = new AvanceeTemps(route);
		temps.start();
		AvanceeRoute ar = new AvanceeRoute(route);
		ar.start();
		
		AffichageJeu affichageJeu = new AffichageJeu(moto, route, temps, ar);
		
		ControlMoto controlMoto = new ControlMoto(moto, temps, ar);
		affichageJeu.addKeyListener(controlMoto);
		
		if(!temps.getRun()) {
			ar.stop();
		}
		if(!ar.getRun()) {
			temps.stop();
		}

	}
	
}
