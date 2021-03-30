package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.AvanceeTemps;
import Modele.MyTimer;

public class AffichageTimer extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	/*************ATTRIBUTS*************/
	//private MyTimer temps;
	private AvanceeTemps temps;
	
    JLabel txtTemps = new JLabel();
    JLabel txtTempsEcoule = new JLabel();
    
    /*************CONSTRUCTEUR*************/
	public AffichageTimer(AvanceeTemps at) {
		this.setTemps(at);
		//this.temps.addObserver(this);
		
		this.setPreferredSize(new Dimension(AffichageJeu.LARGAFFICHAGE, 50));
		this.setLayout(new FlowLayout());
		
		txtTemps.setText("Temps restant : ");
		//txtTempsEcoule.setText(String.valueOf(this.getTemps().getTempsEcoule()));
		//System.out.printf("Temps restant : %s", this.getTemps().humanReadableFormat(this.getTemps().getTempsRestant()));
		this.add(txtTemps);
		this.add(txtTempsEcoule);
		
	}

	/*************METHODES*************/
	public AvanceeTemps getTemps() { return temps; }
	public void setTemps(AvanceeTemps temps) { this.temps = temps; }
	
	public void paint(Graphics g) {
    	super.revalidate();
    	super.paint(g);
    	super.repaint();
    	g.drawString(String.valueOf(this.getTemps().getTempsEcoule()), AffichageJeu.LARGAFFICHAGE/2+40, 18);

	}

	@Override
	public void update() {
		//System.out.printf("Temps restant : %s", String.valueOf(this.getTemps().getTempsRestant().toString()));
		//this.getTempsEcoule().setText(String.valueOf(this.getTemps().getTempsEcoule()));
		repaint();
	}
	

}
