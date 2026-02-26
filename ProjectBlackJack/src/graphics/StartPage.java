package graphics;

import controller.Controller;

import javax.swing.*;

public class StartPage extends JFrame{
    Controller controller;

    public StartPage (Controller c)
    {
        this.controller = c;
        setTitle("Project Black Jack");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centra la finestra

        initUI();

        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();

        JButton button = new JButton("Start");
        panel.add(button);

        add(panel);
    }
}
