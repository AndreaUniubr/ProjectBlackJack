package model.entities;

import model.game.Hand;

import java.util.ArrayList;

public abstract class participant {
    private final String name;
    private ArrayList<Hand> hands;


    public participant(String name)
    {
        this.hands = new ArrayList<>();
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
}