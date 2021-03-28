package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import Modele.Moto;
import Modele.Route;

public class AffichageRoute extends JPanel implements Observer{

	/*************CONSTANTES*************/
	private static final long serialVersionUID = 1L;
	
	/*************ATTRIBUTS*************/
    private final Moto moto;
    private final Route route;    
    
    /*************CONSTRUCTEUR*************/
    public AffichageRoute(Moto m, Route r) {
    	this.moto = m;
    	this.route = r;
    	
    	this.route.addObserver(this);
    }
    
    /*************METHODES*************/
	public Moto getMoto() { return moto; }
	public Route getRoute() { return route; }
	
	
	public void paint(Graphics g) {
    	super.revalidate();
    	super.paint(g);
    	super.repaint();
    	
    	paintMoto(g);
    	paintRoute(g);
    	paintHorizon(g);
    	
    }
    
    public void paintMoto(Graphics g) {
    	g.setColor(Color.RED);
    	g.drawString("X", this.getMoto().getPositionX(), this.getMoto().getPositionY());
    }
    
    public void paintRoute(Graphics g) {
    	g.setColor(Color.green);
    	for(int i = 0; i < this.getRoute().getListePointsG().size()-1; i++) {
    		g.drawLine( (int) this.getRoute().getListePointsG().get(i).getX(), 
    					(int) this.getRoute().getListePointsG().get(i).getY(),
    					(int) this.getRoute().getListePointsG().get(i+1).getX(),
    					(int) this.getRoute().getListePointsG().get(i+1).getY() );
    	}
    	
    	for(int i = 0; i < this.getRoute().getListePointsD().size()-1; i++) {
    		g.drawLine( (int) this.getRoute().getListePointsD().get(i).getX(), 
    					(int) this.getRoute().getListePointsD().get(i).getY(),
    					(int) this.getRoute().getListePointsD().get(i+1).getX(),
    					(int) this.getRoute().getListePointsD().get(i+1).getY() );
    	}
    }
    
    public void paintHorizon(Graphics g) {
    	g.setColor(Color.blue);
    	g.fillRect(0, 0, AffichageJeu.LARGAFFICHAGE, Route.POSITIONHORIZON);
    	//g.drawLine( 0, Modele.Route.POSITIONHORIZON, Vue.AffichageJeu.LARGAFFICHAGE, Modele.Route.POSITIONHORIZON);
    }

	@Override
    public void update() {
        this.requestFocusInWindow();
        repaint();
    }


}
