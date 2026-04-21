package fancygraphic;

import model.balance.Balance;
import model.cards.Card;
import model.cards.Deck;
import model.cards.Ranks;
import model.cards.Suits;
import model.entities.Player;
import model.game.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameBox extends JPanel {

    private CardDisplayer cd;
    private Player player;

    public GameBox(Deck deck) {
        player = new Player("Player", 1000, deck);
        setOpaque(false);
        setPreferredSize(new Dimension(600,280));
        setMaximumSize(new Dimension(600,280));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));

        Card c = new Card(Suits.HEARTS, Ranks.SIX);
        Card ca = new Card(Suits.SPADES, Ranks.QUEEN);
        c.setFaceUp(true);
        ca.setFaceUp(true);
        FancyGenButton hitButton = new FancyGenButton("Hit");
        FancyGenButton standButton = new FancyGenButton("Stand");
        add(hitButton);
        add(standButton);
    }

    public void newHand() {

        Hand h = new Hand();
        player.setHand(h);
        cd = new CardDisplayer(h);
        add(cd);
        cd.updateCards();

    }

    public void iniCard()
    {
        player.card();
        cd.updateCards();
        player.card();
        cd.updateCards();
    }

}
