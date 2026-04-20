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

    public Card getCard()
    {
        return this.cards.removeFirst();
    }

    public void restore()
    {
        if (this.cards == null) this.cards = new ArrayList<>();

        this.cards.clear();

        for (int i = 0; i < N_MAZZI; i++)
            for (Suits seme : Suits.values())
                for (Ranks val : Ranks.values())
                    this.cards.add(new Card(seme, val));

        Collections.shuffle(this.cards);
    }

}
