package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import fancygraphic.GameBox;
import model.balance.Balance;
import model.cards.Deck;
import model.entities.Player;
import model.game.DealerBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static view.Colours.getTableColor;

public class PlayPage extends JPanel {
    private final Controller controller;
    private final Deck deck = new Deck();
    private final DealerBox dealerBox;
    private final GameBox gameBox;



    public PlayPage(Controller controller, int nPartecipanti, Balance balance)
    {
        dealerBox = new DealerBox(deck);
        gameBox = new GameBox(deck, balance);
        this.controller = controller;

        // per ora nPartecipanti inutilizzato

        setLayout(new BorderLayout());
        setBackground(getTableColor());

        add(createTop(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);
        add(createBottom(), BorderLayout.SOUTH);

        gameStart();
    }

    /*
    * Function that handle rounds:
    * Card 1 all players
    * Card 1 dealer
    * giocatori carta 2
    * dealer carta 2 coperta
    * player plays
    * dealer plays
    * check win
    * */
    private void gameStart()
    {

        // todo qui vannno implementate le puntate
        // todo aggiungi var puntata nelle mani

        iniGame();
        card1();
        card2();
        gameBox.setPlaying(true);

        // todo dio porco si è rotto lo split

        gameBox.addIsPlayingListener(evt -> {
            boolean newValue = (boolean) evt.getNewValue();
            if (!newValue) {

                dealerBox.play();
                dealerBox.addIsPlayingListener(evt1 -> {
                    // todo non ha accesso alla win perchè? liste3ner problema
                    // non vede listener
                    checkWin();
                });

            }
        });


        // todo:questa parte non cambia gioco bene, mettere pulsante ok a vincita e pulizia totale
        /*gameBox.addIsPlayingListener(evt -> {
            //gameBox.res();
            //dealerBox.res();
            gameStart();
        });*/
    }













    /*
    * todo:
    *  - implementare funzione true/false BJ
    * */



    // =========================
    // todo: sviluppare questa parte, ovvero mettere timer e altre fasi nel gioco
    // seguire le fasi vere come scritto sopra


    // todo da rifareeee

    public void checkWin()
    {
        int p1 = gameBox.getCD();
        int d = dealerBox.getCd();


// todo: fare costanti

// todo: fare in modo di considerare BJ e cose cosi
        if (p1 < 22)
        {
            if (p1 > d)
            {
                gameBox.setIsWin(1);
            }
            else
            {
                if (d < 22)
                    gameBox.setIsWin(2);
                else
                    gameBox.setIsWin(1);
            }
        }
        else
        {
            gameBox.setIsWin(2);
        }

        switch (gameBox.getIsWin()) {
            case 1:
                System.out.println("WIN");
                break;
            case 2:
                System.out.println("LOSE");
                break;
            default:
                System.out.println("Problema riscontrato");
                break;
        }
    }











    public void card2()
    {
        dealerBox.card2();
        gameBox.iniCard();
    }

    public void card1()
    {
        dealerBox.card1();
        gameBox.iniCard();
    }

    public void iniGame()
    {
        if (deck.getDim() < 35) deck.restore();
        gameBox.setPlaying(false);
        dealerBox.newHand();
        gameBox.newHand();
    }

    // TOP (DEALER)
    private JPanel createTop() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        panel.add(dealerBox);
        return panel;
    }

    //  CENTRO (SPAZIO)
    private JPanel createCenter() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    // BOTTOM (PLAYER + BAR)
    private JPanel createBottom() {

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        // PLAYER
        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        playerPanel.setOpaque(false);
        playerPanel.add(gameBox);

        wrapper.add(playerPanel, BorderLayout.CENTER);

        // STATUS BAR
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(Color.BLACK);
        statusBar.setPreferredSize(new Dimension(0, 40));
        JPanel leftBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBar.setOpaque(false);

        // PRENDI IL BACK DA GAMEBOX
        FancyGenButton backButton = new FancyGenButton("Back");

        // AZIONE (qui hai il controller)
        backButton.addActionListener(e ->
                 controller.setState(State.HOME)
        );

        leftBar.add(backButton);

        statusBar.add(leftBar, BorderLayout.WEST);

        JLabel amountLabel = new JLabel(
                "Amount: " + gameBox.getPlayer().getBalance().getSaldo()
        );
        amountLabel.setForeground(Color.WHITE);

        JPanel centerBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerBar.setOpaque(false);
        centerBar.add(amountLabel);

        statusBar.add(centerBar, BorderLayout.CENTER);

        wrapper.add(statusBar, BorderLayout.SOUTH);

        return wrapper;
    }
}