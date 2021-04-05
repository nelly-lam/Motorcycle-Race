package Modele;

import java.awt.Point;

public class Checkpoint{
	
	/****************ATTRIBUTS****************/
	//Zone rectangulaire qui forme un Checkpoint
	private Point hautGauche;
	private Point hautDroit;
	private Point basGauche;
	private Point basDroit;
	
	/****************CONSTRUCTEUR****************/
	public Checkpoint(Point p1, Point p2, Point p3, Point p4) {
		this.hautGauche = p1;
		this.hautDroit = p2;
		this.basGauche = p3;
		this.basDroit = p4;
	}
	
	/****************METHODES****************/
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
	


}
