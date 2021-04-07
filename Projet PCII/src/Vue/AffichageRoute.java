package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controleur.AvanceeTemps;
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
    	this.add(panel);
    	
    }
    
    /*************METHODES*************/
	public Moto getMoto() { return moto; }
	public Route getRoute() { return route; }
	public AvanceeTemps getTemps() { return temps; }
	public void setTemps(AvanceeTemps tps) { this.temps = tps; }
	
	
	/*
	private void dessinerEcranPause(Graphics g){
		g.setColor(Color.RED);
		g.fillRect(0,0,AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE);
		g.drawString("Pause (appuyer sur espace pour reprendre la partie)", 400,300);
	}
	*/
	

	public void paint(Graphics g) {
    	super.revalidate();
    	super.paint(g);
    	super.repaint();
    	
    	paintGrass(g);
    	paintRoute(g);
    	
    	try {
			paintObstacles(g);
		} catch (IOException e) {
			e.printStackTrace();
		}

    	paintCheckpoints(g);
    	
    	try {
			paintHorizon(g);
			paintMoto(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	paintTimer(g);
    	
    }

	/**
	 * Methode paintGrass() : colorie la pelouse
	 * @param g
	 */
	public void paintGrass(Graphics g) {
	   	g.setColor(Color.green);
    	g.fillRect(0, Route.POSITIONHORIZON, AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE);
	}

	/**
	 * Methode paintMoto() : affiche la moto et sa zone de collision
	 * @param g
	 * @throws IOException
	 */
    public void paintMoto(Graphics g) throws IOException {
    	g.setColor(Color.blue);
		Image img1 = ImageIO.read(new File("./src/Images/moto.png")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		g.drawRect((int) this.getMoto().getHautGauche().getX(), (int) this.getMoto().getHautGauche().getY(),
					(int) this.getMoto().getBasDroit().getX() - (int) this.getMoto().getHautGauche().getX(), (int) this.getMoto().getBasDroit().getY() - (int) this.getMoto().getHautGauche().getY());
		g.drawImage(img1, this.getMoto().getPositionX() - 15, this.getMoto().getPositionY() - 15, this);
		
    	//g.drawString("X", this.getMoto().getPositionX(), this.getMoto().getPositionY());
    }
    
    /**
     * Methode paintRoute() : colorie la route en gris
     * @param g
     */
    public void paintRoute(Graphics g) {
    	g.setColor(Color.gray);
    	
    	///////////////////////////////////CREATION D'UN POLYGONE/////////////////////////////
    	int[] pointsX = new int[this.getRoute().getListePointsG().size() + this.getRoute().getListePointsD().size()];
    	int[] pointsY = new int[this.getRoute().getListePointsG().size() + this.getRoute().getListePointsD().size()];

    	int compteur = 0;
    	
    	/*on ajoute tous les abscisses de listePointsG dans pointsX et ordonnees dans pointsY,
    	 * en partant du premier point de listePointsG
    	 */
    	for(int i = 0; i < this.getRoute().getListePointsG().size(); i++) {
    		pointsX[compteur] = (int) this.getRoute().getListePointsG().get(i).getX();
    		pointsY[compteur] = (int) this.getRoute().getListePointsG().get(i).getY();
    		compteur++;
    	}
    	
    	/*on ajoute tous les abscisses de listePointsD dans pointX et ordonnees dans pointsY,
    	 * en partant du dernier point de listePointD
    	 */
    	for(int j = this.getRoute().getListePointsD().size() - 1; j >= 0; j--) {
    		pointsX[compteur] = (int) this.getRoute().getListePointsD().get(j).getX();
    		pointsY[compteur] = (int) this.getRoute().getListePointsD().get(j).getY();
    		compteur++;
    	}
    	
    	g.fillPolygon(pointsX, pointsY, pointsX.length);
    	

    }
    
    /**
     * Methode paintHorizon() : affiche l'image de l'horizon
     * @param g
     * @throws IOException
     */
    public void paintHorizon(Graphics g) throws IOException {
    	//g.setColor(Color.blue);
    	//g.fillRect(0, 0, AffichageJeu.LARGAFFICHAGE, Route.POSITIONHORIZON);
    	//g.drawLine( 0, Modele.Route.POSITIONHORIZON, Vue.AffichageJeu.LARGAFFICHAGE, Modele.Route.POSITIONHORIZON);

		Image img = ImageIO.read(new File("./src/Images/fuji.png")).getScaledInstance(AffichageJeu.LARGAFFICHAGE, Route.POSITIONHORIZON, Image.SCALE_DEFAULT);
		g.drawImage(img, 0, 0, this);
    }

    /**
     * Methode paintObstacle() : affiche les obstacles de la route
     * @param g
     * @throws IOException
     */
    public void paintObstacles(Graphics g) throws IOException {
    	g.setColor(Color.red);
    	//Image img;
    	for(int i = 0; i < this.getRoute().getListeObstacles().size(); i++) {
    		//img = ImageIO.read(new File("./src/Images/cerisier.png")).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    		//g.drawImage(img, (int) this.getRoute().getListeObstacles().get(i).getX(), (int) this.getRoute().getListeObstacles().get(i).getY(), this);
    		g.drawString("X", (int) this.getRoute().getListeObstacles().get(i).getX(), (int) this.getRoute().getListeObstacles().get(i).getY());
    	}
    }
    
    /**
     * Methode paintCheckpoints() : affiche les checkpoints de la route
     * @param g
     */
    public void paintCheckpoints(Graphics g) {
    	g.setColor(Color.orange);
    	if(this.getRoute().getListeCheckpoints().size() != 0) {
    		for(int i = 0; i < this.getRoute().getListeCheckpoints().size(); i++) {
    			g.fillRect((int) this.getRoute().getListeCheckpoints().get(i).getHautGauche().getX(), 
    						(int) this.getRoute().getListeCheckpoints().get(i).getHautGauche().getY(),
    						(int) this.getRoute().getListeCheckpoints().get(i).getBasDroit().getX() - (int) this.getRoute().getListeCheckpoints().get(i).getHautGauche().getX(),
    						(int) this.getRoute().getListeCheckpoints().get(i).getBasDroit().getY() - (int) this.getRoute().getListeCheckpoints().get(i).getHautGauche().getY());
    		}
    	}
    }
    
    /**
     * Methode paintTimer() : affiche le temps restant avant la fin de la partie
     * @param g
     */
    public void paintTimer(Graphics g) {
    	g.setColor(Color.red);
    	g.drawString("Temps restant : ", AffichageJeu.LARGAFFICHAGE/2 - 50, 23);
    	g.drawString(String.valueOf(this.getTemps().getTempsEcoule()), AffichageJeu.LARGAFFICHAGE/2+40, 23);
    }

	@Override
    public void update() {
        this.requestFocusInWindow();
        repaint();
    }



}
