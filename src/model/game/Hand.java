package model.game;

import model.cards.Card;
import model.cards.Ranks;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards;
    private int value;
    private final PropertyChangeSupport support;

    private int app = 0;

    public Hand()
    {
        this.cards = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public void addCard(Card c)
    {
        this.cards.add(c);
        this.calcola();
    }

    private void calcola()
    {
        int totale = 0;
        int assi = 0;

        for (Card c : cards)
        {
            if (c.isFaceUp())
            {
                totale += c.getRank().getMinValue();
                if (c.getRank() == Ranks.ACE)
                    assi++;
            }
        }

        while (assi > 0 && totale + 10 <= 21)
        {
            totale += 10;
            assi--;
        }
        this.setValore(totale);
    }

    public void setValore(int nuovoValore)
    {
        int vecchioValore = this.value;
        this.value = nuovoValore;
        support.firePropertyChange("value", vecchioValore, nuovoValore);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(listener);
    }

    public void revealCards()
    {
        for (Card c : this.getCards())
        {
            c.setFaceUp(true);
        }
        calcola();
    }

    public int getValue()
    {
        this.calcola();
        return this.value;
    }

    public ArrayList<Card> getCards()
    {
        return this.cards;
    }
}
