package edu.austral.dissis.starship.controllers;

import edu.austral.dissis.starship.base.framework.ImageLoader;
import edu.austral.dissis.starship.drawers.Drawable;
import edu.austral.dissis.starship.drawers.DrawerController;
import edu.austral.dissis.starship.drawers.DrawerCreator;
import edu.austral.dissis.starship.input.InputController;
import edu.austral.dissis.starship.input.InputHandler;
import edu.austral.dissis.starship.input.InputHandlerImpl;
import edu.austral.dissis.starship.input.KeyCreator;
import edu.austral.dissis.starship.gameObjects.Spaceship;
import processing.core.PGraphics;

import java.util.List;
import java.util.Set;

public class GameController implements Controller {

    private final GameStateController gameStateController = new GameStateController();
    private final InputController inputController = new InputController();
    private final DrawerCreator drawerCreator;

    public GameController(ImageLoader imageLoader) {
        drawerCreator = new DrawerCreator(imageLoader);
        initGameState();
    }

    @Override
    public void handleInput(Set<Integer> keys) {
        inputController.handleInput(keys);
    }

    @Override
    public void update() {
        gameStateController.update();
        removeNonActive();
    }

    @Override
    public void draw(PGraphics graphics) {
        DrawerController.drawBackground(graphics, drawerCreator.getBackground());
        final List<Drawable> drawables = drawerCreator.createDrawables(gameStateController.getGameObjects());
        DrawerController.draw(graphics, drawables);
    }

    private void initGameState() {
        final List<Spaceship> spaceships = gameStateController.init(2);

        final InputHandler inputHandler = new InputHandlerImpl(spaceships.get(0), KeyCreator.keys1());
        inputController.add(inputHandler);

        final InputHandler inputHandler1 = new InputHandlerImpl(spaceships.get(1), KeyCreator.keys2());
        inputController.add(inputHandler1);
    }

    private void removeNonActive() {
        gameStateController.removeNonActive();
        inputController.removeNonActive();
    }

}
