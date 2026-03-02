package view;

import controller.Controller;
import fancygraphic.FancyPlayButton;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    // todo this is only example
    public HomePage(Controller controller) {

        setLayout(new BorderLayout());
        Color tavolo = new Color(0, 81, 44);
        JLabel label = new JLabel("HOME PAGE", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        this.setBackground(tavolo);

        FancyPlayButton playButton = new FancyPlayButton("PLAY", 120);

        playButton.addActionListener(e ->
                controller.setState(State.PLAY)
        );

        add(playButton, BorderLayout.SOUTH);
    }
}