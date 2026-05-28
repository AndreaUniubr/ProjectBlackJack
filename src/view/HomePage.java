package view;

import controller.Controller;
import fancygraphic.FancyPlayButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

import static model.game.Constants.TABLE_COLOR;

/**
 * Main menu panel of the project.
 * Displays navigation buttons for the different sections.
 */
public class HomePage extends JPanel {
    private static final Color FG_COLOR = new Color(218, 165, 32);
    private static final Font FONT = new Font("Playfair Display", Font.BOLD, 48);
    private static final int BUTTON_DIAMETER = 120;
    private static final Dimension DISTANCE_BETWEEN_BUTTONS = new Dimension(0, 20);

    private final Controller controller;
    private final JPanel centerPanel;

    public HomePage(Controller controller) {
        this.controller = controller;

        setLayout(new BorderLayout());
        setBackground(TABLE_COLOR);

        JLabel label = new JLabel("BLACKJACK");
        label.setForeground(FG_COLOR);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(60, 0, 0, 0));
        label.setFont(FONT);

        add(label, BorderLayout.NORTH);


        centerPanel = new JPanel();

        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(Box.createVerticalGlue());
        initializeButtons();
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }

    // Creates and adds navigation buttons to the center panel.
    private void initializeButtons()
    {
        FancyPlayButton playButton = new FancyPlayButton("▶", BUTTON_DIAMETER);
        FancyPlayButton detailsButton = new FancyPlayButton("≡", BUTTON_DIAMETER);
        FancyPlayButton balanceButton = new FancyPlayButton("$", BUTTON_DIAMETER);

        playButton.addActionListener(e -> controller.setState(State.PLAY));
        detailsButton.addActionListener(e -> controller.setState(State.DETAILS));
        balanceButton.addActionListener( e -> controller.setState(State.BALANCE));

        Arrays.asList(playButton, detailsButton, balanceButton)
                .forEach(button -> {
                    button.setAlignmentX(Component.CENTER_ALIGNMENT);

                    centerPanel.add(button);
                    centerPanel.add(
                            Box.createRigidArea(DISTANCE_BETWEEN_BUTTONS)
                    );
                });
    }
}