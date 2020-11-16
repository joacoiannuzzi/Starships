package edu.austral.dissis.starship.models;

import java.util.List;
import java.util.function.Consumer;

public interface BulletCallback {

    void addBulletsCallback(Consumer<List<Bullet>> callback);
}
