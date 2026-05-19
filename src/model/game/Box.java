package model.game;

import fancygraphic.CardDisplayer;
import model.entities.Entities;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Box extends JPanel {
    protected CardDisplayer cd;

    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    protected boolean isPlaying;

    public Box ()
    {
        this.isPlaying = false;
    }

    public void addIsPlayingListener(PropertyChangeListener l)
    {
        pcs.addPropertyChangeListener("isPlaying", l);
    }

    public int getCd()
    {
        return this.cd.getValue();
    }

    public abstract void newHand();
    public abstract boolean isBJ();
    protected abstract void graphicInit();
    public abstract void setPlaying(boolean value);
}
