package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Modele.Moto;
import Modele.Route;

public class AffichageScore extends JPanel implements Observer{

	/*************CONSTANTES*************/
	private static final long serialVersionUID = 1L;
	
	/*************ATTRIBUTS*************/
    JLabel kilometre = new JLabel("");
    JLabel nbKilometre = new JLabel("");
    JLabel score = new JLabel("");
    JLabel nbScore = new JLabel("");
    JLabel texte = new JLabel("");
    
	JPanel panel = new JPanel();
    
	private final Moto moto;
	private final Route route;
	
	/*************CONSTRUCTEUR*************/
	public AffichageScore(Moto m, Route r) {
		this.moto = m;
		this.route = r;
		this.route.addObserver(this);
		
		this.panel.setPreferredSize(new Dimension(AffichageJeu.LARGAFFICHAGE, 50));
		
		JPanel information = new JPanel(new FlowLayout());
		
		this.kilometre.setText("Nombre de kilometres parcourus : ");
		this.nbKilometre.setText(String.valueOf(this.route.getKilometre()));
		
		this.score.setText("Score : ");
		this.nbScore.setText(String.valueOf(this.route.getKilometre()));
		
		information.add(kilometre);
		information.add(nbKilometre);
		information.add(score);
		information.add(nbScore);

		this.panel.setLayout(new BoxLayout(information, BoxLayout.PAGE_AXIS));
		this.panel.setLayout(new BoxLayout(this.texte, BoxLayout.PAGE_AXIS));
		
	}

	/*************METHODES*************/
	public Moto getMoto() { return moto; }
	public Route getRoute() {return route; }

	@Override
	public void update() {
		this.nbKilometre.setText(String.valueOf(this.getRoute().getKilometre()));
		this.nbScore.setText(String.valueOf(this.getRoute().getKilometre()));
	}
	
	public void updateTexte(String str) {
		this.texte.setText(str);
	}

}
