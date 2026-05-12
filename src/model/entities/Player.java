package model.entities;

import model.balance.Balance;
import model.cards.Card;
import model.cards.Deck;
import model.game.Hand;

import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private final String name;
    private Balance balance;
    private Deck deck;
    private boolean isStand;
    private static int id = 0;
    private Queue<Hand> hands = new LinkedList<>();

    public Player(String name, Balance balance, Deck deck)
    {
        this.balance = balance;
        this.deck = deck;
        this.name = name + id;
        id++;
        isStand = false;
    }

    public void addCard()
    {
        if (this.getHand().getValue() <= 21)
        {
            Card c = this.deck.getCard();
            c.setFaceUp(true);
            this.getHand().addCard(c);
        }
    }

    // give obligatory card
    public void card()
    {
        Card c = this.deck.getCard();
        c.setFaceUp(true);
        this.getHand().addCard(c);
    }

    public boolean isSplittable()
    {
            return (this.getHand().getCards().size() == 2) && (this.getHand().getCards().get(0).getRank() == this.getHand().getCards().get(1).getRank());
    }

    // todo chech by pio
    public void split ()
    {
        Hand h = new Hand();
        h.addCard(this.getHand().getCards().remove(1));
        Card c = this.deck.getCard();
        c.setFaceUp(true);
        h.addCard(c);
        this.hands.add(h);
        c = this.deck.getCard();
        c.setFaceUp(true);
        this.getHand().getCards().add(c);
    }

    public int getDim()
    {
        return this.hands.size();
    }

    public Hand getHand()
    {
        return this.hands.peek();
    }

    public void removeHand()
    {
        this.hands.poll();
    }

    public void addHand(Hand hand)
    {
        this.hands.add(hand);
    }

    public void resetHands()
    {
        this.hands = new LinkedList<>();
    }

    // 1 = ok 0 = not ok
    public boolean playsBet(int bet)
    {
        if (this.getBalance().getSaldo() >= bet)
        {
            this.getBalance().togliSoldi(bet);
            return true;
        }
        return false;
    }

    public void pagaWin (double typeWin, int bet)
    {
        this.getBalance().aggiungiSoldi((int) (bet * typeWin));
    }

    public void setStand(boolean stand)
    {
        this.isStand = stand;
    }

    // se è in piedi o meno
    public boolean isBust()
    {
        return isStand;
    }

    public Balance getBalance()
    {
        return balance;
    }

    public String getName()
    {
        return name;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }
}
