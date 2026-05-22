package fancygraphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FancyFichesButton extends JButton
{

    public FancyFichesButton (int x, int y, int width, int height)
    {

        BufferedImage spriteFiches = null;
        try {
            spriteFiches = ImageIO.read(new File("resources/cards/assets/fiches.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage iconaRitagliata = ritagliaImmagine(spriteFiches, x, y, width, height);
        Image iconaRidimensionata = iconaRitagliata.getScaledInstance(50, 48, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(iconaRidimensionata));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(50, 48));
    }

    public BufferedImage ritagliaImmagine(BufferedImage source, int x, int y, int width, int height)
    {
        return source.getSubimage(x, y, width, height);
    }

}


