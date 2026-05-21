package model.cards;

import java.util.ArrayList;
import java.util.Collections;

/**Represents a deck composed of multiple standard card sets.   *
 * Concurrency is not required in this context because PlayPage *
 * guarantees that the deck is accessed without concurrency.    */
public class Deck {
    // we use a List instead of a queue to allow shuffle by Collections
    private final ArrayList<Card> cards;
    private static final int DECK_COUNT = 4;

    public Deck ()
    {
        this.cards = new ArrayList<>();
        restore();
    }

    public int getSize()
    {
        return this.cards.size();
    }

    public Card drawCard()
    {
        if (getSize() == 0) this.restore();
        return this.cards.removeFirst();    // valid, because from java 21 removeFirst is implemented in arraylists
    }

    public void restore()
    {
        this.cards.clear();

        for (int i = 0; i < DECK_COUNT; i++)
            for (Suit suit : Suit.values())
                for (Rank rank : Rank.values())
                    this.cards.add(new Card(suit, rank));

        Collections.shuffle(this.cards);
    }

}
