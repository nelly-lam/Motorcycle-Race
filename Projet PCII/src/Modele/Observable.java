package Modele;

import Vue.Observer;
import java.util.ArrayList;

public abstract class Observable {
	
	/*************ATTRIBUTS*************/
    private ArrayList<Observer> observateurs;

    /*************CONSTRUCTEUR*************/
    public Observable() {
        this.observateurs = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        this.observateurs.add(o);
    }

    public void notifyObservers() {
        for (Observer o : this.observateurs) {
            o.update();
        }
    }
    
    
}
