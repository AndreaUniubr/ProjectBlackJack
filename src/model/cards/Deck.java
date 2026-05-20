package model.cards;

import java.util.ArrayList;
import java.util.Collections;

/**Represents a deck composed of multiple standard card sets.   *
 * Concurrency is not required in this context because PlayPage *
 * guarantees that the deck is accessed without concurrency.    */
public class Deck {
    // we use a List instead of a queue to allow shuffle by Collections
    private final ArrayList<Card> cards;
    private static final int N_MAZZI = 4;

    public Deck ()
    {
        this.cards = new ArrayList<>();
        restore();
    }

    public int getDim()
    {
        return this.cards.size();
    }

    public Card getCard()
    {
        if (getDim() == 0) this.restore();
        return this.cards.removeFirst();
    }

    public void restore()
    {
        this.cards.clear();

        for (int i = 0; i < N_MAZZI; i++)
            for (Suit seme : Suit.values())
                for (Rank val : Rank.values())
                    this.cards.add(new Card(seme, val));

        Collections.shuffle(this.cards);
    }

}
