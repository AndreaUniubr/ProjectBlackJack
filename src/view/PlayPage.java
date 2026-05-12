package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import fancygraphic.GameBox;
import model.cards.Deck;
import model.entities.Player;
import model.game.DealerBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static view.Colours.getTableColor;

// todo: gestire fine deck

public class PlayPage extends JPanel {

    private final Deck deck = new Deck();
    private final DealerBox dealerBox = new DealerBox(deck);
    private final GameBox gameBox = new GameBox(deck);

    private final Controller controller;

    private final int nPartecipanti;
    private ArrayList<Player> partecipanti = new ArrayList<>();

    public PlayPage(Controller controller, int nPartecipanti) {

        this.controller = controller;

        this.nPartecipanti = nPartecipanti;

        setLayout(new BorderLayout());
        setBackground(getTableColor());

        add(createTop(), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);
        add(createBottom(), BorderLayout.SOUTH);

        // todo: questo solo test, va fatto a round e meglio


// todo: usare questa logica del listener per il dealer, al posto di verificare vittoria fa turno dealer, a qui mettere altro listener e fare questo sotto

        iniGame();
        firstRound();
        game();

        gameBox.addIsPlayingListener(evt -> {
            boolean newValue = (boolean) evt.getNewValue();
            if (!newValue) {
                onPlayerFinished();
            }
        });

    }

    private void onPlayerFinished()
    {
        checkWin();
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

    // =========================
    // todo: sviluppare questa parte, ovvero mettere timer e altre fasi nel gioco
    public void iniGame() {
        gameBox.setPlaying(false);
        dealerBox.newHand();
        gameBox.newHand();
    }

    public void firstRound() {
        dealerBox.iniCard();
        gameBox.iniCard();
    }

    public void game() {
        gameBox.setPlaying(true);
        dealerBox.play();
    }

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
}