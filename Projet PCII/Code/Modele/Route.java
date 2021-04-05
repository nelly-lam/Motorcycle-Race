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
	//Deux ArrayLists pour creer la route
	private ArrayList<Point> listePointsG;
	private ArrayList<Point> listePointsD;
	
	private Moto moto;
	private int horizon;
	
	//Le score = nombre de kilometre parcourus
	private int kilometre;
	
	//attribut servant a creer des Checkpoints au bout d'un certain kilometre
	private int compteurKilometre;
	
	private ArrayList<Point> listeObstacles;
	private ArrayList<Checkpoint> listeCheckpoints;
	
	private boolean checkpointTouched;
	
	
	/****************CONSTRUCTEUR****************/
	public Route(Moto m) {
		this.kilometre = 0;
		this.compteurKilometre = 0;
		this.horizon = POSITIONHORIZON;
		this.moto = m;
		this.listePointsG = new ArrayList<Point>();
		this.listePointsD = new ArrayList<Point>();
		this.checkpointTouched = false;
		
		//Les deux premiers points de la route
		Point departG1 = new Point(departXGauche, Vue.AffichageJeu.HAUTAFFICHAGE);
		this.listePointsG.add(departG1);
		Point departG2 = new Point(departXGauche, Vue.AffichageJeu.HAUTAFFICHAGE - 200);
		this.listePointsG.add(departG2);
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE GAUCHE/////////////////////////////////
		int xG = (departG2.x);
		int yG = (departG2.y);
		Random r = new Random();
		
		while(yG > 0) {
			xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			//a chaque creation d'ordonnee, on decremente a partir de l'ordonnee du dernier point de listePointsG
			yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			Point newPoint = new Point(xG, yG);
			listePointsG.add(newPoint);
		}
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE DROIT/////////////////////////////////
		int xD = 0;
		int yD = 0;
		
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			xD = (this.getListePointsG().get(i).x + LARGEURMAXROUTE);
			yD = this.getListePointsG().get(i).y;
			Point newPoint = new Point(xD, yD);
			this.getListePointsD().add(newPoint);
		}
		
		//this.affiche_liste(this.getListePointsG(), "gauche");
		//this.affiche_liste(this.getListePointsD(), "droit");
		
		
		///////////////////////////INITIALISATION DES OBSTACLES/////////////////////////////////
		
		this.listeObstacles = new ArrayList<Point>();
		Random rObstacle = new Random();
		for (int i = 0; i < 4; i++) {
			this.getListeObstacles().add(new Point(rObstacle.nextInt(AffichageJeu.LARGAFFICHAGE), rObstacle.nextInt(AffichageJeu.HAUTAFFICHAGE - POSITIONHORIZON)));
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
	public int getCompteurKilometre() { return compteurKilometre; }
	public ArrayList<Point> getListeObstacles() { return listeObstacles; }
	public ArrayList<Checkpoint> getListeCheckpoints() { return listeCheckpoints; }
	/**
	 * methode getPoint() : renvoie le Point situe a l'indice i de l'ArrayList listePoints
	 * @param i un indice int
	 * @return Point
	 */
	public Point getPointG(int i) { return this.listePointsG.get(i); }
	public boolean getCheckpointTouched() {
		return checkpointTouched;
	}

	public void setCheckpointTouched(boolean checkpointTouched) {
		this.checkpointTouched = checkpointTouched;
	}
	public void setListePointsG(ArrayList<Point> listePoints) { this.listePointsG = listePoints; }
	public void setListePointsD(ArrayList<Point> listePointsParallele) { 
		this.listePointsD = listePointsParallele; 
	}
	public void setKilometre(int kilometre) { this.kilometre = kilometre; }
	public void setHorizon(int horizon) { this.horizon = horizon; }
	public void setMoto(Moto moto) { this.moto = moto; }
	public void setCompteurKilometre(int resetKilometre) { this.compteurKilometre = resetKilometre; }
	public void setListeObstacles(ArrayList<Point> listeObstacles) {
		this.listeObstacles = listeObstacles;
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
		
		/*deplacement des points de la ligne gauche, les abscisses ne bougent pas, 
		 * les ordonnees sont incrementes de TAILLEAVANCEE.
		 * 
		 * on cree une nouvelle newListeG avec les ordonnees modifiees et
		 * on remplace l'ancienne listePointsG par la nouvelle
		 */
		ArrayList<Point> newListeG = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			x = (int) (this.getListePointsG().get(i).x);
			y = (int) (this.getListePointsG().get(i).y + TAILLEAVANCEE);
			newListeG.add(new Point(x, y));
		}
		this.setListePointsG(newListeG);
		
		/*deplacement des points de la ligne droite, les abscisses ne bougent pas,
		 * les ordonnees sont incrementes de TAILLEAVANCEE
		 * 
		 * on cree une nouvelle newListeD avec les ordonnees modifiees et
		 * on remplace l'ancienne listePointsD par la nouvelle
		 */
		ArrayList<Point> newListeD = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsD().size(); i++) {
			x = (int) (this.getListePointsD().get(i).x);
			y = (int) (this.getListePointsD().get(i).y + TAILLEAVANCEE);
			newListeD.add(new Point(x, y));
		}
		this.setListePointsD(newListeD);
		
		//on avertit du changement
		this.notifyObservers();
	}
	
	
	/**
	 * Methode removePointInvisible() : modifie la listePoints en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage
	 */
	public void removePointInvisible() {
		//si le premier Point et le deuxieme Point sont en dehors de l'affichage
		if(this.getListePointsG().get(0).y > Vue.AffichageJeu.HAUTAFFICHAGE && 
				this.getListePointsG().get(1).y > Vue.AffichageJeu.HAUTAFFICHAGE) {
			//on retire le premier Point de la listePoints
			this.getListePointsG().remove(0);
			this.getListePointsD().remove(0);
			
			//on avertit du changement
			this.notifyObservers();
		}
	}
	
	
	/**
	 * Methode addPointInvisible() : modifie les listePointsG/D en ajoutant un 
	 * Point de coordonnee aleatoire a la fin des deux listesPointsG/D, 
	 * si le dernier Point a une ordonnee superieure a -30
	 */
	/*
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
	*/
	
	
	/**
	 * Methode addPointInvisible2() : modifie les listePointsG/D en ajoutant un 
	 * Point de coordonnee aleatoire a la fin des deux listesPointsG/D, 
	 * si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisible2() {

		//si l'ordonnee du dernier Point de listePointsG est superieure a la position de l'horizon
		if(this.getListePointsG().get(this.getListePointsG().size()-1).y > POSITIONHORIZON) {
			Random r = new Random();
			
			//on ajoute un nouveau point d'abscisse aleatoire et d'ordonnee 0 dans listePointsG
			int xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			int yG = 0;
			this.listePointsG.add(new Point(xG, yG));
			
			//on ajoute un nouveau point d'abscisse aleatoire+LARGEURMAXROUTE et d'ordonnee 0 dans listePointsD
			int yD = yG;
			this.getListePointsD().add(new Point(xG + LARGEURMAXROUTE, yD));

			//on avertit des changements
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
	 * methode removePointInvisibleObstacles() : 
	 * modifie la listeObstacles en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage
	 */
	public void removePointInvisibleObstacles() {
		//si le premier Point est en dehors de l'affichage
		if(this.getListeObstacles().get(0).y > Vue.AffichageJeu.HAUTAFFICHAGE) {
			//on retire le premier Point de la listePoints
			this.getListeObstacles().remove(0);
			this.notifyObservers();
		}
	}
	
	/**
	 * methode addPointInvisibleObstacles() :
	 * modifie la listeObstacle en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisibleObstacles() {
		//si l'ordonnee du dernier Point de listeObstacles est superieure a la position de l'horizon
		if(this.getListeObstacles().get(this.getListeObstacles().size()-1).y > POSITIONHORIZON) {
			Random r = new Random();
			int x = r.nextInt(AffichageJeu.LARGAFFICHAGE);
			int y = r.nextInt(POSITIONHORIZON);
			this.getListeObstacles().add(new Point(x, y));
			
			//on avertit des changements
			this.notifyObservers();
		}
	}

	/**
	 * Methode avanceKilometre() :
	 * incremente les kilometres parcourus et le compteur de kilometre de TAILLEAVANCEE
	 */
	public void avanceKilometre() {
		this.setKilometre(this.getKilometre()+TAILLEAVANCEE);
		this.setCompteurKilometre(this.getCompteurKilometre() + TAILLEAVANCEE);
		//System.out.printf("Kilometres parcourus = %d\n", this.getKilometre());
		//System.out.printf("Kilometres reset = %d\n", this.getResetKilometre());
	}
	
	/**
	 * Methode avanceCheckpoint() : 
	 * modifie la liste des checkpoints listeCheckpoints en incrementant leur ordonnee,
	 * si listeCheckpoints n'est pas vide
	 */
	public void avanceCheckpoint() {
		if(this.getListeCheckpoints().size() != 0) { //si la listeCheckpoints n'est pas vide
			ArrayList<Checkpoint> tab = new ArrayList<Checkpoint>();
			Point p1, p2, p3, p4;
			for(int i = 0; i < this.getListeCheckpoints().size(); i++) {
				//on incremente les ordonnees des 4 points qui composent un Checkpoint
				p1 = (new Point((int) this.getListeCheckpoints().get(i).getHautGauche().getX(), (int)this.getListeCheckpoints().get(i).getHautGauche().getY() + TAILLEAVANCEE));
				p2 = (new Point((int) this.getListeCheckpoints().get(i).getHautDroit().getX(), (int)this.getListeCheckpoints().get(i).getHautDroit().getY() + TAILLEAVANCEE));
				p3 = (new Point((int)this.getListeCheckpoints().get(i).getBasGauche().getX(), (int)this.getListeCheckpoints().get(i).getBasGauche().getY() + TAILLEAVANCEE));
				p4 = (new Point((int)this.getListeCheckpoints().get(i).getBasDroit().getX(), (int)this.getListeCheckpoints().get(i).getBasDroit().getY() + TAILLEAVANCEE));

				tab.add(new Checkpoint(p1, p2, p3, p4));
			}
			this.setListeCheckpoints(tab);
			
			//on avertit du changement
			this.notifyObservers();
		}
	}
	
	/**
	 * methode addCheckpoint() :
	 * modifie la listeCheckpoints en ajoutant un Checkpoint de coordonnee aleatoire
	 * a la fin tout les 500 pixels (graduationKilometre/2)
	 */
	public void addCheckpoint() {
		
		//si le compteur de kilometre atteint 500 kilometres, on ajoute un Checkpoint
		if(this.getCompteurKilometre() > graduationKilometre/2) {
			Random r = new Random();
			Point p1 = new Point(r.nextInt(AffichageJeu.LARGAFFICHAGE/2) + AffichageJeu.LARGAFFICHAGE/6, POSITIONHORIZON);
			Point p2 = new Point((int) p1.getX() + 50, POSITIONHORIZON);
			Point p3 = new Point((int) p1.getX(), POSITIONHORIZON + 10);
			Point p4 = new Point((int) p1.getX() + 50, POSITIONHORIZON + 10);
			Checkpoint cp = new Checkpoint(p1, p2, p3, p4);
			this.getListeCheckpoints().add(cp);
			
			//on remet le compteur de kilometres a 0
			this.setCompteurKilometre(0);
			
			//on avertit des changements
			this.notifyObservers();
		}
		
		//affichage des CheckPoints
		/*
		for (int i = 0; i < this.getListeCheckpoints().size(); i++) {
			System.out.printf("Point %s %d = (%d, %d)\n","Checkpoints", i, (int) this.getListeCheckpoints().get(i).getHautGauche().getX(), 
					(int) this.getListeCheckpoints().get(i).getHautGauche().getY());
		}*/
		
	}
	
	/**
	 * Methode removeCheckpoint() : 
	 * modifie la listeCheckpoints en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage,
	 * si la listeCheckpoints contient au minimum 2 Checkpoints
	 */
	public void removeCheckpoint() {
		//si le premier Point et le deuxieme Point sont en dehors de l'affichage
		if(this.getListeCheckpoints().size() > 2) {
			if(this.getListeCheckpoints().get(0).getBasGauche().getY() > Vue.AffichageJeu.HAUTAFFICHAGE) {
				//on retire le premier Point de la listePoints
				this.getListeCheckpoints().remove(0);
				
				//on avertit des changements
				this.notifyObservers();
			}
		}
	}
	
	
	/**
	 * Methode ifTouchCheckpoint() :
	 * si la zone de collision de la moto touche les checkpoints,
	 * cad si la zone de la moto est a l'interieur de la zone checkpoint
	 * modifie l'attribut checkpointTouched en true,
	 * laisse en false sinon
	 */
	public void ifTouchCheckpoint() {
		int xBG, xBD, yHG;
		for(int i = 0; i < this.getListeCheckpoints().size(); i++) {
			//xBG et xBD les extremites en abscisse des Checkpoints
			xBG = (int) this.getListeCheckpoints().get(i).getBasGauche().getX();
			xBD = (int) this.getListeCheckpoints().get(i).getBasDroit().getX();
			//yHG le point le plus haut (en ordonnee) dans la zone des Checkpoints
			yHG = (int) this.getListeCheckpoints().get(i).getHautGauche().getY();
			//System.out.printf("Moto = (%d, %d)\n", (int) this.getMoto().getHautGauche().getX(), (int) this.getMoto().getHautGauche().getY());
			
			///////////////////CONDITION DE COLLISION//////////////////////////
			
			//si ordonnee hautGauche de la moto < ordonnee hautGauche du checkpoint < ordonnee basGauche de la moto
			if(((int) this.getMoto().getHautGauche().getY() < yHG && yHG < (int) this.getMoto().getBasGauche().getY())) {			
				//System.out.printf("Vous avez touche le checkpoint %d\n", i);
				
				//si abscisse HautGauche des Checkpoints <= abscisses de la zone collision de la moto <= abscisse HautDroit des Checkpoints
				if((xBG < (int) this.getMoto().getHautGauche().getX() && (int) this.getMoto().getHautDroit().getX() < xBD)){
					this.setCheckpointTouched(true);
					this.getListeCheckpoints().remove(i);
				}
			}
		}
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
		
		//si !(p1 < moto < p2), on prend les points suivants de listePointsD
		if(!isBetween(p1, p2)) {
			p1 = new Point(this.getListePointsG().get(indice+1));
			p2 = new Point(this.getListePointsG().get(indice+2));
		}
		
		/*coefficient du segment au niveau de la moto*/
		float coef = (float) ( (p2.y - p1.y)
					/ (float) (p2.x - (float) p1.x) );
		
		//abscisse situee sur le segmentG et dont l'ordonnee y correspond a la positionY de la moto
		
		//Ordonnee a l'origine b = ax + y avec x = p1.x, y = p1.y
		float b = p1.y - coef * p1.x;
		
		/* yMoto = coef * result + b 
		 * -> result = (yMoto - b) / coef
		 */
		float result = (this.getMoto().getPositionY() - b) / coef;
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
		
		//si !(p1 < moto < p2), on prend les points suivants de listePointsD
		if(!isBetween(p1, p2)) {
			p1 = new Point(this.getListePointsD().get(indice+1));
			p2 = new Point(this.getListePointsD().get(indice+2));
		}
		
		/*coefficient du segment au niveau de la moto*/
		float coef = (float) ( (p2.y - p1.y) / (float) (p2.x - (float) p1.x) );
		//System.out.printf("COEF droit : %f\n", coef);
		
		//abscisse situee sur le segmentD et dont l'ordonnee y correspond a la positionY de la moto

		//Ordonnee a l'origine b = ax + y avec x = p1.x, y = p1.y
		float b = p1.y - coef * p1.x;
		
		/* yMoto = coef * result + b 
		 * -> result = (yMoto - b) / coef
		 */
		float result = (this.getMoto().getPositionY() - b) / coef;
		//float result = (this.getMoto().getPositionY() - Moto.POSITIONXMOTO) / coef;
		//System.out.printf("abscisse route gauche : %f\n", result);
		return result;
	}
	
	/**
	 * methode estDansRoute():
	 * verifie que l'abscisse de la moto est entre les traces de la route
	 * @return renvoie true si la moto est bien sur la route,
	 * 			false sinon
	 */
	public boolean estDansRoute() {
		if(this.abscisseNiveauMotoG() <= this.getMoto().getPositionX() 
				&& this.getMoto().getPositionX() <= this.abscisseNiveauMotoD()) {
			//System.out.printf("True! abscisseG=%f < La moto=%d < abscisseD=%f\n", this.abscisseNiveauMotoG(), this.getMoto().getPositionX(), this.abscisseNiveauMotoD());
			return true;
			
		} /*else if(this.abscisseNiveauMotoG() > this.getMoto().getPositionX()
					|| this.getMoto().getPositionX() > this.abscisseNiveauMotoD()) {
			//System.out.printf("False! abscisseG=%f > La moto=%d > abscisseD=%f\n", this.abscisseNiveauMotoG(), this.getMoto().getPositionX(), this.abscisseNiveauMotoD());
			return false;
		}*/
		return false;
	}
	
	
	public float calculVitesse(int seconde) {
		//System.out.printf("calculVitesse = %f\n", (float) this.getKilometre()/seconde);
		return (float) this.getKilometre()/seconde;
	}

	
	/**
	 * Methode updateVitesseMoto():
	 * incremente la vitesse de la moto de ACCELERATION si elle est sur la route,
	 * decremente de ACCELERATION*2 sinon (deceleration rapide)
	 */
	public void updateVitesseMoto(int seconde) {
		if(this.estDansRoute()) {
			if(this.getMoto().getVitesse() < VITESSEMAX) { //tant que la vitesse max n'est pas atteint
				this.getMoto().setVitesse(this.getMoto().getVitesse() + ACCELERATION);
				//this.getMoto().setVitesse(calculVitesse(seconde));
				
				//on avertit des changements
				this.notifyObservers();
				//System.out.printf("acceleration : %f\n", this.getMoto().getVitesse());
			}
		}else{
			if(this.getMoto().getVitesse() > 0) {
				this.getMoto().setVitesse(this.getMoto().getVitesse() - ACCELERATION*2);
				//this.getMoto().setVitesse(calculVitesse(seconde));
				
				//on avertit des changements
				this.notifyObservers();
				//System.out.printf("deceleration : %f\n", this.getMoto().getVitesse());
			}
		}
	}


	
	
	
///////////////////////////////////////////END GAME//////////////////////////////////////////////	

	/**
	 * Methode ifCollisionObstacles(): renvoie true si la zone de collision de la moto touche un obstacle
	 * @return true si les coordonnees de la moto sont identiques a celles d'un obstacle, 
	 * false sinon
	 * Deuxieme condition de perte
	 */
	public boolean ifCollisionObstacles() {
		int x, y; //les coordonnes des obstacles
		for(int i = 0; i < this.getListeObstacles().size(); i++) {
			x = (int) this.getListeObstacles().get(i).getX();
			y = (int) this.getListeObstacles().get(i).getY();
			//System.out.printf("Point obstacle %d : (%d, %d)\n", i, x, y);
			//System.out.printf("Point moto : (%d, %d)\n", this.getMoto().getPositionX(), this.getMoto().getPositionY());
			
			/* si les coordonnes d'un obstacle sont dans la zone de collision de la moto  cad :
			 * si abscisse hautGauche de la moto <= abscisse obstacle <= abscisse hautDroit de la moto
			 * si ordonnee hautGauche de la moto <= ordonnee obstacle <= ordonnee badGauche de la moto
			 */
			if(this.getMoto().getHautGauche().getX() <= x && x <= this.getMoto().getHautDroit().getX()) {
				//System.out.printf("Point obstacle %d : (%d, %d)\n", i, x, y);
				//System.out.printf("Point moto : (%d, %d)\n", this.getMoto().getPositionX(), this.getMoto().getPositionY());
				if(this.getMoto().getHautGauche().getY() <= y && y <= this.getMoto().getBasGauche().getY()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Methode ifVitesseNulle() : renvoie true si la vitesse de la moto est a zero
	 * @return true si vitesse = 0, false sinon
	 * Troisieme condition de perte
	 */
	public boolean ifVitesseNulle() {
		if(this.getMoto().getVitesse() == 0.) {
			return true;
		}
		return false;
	}
	
	
	
}
