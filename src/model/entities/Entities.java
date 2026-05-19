package model.entities;

import model.cards.Card;
import model.cards.Deck;
import model.game.Hand;

public abstract class Entities {
    private static int id_gen = 0;
    private final int id;
    private final String name;
    protected Deck deck;

    public Entities(String name, Deck deck)
    {
        this.id = id_gen;
        id_gen++;
        this.name = name;
        this.deck = deck;
    }

    protected void dealCard(boolean faceUp)
    {
        Card c = deck.getCard();
        c.setFaceUp(faceUp);
        this.getHand().addCard(c);
    }

    public abstract Hand getHand();

    public boolean isBJ()
    {
        return getHand().getValue() == 21 && getHand().getCards().size() == 2;
    }

    public int getId() {return id;}

    public String getName() {return name;}
}
