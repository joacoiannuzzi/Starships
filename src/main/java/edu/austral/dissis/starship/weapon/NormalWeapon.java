package edu.austral.dissis.starship.weapon;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.gameObjects.Bullet;
import edu.austral.dissis.starship.interfaces.Valuable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class NormalWeapon implements Weapon {

    private long lastShot = 0L;

    @Override
    public Optional<List<Bullet>> shoot(Vector2 position, Vector2 direction, Consumer<Valuable> hitCallback) {
        if (System.currentTimeMillis() - lastShot < 1000)
            return Optional.empty();

        lastShot = System.currentTimeMillis();
        return Optional.of(
                Collections.singletonList(
                        new Bullet(
                                position.add(direction.multiply((float) Constants.SpaceshipHeight)),
                                direction,
                                7f,
                                Constants.BulletWidth,
                                Constants.BulletHeight,
                                hitCallback
                        )
                )
        );
    }
}
