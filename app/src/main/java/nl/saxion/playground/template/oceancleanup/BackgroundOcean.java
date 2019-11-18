package nl.saxion.playground.template.oceancleanup;

import android.graphics.Bitmap;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;

/**
 * @suthor Wim Luyendijk
 */

public class BackgroundOcean extends Entity {

    private static Bitmap bitmap;

    private Game game;
    private int y;
    private double x, dx;
    private float bgWidth;

    public BackgroundOcean(Game game) {
        this.game = game;
        this.y = 0;
        this.x = 0;
        this.dx = 0;
    }

    //setVector is used to determine how far the background will move to the right per game tick.
    public void setVector(double dx) {
        this.dx = dx;
    }

    //tick is used to move the background to the right the distance of the vector,
    //this method also sets the image's x cord back to 0 once it has traveled across teh screen.
    @Override
    public void tick() {
        x -= dx;

        if (x < -this.bgWidth) {
            x = 0;
        }
    }

    @Override
    public void draw(GameView gv) {
        if (bitmap == null) { //making sure the bitmap exists before assigning it to variables.
            bitmap = gv.getBitmapFromResource(R.drawable.background_menu2); //ocean_background
        }

        this.bgWidth = (float) bitmap.getWidth() / (float) bitmap.getHeight() * game.getHeight();
        setVector(.1);

        for (int x = 0; x <= Math.ceil(game.getWidth() / bgWidth); x++) {
            //draws bitmap at new x cord.
            gv.drawBitmap(bitmap, (float) this.x, 0, bgWidth, game.getHeight());
            if (this.x < 0) {
                //draws a new bitmap to fill void where old bitmap is being moved from.
                gv.drawBitmap(bitmap, (float) this.x + bgWidth, y, bgWidth, game.getHeight());
            }
            tick();
        }
    }
}