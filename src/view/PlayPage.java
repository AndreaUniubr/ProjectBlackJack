package view;

import controller.Controller;
import fancygraphic.CardDisplayer;
import fancygraphic.FancyCard;
import fancygraphic.FancyGenButton;
import fancygraphic.GameBox;
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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setBackground(getTableColor());
        FancyGenButton backButton = new FancyGenButton("Back");
        backButton.addActionListener( e ->
                controller.setState(State.HOME)
        );
        add(backButton);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(100, 0, 0, 0);
        add(new GameBox(), gbc);

        //centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));







    }
}
