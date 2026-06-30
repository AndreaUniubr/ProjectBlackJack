package model.entities;

import model.balance.Balance;
import model.balance.Bet;
import model.cards.Deck;
import model.game.Hand;

import java.util.ArrayList;

public class Player extends GameEntity {
    private final Balance balance;
    private ArrayList<Hand> hands = new ArrayList<>();
    private ArrayList<Bet> bets = new ArrayList<>();
    private int position = 0;

    public Player(String name, Balance balance, Deck deck)
    {
        super(name, deck);
        this.balance = balance;
    }

    public boolean isSplittable()
    {
        if (this.hands.isEmpty()) return false;
        if (this.bets.get(position).getStandardBet() > this.balance.getSaldo()) return false;
        return (this.getHand().getCards().size() == 2) && (this.getHand().getCards().get(0).isEqualValue(this.getHand().getCards().get(1)));
    }

    public void split()
    {
        Hand h = new Hand();
        h.addCard(this.getHand().getCards().remove(1));
        addHand(h);
        addBet();
    }

    public void addHand(Hand hand)
    {
        this.hands.addLast(hand);
    }

    private void addBet()
    {
        this.bets.add(new Bet(this.bets.get(position).getStandardBet()));
    }

    public void resetHands()
    {
        this.hands = new ArrayList<>();
        this.bets = new ArrayList<>();
        position = 0;
    }

    // Places the initial bet if the player has enough balance.
    public boolean playsBet(int bet)
    {
        if (this.getBalance().getSaldo() >= bet)
        {
            this.getBalance().togliSoldi(bet);
            this.bets.add(new Bet(bet));
            return true;
        }else  return false;
    }

    public boolean editBet(int value)
    {
        if (this.getBalance().getSaldo() >= value)
        {
            this.bets.get(position).incrementBet(value);
            this.balance.togliSoldi(value);
            return true;
        }
        else return false;
    }

    public void pagaWin (int win)
    {
        this.getBalance().aggiungiSoldi(win);
    }

    public Bet getBetByPosition(int i)
    {
        return this.bets.get(i);
    }

    public Bet getBet()
    {
        return this.bets.get(position);
    }

    public Hand getHandByPosition(int i)
    {
        return this.hands.get(i);
    }

    public Hand getHand()
    {
        return this.hands.get(position);
    }

    // Deals the mandatory card.
    public void card()
    {
        dealCard(true);
    }

    // Deals one card if the hand has not busted yet.
    public void addCard()
    {
        if (this.getHand().getValue() <= 21)
        {
            card();
        }
    }

    public ArrayList<Hand> getHands() { return this.hands; }
    public int      getPosition()     { return this.position; }
    public void     incPosition()     { this.position++; }
    public int      getDim()          { return this.hands.size(); }
    public Balance  getBalance()      { return balance; }
}
