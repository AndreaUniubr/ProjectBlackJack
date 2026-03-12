package model.game;

import model.cards.Card;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;
    private int value;
    private PropertyChangeSupport support;

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

        this.setValore(ap);
    }

    public void setValore(int nuovoValore)
    {
        int vecchioValore = this.value;
        this.value = nuovoValore;
        support.firePropertyChange("valore", vecchioValore, nuovoValore);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(listener);
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
