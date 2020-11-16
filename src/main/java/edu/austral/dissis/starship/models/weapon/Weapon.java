package edu.austral.dissis.starship.models.weapon;

import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.models.Bullet;
import edu.austral.dissis.starship.score.Valuable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface Weapon {

    Optional<List<Bullet>> shoot(Vector2 position, Vector2 direction, Consumer<Valuable> hitCallback);
}
