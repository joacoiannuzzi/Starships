package edu.austral.dissis.starship.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InputController {

    private List<InputHandler> inputHandlers = new ArrayList<>();

    public void handleInput(Set<Integer> keys) {
        inputHandlers.forEach(inputHandler ->
                keys.forEach(inputHandler::handle)
        );
    }

    public void add(InputHandler inputHandler) {
        inputHandlers.add(inputHandler);
    }

    public void removeNonActive() {
        inputHandlers.removeIf(inputHandler -> !inputHandler.getActionable().isActive());
    }
}
