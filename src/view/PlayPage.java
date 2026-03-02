package view;

import javax.swing.*;
import java.awt.*;

public class PlayPage extends JPanel {

    public PlayPage()
    {
        setLayout(new BorderLayout());
        Color tavolo = new Color(0, 81, 44);
        JLabel label = new JLabel("HOME PAGE", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        this.setBackground(tavolo);
    }
}
