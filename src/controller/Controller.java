package controller;


import view.State;
import java.util.ArrayList;
import java.util.List;
import static view.State.*;

public class Controller {
    private State state = HOME;
    private List<StateObserver> observers = new ArrayList<>();

    public Controller()
    {}




    // todo pulsante
    // todo funzione che cambia da coso con pulsante a schermata gioco
    // todo pulsante bello
    // todo pulsante saldo
    // todo view saldo
    // todo deatils con nostri nomi


    public void addObserver(StateObserver observer) {
        observers.add(observer);
    }

    public void setState(State newState) {
        this.state = newState;
        notifyObservers();
    }

    private void notifyObservers() {
        for (StateObserver obs : observers) {
            obs.onStateChanged(state);
        }
    }

    public State getState()
    {
        return this.state;
    }
}
