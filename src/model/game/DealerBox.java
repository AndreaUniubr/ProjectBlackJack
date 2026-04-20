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
        setPreferredSize(new Dimension(220,150)); // todo modificare le dimensioni qui
        setMaximumSize(new Dimension(220,150));

        // bordo oro
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));
    }

    public void newHand ()
    {
        Hand h = new Hand();
        dealer.addHand(h);
        cd = new CardDisplayer(h);
        add(cd);
    }

    public void play()
    {
        while(!dealer.play())
        {
            cd.updateCards();
        }
    }
}
