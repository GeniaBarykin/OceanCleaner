package nl.saxion.playground.template.oceancleanup.model.shop;

import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

/**
 * @author Wim Luyendijk
 */

public class FuelTankUpgrade extends Upgrade {

    public FuelTankUpgrade(String name, int imageId, int cost, String description) {
        super(name, imageId, cost, description);
    }

    private void raisePrice() {
        this.setCost(this.getCost() + this.getMinCost() * GameProgress.getInstance().getFuelMod());
    }

    @Override
    public void buyItem(int money) {
        if (GameProgress.getInstance().canBuyFuel()) {
            GameProgress.getInstance().buyFuel();
            super.buyItem(money);
            this.setUpgradePossible(true);
            raisePrice();
        } else {
            this.setUpgradePossible(false);
        }
    }
}
