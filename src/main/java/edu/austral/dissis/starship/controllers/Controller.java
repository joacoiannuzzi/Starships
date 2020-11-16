package edu.austral.dissis.starship.controllers;

import processing.core.PGraphics;

import java.util.Set;

public interface Controller {

    void handleInput(Set<Integer> keys);

    void update();

    void draw(PGraphics graphics);
}
