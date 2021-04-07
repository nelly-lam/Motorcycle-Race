package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modele.Moto;
import Modele.Route;

public class ControlMoto implements KeyListener{
	
	/****************ATTRIBUTS****************/
	private final Moto moto;
	private final Route route;
	private AvanceeTemps temps;
	private AvanceeRoute ar;
	
	/****************CONSTRUCTEUR****************/
	public ControlMoto(Moto m, Route r, AvanceeTemps t, AvanceeRoute ar) {
		this.moto = m;
		this.route = r;
		this.temps = t;
		this.ar = ar;
	}

	/****************METHODES****************/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		   case (KeyEvent.VK_RIGHT): // quand la touche correspond à celui de la fleche droite du clavier
			   this.getMoto().deplaceDroit();
		   break;
		   case (KeyEvent.VK_LEFT): 
			   this.getMoto().deplaceGauche();
		   break;
		   /*case (KeyEvent.VK_SPACE):
			   
			   if(!this.getRoute().getIsPaused()) { //si le jeu n'est pas en pause
				   //this.getAvanceeTemps().setRun(false);
				   //this.getAr().setRun(false);
				   this.getRoute().setIsPaused(true);
			   }else {
				   //this.getAvanceeTemps().setRun(true);
				   //this.getAr().setRun(true);
				   this.getRoute().setIsPaused(false);
			   }
			   */
			   /*
			   if(!this.getTimer().getIfPause()) {
				   this.getTimer().pause();
				   //met en arret l'avancee de la route
				   this.getAr().setRun(false);
				   
			   }else {
				   this.getTimer().reprendreTimer();
				   //met en marche l'avancee de la route
				   this.getAr().setRun(true);
			   }
		   break;*/
		  }

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}

	public Moto getMoto() { return moto; }
	public Route getRoute() { return route; }

	public AvanceeRoute getAr() {return ar;}
	public void setAr(AvanceeRoute ar) {this.ar = ar;}
	
	public AvanceeTemps getAvanceeTemps() {return temps;}
	public void setAvanceeTemps(AvanceeTemps timer) {this.temps = timer;}
	

}
