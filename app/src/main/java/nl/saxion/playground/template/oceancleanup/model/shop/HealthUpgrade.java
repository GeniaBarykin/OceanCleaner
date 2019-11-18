package nl.saxion.playground.template.oceancleanup.model.shop;

import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * @author Wim Luyendijk
 */

public class HealthUpgrade extends Upgrade {


    public HealthUpgrade(String name, int imageId, int cost, String description) {
        super(name, imageId, cost, description);
    }

    private void raisePrice() {//increase price after buying upgrade
        this.setCost(
                this.getCost() + this.getMinCost() * GameProgress.getInstance().getHealthMod());
    }

    @Override
    public void buyItem(int money) {
        //checks if upgrade isn't MAXed
        if (GameProgress.getInstance().canBuyHealth()) {
            //if not MAXed return true to ShopActivity and spend money
            GameProgress.getInstance().buyHealth();
            super.buyItem(money);
            this.setUpgradePossible(true);
            raisePrice();
        } else {
            //if MAXed return false
            setUpgradePossible(false);
        }
    }
}

