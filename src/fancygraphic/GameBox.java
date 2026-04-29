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
    private final Player player;
    private final FancyGenButton hitButton;
    private final FancyGenButton standButton;
    private final FancyGenButton backButton;
    private boolean isPlaying;

    public GameBox(Deck deck) {

        setLayout(new BorderLayout());

        pcs.addPropertyChangeListener("isPlaying", evt -> updateButtons());

        player = new Player("Player", 1000, deck);
        setOpaque(false);
        setPreferredSize(new Dimension(600, 250));
        setMaximumSize(new Dimension(600, 250));
        setMinimumSize(new Dimension(600, 250));
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
        backButton = new FancyGenButton("Back");

       setPlaying(true);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean getIsPlaying()
    {
        return this.isPlaying;
    }

    //getter bottone back
    public FancyGenButton getBackButton() {
        return backButton;
    }
    public void newHand() {

        Hand h = new Hand();
        player.setHand(h);
        cd = new CardDisplayer(h);
        cd.setAlignmentX(Component.CENTER_ALIGNMENT);
        cd.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(cd);
        cd.updateCards();

    }

    public Player getPlayer(){
        return this.player;
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

    public void setPlaying(boolean value) {
        this.isPlaying = value;
        updateButtons();
    }

    private void updateButtons() {
        standButton.setVisible(isPlaying);
        hitButton.setVisible(isPlaying);
    }
}
