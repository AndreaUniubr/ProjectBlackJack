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


    /*todo (macroargomenti basandosi su logica MVC in qui il controller gestisce ogni azione):
        -passaggio numero playerinizializzazione del game
        -risolvere problemi grafica e rendere flessibile
        -? impl giocatore bot
        - ca,biare tipo carte?
        - controllo finale, elliminazione metodi inutili, aggiunta di commenti, perfezionamento delle implementazioni
        -usare stream e lambda
    * */

    // todo deve salvare i dati tipo balance?


    /*
    * todo finale, ellimina ogni cosa inutile, commenta, perfeziona e usa stream e lambda se utili/possibile
    *  file/cartelle da fare (se non specificato si intende tutto)
    *   - controller
    *   - fancygraphic
    *   - view
    *       - BalancePage
    *       - DetailPage
    *       - HomePage
    *       - MainPage
    *       - PlayPage
    *       - state
    *   - main
    *   - balance
    *   - entities
    *   - game
    * */

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