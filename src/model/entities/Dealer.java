package model.entities;

import model.game.Hand;

public class Dealer extends Participant{

    public Dealer()
    {
        super("Dealer");
    }

    // override
    // il dealer puo avere solo una mano
    public void addHand(Hand hand)
    {
        if (super.getHands().isEmpty())
            super.addHand(hand);
    }
}
