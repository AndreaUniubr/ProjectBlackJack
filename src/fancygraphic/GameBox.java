package fancygraphic;

import model.cards.Card;
import model.cards.Ranks;
import model.cards.Suits;
import model.game.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameBox extends JPanel {

    public GameBox() {
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
        CardDisplayer cd = new CardDisplayer(h);
        add(cd);
    }

}
