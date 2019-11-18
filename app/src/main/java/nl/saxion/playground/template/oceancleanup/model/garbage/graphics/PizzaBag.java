package nl.saxion.playground.template.oceancleanup.model.garbage.graphics;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.model.garbage.Garbage;

/**
 * Pizza bag -  graphical interpretation of Garbage
 *
 * @author artem
 */
public class PizzaBag extends Garbage {

    static private Bitmap bitmap;

    public PizzaBag(Game game) {
        super(game);
    }

    /**
     * Draws a pizza bag
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {

        if (bitmap == null) {
            bitmap = gv.getBitmapFromResource(R.drawable.pizza_bag);
        }
        gv.drawBitmap(bitmap, this.getXPosition() - this.getWidth() / 2,
                this.getYPosition() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
}
