package edu.austral.dissis.starship.drawers;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.interfaces.EntityType;
import edu.austral.dissis.starship.gameObjects.GameObject;
import processing.core.PImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DrawerCreator {

    final private Map<EntityType, PImage> images;
    private final PImage background;

    public DrawerCreator(ImageLoader imageLoader) {
        images = new HashMap<>(4);

        final PImage spaceship = imageLoader.load("spaceship2.png");
        spaceship.resize(Constants.SpaceshipWidth, Constants.SpaceshipHeight);
        images.put(EntityType.Spaceship, spaceship);

        final PImage asteroid = imageLoader.load("asteroid.png");
        asteroid.resize(Constants.AsteroidWidth, Constants.AsteroidHeight);
        images.put(EntityType.Asteroid, asteroid);

        final PImage bullet = imageLoader.load("laser2.png");
        bullet.resize(Constants.BulletWidth, Constants.BulletHeight);
        images.put(EntityType.Bullet, bullet);

        background = imageLoader.load("background2.jpeg");
        background.resize(Constants.GameWidth, Constants.GameHeight);

    }

    public List<Drawable> createDrawables(List<GameObject> gameObjects) {
        return gameObjects.stream()
                .map(gameObject ->
                        new GameObjectDrawer(gameObject,
                                images.get(gameObject.getEntityType())
                        )
                )
                .collect(Collectors.toList());
    }

    public PImage getBackground() {
        return background;
    }
}
