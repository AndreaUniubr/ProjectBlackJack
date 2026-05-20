package model.entities;

import model.cards.Card;
import model.game.Hand;
import model.cards.Deck;

public class Dealer extends Entities{
    private Hand hand;

    public Dealer(Deck deck)
    {
        super("Dealer",deck);
    }

    // distribuzione prime due carte fisse
    public void card1() { dealCard(false); }
    public void card2() { dealCard(true); }

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
            this.hand.addCard(c);
        }

        return this.hand.getValue() > 16;
    }

    // restore a new hand
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
