package edu.austral.dissis.starship.gameObjects;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.interfaces.Damageable;
import edu.austral.dissis.starship.interfaces.EntityType;
import edu.austral.dissis.starship.interfaces.Valuable;

import java.util.function.Consumer;

public class Bullet extends GameObject implements Damageable {

    private final Consumer<Valuable> hitCallback;

    public Bullet(Vector2 position, Vector2 direction, float speed, float width, float height, Consumer<Valuable> hitCallback) {
        super(position, direction, speed, width, height);
        this.hitCallback = hitCallback;
    }

    @Override
    public void accept(GameObject gameObject) {
        gameObject.collisionedWithBullet(this);
    }

    @Override
    public void update() {
        move();

        final float x = position.getX();
        final float y = position.getY();

        isActive = x < Constants.GameWidth
                && x > 0
                && y < Constants.GameHeight
                && y > 0;
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        hitCallback.accept(asteroid);
        isActive = false;
    }

    @Override
    public void collisionedWithSpaceShip(Spaceship spaceShip) {
        hitCallback.accept(spaceShip);
        isActive = false;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.Bullet;
    }

    @Override
    public int getDamage() {
        return Constants.BulletDamage;
    }
}
