package nl.saxion.playground.template.oceancleanup.model.shop;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.playground.template.R;

/**
 * @author Hakim Wim
 */
public class UpgradeSampleProvider {
    private static List<Upgrade> upgrades = new ArrayList<>();

    static {
        upgrades.add(new HullUpgrade("Reinforced Hull", R.drawable.decrease_damage, 5,
                "Decreases damage taken"));
        upgrades.add(new FuelTankUpgrade("Fuel Tank extension", R.drawable.buyfuel, 5,
                "Increases overall Fuel"));
        upgrades.add(new HealthUpgrade("Health Bar Increase", R.drawable.health, 5,
                "Increases overall Health"));
        upgrades.add(new SafeTimeUpgrade("Increase Safe Time", R.drawable.increase_safe_time, 5,
                "Increases safe time between damage taken"));
        upgrades.add(
                new FuelGainMultiplyUpgrade("Increase Fuel Gain", R.drawable.fuel_efficiancy, 5,
                        "Increases fuel from barrels"));
        upgrades.add(new HealGainMultiplyUpgrade("Increase Health Gain", R.drawable.health_gain, 5,
                "Increases health from wrenches"));
        upgrades.add(new LifeUpgrade("Buy a Life", R.drawable.buylife, 5,
                "Increases your lives by one"));
    }

    public static List<Upgrade> getUpgrades() {
        return upgrades;
    }
}


