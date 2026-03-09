package fancygraphic;

import model.cards.Card;

import javax.swing.*;
import java.awt.*;

public class FancyCard extends JPanel{
    private static Image sprite;

    private static final int NUM_COLS = 14;
    private static final int NUM_ROWS = 4;

    private static int cardWidth;
    private static int cardHeight;

    private Card card;

    private final int widthCorrector = 15;
    private final int heightCorrector = 14;

    // Caricamento statico della sprite (una sola volta)
    static {
        try {
            sprite = new ImageIcon(
                    FancyCard.class.getResource("/cards/assets/deck_classic_dark_1color_0.png")
            ).getImage();

            int spriteWidth = sprite.getWidth(null);
            int spriteHeight = sprite.getHeight(null);

            cardWidth = spriteWidth / NUM_COLS;
            cardHeight = spriteHeight / NUM_ROWS;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FancyCard(Card card) {
        this.card = card;
        setPreferredSize(new Dimension(cardWidth, cardHeight));
        setOpaque(false);
    }

    public void setCard(Card card) {
        this.card = card;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (card == null) return;

        if (!card.isFaceUp()) {
            drawBack(g);
            return;
        }

        int col = card.getRank().getPosition();
        int row = card.getSuit().getPosition();

        int sx = col * cardWidth;
        int sy = row * cardHeight;

        // todo aggiustare dimensioni carte

        g.drawImage(sprite,
                0, 0, getWidth() / widthCorrector, getHeight() / heightCorrector,
                sx, sy, sx + cardWidth, sy + cardHeight,
                this);
    }

    private void drawBack(Graphics g) {
        // posizione dietro della carta
        int backRow = 3;
        int backColumn = 13;

        int sx = backColumn * cardWidth;
        int sy = backRow * cardHeight;

        g.drawImage(sprite,
                0, 0, getWidth() / widthCorrector, getHeight() / heightCorrector,
                sx, sy, sx + cardWidth, sy + cardHeight,
                this);
    }
}
