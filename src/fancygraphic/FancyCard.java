package fancygraphic;

import model.cards.Card;

import javax.swing.*;
import java.awt.*;

public class FancyCard extends JPanel {
    private static final Image sprite;

    private static final int NUM_COLS = 14;
    private static final int NUM_ROWS = 4;
    private static final int BACK_COL = 13;
    private static final int BACK_ROW = 3;

    private static final int CARD_WIDTH;
    private static final int CARD_HEIGHT;

    private Card card;

    // Scale divisors chosen to preserve the card aspect ratio
    private static final int WIDTH_SCALE_DIVISOR = 13;
    private static final int HEIGHT_SCALE_DIVISOR = 12;

    // Loads the whole card deck sprite sheet once for all card instances
    static {
        try {
            sprite = new ImageIcon(
                    FancyCard.class.getResource("/cards/assets/deck_classic_dark_2color_0.png")
            ).getImage();

            int spriteWidth = sprite.getWidth(null);
            int spriteHeight = sprite.getHeight(null);

            CARD_WIDTH = spriteWidth / NUM_COLS;
            CARD_HEIGHT = spriteHeight / NUM_ROWS;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load card sprite", e);
        }
    }

    public FancyCard (Card card)
    {
        this.card = card;
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setOpaque(false);
    }

    public void setCard(Card card)
    {
        this.card = card;
        repaint();
    }

    // Draws either the card face or the back sprite depending on visibility
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (card == null) return;

        int col = card.isFaceUp()
                ? card.getRank().getPosition()
                : BACK_COL;

        int row = card.isFaceUp()
                ? card.getSuit().getPosition()
                : BACK_ROW;

        int sx = col * CARD_WIDTH;
        int sy = row * CARD_HEIGHT;

        g.drawImage(
                sprite,
                0, 0,
                getWidth() / WIDTH_SCALE_DIVISOR, getHeight() / HEIGHT_SCALE_DIVISOR,
                sx, sy,
                sx + CARD_WIDTH, sy + CARD_HEIGHT,
                this);
    }
}
