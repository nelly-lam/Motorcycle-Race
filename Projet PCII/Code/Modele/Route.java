package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import Vue.AffichageJeu;


public class Route extends Observable{
	
	/****************CONSTANTES****************/
	public final static int POSITIONXMOTO = 100;
	public final static int POSITIONHORIZON = 180;
	
	public final static int LARGEURMINROUTE = 50;
	public final static int LARGEURMAXROUTE = 200;
	
	public final static int HAUTEURMINROUTE = 100;
	public final static int HAUTEURMAXROUTE = 150;
	
	public final static int bordureMinX = 150;
	public final static int plageLargeurRoute = LARGEURMAXROUTE - LARGEURMINROUTE;
	
	public final static int departXGauche = Moto.POSITIONXMOTO - LARGEURMAXROUTE/2 - 40;
	
	public final static int TAILLEAVANCEE = 10;
	public final static int ACCELERATION = 5;
	
	public final static int VITESSEMAX = 300;
	public final static int graduationKilometre = 1000; //(1000 pixels en ordonnee = 1km)
	
	public final static int LARGCHECKPOINT = 30;
	
	/****************ATTRIBUTS****************/
	private ArrayList<Point> listePointsG;
	private ArrayList<Point> listePointsD;
	private Moto moto;
	private int horizon;
	//score = nombre de kilometre parcourus
	private int kilometre;
	
	private ArrayList<Point> listeObstacles;
	private ArrayList<Checkpoint> listeCheckpoints;
	
	
	/****************CONSTRUCTEUR****************/
	public Route(Moto m) {
		this.kilometre = 0;
		this.horizon = POSITIONHORIZON;
		this.moto = m;
		this.listePointsG = new ArrayList<Point>();
		this.listePointsD = new ArrayList<Point>();
		
		Point departG1 = new Point(departXGauche, Vue.AffichageJeu.HAUTAFFICHAGE);
		this.listePointsG.add(departG1);
		Point departG2 = new Point(departXGauche, Vue.AffichageJeu.HAUTAFFICHAGE - 200);
		this.listePointsG.add(departG2);
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE GAUCHE/////////////////////////////////
		int xG = (departG2.x);
		int yG = (departG2.y);
		Random r = new Random();
		
		//int profondeur = 0;
		
		while(yG > -30) {
			xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			//a chaque creation d'ordonnee, on decremente
			yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			Point newPoint = new Point(xG, yG);
			listePointsG.add(newPoint);
		}
		
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE DROIT/////////////////////////////////
		int xD = 0;
		int yD = 0;
		
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			xD = (this.getListePointsG().get(i).x + LARGEURMAXROUTE + 50);
			//System.out.printf("Point abscisse %d gauche recupere : %d", i, this.getListePointsG().get(i).getX());
			yD = this.getListePointsG().get(i).y;
			Point newPoint = new Point(xD, yD);
			this.getListePointsD().add(newPoint);
		}
		
		this.affiche_liste(this.getListePointsG(), "gauche");
		this.affiche_liste(this.getListePointsD(), "droit");
		
		
		///////////////////////////INITIALISATION DES OBSTACLES/////////////////////////////////
		
		this.listeObstacles = new ArrayList<Point>();
		Random rObstacle = new Random();
		for (int i = 0; i < 4; i++) {
			this.getListeObstacles().add(new Point(rObstacle.nextInt(AffichageJeu.LARGAFFICHAGE), rObstacle.nextInt(AffichageJeu.HAUTAFFICHAGE - POSITIONXMOTO - POSITIONHORIZON) + POSITIONHORIZON));
		}
		this.affiche_liste(this.getListeObstacles(), "obstacles");
		
		
		///////////////////////////INITIALISATION DES CHECKPOINTS/////////////////////////////////
		this.listeCheckpoints = new ArrayList<Checkpoint>();

		
	}
	
	

	/****************METHODES****************/
	public ArrayList<Point> getListePointsG() { return listePointsG; }
	public ArrayList<Point> getListePointsD() { return listePointsD; }
	public int getKilometre() { return kilometre; }
	public int getHorizon() { return horizon; }
	public Moto getMoto() { return moto; }
	
	/**
	 * methode getPoint() : renvoie le Point situe a l'indice i de l'ArrayList listePoints
	 * @param i un indice int
	 * @return Point
	 */
	public Point getPointG(int i) { return this.listePointsG.get(i); }
	
	public void setListePointsG(ArrayList<Point> listePoints) { this.listePointsG = listePoints; }
	public void setListePointsD(ArrayList<Point> listePointsParallele) { 
		this.listePointsD = listePointsParallele; 
	}
	public void setKilometre(int kilometre) { this.kilometre = kilometre; }
	public void setHorizon(int horizon) { this.horizon = horizon; }
	public void setMoto(Moto moto) { this.moto = moto; }

	public ArrayList<Point> getListeObstacles() {
		return listeObstacles;
	}
	public void setListeObstacles(ArrayList<Point> listeObstacles) {
		this.listeObstacles = listeObstacles;
	}
	public ArrayList<Checkpoint> getListeCheckpoints() {
		return listeCheckpoints;
	}
	public void setListeCheckpoints(ArrayList<Checkpoint> listeCheckPoints) {
		this.listeCheckpoints = listeCheckPoints;
	}
	
	/**
	 * methode setPointG() : modifie le Point situe a l'indice i de l'ArrayList listePointsG
	 * @param i un indice int
	 * @param pt le Point a substituer
	 */
	public void setPointAtG(int i, Point pt) {
		this.listePointsG.set(i, pt);
	}
	
	/**
	 * methode setPointD() : modifie le Point situe a l'indice i de l'ArrayList listePointsD
	 * @param i un indice int
	 * @param pt le Point a substituer
	 */
	public void setPointAtD(int i, Point pt) {
		this.listePointsD.set(i, pt);
	}
	
	/**
	 * Methode setPoint : modifir le Point situe a l'indice i de l'ArrayList l
	 * @param l une ArrayList<Point> : la liste a modifier
	 * @param i un int : indice
	 * @param pt un Point
	 */
	public void setPoint(ArrayList<Point> l, int i, Point pt) {
		l.set(i, pt);
	}
	
	/**
	 * Methode affiche_liste(): Affiche les Points qui composent une liste
	 * @param list un ArrayList<Point>
	 * @param str un String : le nom de la liste
	 */
	public void affiche_liste(ArrayList<Point> list, String str) {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("Point %s %d = (%d, %d)\n", str, i, list.get(i).x, 
															list.get(i).y);
		}
	}
	
	
	/**
	 * methode avanceRoute() :
	 * modifie les listes listesPointsG et listesPointsG en incrementant leur ordonnee
	 */
	public void avanceRoute() {
		int x;
		int y;
		
		//deplacement des points de la ligne gauche
		ArrayList<Point> newListeG = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			x = (int) (this.getListePointsG().get(i).x);
			y = (int) (this.getListePointsG().get(i).y + TAILLEAVANCEE);
			newListeG.add(new Point(x, y));
		}
		this.setListePointsG(newListeG);
		
		//deplacement des points de la ligne droite
		ArrayList<Point> newListeD = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsD().size(); i++) {
			x = (int) (this.getListePointsD().get(i).x);
			y = (int) (this.getListePointsD().get(i).y + TAILLEAVANCEE);
			newListeD.add(new Point(x, y));
		}
		this.setListePointsD(newListeD);
		
		this.notifyObservers();
	}
	
	/**
	 * methode removePointInvisible() : modifie la listePoints en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage
	 */
	public void removePointInvisible() {
		//si le premier Point et le deuxieme Point sont en dehors de l'affichage
		if(this.getListePointsG().get(0).y > Vue.AffichageJeu.HAUTAFFICHAGE && 
				this.getListePointsG().get(1).y > Vue.AffichageJeu.HAUTAFFICHAGE) {
			//on retire le premier Point de la listePoints
			this.getListePointsG().remove(0);
			this.getListePointsD().remove(0);
			this.notifyObservers();
		}
	}
	
	
	/**
	 * methode addPointInvisible() : modifie la listePoints en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisible() {
		
		//si l'ordonnee du dernier Point de listePointsG est superieure a -30
		if(this.getListePointsG().get(this.getListePointsG().size()-1).y > -30) {
			Random r = new Random();
			int xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			
			//a chaque creation d'ordonnee, on decremente
			//int yG = (int) this.getListePointsG().get(this.getListePointsG().size()-1).getY();
			int yG = this.getPointG(this.getListePointsG().size()-1).y;
			yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			System.out.printf("ordonnee cree : %d\n", yG);
			this.listePointsG.add(new Point(xG, yG));

			int xD = (this.getListePointsG().get(this.getListePointsG().size()-1).x + LARGEURMAXROUTE + 80);
			int yD = this.getListePointsG().get(this.getListePointsG().size()-1).y;
			this.listePointsD.add(new Point(xD, yD));

		}
	}
	
	
	
	/**
	 * methode addPointInvisible() : modifie la listePoints en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisible2() {

		//si l'ordonnee du dernier Point de listePointsG est superieure a la position de l'horizon
		if(this.getListePointsG().get(this.getListePointsG().size()-1).y > POSITIONHORIZON) {
			//System.out.printf("y : %d\n", this.getListePointsG().get(this.getListePointsG().size()-1).y);

			Random r = new Random();
			int xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			
			//a chaque creation d'ordonnee, on decremente
			//int yG = (int) this.getListePointsG().get(this.getListePointsG().size()-1).getY();
			int yG = this.getPointG(this.getListePointsG().size()-1).y;
			//yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			yG =  1;
			//System.out.printf("ordonnee before : %d\n", this.getListePointsG().size());

			this.listePointsG.add(new Point(xG, yG));
			//System.out.printf("ordonnee after : %d\n", this.getListePointsG().size());
			
			int yD = yG;
			//this.getListePointsG().get(this.getListePointsG().size()-1).y;
			this.getListePointsD().add(new Point(xG + LARGEURMAXROUTE, yD));
			//System.out.printf("ordonnee cree : %d\n", this.getListePointsG().size());
			this.notifyObservers();
		}
	}
	
		
	/**
	 * Methode avanceObstacles() : 
	 * modifie la liste des obstacles listeObstacles en incrementant leur ordonnee
	 */
	public void avanceObstacles() {
		int x, y;
		ArrayList<Point> newListe = new ArrayList<Point>();
		for(int i = 0; i < this.getListeObstacles().size(); i++) {
			x = (int) (this.getListeObstacles().get(i).x);
			y = (int) (this.getListeObstacles().get(i).y + TAILLEAVANCEE);
			newListe.add(new Point(x, y));
		}
		this.setListeObstacles(newListe);
		this.notifyObservers();
	}
	
	
	/**
	 * methode removePointInvisibleObstacles() : modifie la listeObstacles en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage
	 */
	public void removePointInvisibleObstacles() {
		//si le premier Point et le deuxieme Point sont en dehors de l'affichage
		if(this.getListeObstacles().get(0).y > Vue.AffichageJeu.HAUTAFFICHAGE && 
				this.getListeObstacles().get(1).y > Vue.AffichageJeu.HAUTAFFICHAGE) {
			//on retire le premier Point de la listePoints
			this.getListeObstacles().remove(0);
			this.getListeObstacles().remove(0);
			this.notifyObservers();
		}
	}
	
	/**
	 * methode addPointInvisibleObstacles() : modifie la listeObstacle en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisibleObstacles() {
		//si l'ordonnee du dernier Point de listeObstacles est superieure a la position de l'horizon
		if(this.getListeObstacles().get(this.getListeObstacles().size()-1).y > POSITIONHORIZON) {
			//System.out.printf("y : %d\n", this.getListeObstacles().get(this.getListeObstacles().size()-1).y);

			Random r = new Random();
			int x = r.nextInt(AffichageJeu.LARGAFFICHAGE);
			int y = r.nextInt(POSITIONHORIZON);
			this.getListeObstacles().add(new Point(x, y));
			
			this.notifyObservers();
		}
	}

	
	public void avanceKilometre() {
		this.setKilometre(this.getKilometre()+TAILLEAVANCEE);
		System.out.printf("Kilometres parcourus = %d\n", this.getKilometre());
	}

	
	
//////////////////////////////////// GESTION VITESSE MOTO /////////////////////////////////////////	
	
	
	/**
	 * methode iBetween() :
	 * @param p1 un Point
	 * @param p2 un Point
	 * @return true si la moto se trouve entre les abscisses des deux points p1 (inclus) et p2 (exclus), 
	 * 			false sinon
	 */
	public boolean isBetween(Point p1, Point p2) {
        if(( p1.getX() <= this.getMoto().getPositionX())
        		&& this.getMoto().getPositionX() < p2.x) {
        	return true;
        }
        return false;
	}
	
	
	/**
	 * methode abscisseNiveauMotoG() :
	 * @return l'abscisse du Point qui se trouve sur le segment gauche de la route 
	 * 			(au niveau de la moto) par rapport a l'ordonnee de la moto	
	 */
	public float abscisseNiveauMotoG() {
		int indice = 0;

		/* recuperation des Points les plus proches de la moto
		 * p1 le Point en dessous, p2 le Point au dessus
		 */
		Point p1 = new Point(this.getListePointsG().get(indice));
		Point p2 = new Point(this.getListePointsG().get(indice+1));
		if(!isBetween(p1, p2)) {
			p1 = new Point(this.getListePointsG().get(indice+1));
			p2 = new Point(this.getListePointsG().get(indice+2));
		}
		
		/*coefficient du segment au niveau de la moto*/
		float coef = (float) ( (p2.y - p1.y)
					/ (float) (p2.x - (float) p1.x) );
		//System.out.printf("COEF gauche : %f\n", coef);
		
		//abscisse situee sur le segmentG et dont l'ordonnee correspond a la positionY de la moto
		
		//Ordonnee a l'origine b
		float b = p1.y - coef * p1.x;
		//y = coef * result + b -> result = (y-b) / coef
		float result = (this.getMoto().getPositionY() - b) / coef;
		//float result = (this.getMoto().getPositionY() - Moto.POSITIONXMOTO) / coef;
		//System.out.printf("abscisse route gauche : %f\n", result);
		return result;
	}
	
	
	/**
	 * methode abscisseNiveauMotoG() :
	 * @return l'abscisse du Point qui se trouve sur le segment droit de la route 
	 * 			(au niveau de la moto) par rapport a l'ordonnee de la moto	
	 */
	public float abscisseNiveauMotoD() {
		int indice = 0;
		/* recuperation des Points les plus proches de la moto
		 * p1 le Point en dessous, p2 le Point au dessus
		 */
		Point p1 = new Point(this.getListePointsD().get(indice));
		Point p2 = new Point(this.getListePointsD().get(indice+1));
		if(!isBetween(p1, p2)) {
			p1 = new Point(this.getListePointsD().get(indice+1));
			p2 = new Point(this.getListePointsD().get(indice+2));
		}
		
		/*coefficient du segment au niveau de la moto*/
		float coef = (float) ( (p2.y - p1.y)
					/ (float) (p2.x - (float) p1.x) );
		//System.out.printf("COEF droit : %f\n", coef);
		
		//abscisse situee sur le segmentD et dont l'ordonnee correspond a la positionY de la moto

		//Ordonnee a l'origine b
		float b = p1.y - coef * p1.x;
		//y = coef * result + b -> result = (y-b) / coef
		float result = (this.getMoto().getPositionY() - b) / coef;
		//System.out.printf("abscisse route droit : %f\n", result);
		return result;
	}
	
	/**
	 * methode estDansRoute():
	 * verifie que l'abscisse de la moto est entre les traces de la route
	 * @return renvoie true si la moto est bien sur la route,
	 * 			false sinon
	 */
	public boolean estDansRoute() {
		if(this.abscisseNiveauMotoG() < this.getMoto().getPositionX() 
				&& this.getMoto().getPositionX() < this.abscisseNiveauMotoD()) {
			//System.out.printf("True! abscisseG=%f < La moto=%d < abscisseD=%f\n", this.abscisseNiveauMotoG(), this.getMoto().getPositionX(), this.abscisseNiveauMotoD());
			return true;
		} else if(this.abscisseNiveauMotoG() > this.getMoto().getPositionX()
					|| this.getMoto().getPositionX() > this.abscisseNiveauMotoD()) {
			//System.out.printf("False! abscisseG=%f > La moto=%d > abscisseD=%f\n", this.abscisseNiveauMotoG(), this.getMoto().getPositionX(), this.abscisseNiveauMotoD());
			return false;
		}
		return false;
	}
	
	
	public float calculVitesse(int seconde) {
		return this.getKilometre()/seconde;
	}

	
	/**
	 * methode updateVitesseMoto():
	 * incremente la vitesse de la moto de ACCELERATION si elle est sur la route,
	 * decremente sinon
	 */
	public void updateVitesseMoto() {
		if(this.estDansRoute()) {
			if(this.getMoto().getVitesse() < VITESSEMAX) { //tant que la vitesse max n'est pas atteint
				this.getMoto().setVitesse(this.getMoto().getVitesse() + ACCELERATION);
				this.notifyObservers();
				//System.out.printf("acceleration : %f\n", this.getMoto().getVitesse());
			}
		}else{
			if(this.getMoto().getVitesse() > 0) {
				this.getMoto().setVitesse(this.getMoto().getVitesse() - ACCELERATION);
				this.notifyObservers();
				//System.out.printf("deceleration : %f\n", this.getMoto().getVitesse());
			}
		}
	}


	
	
	
///////////////////////////////////////////END GAME//////////////////////////////////////////////	

	/**
	 * Methode ifCollisionObstacles(): renvoie true si la moto touche un obstacle
	 * @return true si les coordonnees de la moto sont identiques a celles d'un obstacle, false sinon
	 */
	public boolean ifCollisionObstacles() {
		Point pt;
		int x, y;
		for(int i = 0; i < this.getListeObstacles().size(); i++) {
			x = (int) this.getListeObstacles().get(i).getX();
			y = (int) this.getListeObstacles().get(i).getY();
			pt = new Point(x, y);
			//System.out.printf("Point obstacle %d : (%d, %d)\n", i, x, y);
			//System.out.printf("Point moto : (%d, %d)\n", this.getMoto().getPositionX(), this.getMoto().getPositionY());
			//si les coordonnees de la moto == les coordonnes d'un obstacle
			if(this.getMoto().getHautGauche().getX() <= pt.x && pt.x <= this.getMoto().getHautDroit().getX()) {
				System.out.printf("Point obstacle %d : (%d, %d)\n", i, x, y);
				System.out.printf("Point moto : (%d, %d)\n", this.getMoto().getPositionX(), this.getMoto().getPositionY());
				if(this.getMoto().getHautGauche().getY() <= pt.y && pt.y <= this.getMoto().getBasGauche().getY()) {
					return true;
				}
			}
			/*
			if(pt.y == this.getMoto().getPositionY() && pt.x == this.getMoto().getPositionX()) {
				//System.out.printf("Point obstacle %d : (%d, %d)\n", i, x, y);
				//System.out.printf("Point moto : (%d, %d)\n", this.getMoto().getPositionX(), this.getMoto().getPositionY());
				//System.out.printf("Touche coule");
				return true;
			}*/
		}
		return false;
	}
	
	
	/**
	 * Methode ifVitesseNulle() : renvoie true si la vitesse de la moto est a zero
	 * @return true si vitesse = 0, false sinon
	 */
	public boolean ifVitesseNulle() {
		//Troisieme condition : si la vitesse de la moto est a null
		if(this.getMoto().getVitesse() == 0.) { //si la moto n'avance plus
			return true;
		}
		return false;
	}
	
	
	
}
