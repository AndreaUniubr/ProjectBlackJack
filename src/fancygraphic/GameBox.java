package fancygraphic;

import model.cards.Deck;
import model.entities.Player;
import model.game.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameBox extends JPanel {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    private CardDisplayer cd;
    private final Player player;
    private final FancyGenButton hitButton;
    private final FancyGenButton standButton;
    private final FancyGenButton backButton;
    private boolean isPlaying;

    /*todo
    * il game box, deve aggiungere i nuovi pulsanti, ognuno con i suoi listener, controlli col player ecc
    * implementare lo split, con la funzione di igiv1card
    * il game box deve inoltre poter ciclare quando si passa tra eventuali altri Hand e giocare in tutti
    * */

    private final PropertyChangeSupport pcs2 = new PropertyChangeSupport(this);

    public void setPlaying(boolean value) {
        updateButtons();
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs.firePropertyChange("isPlaying", old, value);
        pcs2.firePropertyChange("isPlaying", old, value);

        // todo controlla stand
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public void addIsPlayingListener(PropertyChangeListener l) {
        pcs2.addPropertyChangeListener("isPlaying", l);
    }

    private int isWin = 0; // 0 = in game, 1 = win, 2 = lose, 3 = BJ

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

    private void updateButtons() {
        standButton.setVisible(isPlaying);
        hitButton.setVisible(isPlaying);
    }

    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }

    public int getCD()
    {
        return this.cd.getValue();
    }
}
