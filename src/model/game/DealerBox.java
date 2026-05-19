package model.game;

import fancygraphic.CardDisplayer;
import model.cards.Deck;
import model.entities.Dealer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DealerBox extends Box {
    private final Dealer dealer;

    public DealerBox(Deck deck)
    {
        super();
        dealer = new Dealer(deck);
    }

    protected void graphicInit()
    {
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,200));
        setMaximumSize(new Dimension(250,200));

        // bordo oro
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));
    }

    public void card1()
    {
        dealer.card1();
        cd.updateCards();
    }

    public void card2()
    {
        dealer.card2();
        cd.updateCards();
    }

    public void newHand ()
    {
        isPlaying = false;
        Hand h = new Hand();
        dealer.setHand(h);
        if (cd == null)
        {
            cd = new CardDisplayer(h);
            add(cd);
        }
        else cd.setHand(h);
        cd.updateCards();
    }

    public void play()
    {
        isPlaying = (true);
        dealer.prePlay();
        cd.updateCards();

        Timer timer = new Timer(500, null); // 0.5 secondi

        timer.addActionListener(e -> {
            if (!dealer.play()) {
                cd.updateCards();
            } else {
                cd.updateCards();
                timer.stop();
            }
        });

        timer.start();
        setPlaying(false);
    }

    public boolean isBJ()
    {
        return dealer.isBJ();
    }

    public void setPlaying(boolean value) {
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs.firePropertyChange("isPlaying", old, value);
    }
}
