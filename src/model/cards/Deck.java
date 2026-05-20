package model.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
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
        if (getDim() < 1) this.restore();
        return this.cards.removeFirst();
    }

    public void restore()
    {
        if (this.cards == null) this.cards = new ArrayList<>();

        this.cards.clear();

        for (int i = 0; i < N_MAZZI; i++)
            for (Suit seme : Suit.values())
                for (Rank val : Rank.values())
                    this.cards.add(new Card(seme, val));

        Collections.shuffle(this.cards);
    }

}
