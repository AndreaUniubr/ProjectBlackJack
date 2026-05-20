package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import model.game.GameBox;
import model.balance.Balance;
import model.cards.Deck;
import model.game.DealerBox;

import javax.swing.*;
import java.awt.*;

import static view.Colours.getTableColor;

public class PlayPage extends JPanel {
    private final Controller controller;
    private final Deck deck = new Deck();
    private final DealerBox dealerBox;
    private final GameBox gameBox;
    private FancyGenButton backButton;



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
        // ricordati split usa cashhhhh


        // todo no timer quiiiiiiii
        iniGame();
        card1();
        card2();
        gameBox.setPlaying(true);

        // todo importantissimo, ad un certo punto si bugga tutto
        // todo ad una certa si è buggato e ha dato una carta in piu al dealer

        gameBox.addIsPlayingListener(evt -> {
            boolean newValue = (boolean) evt.getNewValue();
            if (!newValue) {
                dealerBox.addIsPlayingListener(evt1 -> {
                    checkWin();
// no va bene ini qui listeber
                    gameBox.fOk.addActionListener(evt2 -> {
                        gameStart();
                        gameBox.fOk.setVisible(false);
                    });
                    gameBox.fOk.setVisible(true);

                });
                dealerBox.play();
            }
        });
    }










    public void checkWin()
    {
        int d = dealerBox.getCd();
        int win = gameBox.calcolaWin(d,dealerBox.isBJ());

        // todo mostra questo valore
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
        statusBar.setPreferredSize(new Dimension(0, 60));
        JPanel leftBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBar.setOpaque(false);

        // BACK
        backButton = new FancyGenButton("Back");

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

        //centerBar.add(amountLabel);

        statusBar.add(centerBar, BorderLayout.CENTER);

        wrapper.add(statusBar, BorderLayout.SOUTH);

        return wrapper;
    }
}