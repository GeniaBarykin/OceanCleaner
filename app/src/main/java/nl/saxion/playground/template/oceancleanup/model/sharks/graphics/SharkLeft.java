package nl.saxion.playground.template.oceancleanup.model.sharks.graphics;

import android.graphics.Bitmap;
import android.util.Log;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.model.sharks.Shark;

/**
 * Shark with a head pointing to the LEFT - graphical interpretation of Shark
 *
 * @author artem
 */
public class SharkLeft extends Shark {

    static private Bitmap bitmap;

    public SharkLeft(Game game) {
        super(game);
    }

    /**
     * With every tick the SharkLeft moves to the LEFT
     * Once outside of the screen -> it gets destroyed
     */
    @Override
    public void tick() {
        this.setXPosition(this.getXPosition() - this.getXSpeed());

        if (this.getXPosition() < (0 - this.getWidth())) {
            this.getGame().removeEntity(this);
        }
    }

    /**
     * Draws a shark pointing to the left
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {
        if (bitmap == null) {

            bitmap = gv.getBitmapFromResource(R.drawable.shark_left);
        }
        gv.drawBitmap(bitmap, this.getXPosition() - this.getWidth() / 2,
                this.getYPosition() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
}
