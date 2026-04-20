package view;

import controller.Controller;
import fancygraphic.CardDisplayer;
import fancygraphic.FancyCard;
import fancygraphic.FancyGenButton;
import model.cards.Card;
import model.cards.Ranks;
import model.cards.Suits;
import model.game.Hand;

import javax.swing.*;
import java.awt.*;

import static view.Colours.getTableColor;

public class PlayPage extends JPanel {

    public PlayPage(Controller controller)
    {
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
        c.setFaceUp(true);
        ca.setFaceUp(true);
        h.addCard(c);
        h.addCard(ca);
        h1.addCard(c);
        h1.addCard(c);

        CardDisplayer cd = new CardDisplayer(h);

        //centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        CardDisplayer cd1 = new CardDisplayer(h1);

        add(cd);
        add(cd1);



    }
}
