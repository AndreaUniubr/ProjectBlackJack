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
    private static final int ICON_WIDTH_2 = 50;
    private static final int ICON_HEIGHT_2 = 48;

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

    public FancyFichesButton (int diameter, int type)
    {
        BufferedImage spriteFiches = loadSprite(SPRITE_PATH_2);

        int x;
        int y = 422;
        x = switch (type) {
            case 1 -> 289;
            case 2 -> 462;
            default -> 107; // 0
        };

        BufferedImage croppedIcon = cropImage(spriteFiches, x, y, diameter, diameter);

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

    public BufferedImage cropImage(BufferedImage source, int x, int y, int width, int height)
    {
        return source.getSubimage(x, y, width, height);
    }

}


