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
import java.util.ArrayList;

import static model.game.Constants.BLACKJACK;
import static model.game.Constants.WIN;

public class GameBox extends Box {
    public JPanel buttonPanel;
    private FancyGenButton hitButton;
    private FancyGenButton standButton;
    private FancyGenButton splitButton;
    public FancyGenButton fOk = new FancyGenButton("OK");
    private final Player player;
    private int bet;
    private int totalBet;

    protected final PropertyChangeSupport pcs2 = new PropertyChangeSupport(this);

    public GameBox(Deck deck, Balance balance)
    {
        super();
        player = new Player("Player", balance, deck);
        this.bet = 0;

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

    private void nextHand()
    {
        this.cd.setHand(player.getHand());
        cd.updateCards();
        this.check2();
        setPlaying(true);
    }

    public int calcolaWin (int dealer, boolean bjd)
    {
        int sum = 0;
        for (Hand hand : player.getHands())
        {
            if (hand.getValue() > dealer)
            {
                if (player.isBJ())
                    sum += (int) (this.bet + this.bet * BLACKJACK);
                else
                    sum += (int) (this.bet + this.bet * WIN);
            }
            else if (hand.getValue() == dealer)// uguali
                if (!bjd || player.isBJ())
                    sum += bet;
        }

        player.pagaWin(sum);
        return sum;
    }

    public boolean setBet(int amount)
    {
        if (player.playsBet(amount))
        {
            bet = amount;
            totalBet = totalBet + amount;
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getBet()
    {
        return totalBet;
    }

    public void resetTotalBet()
    {
        totalBet = 0;
    }

    public boolean isPagable(int sum)
    {
        return sum >= this.getBet();
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
        splitButton.setVisible(player.isSplittable() && isPagable(player.getBalance().getSaldo()));
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
                if (player.isSplittable() && setBet(bet))
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

    public void setPlaying(boolean value) {
        boolean old = this.isPlaying;
        this.isPlaying = value;
        pcs2.firePropertyChange("isPlaying", old, value);
    }

    public void setPlayingFalse()
    {
        this.isPlaying = false;
        pcs.firePropertyChange("isPlaying", true, false);
        pcs2.firePropertyChange("isPlaying", true, false);
    }
}
