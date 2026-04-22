package fancygraphic;

import model.cards.Deck;
import model.entities.Player;
import model.game.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;

public class GameBox extends JPanel {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private CardDisplayer cd;
    private Player player;
    private FancyGenButton hitButton;
    private FancyGenButton standButton;
    private boolean isPlaying;

    public GameBox(Deck deck) {
        pcs.addPropertyChangeListener("isPlaying", evt -> updateButtons());

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
                setPlaying(false);
            }
        });


       setPlaying(true);

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

    public void setPlaying(boolean value) {
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs.firePropertyChange("isPlaying", old, value);
    }

    private void updateButtons() {
        standButton.setVisible(isPlaying);
        hitButton.setVisible(isPlaying);
    }



}
