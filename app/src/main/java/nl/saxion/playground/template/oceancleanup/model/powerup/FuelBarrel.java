package nl.saxion.playground.template.oceancleanup.model.powerup;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * Fuel barrel that increases fuel level of the Submarine
 * Concrete implementation of PowerUp
 *
 * @author Genia
 */
public class FuelBarrel extends PowerUp {

    static private Bitmap bitmap;

    public FuelBarrel(Game game) {
        super(game, 1000);
    }

    /**
     * Draws a fuel barrel
     *
     * @param gv The `GameView` to draw to.
     */
    @Override
    public void draw(GameView gv) {
        if (bitmap == null) {
            bitmap = gv.getBitmapFromResource(R.drawable.oil_barrel);
        }
        gv.drawBitmap(bitmap, this.getXPosition() - this.getWidth() / 2,
                this.getYPosition() - this.getHeight() / 2, this.getWidth(), this.getHeight());
    }
}
