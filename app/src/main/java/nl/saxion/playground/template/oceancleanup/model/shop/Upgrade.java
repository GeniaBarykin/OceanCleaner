package nl.saxion.playground.template.oceancleanup.model.shop;

import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * @author Hakim
 */
public abstract class Upgrade {
    private int cost;
    private String name;
    private String description;
    private int imageId;
    private boolean upgradePossible;
    private int minCost = 10;


    public Upgrade(String name, int imageId, int cost, String description) {
        this.cost = cost;
        this.name = name;
        this.imageId = imageId;
        this.description = description;
        this.upgradePossible = true;
    }

    public void setUpgradePossible(boolean upgradePossible) {
        this.upgradePossible = upgradePossible;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getMinCost() {
        return minCost;
    }

    public void resetCost() {
        setCost(minCost);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return this.imageId;
    }

    /**
     * Spend money on upgrade
     *
     * @param money
     */
    public void buyItem(int money) {
        GameProgress.getInstance().setMoney(money - cost);
    }

    public boolean isUpgradePossible() {
        return this.upgradePossible;
    }
}
