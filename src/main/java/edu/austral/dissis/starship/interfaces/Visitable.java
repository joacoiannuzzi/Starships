package edu.austral.dissis.starship.interfaces;

import edu.austral.dissis.starship.gameObjects.Asteroid;
import edu.austral.dissis.starship.gameObjects.Bullet;
import edu.austral.dissis.starship.gameObjects.GameObject;
import edu.austral.dissis.starship.gameObjects.Spaceship;

public interface Visitable {

    void accept(GameObject gameObject);

    default void collisionedWithSpaceShip(Spaceship spaceShip) {
    }

    default void collisionedWithAsteroid(Asteroid asteroid) {
    }

    default void collisionedWithBullet(Bullet bullet) {
    }

}
