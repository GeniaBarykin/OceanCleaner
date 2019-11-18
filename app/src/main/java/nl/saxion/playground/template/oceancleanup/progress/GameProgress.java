package nl.saxion.playground.template.oceancleanup.progress;

import nl.saxion.playground.template.oceancleanup.model.EntityCreator;
import nl.saxion.playground.template.oceancleanup.model.shop.UpgradeSampleProvider;

/**
 * Saved from lv to lv progress of the player
 *
 * @author genia
 */
public class GameProgress {
    //shop param
    private static GameProgress gp;
    private float difficultyLv;
    private int fuelGainMultiply;
    private int healthGainMultiply;
    private int money;
    private int lives;
    private int livesWereBought;
    private int healthMod;
    private int fuelMod;
    private int increaseSafeTime;
    private int decreaseDamage;
    private int lv;

    //Non shop param
    private int heathMax;
    private int fuelMax;
    private int time;
    private int maxTime;

    /**
     * Get instance
     *
     * @return GameProgress instance
     */
    public static GameProgress getInstance() {
        if (gp == null) {
            gp = new GameProgress();
        }
        return gp;
    }

    /**
     * Initial values for the start of the game
     */
    private GameProgress() {
        maxTime = 3000;
        heathMax = 1000;
        fuelMax = 4000;
        difficultyLv = 1;
        fuelGainMultiply = 0;
        healthGainMultiply = 0;
        money = 0;
        lives = 1;
        increaseSafeTime = 0;
        livesWereBought = 0;
        decreaseDamage = 0;
        time = 0;
        lv = 1;
    }

    public float getDifficultyLv() {
        return difficultyLv;
    }

    public int getHeathMax() {
        return heathMax;
    }

    public int getFuelMax() {
        return fuelMax;
    }

    public int getTime() {
        return time;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getLv() {
        return lv;
    }

    public int getLives() {
        return lives;
    }

    public int getDecreaseDamage() {
        return decreaseDamage;
    }

    public int getIncreaseSafeTime() {
        return increaseSafeTime;
    }

    public int getFuelGainMultiply() {
        return fuelGainMultiply;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setTime(int time) {
        this.time = time;
    }


    //For shop
    public int getHealthMod() {
        return healthMod;
    }

    /**
     * Shop check
     *
     * @return boolean to show if it is possibble to buy new upgrade
     */
    public boolean canBuyHealth() {
        if (healthMod <= 10) {
            return true;
        }
        return false;
    }

    public int getLivesWereBought() {
        return livesWereBought;
    }


    public int getFuelMod() {
        return fuelMod;
    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyHealth() {
        this.healthMod++;
        heathMax += 70 * healthMod;
    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyFuel() {
        this.fuelMod++;
        fuelMax += +750 * fuelMod;
    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyLife() {
        this.lives++;
        livesWereBought++;

    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyIncreaseSafeTime() {
        this.increaseSafeTime++;
    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyFuelGainMultiply() {
        this.fuelGainMultiply++;
    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyDecreaseDamage() {
        this.decreaseDamage++;
    }

    /**
     * Increase param after upgrade was bought
     */
    public void buyHealthGainMultiply() {
        this.healthGainMultiply++;
    }


    public int getHealthGainMultiply() {
        return healthGainMultiply;
    }

    /**
     * Shop check
     *
     * @return boolean to show if it is possible to buy new upgrade
     */
    public boolean canBuyDecreaseDamage() {
        if (decreaseDamage <= 5) {
            return true;
        }
        return false;
    }

    /**
     * Shop check
     *
     * @return boolean to show if it is possible to buy new upgrade
     */
    public boolean canBuyIncreaseSafeTime() {
        if (increaseSafeTime <= 5) {
            return true;
        }
        return false;
    }

    /**
     * Shop check
     *
     * @return boolean to show if it is possible to buy new upgrade
     */
    public boolean canbuyHealthGainMultiply() {
        if (healthGainMultiply <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Shop check
     *
     * @return boolean to show if it is possible to buy new upgrade
     */
    public boolean canBuyFuelGainMultiply() {
        if (fuelGainMultiply <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Shop check
     *
     * @return boolean to show if it is possible to buy new upgrade
     */
    public boolean canBuyFuel() {
        if (fuelMod <= 10) {
            return true;
        }
        return false;
    }

    /**
     * Player loses life
     */
    public void takeLife() {
        lives--;
    }

    /**
     * After lv is finished, difficulty increases
     */
    public boolean increaseDifficultyLv() {
        lv++;
        this.maxTime = this.maxTime + 5 * (int) difficultyLv;
        this.difficultyLv = difficultyLv + 0.5f;
        return false;
    }

    /**
     * Start new game
     */
    public void wipeProgress() {
        for (int i = 0; i < UpgradeSampleProvider.getUpgrades().size(); i++) {
            UpgradeSampleProvider.getUpgrades().get(i).resetCost();
        }
        gp = null;
        EntityCreator.resetTicks();
    }
}
