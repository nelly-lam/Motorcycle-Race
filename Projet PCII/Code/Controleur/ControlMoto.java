package Controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modele.Moto;

public class ControlMoto implements KeyListener{
	
	/****************ATTRIBUTS****************/
	private Moto moto;
	
	public ControlMoto(Moto m) {
		this.moto = m;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); 
		switch (key){
		   case (KeyEvent.VK_RIGHT): // quand le code correspond à celui de la fleche droite du clavier
			   this.getMoto().deplaceDroit();
		   break;
		   case (KeyEvent.VK_LEFT): 
			   this.getMoto().deplaceGauche();
		   break;
		  }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Moto getMoto() { return moto; }

	public void setMoto(Moto moto) { this.moto = moto; }

}
