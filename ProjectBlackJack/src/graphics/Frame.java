package Graphics;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame() {

        super("BlackJack");

        setSize(800, 500);
        getContentPane().setBackground(new Color(45, 108, 53));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
}