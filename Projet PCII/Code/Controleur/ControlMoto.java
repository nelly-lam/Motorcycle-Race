package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Modele.AvanceeRoute;
import Modele.AvanceeTemps;
import Modele.Moto;

public class ControlMoto implements KeyListener{
	
	/****************ATTRIBUTS****************/
	private final Moto moto;
	private AvanceeTemps temps;
	private AvanceeRoute ar;
	
	/****************CONSTRUCTEUR****************/
	public ControlMoto(Moto m, AvanceeTemps t, AvanceeRoute ar) {
		this.moto = m;
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
		//System.out.printf("on rentre dans keyPressed\n");
		switch (e.getKeyCode()){
		   case (KeyEvent.VK_RIGHT): // quand la touche correspond à celui de la fleche droite du clavier
			   //System.out.printf("La moto se deplace a droite\n");
			   this.getMoto().deplaceDroit();
		   break;
		   case (KeyEvent.VK_LEFT): 
			   this.getMoto().deplaceGauche();
		   break;
		   case (KeyEvent.VK_ESCAPE):
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

	public AvanceeTemps getTimer() {
		return temps;
	}

	public void setTimer(AvanceeTemps timer) {
		this.temps = timer;
	}

	public AvanceeRoute getAr() {
		return ar;
	}

	public void setAr(AvanceeRoute ar) {
		this.ar = ar;
	}

}
