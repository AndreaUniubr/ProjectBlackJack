package controller;


import view.State;
import java.util.ArrayList;
import java.util.List;
import static view.State.*;

public class Controller {
    private State state = HOME;
    private List<StateObserver> observers = new ArrayList<>();

    private int turno;
    private int nPartecipanti;
    private boolean giveCards;

    public Controller()
    {
        this.nPartecipanti = 1; // todo possibile modificarlo
        this.turno = 0;
        this.giveCards = true;
    }
// todo risolvi problema importantissimo, se riduci schermo non vedi niente
// todo carta puo essere a faccia in giu


    public int getnPartecipanti()
    {
        return nPartecipanti;
    }

    public void setnPartecipanti(int nPartecipanti)
    {
        this.nPartecipanti = nPartecipanti;
    }

    public boolean isGiveCards()
    {
        return giveCards;
    }

    public void setGiveCards(boolean giveCards) {
        this.giveCards = giveCards;
    }

    public int getTurno ()
    {
        return this.turno;
    }

    // incrementa il turno di 1
    public void setTurno ()
    {
        if (turno == nPartecipanti) turno = 0;
        else turno += 1;
    }

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
