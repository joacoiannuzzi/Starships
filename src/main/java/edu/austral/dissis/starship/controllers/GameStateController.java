package edu.austral.dissis.starship.controllers;

import edu.austral.dissis.starship.base.collision.CollisionEngine;
import edu.austral.dissis.starship.models.Bullet;
import edu.austral.dissis.starship.models.GameObject;
import edu.austral.dissis.starship.models.Spaceship;
import edu.austral.dissis.starship.utils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameStateController {

    private List<GameObject> gameObjects = new ArrayList<>();
    private CollisionEngine collisionEngine = new CollisionEngine();

    private long lastAsteroidTime = System.currentTimeMillis();

    public void update() {
        gameObjects.forEach(GameObject::update);
        collisionEngine.checkCollisions(gameObjects);
        createAsteroid();
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void removeNonActive() {
        gameObjects.removeIf(gameObject -> !gameObject.isActive());
    }

    private void addBullets(List<Bullet> bullets) {
        bullets.forEach(this::add);
    }

    public List<Spaceship> createSpaceships(int quantity) {
        final List<Spaceship> spaceships =
                Stream.generate(Randoms::createSpaceship)
                        .limit(quantity)
                        .collect(Collectors.toList());
        spaceships.forEach(spaceship -> spaceship.addBulletsCallback(this::addBullets));
        gameObjects.addAll(spaceships);
        return spaceships;
    }

    public void createAsteroids() {
        Stream.generate(Randoms::createAsteroid)
                .limit(20)
                .forEach(gameObjects::add);
    }

    public void createAsteroid() {
        if (Randoms.bound(10) > 5 && System.currentTimeMillis() - lastAsteroidTime > 1500) {
            gameObjects.add(Randoms.createAsteroid());
            lastAsteroidTime = System.currentTimeMillis();
        }
    }
}
