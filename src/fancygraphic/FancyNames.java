package fancygraphic;

import javax.swing.*;
import java.awt.*;

// Class to show in an elegant way the names on the screen
public class FancyNames extends JPanel {
    private static final Font NAME_FONT = new Font("Serif", Font.BOLD | Font.ITALIC, 36);

    public FancyNames(String[] names) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Empty space Up for a better positioning
        add(Box.createVerticalGlue());

        JLabel label;
        for (String name : names)
        {
            label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(Color.WHITE);
            label.setFont(NAME_FONT);

            add(label);
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        }

        // Empty space Under for a better positioning
        add(Box.createVerticalGlue());
    }
}