package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.AvanceeTemps;
import Modele.Moto;
import Modele.Route;

public class AffichageRoute extends JPanel implements Observer{

	/*************CONSTANTES*************/
	private static final long serialVersionUID = 1L;
	
	/*************ATTRIBUTS*************/
    private final Moto moto;
    private final Route route;
    private AvanceeTemps temps;
    
    /*************CONSTRUCTEUR*************/
    public AffichageRoute(Moto m, Route r, AvanceeTemps t) {
    	this.moto = m;
    	this.route = r;
    	this.temps = t;
    	
    	this.route.addObserver(this);
    	
    	JPanel panel = new JPanel();
    	
    	/*
        JLabel title = new JLabel("Temps restant : ");
        title.setBounds(AffichageJeu.LARGAFFICHAGE/2+40, 200, 15, 80); //placer le label dans la fenetre
        title.setForeground(Color.black);
        panel.add(title);

        ImageIcon img = new ImageIcon(new ImageIcon("./Code/Images/fuji.png").getImage().getScaledInstance(AffichageJeu.LARGAFFICHAGE - 50, Route.POSITIONHORIZON, Image.SCALE_DEFAULT));
        JLabel background = new JLabel(img);
        background.setBounds(0,0,AffichageJeu.LARGAFFICHAGE, Route.POSITIONHORIZON);
        panel.add(background);
    	*/
        
    	this.add(panel);
    	
    }
    
    /*************METHODES*************/
	public Moto getMoto() { return moto; }
	public Route getRoute() { return route; }
	public AvanceeTemps getTemps() { return temps; }
	public void setTemps(AvanceeTemps tps) { this.temps = tps; }
	
	private void dessinerEcranPause(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0,0,AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE);
		g.drawString("Pause (appuyer sur espace pour reprendre la partie)", 400,300);
	}
	
	public void paint(Graphics g) {
    	super.revalidate();
    	super.paint(g);
    	super.repaint();
    	
    	try {
			paintMoto(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	paintRoute(g);
    	try {
			paintHorizon(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			paintObstacles(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	paintTimer(g);
    	
    }


    public void paintMoto(Graphics g) throws IOException {
    	//g.setColor(Color.RED);
		Image img1 = ImageIO.read(new File("./Code/Images/moto.png")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		g.drawImage(img1, this.getMoto().getPositionX() - 15, this.getMoto().getPositionY() - 15, this);
    	//g.drawString("X", this.getMoto().getPositionX(), this.getMoto().getPositionY());
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
    
    
    public void paintHorizon(Graphics g) throws IOException {
    	//g.setColor(Color.blue);
    	//g.fillRect(0, 0, AffichageJeu.LARGAFFICHAGE, Route.POSITIONHORIZON);

		Image img = ImageIO.read(new File("./Code/Images/fuji.png")).getScaledInstance(AffichageJeu.LARGAFFICHAGE, Route.POSITIONHORIZON, Image.SCALE_DEFAULT);
		g.drawImage(img, 0, 0, this);
    	//g.drawLine( 0, Modele.Route.POSITIONHORIZON, Vue.AffichageJeu.LARGAFFICHAGE, Modele.Route.POSITIONHORIZON);
    }

    
    public void paintObstacles(Graphics g) throws IOException {
    	g.setColor(Color.red);
    	//Image img;
    	for(int i = 0; i < this.getRoute().getListeObstacles().size(); i++) {
    		//img = ImageIO.read(new File("./Code/Images/cerisier.png")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    		//g.drawImage(img, (int) this.getRoute().getListeObstacles().get(i).getX(), (int) this.getRoute().getListeObstacles().get(i).getY(), this);
    		g.drawString("X", (int) this.getRoute().getListeObstacles().get(i).getX(), (int) this.getRoute().getListeObstacles().get(i).getY());
    	}
    }
    
    public void paintCheckpoints(Graphics g) {
    	g.setColor(Color.red);
    	for(int i = 0; i < this.getRoute().getListeCheckpoints().size()-1; i++) {
    		g.drawString("X", (int) this.getRoute().getListeCheckpoints().get(i).getX(), (int) this.getRoute().getListeCheckpoints().get(i).getY());
    	}
    }
    
    public void paintTimer(Graphics g) {
    	g.drawString("Temps restant : ", AffichageJeu.LARGAFFICHAGE/2 - 50, 23);
    	g.drawString(String.valueOf(this.getTemps().getTempsEcoule()), AffichageJeu.LARGAFFICHAGE/2+40, 23);
    }

	@Override
    public void update() {
        this.requestFocusInWindow();
        repaint();
    }



}
