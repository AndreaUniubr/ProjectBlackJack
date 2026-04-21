package view;

import controller.Controller;
import fancygraphic.CardDisplayer;
import fancygraphic.FancyGenButton;
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




    private final int nPartecipanti;

    private ArrayList<Player> partecipanti = new ArrayList<>();

    //todo mazzo e elenco partecipanti in un array

    public PlayPage(Controller controller, int nPartecipanti)
    {



        this.nPartecipanti = nPartecipanti;
        //setLayout(new BorderLayout());
        JLabel label = new JLabel("BLACKJACK", SwingConstants.CENTER);
        add(label, BorderLayout.SOUTH);
        this.setBackground(getTableColor());

        FancyGenButton fancyButton = new FancyGenButton("Back");

        fancyButton.addActionListener( e ->
                controller.setState(State.HOME)
        );
        add(fancyButton);

        Hand h = new Hand();
        Hand h1 = new Hand();
        Card c = new Card(Suits.CLUBS,Ranks.TEN);
        Card ca = new Card(Suits.SPADES, Ranks.FIVE);
        Card ca1 = new Card(Suits.SPADES, Ranks.FIVE);
        c.setFaceUp(true);
        ca.setFaceUp(true);
        h.addCard(c);
        h.addCard(ca);
        h1.addCard(c);
        h1.addCard(c);
        h1.addCard(ca1);

        CardDisplayer cd = new CardDisplayer(h);

        //centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        CardDisplayer cd1 = new CardDisplayer(h1);

        add(cd);
        add(cd1);

        add(dealerBox);
        //dealerBox.newHand();
        //dealerBox.iniCard();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //dealerBox.play();



    }

    // player contiene ogni mano e per ognuna se vincenre o meno


    public void checkWin()
    {
        /*int d = dealer.getValue();
        for (Player p : partecipanti)
        {
            //(partecipanti)
        }*/
    }
}
