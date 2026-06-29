package model.game;

import fancygraphic.CardDisplayer;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Box extends JPanel {
    protected CardDisplayer cd;

    // Manages listeners interested in the playing state of the box.
    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    protected boolean isPlaying;

    public Box ()
    {
        this.cd = null;
        this.isPlaying = false;
    }

    public void addIsPlayingListener(PropertyChangeListener l)
    {
        pcs.addPropertyChangeListener("isPlaying", l);
    }

    public void removeIsPlayingListener(PropertyChangeListener l)
    {
        pcs.removePropertyChangeListener("isPlaying", l);
    }

    public int getHandValue()
    {
        return this.cd.getValue();
    }

    public abstract void newHand();
    public abstract boolean isBJ();
    protected abstract void graphicInit();
    public abstract void setPlaying(boolean value);
}
