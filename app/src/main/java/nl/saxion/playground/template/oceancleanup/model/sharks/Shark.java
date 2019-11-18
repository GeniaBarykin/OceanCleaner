package nl.saxion.playground.template.oceancleanup.model.sharks;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.model.sharks.graphics.SharkLeft;

public abstract class Shark extends Entity {

    private final float width;
    private final float height;

    private float xSpeed;

    private float ySpeed;

    private final int damage;

    private Game game;

    private float xPosition, yPosition;

    public Shark(Game game) {
        this.game = game;

        this.width = 15f;
        this.height = 15f;
        this.damage = 25;

        init();
    }

    public void setXPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getXPosition() {
        return xPosition;
    }

    public float getYPosition() {
        return yPosition;
    }

    public int getDamage() {
        return damage;
    }

    public Game getGame() {
        return game;
    }

    private void init() {
        if (this instanceof SharkLeft) {
            this.xPosition = game.getWidth() + (float) (Math.random() * game.getWidth());
        } else {
            this.xPosition = 0 - ((float) (Math.random() * game.getWidth()));
        }
        this.yPosition = (float) (Math.random() * (game.getHeight() * 0.70));

        this.xSpeed = (float) (Math.random() * 0.1 + 0.1);
        this.ySpeed = (float) (Math.random() * 0.2 + 0.3);
    }
}
