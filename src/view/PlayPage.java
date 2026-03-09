package view;

import controller.Controller;
import fancygraphic.FancyCard;
import model.cards.Card;
import model.cards.Ranks;
import model.cards.Suits;

import javax.swing.*;
import java.awt.*;

public class PlayPage extends JPanel {

    public PlayPage(Controller controller)
    {
        setLayout(new BorderLayout());
        Color tavolo = new Color(0, 81, 44);
        JLabel label = new JLabel("HOME PAGE", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        this.setBackground(tavolo);

        Card card = new Card(Suits.HEARTS, Ranks.ACE);
        card.setFaceUp(true);
        FancyCard fc = new FancyCard(card);

        add(fc);
    }
}
