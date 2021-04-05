package Controleur;
import Modele.Route;
import Vue.AffichageFin;

public class AvanceeTemps extends Thread{
	
	/****************CONSTANTES****************/
	public final static int tempsCheckPoint = 30;
	public final static int seconde = 1000; //(1s = 1000ms)
	public final static int graduationTemps = 10000;
	public final static int tempsBonus = 20;
	
	/****************ATTRIBUTS****************/
	private boolean run;
	private Route route;
	private Thread thread;
	private int tempsEcoule; //le temps qu'il nous reste avant la fin de la partie
	private int gradTemps; //divise le temps bonus tout les gradTemps
	private int diviseTempsBonus; //divise le temps bonus
	
	/****************CONSTRUCTEUR****************/
	public AvanceeTemps(Route r) {
		this.run = true;
		this.setRoute(r);
		this.setTempsEcoule(tempsCheckPoint);
		this.gradTemps = graduationTemps;
		this.diviseTempsBonus = 1;
	}
	
	/****************METHODES****************/
	
	public int getTempsRestant(int i) {
		return this.getTempsEcoule() - i;
	}
	
	public void run() {
		while(this.getRun()) { //boucle infinie
			try {
				//System.out.printf("Temps ecoule %d sec\n", this.getTempsRestant(1));
				this.setTempsEcoule(this.getTempsRestant(1));
				
				/////////////////////////////BONUS TEMPS////////////////////////////
				if(this.getRoute().getCheckpointTouched()) {
					//System.out.print("Checkpoint!\n");
					//System.out.printf("Bonus = %d\n", tempsBonus/this.diviseTempsBonus);
					if(tempsBonus/this.diviseTempsBonus >= 10) {
						this.setTempsEcoule(this.getTempsRestant(0) + tempsBonus/this.diviseTempsBonus);
					}else {
						this.setTempsEcoule(this.getTempsRestant(0) + 5);
					}
					this.getRoute().setCheckpointTouched(false);
					//System.out.printf("Nouveau temps %d sec\n", this.getTempsRestant(1) + (int) (tempsCheckPoint/diviseTempsBonus));
					//System.out.printf("Temps ecoule %d sec\n", this.getTempsRestant(1));
				}
				
				this.gradTemps -= seconde;
				//System.out.printf("gradTemps = %d\n", this.gradTemps);
				if(this.gradTemps == 0) {
					this.gradTemps = graduationTemps;
					this.diviseTempsBonus += 1;
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

	public int getGradTemps() {
		return gradTemps;
	}

	public void setGradTemps(int gradTemps) {
		this.gradTemps = gradTemps;
	}

	public int getDiviseTempsBonus() {
		return diviseTempsBonus;
	}

	public void setDiviseTempsBonus(int diviseTempsBonus) {
		this.diviseTempsBonus = diviseTempsBonus;
	}
}
