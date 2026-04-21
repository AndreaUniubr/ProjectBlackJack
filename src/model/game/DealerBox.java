package model.game;

import fancygraphic.CardDisplayer;
import model.cards.Deck;
import model.entities.Dealer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DealerBox extends JPanel {
    private CardDisplayer cd;
    private final Dealer dealer;

    public DealerBox(Deck deck)
    {
        dealer = new Dealer(deck);

        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,170)); // todo modificare le dimensioni qui
        setMaximumSize(new Dimension(250,170));

        // bordo oro
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));

        //todo metti scritta dealer
    }

    public void newHand ()
    {
        Hand h = new Hand();
        dealer.setHand(h);
        cd = new CardDisplayer(h);
        add(cd);
    }

    public void play()
    {
        dealer.prePlay();
        cd.updateCards();

        //while(!dealer.play())
        //{
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
            //preplay
            //dealer.getHands().getFirst().calcola();
            //cd.updateCards();
        //}
    }
}
