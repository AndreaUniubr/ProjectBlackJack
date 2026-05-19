package model.entities;

import model.balance.Balance;
import model.cards.Card;
import model.cards.Deck;
import model.game.Hand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player extends Entities{
    private final Balance balance;
    private boolean isStand;

    private ArrayList<Hand> hands = new ArrayList<>();
    private int position = 0;

    public Player(String name, Balance balance, Deck deck)
    {
        this.balance = balance;
        super(name, deck);
        isStand = false;
    }

    public void addCard()
    {
        if (this.getHand().getValue() <= 21)
        {
            card();
        }
    }

    // give obligatory card
    public void card()
    {
        dealCard(true);
    }

    public boolean isSplittable()
    {
            if (this.hands.isEmpty()) return false;
            return (this.getHand().getCards().size() == 2) && (this.getHand().getCards().get(0).getRank().getMaxValue() == this.getHand().getCards().get(1).getRank().getMaxValue());
    }

    public void split()
    {
        Hand h = new Hand();
        h.addCard(this.getHand().getCards().remove(1));
        addHand(h);
    }

    public void incPosition()
    {
        this.position++;
    }

    public int getDim()
    {
        return this.hands.size();
    }

    public void addHand(Hand hand)
    {
        this.hands.addLast(hand);
    }

    public void resetHands()
    {
        this.hands = new ArrayList<>();
        position = 0;
    }

    public ArrayList<Hand> getHands()
    {
        return this.hands;
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

    public void pagaWin (int win)
    {
        this.getBalance().aggiungiSoldi(win);
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

    public int getPosition()
    {
        return this.position;
    }

    public Hand getHand()
    {
        return this.hands.get(position);
    }
}
