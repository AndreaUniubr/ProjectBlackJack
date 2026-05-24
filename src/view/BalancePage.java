package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import model.balance.Balance;

import javax.swing.*;
import java.awt.*;

import static model.game.Constants.TABLE_COLOR;

public class BalancePage extends JPanel {
    private final Controller controller;
    private final Balance playerBalance;
    private JLabel balanceText;
    private JPanel input;
    private FancyGenButton fancyButton;

    public BalancePage(Controller controller, Balance playerBalance)
    {
        this.controller = controller;
        this.playerBalance = playerBalance;
        setBackground(TABLE_COLOR);

        this.setupBalanceText();
        this.setupInput();
        this.setupFancyButton();

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        balanceText.setAlignmentX(Component.CENTER_ALIGNMENT);
        fancyButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(balanceText);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 180)));
        centerPanel.add(input);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 90)));
        centerPanel.add(fancyButton);
        centerPanel.add(Box.createVerticalGlue());

        add(centerPanel, BorderLayout.CENTER);
    }

    private void setupFancyButton()
    {
        fancyButton = new FancyGenButton("Back");

        fancyButton.addActionListener( e ->
                controller.setState(State.HOME)
        );
    }

    private void setupInput()
    {
        input = new JPanel(new FlowLayout(FlowLayout.LEFT));
        input.setOpaque(false);

        JLabel text = new JLabel("Add amount");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Serif", Font.PLAIN, 16));

        JTextField i = new JTextField(10);
        i.setFont(new Font("Arial", Font.PLAIN, 20));
        i.setPreferredSize(new Dimension(200, 40));

        FancyGenButton add = getAddButton(i);

        input.add(text);
        input.add(i);
        input.add(add);
    }

    private FancyGenButton getAddButton(JTextField i)
    {
        FancyGenButton add = new FancyGenButton("Add");
        add.addActionListener( e ->
                {
                    try
                    {
                        int amount = Integer.parseInt(i.getText());
                        if (amount > 0) {
                            i.setText("");
                            playerBalance.aggiungiSoldi(amount);
                        }
                    } catch (NumberFormatException ex) {
                        i.setText("");
                    }
                }
        );
        return add;
    }

    private void setupBalanceText()
    {
        balanceText = new JLabel("Your balance is : " + playerBalance.getSaldo() + "$");

        playerBalance.addBalanceListener( e -> {
            balanceText.setText("Your balance is : " + playerBalance.getSaldo() + "$");
        });

        balanceText.setForeground(new Color(212, 175, 55));
        balanceText.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 46));
    }
}
