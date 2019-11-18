package nl.saxion.playground.template.oceancleanup.model.powerup;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.oceancleanup.Game;

/**
 * PowerUps for the Submarine
 * Has concrete implementations: Wrench(for health increase) and FuelBarrel(for fuel increase)
 *
 * @author Genia
 */
public abstract class PowerUp extends Entity {

    private final float width;
    private final float height;
    private int quality;
    private float xSpeed;

    private Game game;

    private float xPosition, yPosition;

    public int getQuality() {
        return quality;
    }

    public PowerUp(Game game, int quality) {
        this.game = game;
        this.quality = quality;
        this.width = 15f;
        this.height = 15f;

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

    public Game getGame() {
        return game;
    }

    /**
     * With every tick the PowerUp entity moves to the left
     */
    @Override
    public void tick() {
        this.xPosition -= xSpeed;

        if (this.getXPosition() < 0 - this.width) {
            this.getGame().removeEntity(this);
        }
    }

    private void init() {

        this.xPosition = game.getWidth() + (float) (Math.random() * game.getWidth());
        this.yPosition = (float) (Math.random() * (game.getHeight() * 0.60));

        this.xSpeed = (float) (Math.random() * 0.1 + 0.05);
    }
}




