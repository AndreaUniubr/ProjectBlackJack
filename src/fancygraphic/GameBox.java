package fancygraphic;

import javax.swing.*;

public class GameBox extends JFrame {
    private final int id;

    public GameBox(int id)
    {
        this.id = id;
    }


    public int getId()
    {
        return this.id;
    }
}
