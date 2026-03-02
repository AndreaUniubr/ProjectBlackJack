package fancygraphic;

import javax.swing.*;
import java.awt.*;

public class FancyGenButton extends JButton {

    private final Color backgroundColor = new Color(20, 20, 20);      // nero elegante
    private final Color hoverColor = new Color(40, 40, 40);           // leggero hover
    private final Color borderColor = new Color(212, 175, 55);        // oro casino
    private final Color textColor = new Color(212, 175, 55);          // oro

    private boolean isHovered = false;

    public FancyGenButton(String text) {
        super(text);

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);

        setForeground(textColor);
        setFont(new Font("Serif", Font.BOLD, 18));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setMargin(new Insets(10, 25, 10, 25));

        // Hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(isHovered ? hoverColor : backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 30, 30);

        g2.dispose();
    }
}