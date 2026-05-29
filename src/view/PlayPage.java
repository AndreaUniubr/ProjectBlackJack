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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.game.Constants.TABLE_COLOR;


public class PlayPage extends JPanel {
    private final Controller controller;
    private final Deck deck = new Deck();
    private final DealerBox dealerBox;
    private final GameBox gameBox;
    private FancyGenButton backButton;
    private JLabel amountLabel;
    private JLabel betLabel;
    private static FancyFichesButton fichesButtonTen;
    private static FancyFichesButton fichesButtonTwenty;
    private static FancyFichesButton fichesButtonFifty;
    private static FancyFichesButton fichesButtonHundred;
    private static FancyFichesButton fichesButtonTwoHundred;
    private static FancyFichesButton fichesButtonFiveHundred;
    private static FancyFichesButton fichesButtonThousand;

    public PlayPage(Controller controller, int nPartecipanti, Balance balance)
    {
        dealerBox = new DealerBox(deck);
        gameBox = new GameBox(deck, balance);
        amountLabel = new JLabel("Amount: " + gameBox.getPlayer().getBalance().getSaldo());
        betLabel = new JLabel("Bet: " + gameBox.getBet());
        this.controller = controller;

        // per ora nPartecipanti inutilizzato

        setLayout(new BorderLayout());
        setBackground(TABLE_COLOR);

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
        // todo porco dio mi fa giocare senza puntate,....
        // perchè mi mette a 2k la vincita se perdo? e scommettere mentre si gioca...

        // ok non puo comparire subito


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
                        gameBox.resetTotalBet();
                        updateBalanceAndBet(amountLabel, betLabel);
                        addFichesButton();
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
        if (deck.getSize() < 35) deck.restore();
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
        leftBar.setPreferredSize(new Dimension(600, 60));
        leftBar.setMinimumSize(new Dimension(600, 60));
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
        fichesButtonTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(10);
                updateBalanceAndBet(amountLabel, betLabel);

            }
        });
        fichesButtonTwenty = new FancyFichesButton(289, 423, 160, 154);
        fichesButtonTwenty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(20);
                updateBalanceAndBet(amountLabel, betLabel);
            }
        });
        fichesButtonFifty = new FancyFichesButton(469, 422, 160, 154);
        fichesButtonFifty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(50);
                updateBalanceAndBet(amountLabel, betLabel);
            }
        });
        fichesButtonHundred = new FancyFichesButton(642, 423, 160, 154);
        fichesButtonHundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(100);
                updateBalanceAndBet(amountLabel, betLabel);
            }
        });
        fichesButtonTwoHundred = new FancyFichesButton(814, 423, 160, 154);
        fichesButtonTwoHundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(200);
                updateBalanceAndBet(amountLabel, betLabel);
            }
        });
        fichesButtonFiveHundred = new FancyFichesButton(986, 422, 160, 154);
        fichesButtonFiveHundred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(500);
                updateBalanceAndBet(amountLabel, betLabel);
            }
        });
        fichesButtonThousand = new FancyFichesButton(1162, 422, 160, 154);
        fichesButtonThousand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBox.setBet(1000);
                updateBalanceAndBet(amountLabel, betLabel);
            }
        });




        centerBar.setOpaque(false);
        centerBar.setPreferredSize(new Dimension(600, 60));
        centerBar.setMinimumSize(new Dimension(600, 60));
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
        rightBar.setPreferredSize(new Dimension(600, 60));
        rightBar.setMinimumSize(new Dimension(600, 60));
        rightBar.setOpaque(false);

        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(getFont().deriveFont(40f));
        betLabel.setForeground(Color.WHITE);
        betLabel.setFont(getFont().deriveFont(40f));
        rightBar.add(amountLabel);
        rightBar.add(betLabel);
        statusBar.add(rightBar, BorderLayout.EAST);
        wrapper.add(statusBar, BorderLayout.SOUTH);

        return wrapper;
    }

    public void updateBalanceAndBet(JLabel amountLabel, JLabel betLabel)
    {
        amountLabel.setText("Amount: " + gameBox.getPlayer().getBalance().getSaldo());
        betLabel.setText("Bet: " + gameBox.getBet());
    }

    public static void removeFichesButton()
    {
        fichesButtonTen.setVisible(false);
        fichesButtonTwenty.setVisible(false);
        fichesButtonFifty.setVisible(false);
        fichesButtonHundred.setVisible(false);
        fichesButtonTwoHundred.setVisible(false);
        fichesButtonFiveHundred.setVisible(false);
        fichesButtonThousand.setVisible(false);
    }

    public static void addFichesButton()
    {
        fichesButtonTen.setVisible(true);
        fichesButtonTwenty.setVisible(true);
        fichesButtonFifty.setVisible(true);
        fichesButtonHundred.setVisible(true);
        fichesButtonTwoHundred.setVisible(true);
        fichesButtonFiveHundred.setVisible(true);
        fichesButtonThousand.setVisible(true);
    }
}