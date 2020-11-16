package edu.austral.dissis.starship.input;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class InputHandlerImpl implements InputHandler {

    private final Actionable actionable;
    private final Map<Integer, Consumer<Actionable>> keys;

    public InputHandlerImpl(Actionable actionable, Map<Integer, Consumer<Actionable>> keys) {
        this.actionable = actionable;
        this.keys = keys;
    }

    @Override
    public void handle(Integer key) {
        Optional.ofNullable(keys.get(key))
                .ifPresent(cb -> cb.accept(actionable));
    }

    @Override
    public Actionable getActionable() {
        return actionable;
    }
}
