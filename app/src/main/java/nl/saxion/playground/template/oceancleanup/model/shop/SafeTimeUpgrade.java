package nl.saxion.playground.template.oceancleanup.model.shop;

import nl.saxion.playground.template.oceancleanup.progress.GameProgress;


/**
 * @author Wim Luyendijk
 */
public class SafeTimeUpgrade extends Upgrade {

    public SafeTimeUpgrade(String name, int imageId, int cost, String description) {
        super(name, imageId, cost, description);
    }

    private void raisePrice() {//increase price after buying upgrade
        this.setCost(
                this.getCost() + this.getMinCost() * GameProgress.getInstance().getIncreaseSafeTime());
    }

    @Override
    public void buyItem(int money) {
        //checks if upgrade isn't MAXed
        if (GameProgress.getInstance().canBuyIncreaseSafeTime()) {
            //if not MAXed return true to ShopActivity and spend money
            GameProgress.getInstance().buyIncreaseSafeTime();
            super.buyItem(money);
            this.setUpgradePossible(true);
            raisePrice();
        } else {
            //if MAXed return false
            this.setUpgradePossible(false);
        }
    }
}