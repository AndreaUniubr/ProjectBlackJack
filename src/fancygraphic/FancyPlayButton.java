package fancygraphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FancyPlayButton extends JButton {
    private final int diameter;


    public FancyPlayButton(int diameter, int type)
    {
        this.diameter = diameter;


        BufferedImage spriteFiches = null;
        try {
            spriteFiches = ImageIO.read(new File("resources/cards/assets/personalized_fiches.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage iconaRitagliata = ritagliaImmagine(spriteFiches,  107, 422, diameter);
        Image iconaRidimensionata = iconaRitagliata.getScaledInstance(50, 48, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(iconaRidimensionata));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(50, 48));


    }


    public BufferedImage ritagliaImmagine(BufferedImage source, int x, int y, int diameter)
    {
        return source.getSubimage(x, y, diameter, diameter);
    }
    /*
    private boolean hover = false;
    private boolean pressed = false;

    public FancyPlayButton(String text, int diameter) {

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setFont(getFont().deriveFont(60f));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                pressed = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
                repaint();
            }
        });
    }

    /* =========================
       DIMENSIONI CORRETTE
       =========================

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(diameter, diameter);
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    /* =========================
       DISEGNO
       =========================

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int padding = 10;

        // Prendiamo il lato minore per mantenere il cerchio perfetto
        int size = Math.min(w, h) - (padding * 2);

        // Centriamo il cerchio
        int x = (w - size) / 2;
        int y = (h - size) / 2;

        /* ===== Ombra =====
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.4f));
        g2.setColor(Color.BLACK);
        g2.fillOval(x + 4, y + 4, size - 8, size - 8);
        g2.setComposite(AlphaComposite.SrcOver);

        /* ===== Corpo principale =====
        Color base = new Color(220, 0, 0);

        if (pressed) base = base.darker();
        else if (hover) base = base.brighter();

        Point2D center = new Point2D.Float(w / 2f, h / 2f);
        float radius = size / 2f;

        float[] dist = {0f, 1f};
        Color[] colors = {base.brighter(), base.darker()};

        RadialGradientPaint gradient =
                new RadialGradientPaint(center, radius, dist, colors);

        g2.setPaint(gradient);
        g2.fillOval(x, y, size, size);

        /* ===== Bordo esterno =====
        g2.setStroke(new BasicStroke(size / 10f));
        g2.setColor(Color.WHITE);
        g2.drawOval(x, y, size, size);

        /* ===== Testo =====

        FontMetrics fm = g2.getFontMetrics(getFont());
        String text = getText();

        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int textX = w / 2 - textWidth / 2;
        int textY = 20 + textHeight;

        // Ombra testo
        g2.setColor(new Color(0, 0, 0, 120));
        g2.drawString(text, textX + 1, textY + 1);

        // Testo principale
        g2.setColor(Color.WHITE);
        g2.drawString(text, textX, textY);

        g2.dispose();
    }

    /* =========================
       CLICK SOLO NEL CERCHIO
       =========================

    @Override
    public boolean contains(int x, int y) {

        int w = getWidth();
        int h = getHeight();
        int size = Math.min(w, h);

        int cx = (w - size) / 2;
        int cy = (h - size) / 2;

        Ellipse2D circle = new Ellipse2D.Float(cx, cy, size, size);
        return circle.contains(x, y);
    }*/
}