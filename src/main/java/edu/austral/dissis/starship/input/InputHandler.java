package edu.austral.dissis.starship.input;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class InputHandler {

    private Actionable actionable;
    private Map<Integer, Consumer<Actionable>> keys;

    public InputHandler(Actionable actionable, Map<Integer, Consumer<Actionable>> keys) {
        this.actionable = actionable;
        this.keys = keys;
    }

    void handle(Integer key) {
        Optional.ofNullable(keys.get(key))
                .ifPresent(cb -> cb.accept(actionable));
    }

    public Actionable getActionable() {
        return actionable;
    }
}
