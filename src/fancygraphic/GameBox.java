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

    private JPanel buttonPanel;
    private FancyGenButton hitButton;
    private FancyGenButton standButton;
    private FancyGenButton splitButton;





    /*
    * questa box, deve mostrare le carte attuali, le opzioni di gioco
    * scorrere tra eventuali mani aggiuntive, avere listener per ogni fase di gioco
    * (pre, giocando, passando, fine ecc)
    * in piu funzioni di scorrimento e pagamento/riscatto crediti
    * inoltre deve gestire eventuali compari/scompari dei bottoni
    *
    * nella fase di vincita/check vincite, dovrebbe scorrerli tutti e mettere risultato....
    * */


/*
* i turni devono essere:
*  giocatori carta 1
*  dealer carta 1
* giocatori carta 2
 *  dealer carta 2 coperta
 * player gioca
 * dealer gioca
* */


    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);




    private boolean isPlaying;

    /*todo
    * il game box, deve aggiungere i nuovi pulsanti, ognuno con i suoi listener, controlli col player ecc
    * implementare lo split, con la funzione di igiv1card
    * il game box deve inoltre poter ciclare quando si passa tra eventuali altri Hand e giocare in tutti
    * */

    // todo quando arriva ad una mano nuova, check2

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

        player = new Player("Player", new Balance(1000), deck);
        setOpaque(false);
        setPreferredSize(new Dimension(600, 250));
        setMaximumSize(new Dimension(600, 250));
        setMinimumSize(new Dimension(600, 250));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212,175,55), 3),
                new EmptyBorder(8,8,8,8)
        ));


        iniButtons();


       setPlaying(true);

    }





    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }





    private void actionOnStand()
    {
        setPlaying(false);
        // todo tutte le azioni da fare con lo stand
         // ci deve essere un if, se no tutti fatti no set ma solo visible del split
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
