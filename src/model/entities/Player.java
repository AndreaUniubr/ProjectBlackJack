package model.entities;

import model.balance.Balance;
import model.cards.Card;
import model.cards.Deck;
import model.game.Hand;

public class Player {
    private Balance balance;
    private final Deck deck;
    private Hand hand;
    private boolean isStand;
    private static int id = 0;

    public Player(String name, int initial_balance, Deck deck)
    {
        this.balance = new Balance(initial_balance);
        this.deck = deck;
        name = name + id;
        id++;
        //super(name);
        isStand = false;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public boolean play()
    {
        if (this.hand.getValue() <= 16)
        {
            Card c = deck.getCard();
            c.setFaceUp(true);
            this.hand.addCard(c);
        }

        return this.hand.getValue() > 16;
    }

    private void dealCard(boolean faceUp)
    {
        Card c = deck.getCard();
        c.setFaceUp(faceUp);
        hand.addCard(c);
    }

    public void card() { dealCard(true); }
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