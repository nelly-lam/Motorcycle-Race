package Vue;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Route;

public class AffichageFin extends JFrame{
	
	private final Route route;
	
	public AffichageFin(Route r, String str) {
		this.route = r;
		
		this.setTitle("Fin de la partie");
    	this.setPreferredSize(new Dimension(AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE));
    	this.setResizable(false);
		this.setFocusable(true);
		
		JPanel panel = new JPanel(new GridLayout());
		
		JLabel perdu = new JLabel("Vous avez perdu car : ");
		JLabel raison = new JLabel(str);
		JLabel score = new JLabel("Votre score est de : ");
		JLabel nbScore = new JLabel(String.valueOf(this.route.getKilometre()));
		
		panel.add(perdu);
		panel.add(raison);
		panel.add(score);
		panel.add(nbScore);
		this.add(panel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public Route getRoute() {
		return route;
	}

}
