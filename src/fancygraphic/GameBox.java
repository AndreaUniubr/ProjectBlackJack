package fancygraphic;

import controller.Controller;
import model.cards.Card;
import model.cards.Ranks;
import model.cards.Suits;
import model.game.Hand;
import view.State;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameBox extends JPanel {


    public GameBox() {

        //setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(600,280));
        setMaximumSize(new Dimension(600,280));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));
        Hand h = new Hand();
        Card c = new Card(Suits.CLUBS, Ranks.TEN);
        Card ca = new Card(Suits.SPADES, Ranks.FIVE);
        c.setFaceUp(true);
        ca.setFaceUp(true);
        h.addCard(c);
        h.addCard(ca);
        add(new CardDisplayer(h));
        FancyGenButton hitButton = new FancyGenButton("Hit");

        add(hitButton);
        FancyGenButton standButton = new FancyGenButton("Stand");
        add(standButton);
    }
}
