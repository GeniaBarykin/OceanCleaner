package nl.saxion.playground.template.oceancleanup.model;

import java.util.Random;

import nl.saxion.playground.template.lib.Entity;
import nl.saxion.playground.template.oceancleanup.Game;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;
import nl.saxion.playground.template.oceancleanup.model.garbage.graphics.GlassBottle;
import nl.saxion.playground.template.oceancleanup.model.garbage.graphics.PizzaBag;
import nl.saxion.playground.template.oceancleanup.model.garbage.graphics.PlasticBottle;
import nl.saxion.playground.template.oceancleanup.model.powerup.FuelBarrel;
import nl.saxion.playground.template.oceancleanup.model.powerup.Wrench;
import nl.saxion.playground.template.oceancleanup.model.sharks.graphics.SharkLeft;
import nl.saxion.playground.template.oceancleanup.model.sharks.graphics.SharkRight;


/**
 * Creates all the entities of the game
 *
 * @author genia, artem
 */
public class EntityCreator extends Entity {

    private Game game;
    private Random random = new Random();
    private static int tickCount;

    public static boolean tickFreeze;

    public EntityCreator(Game game) {
        this.game = game;
    }

    public static int getTickCount() {
        return tickCount;
    }

    public static void resetTicks() {
        tickCount = 0;
    }

    @Override
    public void tick() {
        if (!tickFreeze) {
            tickCount++;
        }
        float avgTimeBetweenSharks = 2f / GameProgress.getInstance().getDifficultyLv();
        float avgTimeBetweenGarbage = 1f;
        float avgTimeBetweenPowerUps = 3f + 1 * GameProgress.getInstance().getDifficultyLv() / 2;

        while (random.nextFloat() < (1f / avgTimeBetweenSharks) / game.ticksPerSecond()) {
            int randomNumber = (int) (Math.random() * 100);
            if (randomNumber < 40) {
                game.addEntity(new SharkLeft(game));
            } else {
                game.addEntity(new SharkRight(game));
            }
        }
        while (random.nextFloat() < (1f / avgTimeBetweenGarbage) / game.ticksPerSecond()) {
            int randomNumber = (int) (Math.random() * 100);
            if (randomNumber < 20) {
                game.addEntity(new GlassBottle(game));
            } else if (randomNumber < 50) {
                game.addEntity(new PizzaBag(game));
            } else {
                game.addEntity(new PlasticBottle(game));
            }
        }
        Submarine submarine = game.getEntity(Submarine.class);
        if (submarine != null) {
            while (random.nextFloat() < (1f / avgTimeBetweenPowerUps) / game.ticksPerSecond()) {
                int randomNumber = (int) (Math.random() * 100);
                if (submarine.getFuel() < (submarine.getMaxFuel() / 5) * 7) {
                    if (randomNumber < 30) {
                        game.addEntity(new Wrench(game));
                    } else {
                        game.addEntity(new FuelBarrel(game));
                    }
                } else {
                    if (randomNumber > 30) {
                        game.addEntity(new Wrench(game));
                    } else {
                        game.addEntity(new FuelBarrel(game));
                    }
                }
            }
        }
    }
}





