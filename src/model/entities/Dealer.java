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

    // distribuzione prime due carte fisse
    public void card1() { dealCard(false); }
    public void card2() { dealCard(true); }

    private void dealCard(boolean faceUp)
    {
        Card c = deck.getCard();
        c.setFaceUp(faceUp);
        hand.addCard(c);
    }

    // mostra tutte le carte prima di iniziare a giocare
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

    // restore a new hand
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public Hand getHand()
    {
        return this.hand;
    }

    public int getValue()
    {
        return this.getHand().getValue();
    }
}
