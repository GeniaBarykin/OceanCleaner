package nl.saxion.playground.template.oceancleanup.model.garbage;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.oceancleanup.Game;

/**
 * Model class of Garbage
 *
 * @author artem
 */
public abstract class Garbage extends Entity {

    private final float width;
    private final float height;

    private float xSpeed;

    private final int price;

    private Game game;

    private float xPosition, yPosition;

    /**
     * Creates an instance of Garbage with random position and speed
     *
     * @param game
     */
    public Garbage(Game game) {
        this.game = game;

        this.width = 15f;
        this.height = 15f;
        this.price = 1;

        init();
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

    public int getPrice() {
        return price;
    }

    public Game getGame() {
        return game;
    }

    /**
     * With every tick the Garbage moves to the left
     * Once it is fully out of the screen -> it gets destroyed
     */
    @Override
    public void tick() {
        this.xPosition -= xSpeed;

        if (this.getXPosition() < 0 - this.width) {
            this.getGame().removeEntity(this);
        }
    }

    private void init() {
        // Horizontal position is always outside the right side of the screen
        this.xPosition = game.getWidth() + (float) (Math.random() * game.getWidth());
        this.yPosition = (float) (Math.random() * (game.getHeight() * 0.60));
        // Vertical position is always within the background of the game

        // Speed is generated randomly
        this.xSpeed = (float) (Math.random() * 0.1 + 0.05);
    }
}
