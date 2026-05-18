package fancygraphic;

import model.cards.Deck;
import model.balance.Balance;
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
    private CardDisplayer cd;
    private final Player player;

    private FancyGenButton hitButton;
    private FancyGenButton standButton;
    private FancyGenButton splitButton;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final PropertyChangeSupport pcs2 = new PropertyChangeSupport(this);

    private boolean isPlaying;


    public GameBox(Deck deck, Balance balance)
    {
        setLayout(new BorderLayout());

        pcs.addPropertyChangeListener("isPlaying", evt -> updateButtons());

        player = new Player("Player", balance, deck);

        graphicInit();
        iniButtons();
    }

    private void graphicInit ()
    {
        setOpaque(false);
        setPreferredSize(new Dimension(600, 250));
        setMaximumSize(new Dimension(600, 250));
        setMinimumSize(new Dimension(600, 250));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));
    }

    public void addIsPlayingListener(PropertyChangeListener l)
    {
        pcs2.addPropertyChangeListener("isPlaying", l);
    }

    public void setPlaying(boolean value) {
        updateButtons();
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs.firePropertyChange("isPlaying", old, value);
        pcs2.firePropertyChange("isPlaying", old, value);
    }











    /*
    * todo: la fase di controllo vincite è da rifare:
    *  - deve scorrere tra tutte le mani
    *  - deve uscire la scritta vinto oppure no, almeno per un tot secondi
    * */

    private int isWin = 0; // 0 = in game, 1 = win, 2 = lose, 3 = BJ




    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }

    // todo, show nome player???

    // end todo










// todo non cancella split
    // e non mette valore corretto
    private void nextHand()
    {
        updateButtons();
        this.cd.setHand(player.getHand());
        cd.updateCards();
        this.check2();
        cd.updateCards();
        setPlaying(true);
    }









    private void actionOnStand()
    {
        if (player.getPosition() != player.getDim() - 1)
        {
            player.incPosition();
            nextHand();
        }
        else
        {
            setPlaying(false);
        }
    }

    public boolean getIsPlaying()
    {
        return isPlaying;
    }

    // da fare 2 volte
    public void iniCard()
    {
        player.card();
        cd.updateCards();
    }

    // inizializzazione nuova mano
    public void newHand() {
        Hand h = new Hand();
        player.resetHands();
        player.addHand(h);
        cd = new CardDisplayer(h);
        cd.setAlignmentX(Component.CENTER_ALIGNMENT);
        cd.setAlignmentY(Component.CENTER_ALIGNMENT);
        add(cd);
        cd.updateCards();
    }

    public void res()
    {
        cd.terminate();
    }

    public Player getPlayer()
    {
        return this.player;
    }

    private void updateButtons() {
        standButton.setVisible(isPlaying);
        hitButton.setVisible(isPlaying);
        splitButton.setVisible(player.isSplittable());
    }

    public int getCD()
    {
        return this.cd.getValue();
    }

    // if less than 2 cards, give some, used for first card in splits
    private void check2 ()
    {
        while (this.player.getHand().getCards().size() < 2)
            this.player.card();
    }

    private void addCard()
    {
        player.addCard();
        cd.updateCards();
        if (player.getHand().getValue() > 21) actionOnStand();
    }

    // initialization of all the buttons needed
    public void iniButtons ()
    {
        // initialization
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        hitButton = new FancyGenButton("Hit");
        standButton = new FancyGenButton("Stand");
        splitButton = new FancyGenButton("Split");

        // Action listeners
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
                splitButton.setVisible(false);
            }
        });
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionOnStand();
            }
        });
        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.isSplittable())
                {
                    player.split();
                    addCard();
                }
            }
        });

        splitButton.setVisible(player.isSplittable());
        buttonPanel.setOpaque(false);

        // adding to panel
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(splitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
