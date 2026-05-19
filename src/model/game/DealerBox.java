package model.game;

import fancygraphic.CardDisplayer;
import model.cards.Deck;
import model.entities.Dealer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DealerBox extends JPanel {
    private CardDisplayer cd;
    private final Dealer dealer;
    private Hand h;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean isPlaying;

    public DealerBox(Deck deck)
    {
        dealer = new Dealer(deck);

        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,200));
        setMaximumSize(new Dimension(250,200));

        // bordo oro
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));

        isPlaying = false;

    }

    public void addIsPlayingListener(PropertyChangeListener l)
    {
        pcs.addPropertyChangeListener("isPlaying", l);
    }

    public void newHand ()
    {
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

    public int getCd()
    {
        return this.cd.getValue();
    }
}
