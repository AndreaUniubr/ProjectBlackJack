package model.game;

import model.cards.Card;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Hand {
    private final ArrayList<Card> cards;
    private int value;
    private final PropertyChangeSupport support;

    public Hand()
    {
        this.cards = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public void addCard(Card c)
    {
        this.cards.add(c);
        this.calculateValue();
    }

    private void calculateValue()
    {
        int total = 0;
        int aces = 0;

        for (Card c : cards)
        {
            if (c.isFaceUp())
            {
                total += c.getRank().getMinValue();
                if (c.getRank().isAce())
                    aces++;
            }
        }

        while (aces > 0 && total + 10 <= 21)
        {
            total += 10;
            aces--;
        }
        this.setValue(total);
    }

    // Updates the hand value and notifies listeners.
    public void setValue(int nuovoValore)
    {
        int vecchioValore = this.value;
        this.value = nuovoValore;
        support.firePropertyChange("value", vecchioValore, nuovoValore);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        support.removePropertyChangeListener(listener);
    }

    public void revealCards()
    {
        for (Card c : this.getCards())
        {
            c.reveal();
        }
        calculateValue();
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
