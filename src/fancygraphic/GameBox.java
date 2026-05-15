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

    private final JPanel buttonPanel;
    private FancyGenButton hitButton;
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





    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);



    private final FancyGenButton standButton;
    private final FancyGenButton backButton;
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



        standButton = new FancyGenButton("Stand");
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlaying(false);
            }
        });



        backButton = new FancyGenButton("Back");

       setPlaying(true);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);


        buttonPanel.add(standButton);

        iniButtons();

        add(buttonPanel, BorderLayout.SOUTH);
    }

    //getter bottone back
    public FancyGenButton getBackButton() {
        return backButton;
    }
    public void newHand() {

        Hand h = new Hand();
        player.addHand(h);
        cd = new CardDisplayer(h);
        cd.setAlignmentX(Component.CENTER_ALIGNMENT);
        cd.setAlignmentY(Component.CENTER_ALIGNMENT);
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


    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }
    // todo un ini dei bottoni a visibile






    private void actionOnStand()
    {
        // todo tutte le azioni da fare con lo stand
    }










    public Player getPlayer()
    {
        return this.player;
    }

    private void updateButtons() {
        standButton.setVisible(isPlaying);
        hitButton.setVisible(isPlaying);
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

    public void iniButtons ()
    {
        // initialization
        hitButton = new FancyGenButton("Hit");
        splitButton = new FancyGenButton("Split");

        // Action listeners
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
                splitButton.setVisible(false);
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

        // adding to panel
        buttonPanel.add(hitButton);
        buttonPanel.add(splitButton);

    }
}
