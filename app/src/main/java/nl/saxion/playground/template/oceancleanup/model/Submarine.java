package nl.saxion.playground.template.oceancleanup.model;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameModel;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Activity;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * The Submarine. Main character of the game
 *
 * @author artem
 */
public class Submarine extends Entity {
    private final float width;

    private final float height;

    private final float xSpeed;
    private final float ySpeed;


    private int maxFuel;
    private int fuel;
    private int maxHealth;
    private int health;
    static private Bitmap bitmap;

    private int submarineFrame;
    private Game game;

    private float xPosition, yPosition;

    public Submarine(Game game) {
        this.game = game;

        this.width = 15f;
        this.height = 15f;
        this.xSpeed = 0.5f;
        this.ySpeed = 0.5f;
        this.xPosition = game.getWidth() / 2;
        this.yPosition = game.getHeight() / 2;

        this.submarineFrame = 0;

        this.maxFuel = GameProgress.getInstance().getFuelMax();
        this.maxHealth = GameProgress.getInstance().getHeathMax();
        this.fuel = maxFuel;
        this.health = maxHealth;

        Activity.setUp();
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getXPosition() {
        return xPosition;
    }

    public float getYPosition() {
        return yPosition;
    }

    public int getFuel() {
        return fuel;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * With every tick cheks whether the Submarine is alive
     * Moves the Submarine depending on screen touches
     */
    @Override
    public void tick() {
        //If Submarine has no health -> destroy it!
        if (this.health < 0) {
            game.removeEntity(this);
        } else {
            //Collect all the screen touches
            for (GameModel.Touch touch : this.game.touches) {
                //Put a margin, so the Submarine doesn't touch the edges of game screen
                int margin = 2;
                if (touch.y > yPosition + margin) {
                    if (yPosition < (game.getHeight() * 0.65)) {
                        //If the user touches screen in a higher point than the Submarine is
                        // currently located -> move the Submarine up to that point
                        yPosition += ySpeed;
                    }
                } else if (touch.y < yPosition - margin) {
                    //If the user touches screen in a lower point than the Submarine is
                    // currently located -> move the Submarine down to that point
                    yPosition -= ySpeed;
                }

                if (touch.x > xPosition + margin) {
                    //If the user touches screen in a 'righter' point than the Submarine is
                    // currently located -> move the Submarine right to that point
                    xPosition += xSpeed;
                } else if (touch.x < xPosition - margin) {
                    //If the user touches screen in a 'lefter' point than the Submarine is
                    // currently located -> move the Submarine left to that point
                    xPosition -= xSpeed;
                }
            }
        }
    }

    /**
     * Draws the Submarine using two frames
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {

        //Two frames of the Submarine. Are repeated to make the animation look not too fast
        int[] pics = {R.drawable.idle1, R.drawable.idle1, R.drawable.idle1, R.drawable.idle1,
                R.drawable.idle1, R.drawable.idle1, R.drawable.idle2, R.drawable.idle2,
                R.drawable.idle2, R.drawable.idle2, R.drawable.idle2, R.drawable.idle2};

        //Use current frame
        bitmap = gv.getBitmapFromResource(pics[submarineFrame]);
        //Change to the next frame
        submarineFrame++;
        if (submarineFrame > pics.length - 1) {
            //To avoid array overflow -> go back to the first frame once the last index is reached
            submarineFrame = 0;
        }

        //Draw the submarine
        gv.drawBitmap(bitmap, xPosition - width / 2, yPosition - height / 2, width, height);
        //Update the picture
        gv.invalidate();
    }
}
