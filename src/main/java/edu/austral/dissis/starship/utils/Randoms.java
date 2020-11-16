package edu.austral.dissis.starship.utils;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.vector.Vector2;
import edu.austral.dissis.starship.gameObjects.Asteroid;
import edu.austral.dissis.starship.gameObjects.Spaceship;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randoms {

    private static final Random random = ThreadLocalRandom.current();

    public static Asteroid createAsteroid() {
        return new Asteroid(
                createVector(Constants.GameWidth / 5, Constants.GameWidth, Constants.GameHeight / 5, Constants.GameHeight),
                createVector().asUnitary(),
                0.4f,
                Constants.AsteroidWidth,
                Constants.AsteroidHeight
        );
    }

    public static Spaceship createSpaceship() {
        return new Spaceship(
                createVector(Constants.GameWidth, Constants.GameHeight),
                createVector().asUnitary(),
                3f,
                Constants.SpaceshipWidth,
                Constants.SpaceshipHeight
        );
    }

    public static Vector2 createVector() {
        return Vector2.vector(random.nextFloat(), random.nextFloat());
    }

    public static Vector2 createVector(int x, int y) {
        return Vector2.vector(bound(x), bound(y));
    }

    public static Vector2 createVector(int x1, int x2, int y1, int y2) {
        return Vector2.vector(range(x1, x2), range(y1, y2));
    }

    public static int bound(int i) {
        return range(0, i);
    }

    public static int range(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private Randoms() {
    }
}
