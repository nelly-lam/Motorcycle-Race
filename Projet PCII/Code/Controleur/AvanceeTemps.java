package Controleur;
import Modele.Route;
import Vue.AffichageFin;

public class AvanceeTemps extends Thread{
	
	/****************CONSTANTES****************/
	public final static int tempsCheckPoint = 31;
	public final static int seconde = 1000; //(1s = 1000ms)
	public final static int graduationTemps = 8000;
	
	/****************ATTRIBUTS****************/
	private boolean run;
	private Route route;
	private Thread thread;
	private int tempsEcoule; //le temps qu'il nous reste avant la fin de la partie
	
	/****************CONSTRUCTEUR****************/
	public AvanceeTemps(Route r) {
		this.run = true;
		this.setRoute(r);
		this.setTempsEcoule(tempsCheckPoint);
	}
	
	/****************METHODES****************/
	
	public int getTempsRestant(int i) {
		return this.getTempsEcoule() - i;
	}
	
	public void run() {
		while(this.getRun()) { //boucle infinie
			try {
				//System.out.printf("Temps ecoule %d sec\n", this.getTempsRestant(1));
				int diviseTempsBonus = 1;
				int gradTemps = graduationTemps;
				this.setTempsEcoule(this.getTempsRestant(1));
				
				/////////////////////////////BONUS TEMPS////////////////////////////
				if(this.getRoute().ifTouchCheckpoint()) {
					System.out.print("Checkpoint!\n");
					this.setTempsEcoule(tempsCheckPoint);
					//System.out.printf("Nouveau temps %d sec\n", this.getTempsRestant(1) + (int) (tempsCheckPoint/diviseTempsBonus));
					//System.out.printf("Temps ecoule %d sec\n", this.getTempsRestant(1));
				}
				
				gradTemps -= seconde;
				if(gradTemps == 0) {
					diviseTempsBonus += 1;
				}

				/////////////////////////////CONDITION DE PERTE//////////////////////////////
				if (this.getTempsEcoule() == 0) {
					this.setRun(false);
					new AffichageFin(this.getRoute(), "Le temps est écoulé !");
				}
					
				Thread.sleep(seconde);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean getRun() {
		return this.run;
	}
	
	public void setRun(boolean b) { this.run = b; }

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public int getTempsEcoule() {
		return tempsEcoule;
	}

	public void setTempsEcoule(int tempsEcoule) {
		this.tempsEcoule = tempsEcoule;
	}
}
