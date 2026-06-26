package fancygraphic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Creates a transparent button using a cropped chip image from the fiches sprite
public class FancyFichesButton extends JButton
{
    private static final String SPRITE_PATH = "resources/cards/assets/fiches.png";
    private static final String SPRITE_PATH_2 = "resources/cards/assets/personalized_fiches.png";
    private static final int ICON_WIDTH_1 = 50;
    private static final int ICON_HEIGHT_1 = 48;
    private static final int ICON_WIDTH_2 = 110;
    private static final int ICON_HEIGHT_2 = 104;

    private final Image resizedIcon;

    public FancyFichesButton (int x, int y, int width, int height)
    {
        BufferedImage spriteFiches = loadSprite(SPRITE_PATH);
        BufferedImage croppedIcon = cropImage(spriteFiches, x, y, width, height);

        resizedIcon = croppedIcon.getScaledInstance(
                ICON_WIDTH_1,
                ICON_HEIGHT_1,
                Image.SCALE_SMOOTH);

        gen_ini();

        setPreferredSize(new Dimension(ICON_WIDTH_1, ICON_HEIGHT_1));
    }

    public FancyFichesButton (int type)
    {
        BufferedImage spriteFiches = loadSprite(SPRITE_PATH_2);

        int x;
        int y;
        int width;
        int height;

        switch(type)
        {
            case 2: // green
                x = 457;
                y = 413;
                width = 175;
                height = 167;
                break;

            case 1: // blue
                x = 278;
                y = 413;
                width = 175;
                height = 167;
                break;

            default: // red
                x = 101;
                y = 413;
                width = 175;
                height = 167;
        }

        BufferedImage croppedIcon = cropImage(spriteFiches, x, y, width, height);

        resizedIcon = croppedIcon.getScaledInstance(
                ICON_WIDTH_2,
                ICON_HEIGHT_2,
                Image.SCALE_SMOOTH);

        gen_ini();

        setPreferredSize(new Dimension(ICON_WIDTH_2, ICON_HEIGHT_2));
    }

    private void gen_ini()
    {
        setIcon(new ImageIcon(resizedIcon));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }

    private BufferedImage loadSprite(String path)
    {
        try
        {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load fiches sprite ", e);
        }
    }

    private BufferedImage cropImage(BufferedImage source, int x, int y, int width, int height)
    {
        return source.getSubimage(x, y, width, height);
    }

}


