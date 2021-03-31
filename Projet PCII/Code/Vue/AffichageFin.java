package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Modele.Route;

public class AffichageFin extends JFrame{
	
	/*************CONSTANTES*************/
	private static final long serialVersionUID = 1L;
	
	/*************ATTRIBUTS*************/
	private final Route route;
	private String texte;
	
	/*************CONSTRUCTEUR*************/
	public AffichageFin(Route r, String str) {
		this.route = r;
		this.setTexte(str);
		
		this.setTitle("Fin de la partie");
    	this.setPreferredSize(new Dimension(AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE));
    	//this.setResizable(false);
		this.setFocusable(true);
		
		JPanel panel = new JPanel(null);
		
		/*
		ImageIcon img = new ImageIcon("./Code/Images/game-over_LI.jpg");
		img.getImage().getScaledInstance(AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE, Image.SCALE_DEFAULT);
		JLabel image = new JLabel(img);
		panel.add(image);
*/
		
		JLabel perdu = new JLabel("Vous avez perdu car : ");
		perdu.setBounds(300, 200, 400, 50);
		JLabel raison = new JLabel(str);
		raison.setBounds(300, 260, 400, 50);
		JLabel score = new JLabel("Votre score est de : ");
		score.setBounds(300, 320, 200, 50);
		JLabel nbScore = new JLabel(String.valueOf(this.route.getKilometre()));
		perdu.setBounds(410, 320, 100, 30);
		
/*
		perdu.setForeground(Color.WHITE);
		raison.setForeground(Color.WHITE);
		score.setForeground(Color.WHITE);
		nbScore.setForeground(Color.WHITE);
*/
		
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

	/*************METHODES*************/
	public Route getRoute() {
		return route;
	}
	
	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	
	public void paint(Graphics g) {
		Image img;
    	g.drawString("Vous avez perdu car : ", AffichageJeu.LARGAFFICHAGE/2 - 50, 50);
    	/*
    	g.drawString(this.getTexte(), AffichageJeu.LARGAFFICHAGE/2 - 50, 70);
    	g.drawString("Votre score est de : ", AffichageJeu.LARGAFFICHAGE/2 - 50, 120);
    	g.drawString(String.valueOf(this.route.getKilometre()), AffichageJeu.LARGAFFICHAGE/2 + 30, 120);
		*/
		try {
			img = ImageIO.read(new File("./Code/Images/game-over_LI.jpg")).getScaledInstance(AffichageJeu.LARGAFFICHAGE, AffichageJeu.HAUTAFFICHAGE, Image.SCALE_DEFAULT);
			g.drawImage(img, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.paintPerdu(g);
	}
	
	public void paintPerdu(Graphics g) {
		g.setColor(Color.WHITE);
    	//g.drawString("Vous avez perdu car : ", AffichageJeu.LARGAFFICHAGE/2 - 70, 70);
       	g.drawString(this.getTexte(), AffichageJeu.LARGAFFICHAGE/2 - 80, 90);
    	g.drawString("Votre score est de ", AffichageJeu.LARGAFFICHAGE/2 - 80, 120);
    	g.drawString(String.valueOf(this.route.getKilometre()), AffichageJeu.LARGAFFICHAGE/2 + 20, 120);

	}



}
