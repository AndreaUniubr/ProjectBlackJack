package view;

import controller.Controller;
import fancygraphic.FancyFichesButton;
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
    private FancyFichesButton fichesButtonTen;
    private FancyFichesButton fichesButtonTwenty;
    private FancyFichesButton fichesButtonFifty;
    private FancyFichesButton fichesButtonHundred;
    private FancyFichesButton fichesButtonTwoHundred;
    private FancyFichesButton fichesButtonFiveHundred;
    private FancyFichesButton fichesButtonThousand;

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
        statusBar.setOpaque(false);
        statusBar.setPreferredSize(new Dimension(0, 60));
        // STATUS BAR LEFT
        JPanel leftBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftBar.setOpaque(false);

        backButton = new FancyGenButton("Back");
        backButton.addActionListener(e ->
                 controller.setState(State.HOME)
        );
        leftBar.add(backButton);
        statusBar.add(leftBar, BorderLayout.WEST);
        // STATUS BAR CENTER
        JPanel centerBar = new JPanel(new FlowLayout(FlowLayout.CENTER));


        fichesButtonTen = new FancyFichesButton(107, 422, 160, 154);
        fichesButtonTwenty = new FancyFichesButton(289, 423, 160, 154);
        fichesButtonFifty = new FancyFichesButton(469, 422, 160, 154);
        fichesButtonHundred = new FancyFichesButton(642, 423, 160, 154);
        fichesButtonTwoHundred = new FancyFichesButton(814, 423, 160, 154);
        fichesButtonFiveHundred = new FancyFichesButton(986, 422, 160, 154);
        fichesButtonThousand = new FancyFichesButton(1162, 422, 160, 154);




        centerBar.setOpaque(false);
        //centerBar.setPreferredSize(new Dimension(600, 60));
        //centerBar.setMaximumSize(new Dimension(600, 60));
        //centerBar.setMinimumSize(new Dimension(600, 60));
        centerBar.add(fichesButtonTen);
        centerBar.add(fichesButtonTwenty);
        centerBar.add(fichesButtonFifty);
        centerBar.add(fichesButtonHundred);
        centerBar.add(fichesButtonTwoHundred);
        centerBar.add(fichesButtonFiveHundred);
        centerBar.add(fichesButtonThousand);
        statusBar.add(centerBar, BorderLayout.CENTER);

        // STATUS BAR RIGHT
        JPanel rightBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBar.setOpaque(false);
        JLabel amountLabel = new JLabel("Amount: " + gameBox.getPlayer().getBalance().getSaldo());
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(getFont().deriveFont(40f));
        rightBar.add(amountLabel);
        statusBar.add(rightBar, BorderLayout.EAST);
        wrapper.add(statusBar, BorderLayout.SOUTH);

        return wrapper;
    }
}