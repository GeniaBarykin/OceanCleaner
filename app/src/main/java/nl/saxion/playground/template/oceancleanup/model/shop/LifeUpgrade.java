package nl.saxion.playground.template.oceancleanup.model.shop;


import nl.saxion.playground.template.oceancleanup.progress.GameProgress;


/**
 * @author Wim Luyendijk
 */
public class LifeUpgrade extends Upgrade {

    public LifeUpgrade(String name, int imageId, int cost, String description) {
        super(name, imageId, cost, description);
    }

    private void raisePrice() {//increase price after buying upgrade
        this.setCost(this.getCost() +
                this.getMinCost() * GameProgress.getInstance().getLivesWereBought());
    }

    @Override
    public void buyItem(int money) {

        GameProgress.getInstance().buyLife();
        super.buyItem(money);
        this.setUpgradePossible(true);
        raisePrice();
    }
}
