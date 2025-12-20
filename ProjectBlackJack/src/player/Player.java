package player;

import balance.Balance;
import game.Hand;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Balance balance;
    private List<Hand> hands;
    private boolean isStand;

    public Player(String name, double starting_founds) {
        this.setName(name);
        this.balance = new Balance(starting_founds);
        resetHands();
    }

    // cosa bisognia fare qui?
    //public action choseAction(Hand){}

    public void resetHands()
    {
        this.hands = new ArrayList<Hand>();
    }

    public void addHand(Hand hand)
    {
        this.hands.add(hand);
    }

    public List<Hand> getHands()
    {
        return this.hands;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Balance getBalance()
    {
        return balance;
    }

    public void setBalance(Balance balance)
    {
        this.balance = balance;
    }

    //????
    public boolean isBust() {
        return isStand;
    }
}
