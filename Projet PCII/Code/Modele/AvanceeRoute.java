package Modele;

public class AvanceeRoute extends Thread{
	
	/****************CONSTANTES****************/
	public final static int secondeAvancee = 400;
	
	/****************ATTRIBUTS****************/
	private Route route;
	private boolean run;
	
	/****************CONSTRUCTEUR****************/
	public AvanceeRoute(Route r) {
		this.route = r;
		this.run = true;
	}

	/****************METHODES****************/
	public Route getRoute() { return route; }
	public boolean getRun() { return run; }
	public void setRoute(Route route) { this.route = route; }
	public void setRun(boolean b) { this.run = b; }
	
	public void run() {
		while(this.getRun()) { //boucle infinie
			try {
				this.getRoute().avanceRoute();
				this.getRoute().removePointInvisible();
				this.getRoute().addPointInvisible();
				
				Thread.sleep(secondeAvancee); //on renouvelle toutes les 'secondes'
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
