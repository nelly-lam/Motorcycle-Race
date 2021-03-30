package Modele;

import Vue.AffichageFin;

public class AvanceeRoute extends Thread{
	
	/****************CONSTANTES****************/
	
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
				route.avanceRoute();
				route.addPointInvisible2();
				route.removePointInvisible();
				route.avanceObstacles();
				route.addPointInvisibleObstacles();
				route.removePointInvisibleObstacles();
				route.updateVitesseMoto();
				//System.out.printf("dans AvanceeRoute");
				
				if(this.getRoute().estDansRoute()) { //si la moto est sur la route
					if(this.getSecondeAvancee() > 150) {
						this.setSecondeAvancee(this.getSecondeAvancee() - 20*Route.ACCELERATION);
					}
					Thread.sleep(secondeAvancee);
				}else{ //si la moto n'est pas sur la route
					if(this.getSecondeAvancee() < 1500) {
						this.setSecondeAvancee(this.getSecondeAvancee() + 10);
					}
					Thread.sleep(secondeAvancee);
				}
				
				/////////////////////////////CONDITIONS DE PERTE//////////////////////////////
				if(this.getRoute().ifCollisionObstacles()) {
					this.setRun(false);
					new AffichageFin(this.getRoute(), "vous avez touche un obstacle !");
				}
				if(this.getRoute().ifVitesseNulle()) {
					this.setRun(false);
					new AffichageFin(this.getRoute(), "vous etes en arret !");
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
}
