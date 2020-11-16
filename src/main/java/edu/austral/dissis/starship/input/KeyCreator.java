package edu.austral.dissis.starship.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class KeyCreator {

    public static Map<Integer, Consumer<Actionable>> keys1() {
        return createKeys(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
    }

    public static Map<Integer, Consumer<Actionable>> keys2() {
        return createKeys(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_X);
    }

    public static Map<Integer, Consumer<Actionable>> createKeys(int accelerate, int left, int right, int shoot) {
        Map<Integer, Consumer<Actionable>> map = new HashMap<>();
        map.put(accelerate, Actionable::accelerate);
        map.put(left, actionable -> actionable.rotate((float) (Math.PI / -60)));
        map.put(right, actionable -> actionable.rotate((float) (Math.PI / 60)));
        map.put(shoot, Actionable::shoot);
        return map;
    }
}
