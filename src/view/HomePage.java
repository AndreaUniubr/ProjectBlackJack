package view;

import controller.Controller;
import fancygraphic.FancyPlayButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static view.Colours.getTableColor;

public class HomePage extends JPanel {

    public HomePage(Controller controller) {

        setLayout(new BorderLayout());

        setBackground(getTableColor());

        JLabel label = new JLabel("BLACKJACK");
        label.setForeground(new Color(218, 165, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(60, 0, 0, 0));
        label.setFont(new Font("Playfair Display", Font.BOLD, 48));

        add(label, BorderLayout.NORTH);

        FancyPlayButton playButton = new FancyPlayButton("▶", 120);
        FancyPlayButton detailsButton = new FancyPlayButton("≡", 120);
        FancyPlayButton balanceButton = new FancyPlayButton("$", 120);

        playButton.addActionListener(e ->
                controller.setState(State.PLAY)
        );

        detailsButton.addActionListener(e ->
                controller.setState(State.DETAILS)
        );

        balanceButton.addActionListener( e ->
                controller.setState(State.BALANCE)
        );

        // 🔥 pannello centrale per i bottoni
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        detailsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        balanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(playButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(detailsButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(balanceButton);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}