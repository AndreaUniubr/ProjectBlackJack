package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import fancygraphic.FancyNames;

import javax.swing.*;
import java.awt.*;

import static view.Colours.getTableColor;

public class DetailsPage extends JPanel {

    public DetailsPage(Controller controller) {

        setLayout(new BorderLayout());

        setBackground(getTableColor());

        String[] names = {
                "Ideated and Implementated By",
                "Andrea Spadoni",
                "Aurora Mazzone",
                "Francesco Venturi",
                "Jonas Vitali"
        };

        FancyNames fancyNames = new FancyNames(names);
        FancyGenButton fancyButton = new FancyGenButton("Back");

        fancyButton.addActionListener( e ->
            controller.setState(State.HOME)
        );

        // pannello centrale per centrare bene tutto
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        fancyNames.setAlignmentX(Component.CENTER_ALIGNMENT);
        fancyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(fancyNames);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(fancyButton);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}