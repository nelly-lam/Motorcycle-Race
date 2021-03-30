package Modele;
import java.time.Duration;
import java.time.Instant;


public class MyTimer extends Observable {

	/*************ATTRIBUTS*************/
    /**debut timer**/
    private Instant debutTimer;
    
    /**Le temps mesure par le timer, correspond a ce qui va etre affiche **/
    private long time;

    /**Le debut de la pause**/
    private Instant debutPause;

    /** verification si time est en pause**/
    private boolean ifPause;
    

    /*************CONSTRUCTEUR*************/
    /** cree un timer qui se declenche direct a son initialisation */
    public MyTimer(long tps){
        this.debutTimer = Instant.now();
        this.time = tps;
        this.ifPause = false;
    }
  
    
    /*************METHODES*************/
    
    /** Getter et setters classiques */
    public Instant getDebutTimer() { return this.debutTimer; }
    public void setDebutTimer(Instant i) { this.debutTimer = i; }
    public long getTime(){ return this.time; }
    public void setTime(long l) { this.time = l; }
    public Instant getDebutPause() { return this.debutPause; }
    public void setDebutPause(Instant i) { this.debutPause = i; }
    public boolean getIfPause(){ return this.ifPause; }
    public void setIfPause(boolean b) { this.ifPause = b; }

    /**Methode pause():
     * mets en pause le timer
     * TODO : mettre la fenetre en pause, cad arret de la route, arret de la voiture, arret du temps
     */
    public void pause(){
        this.setIfPause(true);
        this.setDebutPause(Instant.now());
    }
    
    /**
     * Methode getTempsRestant():
     * indique le temps qui s'est ecoule depuis l'enclenchement du timer
     * @return une Duration
     */
    public Duration getTempsRestant(){
        if(this.getIfPause()){
        	//this.notifyObservers();
        	Duration.between(this.getDebutPause(), this.getDebutTimer()).toString();
            return Duration.between(this.getDebutPause(), this.getDebutTimer());
        } else {
        	//this.notifyObservers();
        	//Duration.between(this.getDebutPause(), this.getDebutTimer()).toString();
            return Duration.between(Instant.now(),this.getDebutTimer());
        }
    }
    
    /** fonctions pour faire avancer ou reculer le timer */

    /**
     * Methode avancerTimer():
     * incremente le temps de tps
     * @param tps de type long
     */
    public void avancerTimer(long tps){
        this.setTime(this.getTime() + tps);
    }
    
    /**
     * Methode reculerTimer():
     * decremente le temps a chaque seconde
     * @param tps de type long
     */
    public void reculerTimer(long tps){
    	this.setTime(this.getTime() - tps);
    }
    
    /**
     * Methode reprendreTimer():
     * 
     */
    public void reprendreTimer(){
        if(!this.getIfPause()){
            throw new RuntimeException("Probleme, le temps n'etait pas en pause");
        } else {
            this.setIfPause(false); //la pause est finie
            Instant restart = Instant.now();
            long pause = Duration.between(this.getDebutPause(), restart).toSeconds();
            this.setDebutTimer(this.getDebutTimer().plusSeconds(pause));
            //this.debutTimer = this.debutTimer.plusSeconds(pause);  
        }
    }
    
    /**
     * Methode timerOver():
     * 
     * @return
     */
    public boolean timerOver(){
        Duration duration = null;
        if(this.ifPause){
            duration = duration.between(this.getDebutTimer(), this.getDebutPause());
        } else {
            duration = duration.between(this.getDebutTimer(), Instant.now());
        }
        return duration.toSeconds() >= this.getTime();
    }
  
    /**
     * Methode toString():
     * affichage du temps qui s'ecoule
     */
    public String toString(){
        Duration d = this.getTempsRestant();
        int h = d.toHoursPart();
        int m = d.toMinutesPart();
        int sec = d.toSecondsPart();
        System.out.print("dans toString()");
        String str = "";
        if( h!=0 ) {
            str += String.valueOf(h) + ":";
        }
        if( m!=0 ) {
            str += String.valueOf(m) + ":";
        }
        if( sec!=0 ) {
            str += String.valueOf(sec);
        }
        return str;
    }
    
    public String humanReadableFormat(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }
    
    
    public void updateTime() {
    	
    }
    
    
    
    
}
    
    
