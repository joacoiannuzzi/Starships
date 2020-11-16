package edu.austral.dissis.starship.interfaces;

import edu.austral.dissis.starship.gameObjects.Bullet;

import java.util.List;
import java.util.function.Consumer;

public interface BulletCallback {

    void addBulletsCallback(Consumer<List<Bullet>> callback);
}
