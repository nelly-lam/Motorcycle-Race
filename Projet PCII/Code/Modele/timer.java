import java.time.Duration;
import java.time.Instant;



public class MyTimer {

    /** debut timer**/
    private Instant debutTimer;

    /**Le temps mesuré par le timer **/
    private long time;

    /**Le debut de la pause**/
    private Instant debutPause;

    /** vérification si timer Est en pause**/
    private boolean pause;


    /** crée un timer qui se déclenche direct à son initialisation */
    public MyTimer(long sec){
        this.startTimer = Instant.now();
        this.time = sec;
        this.pause = false;
    }
  
    /** fonction pause qui mets en pause le timer*/
  
    public void pause(){
        this.pause = true;
        this.startPause = Instant.now();
    }
    
    /** Getter et setters classiques */
    
    public boolean getifPaused(){
        return this.pause;
    }

    /** Renvoie le temps du timer */
    public long getTimer(){
        return this.time;
    }
    
    /** fonctions pour faire avancer ou reculer le timer */

    public void avancerTimer(long tps){
        this.time() += tps;
    }
    
    public void reculerTimer(long tps){
        this.time() -= tps;
    }
    
    
    
  
