package model.entities;

import model.balance.Balance;

public class Player {
    private Balance balance;
    private boolean isStand;

    public Player(String name, int initial_balance)
    {
        this.balance = new Balance(initial_balance);
        //super(name);
        isStand = false;
    }
/*package model.entities;

import model.game.Hand;

import java.util.ArrayList;

public abstract class Participant {
    private final String name;
    private ArrayList<Hand> hands;


    public Participant(String name)
    {
        resetHands();
        this.name = name;
    }

    public void addHand(Hand hand)
    {
        this.hands.add(hand);
    }

    public ArrayList<Hand> getHands()
    {
        return this.hands;
    }

    public void resetHands()
    {
        this.hands = new ArrayList<>();
    }

    public String getName()
    {
        return this.name;
    }
}*/
    /*
    * ogni player ha un balance e una sezione con le azioni, quando esegue azioni modifica la mano, ad ogni
    * puntata corrisponde un falore true o false in base a se vincente o meno,
    * */

    public Balance getBalance()
    {
        return balance;
    }

    // gestione controllo validità valori effettuata esternamente
    public void setBalance(Balance balance)
    {
        this.balance = balance;
    }

    public void setStand(boolean stand)
    {
        this.isStand = stand;
    }

    // se è impiedi o gioca
    public boolean isBust()
    {
        return isStand;
    }
}