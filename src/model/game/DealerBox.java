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
        cd.updateCards();
    }

    public void iniCard()
    {
        Timer timer = new Timer(500, null); // 0.5 secondi

        timer.addActionListener(e -> {
            dealer.card1();
            cd.updateCards();
        });
        dealer.card2();
        cd.updateCards();
    }

    public void play()
    {
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
}
