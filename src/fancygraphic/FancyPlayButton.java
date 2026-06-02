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

        //289, 423 secondo
        //469, 422 terzo
    }


    public BufferedImage ritagliaImmagine(BufferedImage source, int x, int y, int diameter)
    {
        return source.getSubimage(x, y, diameter, diameter);
    }
}