package controller;

import view.State;

public interface StateObserver {
    void onStateChanged(State newState);
}