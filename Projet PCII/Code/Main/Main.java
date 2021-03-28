package Main;

import Controleur.ControlMoto;
import Modele.AvanceeRoute;
import Modele.Moto;
import Modele.MyTimer;
import Modele.Route;
import Vue.AffichageJeu;

public class Main {
	
	public final static long tempsCheckPoint = 20;

	public static void main(String [] args) {
		
		Moto moto = new Moto();
		Route route = new Route(moto);
		MyTimer temps = new MyTimer(tempsCheckPoint);
		AvanceeRoute ar = new AvanceeRoute(route);
		ar.start();
		
		AffichageJeu affichageJeu = new AffichageJeu(moto, route, temps, ar);
		
		ControlMoto controlMoto = new ControlMoto(moto, temps, ar);
		affichageJeu.addKeyListener(controlMoto);
		


	}
	
}
