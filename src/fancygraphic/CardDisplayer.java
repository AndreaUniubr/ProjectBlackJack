package fancygraphic;

import model.cards.Card;
import model.game.Hand;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class CardDisplayer extends JPanel {
    private static final Color TABLE_COLOR_2 = new Color(10, 90, 50);
    private static final Color BORDER_COLOR = new Color(212,175,55);
    private static final Font VALUE_FONT = new Font("Serif", Font.BOLD, 28);

    private Hand hand;
    // Listener used to update the UI when the hand changes.
    private PropertyChangeListener handListener;

    private JLabel handValuePanel;
    private JPanel cardDisplayerPanel;

    public CardDisplayer(Hand hand)
    {
        init();

        add(cardDisplayerPanel, BorderLayout.CENTER);
        add(handValuePanel, BorderLayout.SOUTH);

        onHandChange(hand);
    }

    private void addHandListener()
    {
        if (hand == null) return;

        handListener = evt -> {

            if("value".equals(evt.getPropertyName()))
                updateHandValue();

            if("cards".equals(evt.getPropertyName()))
                updateCards();

        };

        hand.addPropertyChangeListener(handListener);
    }

    // Removes the listener from the previous hand to avoid duplicated updates.
    private void removeHandListener()
    {
        if (hand != null && handListener != null)
        {
            hand.removePropertyChangeListener(handListener);
            handListener = null;
        }
    }

    private void onHandChange(Hand newHand)
    {
        removeHandListener();

        this.hand = newHand;

        addHandListener();

        updateHandValue();
        updateCards();
    }

    // Updates the displayed hand value and shows BUST if the value exceeds 21.
    private void updateHandValue()
    {
        if (hand == null) return;

        int value = hand.getValue();

        handValuePanel.setText(
                (value <= 21)
                        ? String.valueOf(value)
                        : "BUST"
        );
    }

    // Rebuilds the card panel every time the hand receives or removes cards.
    public void updateCards()
    {

        cardDisplayerPanel.removeAll();

        if (hand == null)
        {
            cardDisplayerPanel.revalidate();
            cardDisplayerPanel.repaint();
            return;
        }

        int x = 10;
        int overlap = 35;

        for (Card c : hand.getCards())
        {
            FancyCard fc = new FancyCard(c);
            fc.setBounds(x, 10, 1200, 1200);
            cardDisplayerPanel.add(fc);
            cardDisplayerPanel.setComponentZOrder(fc, 0);

            x += overlap;
        }

        cardDisplayerPanel.setPreferredSize(new Dimension(x + 30, 110));

        cardDisplayerPanel.revalidate();
        cardDisplayerPanel.repaint();
    }

    private void init()
    {
        setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(250,200));
        setMaximumSize(new Dimension(250,200));

        // gold border
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR , 3),
                new EmptyBorder(8,8,8,8)
        ));

        // initialization on HandValuePanel
        handValuePanel = new JLabel();
        handValuePanel.setFont(VALUE_FONT);
        handValuePanel.setForeground(Color.WHITE);
        handValuePanel.setHorizontalAlignment(SwingConstants.CENTER);

        // initialization on cardDisplayerPanel
        cardDisplayerPanel = new JPanel();
        cardDisplayerPanel.setOpaque(false);
        cardDisplayerPanel.setLayout(null);
    }

    public void setHand(Hand hand)
    {
        onHandChange(hand);
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

    public int getValue ()
    {
        return hand != null ? hand.getValue() : 0;
    }
}