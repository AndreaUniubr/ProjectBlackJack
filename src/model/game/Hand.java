package model.game;

import model.cards.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private int value;

    public Hand()
    {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card c)
    {
        this.cards.add(c);
        this.calcola();
    }

    private void calcola()
    {
        int ap = 0;
        for (Card c: cards)
        {
            ap += c.getRank().getMaxValue();
        }
        if (ap > 21)
        {
            ap = 0;
            for (Card c: cards)
            {
                ap += c.getRank().getMinValue();
            }
        }

        this.value = ap;
    }

    public int getValue()
    {
        return this.value;
    }

    public ArrayList<Card> getCards()
    {
        return this.cards;
    }
}
