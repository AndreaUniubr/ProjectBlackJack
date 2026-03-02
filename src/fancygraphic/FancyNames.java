package fancygraphic;

import javax.swing.*;
import java.awt.*;

public class FancyNames extends JPanel {

    public FancyNames(String[] names) {

        setOpaque(false); // così si vede il verde della pagina dietro
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Spazio sopra per centrare verticalmente meglio
        add(Box.createVerticalGlue());

        for (String name : names) {
            JLabel label = new JLabel(name);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setForeground(Color.BLACK);
            label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 36));

            add(label);
            add(Box.createRigidArea(new Dimension(0, 20))); // spazio tra i nomi
        }

        // Spazio sotto
        add(Box.createVerticalGlue());
    }
}