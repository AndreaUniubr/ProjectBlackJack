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

        iniGame();
        firstRound();
        game();
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

        backButton.addActionListener( e ->
                controller.setState(State.HOME)
        );

        // AZIONE (qui hai il controller)
        backButton.addActionListener(e ->
                System.out.println("Back pressed") // oppure controller.setState(...)
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
}