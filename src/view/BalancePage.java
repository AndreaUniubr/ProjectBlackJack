package view;

import controller.Controller;
import fancygraphic.FancyGenButton;
import model.balance.Balance;

import javax.swing.*;
import java.awt.*;

import static model.game.Constants.TABLE_COLOR;

public class BalancePage extends JPanel {
    public static final Font BALANCE_TEXT_FONT = new Font("Serif", Font.BOLD | Font.ITALIC, 46);
    public static final Color BALANCE_TEXT_COLOR = new Color(212, 175, 55);
    public static final Font AMOUNT_LABEL_FONT = new Font("Serif", Font.PLAIN, 16);
    public static final Font AMOUNT_FIELD_FONT = new Font("Arial", Font.PLAIN, 20);

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
        input = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        input.setOpaque(false);

        JLabel amountLabel = new JLabel("Add amount");
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(AMOUNT_LABEL_FONT);

        JTextField amountField = new JTextField(10);
        amountField.setFont(AMOUNT_FIELD_FONT);
        amountField.setPreferredSize(new Dimension(200, 40));

        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        FancyGenButton add = getAddButton(amountField);

        // Pressing ENTER triggers the add button action
        amountField.addActionListener(e -> add.doClick());

        input.add(amountLabel);
        input.add(amountField);
        input.add(add);
    }

    private FancyGenButton getAddButton(JTextField amountField)
    {
        FancyGenButton add = new FancyGenButton("Add");
        add.addActionListener( e ->
                {
                    try
                    {
                        int amount = Integer.parseInt(amountField.getText());
                        if (amount > 0) {
                            amountField.setText("");
                            playerBalance.aggiungiSoldi(amount);
                        }
                    } catch (NumberFormatException ex) {
                        amountField.setText("");
                    }
                }
        );
        return add;
    }

    private void setupBalanceText()
    {
        balanceText = new JLabel();
        updateBalanceText();

        // Automatically updates the displayed balance
        // whenever the model balance changes
        playerBalance.addBalanceListener( e -> {updateBalanceText();});

        balanceText.setForeground(BALANCE_TEXT_COLOR);
        balanceText.setFont(BALANCE_TEXT_FONT);
    }

    private void updateBalanceText()
    {
        balanceText.setText(
                "Your balance is: " + playerBalance.getSaldo() + "$"
        );
    }
}
