package model.entities;

import model.cards.Card;
import model.game.Hand;
import model.cards.Deck;

public class Dealer{
    private final Deck deck;
    private Hand hand;

    public Dealer(Deck deck)
    {
        this.deck = deck;
    }

    // restore a new hand
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    private void dealCard(boolean faceUp) {
        Card c = deck.getCard();
        c.setFaceUp(faceUp);
        hand.addCard(c);
    }

    public void card1() { dealCard(false); }
    public void card2() { dealCard(true); }


    public void prePlay()
    {
        this.hand.revealCards();
    }

    // false continua a giocare
    // true ha finito
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

    public Hand getHand()
    {
        return this.hand;
    }

    public int getValue()
    {
        return this.hand.getValue();
    }
}
