package edu.austral.dissis.starship.models.weapon;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.models.Bullet;
import edu.austral.dissis.starship.score.Valuable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiWeapon implements Weapon {

    private long lastShot = 0L;

    @Override
    public Optional<List<Bullet>> shoot(Vector2 position, Vector2 direction, Consumer<Valuable> hitCallback) {
        if (System.currentTimeMillis() - lastShot < 200)
            return Optional.empty();

        lastShot = System.currentTimeMillis();
        return Optional.of(
                IntStream.range(0, 5)
                        .mapToObj(i ->
                                new Bullet(
                                        position.add(direction.multiply((float) (Constants.SpaceshipHeight + i * 0.5))),
                                        direction,
                                        7f,
                                        Constants.BulletWidth,
                                        Constants.BulletHeight,
                                        hitCallback
                                )
                        ).collect(Collectors.toList())
        );
    }
}