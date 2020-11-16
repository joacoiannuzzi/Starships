package edu.austral.dissis.starship.drawers;

import processing.core.PGraphics;

import java.util.List;

public class DrawerController {

    private DrawerController() {
    }

    public static void draw(PGraphics graphics, List<Drawable> drawables) {
        drawables.forEach(d -> d.draw(graphics));
    }
}
