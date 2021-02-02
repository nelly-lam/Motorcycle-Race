package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Route {
	
	/****************CONSTANTES****************/
	public final static int POSITIONXMOTO = 100;
	public final static int POSITIONHORIZON = 180;
	
	public final static int LARGEURMINROUTE = 50;
	public final static int LARGEURMAXROUTE = 150;
	
	public final static int HAUTEURMINROUTE = 50;
	public final static int HAUTEURMAXROUTE = 150;
	
	public final static int bordureMinX = 150;
	public final static int plageLargeurRoute = LARGEURMAXROUTE - LARGEURMINROUTE;
	
	public final static int departXDroit = Moto.POSITIONXMOTO + LARGEURMAXROUTE/2;
	public final static int departXGauche = Moto.POSITIONXMOTO - LARGEURMAXROUTE/2;
	
	public final static int TAILLEAVANCEE = 10;
	
	/****************ATTRIBUTS****************/
	private ArrayList<Point> listePointsG;
	private ArrayList<Point> listePointsD;
	private int horizon;
	private int kilometre;
	
	
	/****************CONSTRUCTEUR****************/
	public Route() {
		this.kilometre = 0;
		this.horizon = POSITIONHORIZON;
		this.listePointsG = new ArrayList<Point>();
		this.listePointsD = new ArrayList<Point>();
		
		Point departG = new Point(departXGauche, Vue.AffichageJeu.HAUTAFFICHAGE);
		this.listePointsG.add(departG);
		Point departD = new Point(departXDroit, Vue.AffichageJeu.HAUTAFFICHAGE);
		this.listePointsD.add(departD);
		
		int xG = (int) (departG.getX());
		int yG = (int) (departG.getY());
		Random r = new Random();
		
		//int profondeur = 0;
		
		while(yG > POSITIONHORIZON) {
			xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			//a chaque creation d'ordonnee, on decremente
			yG = yG - r.nextInt(HAUTEURMAXROUTE - HAUTEURMINROUTE);
			Point newPoint = new Point(xG, yG);
			listePointsG.add(newPoint);
		}
		
		/*
		if(this.getListePointsG().get(this.getListePointsG().size()-1).getY() < POSITIONHORIZON) {
			listePointsG.remove(this.getListePointsG().size()-1);
		}
		//ajout en dernier du point sur l'horizon
		listePointsG.add(new Point(r.nextInt(plageLargeurRoute) + bordureMinX, POSITIONHORIZON));
		*/
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE DROIT/////////////////////////////////
		int xD = 0;
		int yD = 0;
		
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			xD = (int) (this.getListePointsG().get(i).getX() + LARGEURMAXROUTE + 80);
			yD = (int) this.getListePointsG().get(i).getY();
			Point newPoint = new Point(xD, yD);
			this.getListePointsD().add(newPoint);
		}
		this.affiche_listePoints();
		
	}
	
	

	/****************METHODES****************/
	public ArrayList<Point> getListePointsG() { return listePointsG; }
	public ArrayList<Point> getListePointsD() { return listePointsD; }
	public int getKilometre() { return kilometre; }
	public int getHorizon() { return horizon; }

	public void setListePointsG(ArrayList<Point> listePoints) { this.listePointsG = listePoints; }
	public void setListePointsD(ArrayList<Point> listePointsParallele) { 
		this.listePointsD = listePointsParallele; 
	}
	public void setKilometre(int kilometre) { this.kilometre = kilometre; }
	public void setHorizon(int horizon) { this.horizon = horizon; }

	
	/**
	 * methode affiche_listePoints() : affichage d'une liste de Points
	 */
	public void affiche_listePoints() {
		for (int i = 0; i < this.getListePointsG().size(); i++) {
			System.out.printf("Point %d = (%f, %f)\n", i, this.getListePointsG().get(i).getX(), 
															this.getListePointsG().get(i).getY());
		}
	}
	
	
	
	/**
	 * methode avanceRoute() :
	 * modifie les listes listesPointsG et listesPointsG
	 */
	public void avanceRoute() {
		int x;
		int y;
		
		//deplacement des points de la ligne gauche
		ArrayList<Point> newListeG = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsG().size()-1; i++) {
			x = (int) (this.getListePointsG().get(i).getX());
			y = (int) (this.getListePointsG().get(i).getY() + TAILLEAVANCEE);
			newListeG.add(new Point(x, y));
		}
		this.setListePointsG(newListeG);
		
		//deplacement des points de la ligne droite
		ArrayList<Point> newListeD = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsD().size()-1; i++) {
			x = (int) (this.getListePointsD().get(i).getX());
			y = (int) (this.getListePointsD().get(i).getY() + TAILLEAVANCEE);
			newListeD.add(new Point(x, y));
		}
		this.setListePointsD(newListeD);
		
		this.setKilometre(this.getKilometre() + 1);
		
		
	}
	
	
	
	/**
	 * methode removePointInvisible() : modifie la listePoints en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage
	 */
	public void removePointInvisible() {
		//si le premier Point et le deuxieme Point sont en dehors de l'affichage
		if(this.getListePointsG().get(0).getY() > Vue.AffichageJeu.HAUTAFFICHAGE && 
				this.getListePointsG().get(1).getY() > Vue.AffichageJeu.HAUTAFFICHAGE) {
			//on retire le premier Point de la listePoints
			this.getListePointsG().remove(0);
			this.getListePointsD().remove(0);
		}
	}
	
	
	/**
	 * methode addPointInvisible() : modifie la listePoints en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisible() {
		
		//si l'ordonnee du dernier Point de listePointsG est superieure a l'horizon + 30(marge)
		if((int) this.getListePointsG().get(this.getListePointsG().size()-1).getY() > POSITIONHORIZON - 50) {
			
			Random r = new Random();
			int xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			
			//a chaque creation d'ordonnee, on decremente
			int yG = (int) this.getListePointsG().get(this.getListePointsG().size()-1).getY();
			yG = yG - r.nextInt(HAUTEURMAXROUTE - HAUTEURMINROUTE);
			Point newPointG = new Point(xG, yG);
			this.getListePointsG().add(newPointG);
			
			int xD = (int) (this.getListePointsG().get(this.getListePointsG().size()-1).getX() + LARGEURMAXROUTE + 80);
			int yD = (int) this.getListePointsG().get(this.getListePointsG().size()-1).getY();
			Point newPointD = new Point(xD, yD);
			this.getListePointsD().add(newPointD);

		}
	}
	
	

}
