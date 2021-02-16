package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Moto;
import Modele.Route;

public class AffichageJeu extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/*************CONSTANTES*************/
    public static final int LARGAFFICHAGE = 800;
    public static final int HAUTAFFICHAGE = 600;
    
    /*************ATTRIBUTS*************/
    private Moto moto;
    private Route route;
    
    
    /*************CONSTRUCTEUR*************/
    public AffichageJeu(Moto m, Route r) {
    	this.moto = m;
    	this.route = r;
        setPreferredSize(new Dimension(LARGAFFICHAGE, HAUTAFFICHAGE));
        JLabel kilometre = new JLabel("Kilometre");
        this.add(kilometre, BorderLayout.PAGE_END);
    }
    
    
    /*************METHODES*************/
	public Moto getMoto() { return moto; }
	public Route getRoute() { return route; }
	
	public void setMoto(Moto moto) { this.moto = moto; }
	public void setRoute(Route route) { this.route = route; }
	
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
    	g.setColor(Color.gray);
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
    	g.setColor(Color.green);
    	g.fillRect(0, 0, LARGAFFICHAGE, Route.POSITIONHORIZON);
    	//g.drawLine( 0, Modele.Route.POSITIONHORIZON, Vue.AffichageJeu.LARGAFFICHAGE, Modele.Route.POSITIONHORIZON);
    }

}
