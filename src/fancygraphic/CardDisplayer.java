package fancygraphic;

import model.cards.Card;
import model.game.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CardDisplayer extends JPanel {
    private static final Color TABLE_COLOR_2 = new Color(10, 90, 50);
    private static final Color BORDER_COLOR = new Color(212,175,55);
    private static final Font VALUE_FONT = new Font("Serif", Font.BOLD, 28);

    private Hand hand;
    private final JLabel valuePanel;
    private final JPanel contentPanel;

    public CardDisplayer(Hand hand)
    {
        this.hand = hand;

        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,200));
        setMaximumSize(new Dimension(250,200));

        // bordo oro
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR , 3),
                new EmptyBorder(8,8,8,8)
        ));

        // valore mano
        valuePanel = new JLabel( (hand.getValue() <= 21) ? ""+hand.getValue() : "BUST");
        valuePanel.setFont(VALUE_FONT);
        valuePanel.setForeground(Color.WHITE);
        valuePanel.setHorizontalAlignment(SwingConstants.CENTER);

        // pannello carte
        contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(null);

        // listener aggiornamenti
        updateListener();

        add(contentPanel, BorderLayout.CENTER);
        add(valuePanel, BorderLayout.SOUTH);

        updateCards();
    }

    public void updateListener()
    {
        hand.addPropertyChangeListener(evt -> {

            if(evt.getPropertyName().equals("value"))
            {
                int nuovoValore = (int) evt.getNewValue();
                valuePanel.setText("" + ((nuovoValore <= 21)?nuovoValore:"BUST"));
            }

            if(evt.getPropertyName().equals("cards"))
            {
                updateCards();
            }

        });
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
        updateListener();
        updateCards();
    }

    public void updateCards()
    {

        contentPanel.removeAll();

        int x = 10;
        int overlap = 35;

        for (Card c : hand.getCards())
        {
            FancyCard fc = new FancyCard(c);
            fc.setBounds(x, 10, 1200, 1200);
            contentPanel.add(fc);
            contentPanel.setComponentZOrder(fc, 0);

            x += overlap;
        }

        contentPanel.setPreferredSize(new Dimension(x + 30, 110));

        contentPanel.revalidate();
        contentPanel.repaint();
        
    }

    public int getValue ()
    {
        return this.hand.getValue();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(TABLE_COLOR_2);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();
    }
}