package edu.austral.dissis.starship.gameObjects;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.input.Actionable;
import edu.austral.dissis.starship.interfaces.BulletCallback;
import edu.austral.dissis.starship.interfaces.Damageable;
import edu.austral.dissis.starship.interfaces.EntityType;
import edu.austral.dissis.starship.weapon.MultiWeapon;
import edu.austral.dissis.starship.weapon.NormalWeapon;
import edu.austral.dissis.starship.weapon.Weapon;
import edu.austral.dissis.starship.interfaces.Valuable;

import java.util.List;
import java.util.function.Consumer;


public class Spaceship extends GameObject implements Actionable, BulletCallback, Valuable, Damageable {

    private Weapon weapon = new NormalWeapon();
    private Consumer<List<Bullet>> bulletsCallback;

    private int lives = Constants.SpaceshipLives;

    private int score;

    private int lastScoreGunChange = 0;
    private long lastWeaponChange = 0;
    private boolean hasSpecialWeapon;


    public Spaceship(Vector2 position, Vector2 direction, float speed, float width, float height) {
        super(position, direction, speed, width, height);
    }

    @Override
    public void accept(GameObject gameObject) {
        gameObject.collisionedWithSpaceShip(this);
    }

    @Override
    public void update() {

        if (lives <= 0) {
            isActive = false;
            return;
        }

        manageSpecialWeapon();

        checkPosition();

    }

    private void manageSpecialWeapon() {
        final boolean shouldChangeToSpecialWeapon = System.currentTimeMillis() - lastWeaponChange > 2000
                && score - lastScoreGunChange > Constants.GunChangeScore;

        if (shouldChangeToSpecialWeapon) {
            lastScoreGunChange = score;
            lastWeaponChange = System.currentTimeMillis();
            hasSpecialWeapon = true;
            weapon = new MultiWeapon();
            return;
        }

        final boolean shouldChangeToNormalWeapon = hasSpecialWeapon
                && System.currentTimeMillis() - lastWeaponChange > 1000;

        if (shouldChangeToNormalWeapon) {
            weapon = new NormalWeapon();
            hasSpecialWeapon = false;
            lastWeaponChange = System.currentTimeMillis();
        }
    }

    @Override
    public void collisionedWithAsteroid(Asteroid asteroid) {
        lives -= asteroid.getDamage();
    }

    @Override
    public void collisionedWithSpaceShip(Spaceship spaceShip) {
        lives -= spaceShip.getDamage();

    }

    @Override
    public void collisionedWithBullet(Bullet bullet) {
        lives -= bullet.getDamage();
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.Spaceship;
    }

    @Override
    public void accelerate() {
        move();
    }

    @Override
    public void rotate(float angle) {
        direction = direction.rotate(angle);
    }

    @Override
    public void shoot() {
        weapon.shoot(
                position,
                direction,
                valuable -> score += valuable.getValue()
        ).ifPresent(bulletsCallback);

    }

    @Override
    public void addBulletsCallback(Consumer<List<Bullet>> callback) {
        bulletsCallback = callback;
    }

    @Override
    public int getValue() {
        return Constants.SpaceshipValue;
    }

    @Override
    public int getDamage() {
        return Constants.SpaceshipDamage;
    }
}
