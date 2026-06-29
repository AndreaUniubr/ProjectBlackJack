package model.entities;

import model.cards.Card;
import model.cards.Deck;
import model.game.Hand;

// Base class for all game entities, such as players and dealers.
public abstract class GameEntity {
    private static int nextId = 0;
    private final int id;
    private final String name;
    protected Deck deck;

    public GameEntity(String name, Deck deck)
    {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.deck = deck;
    }

    protected void dealCard(boolean faceUp)
    {
        Card c = deck.drawCard();
        if (!faceUp) c.hide();
        this.getHand().addCard(c);
    }

    public abstract Hand getHand();

    // Returns true if the entity has a natural blackjack.
    public boolean isBJ()
    {
        return getHand().getValue() == 21 && getHand().getCards().size() == 2;
    }

    public int getId() {return id;}

    public String getName() {return name;}
}
