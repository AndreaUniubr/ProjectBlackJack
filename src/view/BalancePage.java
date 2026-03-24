package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import fancygraphic.FancyNames;
import model.balance.Balance;

import javax.swing.*;
import java.awt.*;

public class BalancePage extends JPanel {

    public BalancePage(Controller controller, Balance playerBalance)
    {
        setLayout(new BorderLayout());

        Color tavolo = new Color(0, 81, 44);
        setBackground(tavolo);

        JLabel label = new JLabel(String.valueOf(playerBalance.getSaldo()));


        label.setForeground(Color.WHITE);
        label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 36));


        FancyGenButton fancyButton = new FancyGenButton("Back");
        FancyGenButton add = new FancyGenButton("Add");
        JLabel text = new JLabel("Add ammount");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", Font.PLAIN, 16));
        JTextField input = new JTextField(10);

        input.setFont(new Font("Arial", Font.PLAIN, 20));
        input.setPreferredSize(new Dimension(200, 40));

        add.addActionListener( e ->
                {
                    // come faccio solo testo
                    int ammount = Integer.parseInt(input.getText());
                    if (ammount > 0) {
                        input.setText("");
                        playerBalance.aggiungiSoldi(ammount);
                        // e aggiorna
                    }
                }
        );

        fancyButton.addActionListener( e ->
                controller.setState(State.HOME)
        );

        // pannello centrale per centrare bene tutto
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        fancyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(label);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 90)));
        centerPanel.add(add);
        centerPanel.add(text);
        centerPanel.add(input);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 90)));
        centerPanel.add(fancyButton);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }
}
