package nl.saxion.playground.template.oceancleanup.model.shop;

import nl.saxion.playground.template.oceancleanup.progress.GameProgress;

public class HullUpgrade extends Upgrade {

    public HullUpgrade(String name, int imageId, int cost, String description) {
        super(name, imageId, cost, description);
    }

    private void raisePrice() {
        this.setCost(this.getCost() +
                this.getMinCost() * GameProgress.getInstance().getDecreaseDamage());
    }

    @Override
    public void buyItem(int money) {
        if (GameProgress.getInstance().canBuyDecreaseDamage()) {
            GameProgress.getInstance().buyDecreaseDamage();
            super.buyItem(money);
            this.setUpgradePossible(true);
            raisePrice();
        } else {
            this.setUpgradePossible(false
            );
        }
    }
}
