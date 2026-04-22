package fancygraphic;

import model.balance.Balance;
import model.cards.Card;
import model.cards.Deck;
import model.cards.Ranks;
import model.cards.Suits;
import model.entities.Player;
import model.game.Hand;
import view.State;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBox extends JPanel {

    private CardDisplayer cd;
    private Player player;
    private FancyGenButton hitButton;
    private FancyGenButton standButton;
    private boolean isPlaying;

    public GameBox(Deck deck) {
        player = new Player("Player", 1000, deck);
        setOpaque(false);
        setPreferredSize(new Dimension(600,280));
        setMaximumSize(new Dimension(600,280));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));

        hitButton = new FancyGenButton("Hit");
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
            }
        });
        standButton = new FancyGenButton("Stand");
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPlaying = false;
                stand();
            }
        });
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

    public void addCard()
    {
        player.addCard();
        cd.updateCards();
    }

    public void stand(){
        standButton.setVisible(false);
        hitButton.setVisible(false);
    }


}
