package edu.austral.dissis.starship.input;

public interface InputHandler {

    void handle(Integer key);

    Actionable getActionable();
}
