package edu.austral.dissis.starship.drawers;

import edu.austral.dissis.starship.interfaces.Positionable;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class GameObjectDrawer implements Drawable {

    private final Positionable positionable;
    private final PImage image;

    public GameObjectDrawer(Positionable positionable, PImage image) {
        this.positionable = positionable;
        this.image = image;
    }

    @Override
    public void draw(PGraphics graphics) {
        graphics.pushMatrix();
        graphics.translate(positionable.getPosition().getX(), positionable.getPosition().getY());
        graphics.rotate(positionable.getDirection().rotate(PConstants.PI / 2).getAngle());
        graphics.imageMode(PConstants.CENTER);
        graphics.image(image, 0, 0);
        graphics.popMatrix();
    }
}
