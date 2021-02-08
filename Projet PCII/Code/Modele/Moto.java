package Modele;

public class Moto {
	
	/****************CONSTANTES****************/
	public final static int POSITIONXMOTO = Vue.AffichageJeu.LARGAFFICHAGE/2;
	public final static int TAILLEDEPLACEMENT = 5;
	
	/****************ATTRIBUTS****************/
	private int positionX;
	private int positionY;
	private float vitesse;
	
	
	/****************CONSTRUCTEUR****************/
	public Moto() {
		this.positionX = POSITIONXMOTO;
		this.positionY = Vue.AffichageJeu.HAUTAFFICHAGE - 20;
		this.vitesse = 80;
		System.out.printf("POSITIONXMOTO = %d\n", POSITIONXMOTO);
		System.out.printf("positionY = %d\n", this.positionY);
	}
	
	/****************METHODES****************/
	public int getPositionX() { return positionX; }
	public int getPositionY() { return positionY; }
	public float getVitesse() { return vitesse; }

	public void setPositionX(int positionX) { this.positionX = positionX; }
	public void setPositionY(int positionY) { this.positionY = positionY; }
	public void setVitesse(float vitesse) { this.vitesse = vitesse; }
	
	/**
	 * methode deplaceDroit() :
	 * incremente la position en abscisse de la moto
	 */
	public void deplaceDroit() {
		if(this.getPositionX() < Vue.AffichageJeu.LARGAFFICHAGE) {
			this.setPositionX(this.getPositionX() + TAILLEDEPLACEMENT);
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
		}else{
			System.out.printf("La moto va sortir de l'ecran\n");
		}
	}



	
}
