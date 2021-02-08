package Main;


import javax.swing.JFrame;

import Controleur.ControlMoto;
import Modele.AvanceeRoute;
import Modele.Moto;
import Modele.Route;
import Vue.AffichageJeu;



public class Main {

	public static void main(String [] args) {
		
		JFrame fenetre = new JFrame("Course de moto");

		Moto moto = new Moto();
		Route route = new Route(moto);
		
		AffichageJeu affichageJeu = new AffichageJeu(moto, route);
		fenetre.add(affichageJeu);
		affichageJeu.setFocusable(true);
		
		ControlMoto control = new ControlMoto(moto);
		affichageJeu.addKeyListener(control);
		
		AvanceeRoute ar = new AvanceeRoute(route);
		ar.start();
		
		
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}
