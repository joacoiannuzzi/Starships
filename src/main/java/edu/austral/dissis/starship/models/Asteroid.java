package edu.austral.dissis.starship.models;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.score.Valuable;

public class Asteroid extends GameObject implements Valuable, Damageable {


    public Asteroid(Vector2 position, Vector2 direction, float speed, float width, float height) {
        super(position, direction, speed, width, height);
    }

    @Override
    public void collisionedWithSpaceShip(Spaceship spaceShip) {
        isActive = false;
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        direction = direction.rotate(0.05f);
//        direction = direction.multiply(-1f);
        move();
//        System.out.println("Asteroid collisioned with asteroid");
    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        isActive = false;
    }

    @Override
    public void accept(GameObject gameObject) {
        gameObject.collisionedWithAsteroid(this);
    }

    @Override
    public void update() {
        move();
        checkPosition();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.Asteroid;
    }

    @Override
    public int getValue() {
        return Constants.AsteroidValue;
    }

    @Override
    public int getDamage() {
        return Constants.AsteroidDamage;
    }
}
