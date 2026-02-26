import controller.Controller;
import graphics.StartPage;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StartPage(new Controller());
        });

    }
}