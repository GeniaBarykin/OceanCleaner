package nl.saxion.playground.template.oceancleanup.model.sharks.graphics;

import android.graphics.Bitmap;
import android.util.Log;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.model.sharks.Shark;

/**
 * Shark with a head pointing to the RIGHT - graphical interpretation of Shark
 *
 * @author artem
 */
public class SharkRight extends Shark {

    static private Bitmap bitmap;

    public SharkRight(Game game) {
        super(game);
    }

    /**
     * With every tick the SharkRight moves to the RIGHT
     * Once outside of the screen -> it gets destroyed
     */
    @Override
    public void tick() {
        this.setXPosition(this.getXPosition() + this.getXSpeed());

        if (this.getXPosition() > (this.getGame().getWidth() + this.getWidth())) {
            this.getGame().removeEntity(this);
        }
    }

    /**
     * Draws a shark pointing to the right
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {
        if (bitmap == null) {

            bitmap = gv.getBitmapFromResource(R.drawable.shark_right);
        }
        gv.drawBitmap(bitmap, this.getXPosition() - this.getWidth() / 2,
                this.getYPosition() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
}
