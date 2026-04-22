package view;

import controller.Controller;
import fancygraphic.CardDisplayer;
import fancygraphic.FancyGenButton;
import fancygraphic.GameBox;
import model.cards.Card;
import model.cards.Deck;
import model.cards.Ranks;
import model.cards.Suits;
import model.entities.Player;
import model.game.DealerBox;
import model.game.Hand;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static view.Colours.getTableColor;

public class PlayPage extends JPanel {
    private final Deck deck = new Deck();
    private final DealerBox dealerBox = new DealerBox(deck);
    private final GameBox gameBox = new GameBox(deck);




    private final int nPartecipanti;

    private ArrayList<Player> partecipanti = new ArrayList<>();

    //todo mazzo e elenco partecipanti in un array

    public PlayPage(Controller controller, int nPartecipanti)
    {



        this.nPartecipanti = nPartecipanti; // fare ciclo
        //setLayout(new BorderLayout());
        JLabel label = new JLabel("BLACKJACK", SwingConstants.CENTER);
        add(label, BorderLayout.SOUTH);
        this.setBackground(getTableColor());

        FancyGenButton fancyButton = new FancyGenButton("Back");

        fancyButton.addActionListener( e ->
                controller.setState(State.HOME)
        );
        add(fancyButton);

        add(gameBox);
        add(dealerBox);

        iniGame();
        firstRounf();
        game();
    }

    // migliora 1 carta alla volta
    public void iniGame ()
    {
        gameBox.setPlaying(false);
        dealerBox.newHand();
        gameBox.newHand();
    }

    public void firstRounf()
    {
        dealerBox.iniCard();
        gameBox.iniCard();
    }

    public void game()
    {
        gameBox.setPlaying(true);
        //while(gameBox.getIsPlaying());
        dealerBox.play();
    }

}
