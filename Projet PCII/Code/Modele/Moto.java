package Modele;

import java.awt.Point;

public class Moto extends Observable{
	
	/****************CONSTANTES****************/
	public final static int POSITIONXMOTO = Vue.AffichageJeu.LARGAFFICHAGE/2;
	public final static int LARGZONEMOTO = 10;
	public final static int HAUTZONEMOTO = 20;
	public final static int TAILLEDEPLACEMENT = 5;
	
	/****************ATTRIBUTS****************/
	private int positionX;
	private int positionY;
	
	//Zone rectangulaire autour de la moto, zone de collision
	private Point hautGauche;
	private Point hautDroit;
	private Point basGauche;
	private Point basDroit;
	
	private float vitesse;
	
	
	/****************CONSTRUCTEUR****************/
	public Moto() {
		this.positionX = POSITIONXMOTO;
		this.positionY = Vue.AffichageJeu.HAUTAFFICHAGE - 150;
		
		this.hautGauche = new Point(this.positionX - LARGZONEMOTO, this.positionY - HAUTZONEMOTO);
		this.hautDroit = new Point(this.positionX + LARGZONEMOTO, this.positionY - HAUTZONEMOTO);
		this.basGauche = new Point(this.positionX - LARGZONEMOTO, this.positionY + HAUTZONEMOTO);
		this.basDroit = new Point(this.positionX + LARGZONEMOTO, this.positionY + HAUTZONEMOTO);
		
		this.vitesse = 0;
		System.out.printf("POSITIONXMOTO = %d\n", POSITIONXMOTO);
		System.out.printf("positionY = %d\n", this.positionY);
		
		//System.out.printf("Point hautGauche = (%d, %d)\n", this.positionX - LARGZONEMOTO, this.positionY - HAUTZONEMOTO);
		//System.out.printf("Point hautDroit = (%d, %d)\n", this.positionX + LARGZONEMOTO, this.positionY - HAUTZONEMOTO);
		//System.out.printf("Point basGauche = (%d, %d)\n", this.positionX - LARGZONEMOTO, this.positionY + HAUTZONEMOTO);
		//System.out.printf("Point basDroit = (%d, %d)\n", this.positionX + LARGZONEMOTO, this.positionY + HAUTZONEMOTO);

	}
	
	
	/****************METHODES****************/
	public int getPositionX() { return positionX; }
	public int getPositionY() { return positionY; }
	public float getVitesse() { return vitesse; }
	public Point getHautGauche() {
		return hautGauche;
	}

	public void setHautGauche(Point hautGauche) {
		this.hautGauche = hautGauche;
	}

	public Point getHautDroit() {
		return hautDroit;
	}

	public void setHautDroit(Point hautDroit) {
		this.hautDroit = hautDroit;
	}

	public Point getBasGauche() {
		return basGauche;
	}

	public void setBasGauche(Point basGauche) {
		this.basGauche = basGauche;
	}

	public Point getBasDroit() {
		return basDroit;
	}

	public void setBasDroit(Point basDroit) {
		this.basDroit = basDroit;
	}

	public void setPositionX(int positionX) { this.positionX = positionX; }
	public void setPositionY(int positionY) { this.positionY = positionY; }
	public void setVitesse(float vitesse) { this.vitesse = vitesse; }
	
	/**
	 * Methode deplaceZoneMoto() :
	 * deplace d'un nombre "taille" de pixel la position de la moto
	 * @param taille
	 */
	public void deplaceZoneMoto(int taille) {
		this.setHautGauche(new Point((int) this.getHautGauche().getX() + taille, (int) this.getHautGauche().getY()));
		this.setHautDroit(new Point((int) this.getHautDroit().getX() + taille, (int) this.getHautDroit().getY()));
		this.setBasDroit(new Point((int) this.getBasDroit().getX() + taille, (int) this.getBasDroit().getY()));
		this.setBasGauche(new Point((int) this.getBasGauche().getX() + taille, (int) this.getBasGauche().getY()));
	}
	/**
	 * methode deplaceDroit() :
	 * incremente la position en abscisse de la moto
	 */
	public void deplaceDroit() {
		if(this.getPositionX() < Vue.AffichageJeu.LARGAFFICHAGE) {
			this.setPositionX(this.getPositionX() + TAILLEDEPLACEMENT);
			this.deplaceZoneMoto(TAILLEDEPLACEMENT);
			this.notifyObservers();
			//System.out.printf("La moto se deplacea droite\n");
		}else{
			System.out.printf("La moto va sortir de l'ecran\n");
		}
	}
	
	
	/**
	 * methode deplaceGauche() :
	 * decremente la position en abscisse de la moto
	 */
	public void deplaceGauche() {
		if(0 < this.getPositionX()) {
			this.setPositionX(this.getPositionX() - TAILLEDEPLACEMENT);
			this.deplaceZoneMoto(-TAILLEDEPLACEMENT);
			this.notifyObservers();
		}else{
			System.out.printf("La moto va sortir de l'ecran\n");
		}
	}


	
}
