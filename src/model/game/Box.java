package model.game;

import fancygraphic.CardDisplayer;
import model.entities.Entities;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class Box extends JPanel {
    protected final Entities entities;
    protected CardDisplayer cd;

    protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    protected boolean isPlaying;

    public Box (Entities entities)
    {
        this.entities = entities;
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

    protected abstract void graphicInit();
    public abstract void setPlaying(boolean value);
}
