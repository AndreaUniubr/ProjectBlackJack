package model.entities;

import model.cards.Card;
import model.game.Hand;
import model.cards.Deck;

public class Dealer extends GameEntity {
    private Hand hand;

    public Dealer(Deck deck)
    {
        super("Dealer",deck);
    }

    // Deals the dealer's first hidden card.
    public void card1() { dealCard(false); }
    // Deals the dealer's second visible card.
    public void card2() { dealCard(true); }

    // Reveals all dealer cards before playing.
    public void prePlay()
    {
        hand.revealCards();
    }

    // Returns true when the dealer has finished playing.
    public boolean play()
    {
        if (hand.getValue() <= 16)
        {
            dealCard(true);
        }

        return hand.getValue() > 16;
    }

    // Assigns a new hand to the dealer.
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public int getValue()
    {
        return this.getHand().getValue();
    }

    public Hand getHand()
    {
        return this.hand;
    }
}
