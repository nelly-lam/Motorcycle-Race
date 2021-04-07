package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controleur.AvanceeRoute;
import Controleur.AvanceeTemps;
import Controleur.ControlMoto;
import Modele.Moto;
import Modele.Route;

public class AffichageJeu extends JPanel{
	
	/*************CONSTANTES*************/
	private static final long serialVersionUID = 1L;
	
    public static final int LARGAFFICHAGE = 800;
    public static final int HAUTAFFICHAGE = 600;
    
    /*************ATTRIBUTS*************/
	JFrame fenetre = new JFrame();
    private final Moto moto;
    private final Route route;
    private AvanceeTemps temps;
    private AvanceeRoute ar;
    
    /*************CONSTRUCTEUR*************/
    public AffichageJeu(Moto m, Route r, AvanceeTemps t, AvanceeRoute ar) {
    	this.moto = m;
    	this.route = r;
    	this.temps = t;
    	this.ar = ar;
    	
    	this.fenetre.setTitle("Course de moto");
    	this.fenetre.setPreferredSize(new Dimension(LARGAFFICHAGE, HAUTAFFICHAGE));
    	this.fenetre.setResizable(false);
		//this.fenetre.setFocusable(true);
		
		JPanel panel = new JPanel(new BorderLayout());
    	
    	AffichageRoute affichageRoute = new AffichageRoute(this.moto, this.route, this.temps);
    	affichageRoute.addKeyListener(new ControlMoto(this.moto, this.route, this.temps, this.ar));
		panel.add(affichageRoute, BorderLayout.CENTER);

        AffichageScore affichageScore = new AffichageScore(this.moto, this.route);
        panel.add(affichageScore, BorderLayout.SOUTH); 
        
        //AffichageTimer affichageTimer = new AffichageTimer(this.temps);
        //panel.add(affichageTimer, BorderLayout.NORTH);
        
        this.fenetre.add(panel);
        
		fenetre.pack();
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    /*************METHODES*************/
	public Moto getMoto() { return moto; }
	public Route getRoute() { return route; }
	public AvanceeRoute getAr() {
		return ar;
	}
	public void setAr(AvanceeRoute ar) {
		this.ar = ar;
	}
	
	//public void setMoto(Moto moto) { this.moto = moto; }
	//public void setRoute(Route route) { this.route = route; }
	
}
