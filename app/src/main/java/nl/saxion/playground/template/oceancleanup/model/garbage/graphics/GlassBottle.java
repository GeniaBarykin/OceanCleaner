package nl.saxion.playground.template.oceancleanup.model.garbage.graphics;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.model.garbage.Garbage;

/**
 * Glass bottle -  graphical interpretation of Garbage
 *
 * @author artem
 */
public class GlassBottle extends Garbage {

    static private Bitmap bitmap;

    public GlassBottle(Game game) {
        super(game);
    }

    /**
     * Draws a glass bottle
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {

        if (bitmap == null) {
            bitmap = gv.getBitmapFromResource(R.drawable.bottle2);
        }
        gv.drawBitmap(bitmap, this.getXPosition() - this.getWidth() / 2,
                this.getYPosition() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
}
