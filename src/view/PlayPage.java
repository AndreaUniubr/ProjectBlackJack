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

public class PlayPage extends JPanel {

    public PlayPage(Controller controller)
    {
        setLayout(new BorderLayout());
        Color tavolo = new Color(0, 81, 44);
        JLabel label = new JLabel("BLACKJACK", SwingConstants.CENTER);
        add(label, BorderLayout.SOUTH);
        this.setBackground(tavolo);

        FancyGenButton fancyButton = new FancyGenButton("Back");

        fancyButton.addActionListener( e ->
                controller.setState(State.HOME)
        );
        add(fancyButton);

        Hand h = new Hand();
        Card c = new Card(Suits.CLUBS,Ranks.TEN);
        Card ca = new Card(Suits.HEARTS, Ranks.ACE);
        c.setFaceUp(true);
        ca.setFaceUp(true);
        h.addCard(c);
        h.addCard(ca);

        CardDisplayer cd = new CardDisplayer(h);

        add(cd);



    }
}
