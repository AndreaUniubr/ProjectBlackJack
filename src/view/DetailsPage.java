package view;

import fancygraphic.FancyNames;

import javax.swing.*;
import java.awt.*;

public class DetailsPage extends JPanel {

    public DetailsPage() {

        setLayout(new BorderLayout());

        Color tavolo = new Color(0, 81, 44);
        setBackground(tavolo);

        String[] names = {
                "Ideated and Implementated By",
                "Andrea Spadoni",
                "Aurora Mazzone",
                "Francesco Venturi",
                "Jonas Vitali"
        };

        FancyNames fancyNames = new FancyNames(names);

        add(fancyNames, BorderLayout.CENTER);
    }
}