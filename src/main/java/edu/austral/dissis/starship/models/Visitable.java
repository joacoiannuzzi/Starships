package edu.austral.dissis.starship.models;

public interface Visitable {

    void accept(GameObject gameObject);

    default void collisionedWithSpaceShip(Spaceship spaceShip) {
    }

    default void collisionedWithAsteroid(Asteroid asteroid) {
    }

    default void collisionedWithBullet(Bullet bullet) {
    }

}
