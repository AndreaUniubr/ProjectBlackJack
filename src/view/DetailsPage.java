package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import fancygraphic.FancyNames;

import javax.swing.*;
import java.awt.*;

import static model.game.Constants.TABLE_COLOR;

public class DetailsPage extends JPanel {

    public DetailsPage(Controller controller) {

        setLayout(new BorderLayout());
        setBackground(TABLE_COLOR);

        String[] names = {
                "Designed and Developed By",
                "Andrea Spadoni",
                "Aurora Mazzone",
                "Francesco Pio Venturi",
                "Jonas Vitali"
        };

        FancyNames fancyNames = new FancyNames(names);
        FancyGenButton fancyButton = new FancyGenButton("Back");

        fancyButton.addActionListener(e -> controller.setState(State.HOME));

        // vertical centration
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        // internal vertical container
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        fancyNames.setAlignmentX(Component.CENTER_ALIGNMENT);
        fancyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // internal spacing
        content.add(fancyNames);
        content.add(Box.createVerticalStrut(30));
        content.add(fancyButton);

        centerPanel.add(content);

        add(centerPanel, BorderLayout.CENTER);
    }
}