package model.game;

import fancygraphic.CardDisplayer;
import model.cards.Deck;
import model.entities.Dealer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DealerBox extends Box {
    private final Dealer dealer;

    public DealerBox(Deck deck)
    {
        super();
        dealer = new Dealer(deck);
    }

    @Override
    protected void graphicInit()
    {
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,200));
        setMaximumSize(new Dimension(250,200));

        // gold border
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

    @Override
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
        setPlaying(true);

        dealer.prePlay();
        cd.updateCards();

        Timer timer = new Timer(500, null);

        timer.addActionListener(e -> {
            boolean finished = dealer.play();

            cd.updateCards();

            if (finished)
            {
                timer.stop();
                setPlaying(false);
            }
        });

        timer.setInitialDelay(500);
        timer.start();
    }

    @Override
    public boolean isBJ()
    {
        return dealer.isBJ();
    }

    @Override
    public void setPlaying(boolean value)
    {
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs.firePropertyChange("isPlaying", old, value);
    }
}
