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


    public DealerBox(Deck deck)
    {
        super(new Dealer(deck));
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









    private Hand h;



    public void newHand ()
    {
        isPlaying = false;
        h = new Hand();
        dealer.setHand(h);
        cd = new CardDisplayer(h);
        add(cd);
        cd.updateCards();
    }

    public void res()
    {
        //cd.terminate();
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

    public void play()
    {
        isPlaying = true;
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
    }

    public boolean isBlackJack()
    {
        return getCd() == 21 && h.getCards().size() == 2;
    }













    public void setPlaying(boolean value) {
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs.firePropertyChange("isPlaying", old, value);
    }
}
