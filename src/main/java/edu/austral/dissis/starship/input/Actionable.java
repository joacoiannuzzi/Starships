package edu.austral.dissis.starship.input;

public interface Actionable {

    void accelerate();

    void rotate(float angle);

    void shoot();

    boolean isActive();
}

