package view;

import controller.Controller;
import fancygraphic.FancyPlayButton;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    public HomePage(Controller controller) {

        setLayout(new BorderLayout());

        Color tavolo = new Color(0, 81, 44);
        setBackground(tavolo);

        JLabel label = new JLabel("HOME PAGE");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 28));

        add(label, BorderLayout.NORTH);

        FancyPlayButton playButton = new FancyPlayButton("PLAY", 120);
        FancyPlayButton detailsButton = new FancyPlayButton("DETAILS", 120);

        playButton.addActionListener(e ->
                controller.setState(State.PLAY)
        );

        detailsButton.addActionListener(e ->
                controller.setState(State.DETAILS)
        );

        // 🔥 pannello centrale per i bottoni
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(playButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(detailsButton);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}