package Modele;

import Vue.AffichageFin;

public class AvanceeTemps extends Thread{
	
	/****************CONSTANTES****************/
	public final static int tempsCheckPoint = 21;
	public final static int seconde = 1000; //(1s = 1000ms)
	
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
				for(int i = 0; i < tempsCheckPoint; i++){
					//System.out.printf("Temps ecoule %d sec\n", this.getTempsRestant(1));
					this.setTempsEcoule(this.getTempsRestant(1));
					Thread.sleep(seconde);
				}
				
				/////////////////////////////CONDITION DE PERTE//////////////////////////////
				if (this.getTempsEcoule() == 0) {
					this.setRun(false);
					new AffichageFin(this.getRoute(), "le temps est ecoule !");
				}

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
