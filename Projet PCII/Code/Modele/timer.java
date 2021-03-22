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
    public MyTimer(long tps){
        this.startTimer = Instant.now();
        this.time = tps;
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
    
    /** fonction pour reprendre timer */
    public void reprendreTimer(){
        if(this.pause == false){
            throw new RuntimeException("Problème");
        } else {
            this.pause = false;
            Instant restart = Instant.now();
            long pause = Duration.between(this.startPause, restart).toSeconds();
            this.startTimer = this.startTimer.plusSeconds(pause);  
        }
    }
    
    public boolean timerOver(){
        duration duration;
        if(pause){
            d = duration.between(this.debutTimer, this.debutPause);
        } else {
            d = duration.between(this.debutTimer, Instant.now());
        }
        return duration.toSeconds() >= this.time;
    
    }
  
