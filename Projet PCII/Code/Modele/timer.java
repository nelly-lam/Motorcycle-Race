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

  
