package model.game;

import fancygraphic.CardDisplayer;
import fancygraphic.FancyGenButton;
import model.cards.Deck;
import model.balance.Balance;
import model.entities.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameBox extends Box {
    public JPanel buttonPanel;
    private FancyGenButton hitButton;
    private FancyGenButton standButton;
    private FancyGenButton splitButton;
    public FancyGenButton fOk = new FancyGenButton("OK");
    private final Player player;

    protected final PropertyChangeSupport pcs2 = new PropertyChangeSupport(this);

    public GameBox(Deck deck, Balance balance)
    {
        super();
        player = new Player("Player", balance, deck);

        pcs2.addPropertyChangeListener("isPlaying", evt -> updateButtons());
        graphicInit();
        iniButtons();
    }

    protected void graphicInit ()
    {
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 250));
        setMaximumSize(new Dimension(600, 250));
        setMinimumSize(new Dimension(600, 250));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));
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


    // todo quando cambia mano non mostra valore corretto
    private void nextHand()
    {

        // todo update anche var che mostra il valore
        this.cd.setHand(player.getHand());
        cd.updateCards();
        this.check2();
        setPlaying(true);
    }






    // da fare 2 volte
    public void iniCard()
    {
        player.card();
        cd.updateCards();
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
            setPlayingFalse();
        }
    }

    public boolean getIsPlaying()
    {
        return isPlaying;
    }

    // inizializzazione nuova mano
    public void newHand() {
        setPlaying(false);
        Hand h = new Hand();
        player.resetHands();
        player.addHand(h);
        if (cd == null)
        {
            cd = new CardDisplayer(h);
            add(cd);
        }
        else cd.setHand(h);
        cd.updateCards();
    }

    private void addCard()
    {
        player.addCard();
        cd.updateCards();
        if (player.getHand().getValue() > 21) actionOnStand();
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

    // if less than 2 cards, give some, used for first card in splits
    private void check2 ()
    {
        while (this.player.getHand().getCards().size() < 2)
        {
            this.player.card();
            cd.updateCards();
        }
    }

    public boolean isBJ()
    {
        return player.isBJ();
    }

    // initialization of all the buttons needed
    public void iniButtons ()
    {
        // initialization
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
                    updateButtons();
                }
            }
        });

        splitButton.setVisible(player.isSplittable());
        buttonPanel.setOpaque(false);
        fOk.setVisible(false);

        // adding to panel
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(splitButton);
        buttonPanel.add(fOk);

        add(buttonPanel, BorderLayout.SOUTH);
    }
   // todo setplaying viene chiamato ad ogni modifica di isplaying
    public void setPlaying(boolean value) {
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs2.firePropertyChange("isPlaying", old, value);
    }
    // todo setplaying false viene chiamato se smetto di giocare
    public void setPlayingFalse()
    {
        this.isPlaying = false;
        pcs.firePropertyChange("isPlaying", true, false);
        pcs2.firePropertyChange("isPlaying", true, false);
    }
}
