package edu.austral.dissis.starship;

import edu.austral.dissis.starship.base.framework.GameFramework;
import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.base.framework.WindowSettings;
import edu.austral.dissis.starship.controllers.Controller;
import edu.austral.dissis.starship.controllers.GameController;
import processing.core.PGraphics;
import processing.event.KeyEvent;

import java.util.Set;

public class CustomGameFramework implements GameFramework {

    private Controller controller;

    @Override
    public void setup(WindowSettings windowsSettings, ImageLoader imageLoader) {
        windowsSettings
                .setSize(Constants.GameWidth, Constants.GameHeight);

        controller = new GameController(imageLoader);

    }

    @Override
    public void draw(PGraphics graphics, float timeSinceLastDraw, Set<Integer> keySet) {
        controller.handleInput(keySet);
        controller.update();
        controller.draw(graphics);
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }
}
