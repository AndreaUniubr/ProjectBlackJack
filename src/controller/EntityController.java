package controller;

import model.entities.Participant;
import model.game.Hand;

public class EntityController {
    private Participant p;
    private boolean user = true;

    public EntityController(Participant p) {
        this.p = p;
    }

    // forse non va fatto
    public void setAimode()
    {
        this.user = false;
    }

    /*quando splitta crea un altro controller*/




}
