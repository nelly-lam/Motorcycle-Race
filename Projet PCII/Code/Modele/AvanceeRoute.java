package Modele;

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
				route.updateVitesseMoto();
				
				if(this.getRoute().estDansRoute()) {
					this.setSecondeAvancee(this.getSecondeAvancee() - 10*Route.ACCELERATION);
				}else {
					this.setSecondeAvancee(this.getSecondeAvancee() + 10*Route.ACCELERATION);
				}
				Thread.sleep(secondeAvancee); //on renouvelle toutes les 'millisecondes'
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
