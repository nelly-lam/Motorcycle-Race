package Controleur;
import Modele.Route;
import Vue.AffichageFin;

public class AvanceeRoute extends Thread{
	
	/****************CONSTANTES****************/
	public final static int seconde = 1000;
	/****************ATTRIBUTS****************/
	private Route route;
	private boolean run;
	private int secondeAvancee;
	
	/****************CONSTRUCTEUR****************/
	public AvanceeRoute(Route r) {
		this.route = r;
		this.run = true;
		this.secondeAvancee = 1000;
	}

	/****************METHODES****************/
	public Route getRoute() { return route; }
	public boolean getRun() { return run; }
	public int getSecondeAvancee() { return secondeAvancee; }
	public void setRoute(Route route) { this.route = route; }
	public void setRun(boolean b) { this.run = b; }
	public void setSecondeAvancee(int n) { this.secondeAvancee = n; }
	
	public void run() {
		while(this.getRun()) { //boucle infinie
			try {
				route.avanceKilometre();
				
				route.avanceRoute();
				route.addPointInvisible2();
				route.removePointInvisible();
				
				route.avanceObstacles();
				route.addPointInvisibleObstacles();
				route.removePointInvisibleObstacles();
				
				route.updateVitesseMoto(secondeAvancee);
				
				route.avanceCheckpoint();
				route.addCheckpoint();
				route.removeCheckpoint();
				//System.out.printf("dans AvanceeRoute");

				this.getRoute().ifTouchCheckpoint();
				
				if(this.getRoute().estDansRoute()) { //si la moto est sur la route
					if(this.getSecondeAvancee() > 150) {
						this.setSecondeAvancee(this.getSecondeAvancee() - 20*Route.ACCELERATION);
					}
					Thread.sleep(this.getSecondeAvancee());
				}else{ //si la moto n'est pas sur la route
					if(this.getSecondeAvancee() < 1500) {
						this.setSecondeAvancee(this.getSecondeAvancee() + 10);
					}
					Thread.sleep(this.getSecondeAvancee());
				}
				
				
				
				/////////////////////////////CONDITIONS DE PERTE//////////////////////////////
				
				/**
				 * s'il y a collision entre la moto et les obstacles,
				 * on arrete le Thread et on affiche la fenêtre de fin
				 */
				if(this.getRoute().ifCollisionObstacles()) {
					this.setRun(false);
					//met l'attribut finDePartie a true
					this.getRoute().setFinDePartie(true);
					new AffichageFin(this.getRoute(), "Vous avez touché un obstacle !");
				}
				
				/**
				 * si la vitesse de la moto est nulle,
				 * on arrete le Thread et on affiche la fenêtre de fin
				 */
				if(this.getRoute().ifVitesseNulle()) {
					this.setRun(false);
					//met l'attribut finDePartie a true
					this.getRoute().setFinDePartie(true);
					new AffichageFin(this.getRoute(), "Vous êtes a l'arrêt !");
				}
				
				/**
				 * si l'attribut finDePartie est a true (mis a true par l'autre Thread),
				 * on arrete le Thread et on affiche la fenêtre de fin
				 */
				if (this.getRoute().getFinDePartie()) {
					this.setRun(false);
				}
				
				/*//pour la pause
				if(this.getRoute().getIsPaused()) {
					this.setRun(false);
				}else {
					this.setRun(true);
				}*/

				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

	
}
