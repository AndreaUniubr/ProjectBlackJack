package model.entities;

import model.game.Hand;
import model.cards.Deck;

public class Dealer extends Participant{

    private final Deck deck;

    public Dealer(Deck deck) {
        super("Dealer");
        this.deck = deck;

    }

    // override
    // il dealer puo avere solo una mano
    public void addHand(Hand hand)
    {
        if (super.getHands().isEmpty())
            super.addHand(hand);
    }
    // false continua a giocare
    // true ha finito
    public boolean play() {
        if (super.getHands().getFirst().getValue() <= 16)
            super.getHands().getFirst().addCard(deck.getCard());
        return super.getHands().getFirst().getValue() > 16;

    }

    public int getValue() {

        return getHands().getFirst().getValue();

    }
}
