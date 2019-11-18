package nl.saxion.playground.template.oceancleanup;

import android.graphics.Color;
import android.graphics.Paint;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.audio_player.Sound;
import nl.saxion.playground.template.oceancleanup.model.garbage.Garbage;
import nl.saxion.playground.template.oceancleanup.model.powerup.FuelBarrel;
import nl.saxion.playground.template.oceancleanup.model.powerup.PowerUp;
import nl.saxion.playground.template.oceancleanup.model.powerup.Wrench;
import nl.saxion.playground.template.oceancleanup.model.sharks.Shark;
import nl.saxion.playground.template.oceancleanup.model.Submarine;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * @author genia, artem
 */
public class Collision extends Entity {

    private Game game;

    private float hitShark;
    private Sound snd = Sound.getInstance();
    private int safeCount;

    public Collision(Game game) {
        this.game = game;
        this.hitShark = 0;
        this.safeCount = 0;
    }

    private float distance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Events on one tick
     *
     * @author genia, artem
     */
    @Override
    public void tick() {
        this.hitShark = hitShark * 0.99f;

        Submarine submarine = game.getEntity(Submarine.class);

        if (submarine != null) {

            collisionWithGarbage(submarine);

            collisionWithShark(submarine);

            collisionWithPowerUp(submarine);

            submarine.setFuel(submarine.getFuel() - 1);

            Activity.update();
        }
    }

    @Override
    public void draw(GameView gv) {

        if (hitShark > 0.01) {
            Paint redPaint = new Paint();
            redPaint.setColor(Color.argb(Math.round(hitShark * 150), 255, 0, 0));
            gv.getCanvas().drawPaint(redPaint);
        }
    }

    /**
     * Check if submarine is touching garbage
     *
     * @author genia, artem
     */
    private void collisionWithGarbage(Submarine submarine) {

        for (Garbage garbage : game.getEntities(Garbage.class)) {
            if (distance(garbage.getXPosition(), garbage.getYPosition(),
                    submarine.getXPosition(), submarine.getYPosition()) < garbage.getWidth()) {
                GameProgress.getInstance()
                        .setMoney(GameProgress.getInstance().getMoney() + garbage.getPrice());
                game.removeEntity(garbage);
                snd.playSound(3);
            }
        }
    }

    /**
     * Check if submarine is touching sharks
     *
     * @author genia, artem
     */
    private void collisionWithShark(Submarine submarine) {

        for (Shark shark : game.getEntities(Shark.class)) {
            if (distance(shark.getXPosition(), shark.getYPosition(), submarine.getXPosition(),
                    submarine.getYPosition()) < shark.getWidth()) {
                if (safeCount == 0) {
                    this.hitShark = 1;
                    submarine.setHealth(submarine.getHealth() -
                            shark.getDamage() * 100 /
                                    (10 + 10 * GameProgress.getInstance().getDecreaseDamage()));
                    snd.playSound(1);

                } else if (safeCount ==
                        50 + 20 * GameProgress.getInstance().getIncreaseSafeTime()) {
                    safeCount = -1;
                }
                safeCount++;
            }
        }
    }

    /**
     * Check if submarine is touching powerUps
     *
     * @author genia
     */
    private void collisionWithPowerUp(Submarine submarine) {

        for (PowerUp powerUp : game.getEntities(PowerUp.class)) {
            if (distance(powerUp.getXPosition(), powerUp.getYPosition(),
                    submarine.getXPosition(), submarine.getYPosition()) < powerUp.getWidth()) {
                if (powerUp instanceof FuelBarrel) {
                    submarine.setFuel(submarine.getFuel() + powerUp.getQuality() +
                            400 * GameProgress.getInstance().getFuelGainMultiply());
                    if (submarine.getFuel() > submarine.getMaxFuel()) {
                        submarine.setFuel(submarine.getMaxFuel());
                    }
                    snd.playSound(5);
                } else if (powerUp instanceof Wrench) {
                    submarine.setHealth(submarine.getHealth() + powerUp.getQuality() +
                            40 * GameProgress.getInstance().getHealthGainMultiply());
                    if (submarine.getHealth() > submarine.getMaxHealth()) {
                        submarine.setHealth(submarine.getMaxHealth());
                    }
                    snd.playSound(4);
                }
                game.removeEntity(powerUp);
            }
        }
    }
}
