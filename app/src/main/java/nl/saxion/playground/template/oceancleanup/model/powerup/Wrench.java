package nl.saxion.playground.template.oceancleanup.model.powerup;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * Wrench that increases health level of the Submarine
 * Concrete implementation of the PowerUp
 *
 * @author Genia
 */
public class Wrench extends PowerUp {
    static private Bitmap bitmap;

    public Wrench(Game game) {
        super(game, 100);
    }

    /**
     * Draws a wrench
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {

        if (bitmap == null) {
            bitmap = gv.getBitmapFromResource(R.drawable.wrench);
        }
        gv.drawBitmap(bitmap, this.getXPosition() - this.getWidth() / 2,
                this.getYPosition() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
}
