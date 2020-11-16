package edu.austral.dissis.starship.models;

import edu.austral.dissis.starship.Constants;
import edu.austral.dissis.starship.base.collision.Collisionable;
import edu.austral.dissis.starship.base.vector.Vector2;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public abstract class GameObject implements Positionable, Collisionable<GameObject>, Visitable, EntityTypeable {

    protected Vector2 position;
    protected Vector2 direction;
    protected float speed;
    protected float width;
    protected float height;
    protected boolean isActive = true;


    public GameObject(Vector2 position, Vector2 direction, float speed, float width, float height) {
        this.position = position;
        this.direction = direction;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public boolean isActive() {
        return isActive;
    }

    void move() {
        position = position.add(direction.multiply(speed));
    }

    @Override
    public void collisionedWith(GameObject collisionable) {
        collisionable.accept(this);
    }


    @Override
    public Shape getShape() {
        final Rectangle2D.Float baseSquare =
                new Rectangle2D.Float(width / -2, height / -2, width, height);
        final Path2D.Float path = new Path2D.Float();
        path.append(baseSquare, false);

        final AffineTransform transform = new AffineTransform();
        transform.translate(position.getX(), position.getY());
        transform.rotate(direction.rotate((float) (Math.PI / 2.0f)).getAngle());

        path.transform(transform);

        return path;

    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getDirection() {
        return direction;
    }

    void checkPosition() {
        final float x = position.getX();
        final float y = position.getY();

        final int gameWidth = Constants.GameWidth;
        final int gameHeight = Constants.GameHeight;

        if (x < 0) position = Vector2.vector(gameWidth, y);
        else if (x > gameWidth) position = Vector2.vector(0, y);

        if (y < 0) position = Vector2.vector(x, gameHeight);
        else if (y > gameHeight) position = Vector2.vector(x, 0);

    }
}
