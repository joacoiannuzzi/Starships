package edu.austral.dissis.starship.drawers;

import processing.core.PGraphics;
import processing.core.PImage;

import java.util.List;

public class DrawerController {

    private DrawerController() {
    }

    public static void draw(PGraphics graphics, List<Drawable> drawables) {
        drawables.forEach(d -> d.draw(graphics));
    }

    public static void drawBackground(PGraphics graphics, PImage background) {
        graphics.background(background);
    }
}
