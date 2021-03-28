package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Modele.MyTimer;

public class AffichageTimer extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	/*************ATTRIBUTS*************/
	private MyTimer temps;
	
    JLabel txtTemps = new JLabel();
    JLabel tempsEcoule = new JLabel();
	JPanel panel = new JPanel();
    
    /*************CONSTRUCTEUR*************/
	public AffichageTimer(MyTimer t) {
		this.setTemps(t);
		this.temps.addObserver(this);
		
		this.panel.setPreferredSize(new Dimension(AffichageJeu.LARGAFFICHAGE, 50));
		this.panel.setLayout(new FlowLayout());
		
		txtTemps.setText("Temps restant : ");
		tempsEcoule.setText(this.getTemps().getTempsRestant().toString());
		
		System.out.printf("Temps restant : %s", String.valueOf(this.getTemps().getTempsRestant().toString()));
		
		this.panel.add(txtTemps);
		this.panel.add(tempsEcoule);
		
	}

	/*************METHODES*************/
	public MyTimer getTemps() { return temps; }
	public void setTemps(MyTimer temps) { this.temps = temps; }

	@Override
	public void update() {
		//System.out.printf("Temps restant : %s", String.valueOf(this.getTemps().getTempsRestant().toString()));
		this.tempsEcoule.setText(this.getTemps().getTempsRestant().toString());
	}
	
	

}
