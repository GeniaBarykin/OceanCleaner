package nl.saxion.playground.template.oceancleanup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import nl.saxion.playground.template.R;
import nl.saxion.playground.template.oceancleanup.model.shop.Upgrade;
import nl.saxion.playground.template.oceancleanup.model.shop.UpgradeSampleProvider;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;
import nl.saxion.playground.template.oceancleanup.adapters.ShopAdapter;
import nl.saxion.playground.template.oceancleanup.audio_player.Music;
import nl.saxion.playground.template.oceancleanup.audio_player.Sound;
import nl.saxion.playground.template.oceancleanup.сonstraint.ConstraintShopBar;

public class ShopActivity extends AppCompatActivity {

    private List<Upgrade> upgrades;
    private ArrayAdapter<Upgrade> adapter;
    private Upgrade currentUpgrade;
    private ListView listView;
    private Button btBuy, btContinue;
    private ConstraintShopBar constraintShopBar;
    private int money;
    private Music mp = Music.getInstance();
    private ImageView enableMusic, disableMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        upgrades = UpgradeSampleProvider.getUpgrades();
        money = GameProgress.getInstance().getMoney();

        setTitle("Shop");

        adapter = new ShopAdapter(this, upgrades);
        constraintShopBar = findViewById(R.id.constraintShopBar);
        btBuy = findViewById(R.id.btBuy);
        listView = findViewById(R.id.shopList);
        btContinue = findViewById(R.id.buttonShopContinue);
        enableMusic = findViewById(R.id.imageViewEnable2);
        disableMusic = findViewById(R.id.imageViewDisable2);

        constraintShopBar.setParameters(money);
        listView.setAdapter(adapter);

        /**
         * Shop UI
         *
         * @author Hakim
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                currentUpgrade = upgrades.get(position);
                constraintShopBar.updateBar(currentUpgrade.getCost());
            }
        });
        /**
         * Buy item
         *
         * @author Hakim
         */
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUpgrade != null) {//check if you selected upgrade
                    if (money >= currentUpgrade.getCost()) {
                        //check if you have enough money for the upgrade
                        currentUpgrade.buyItem(money);
                        if (currentUpgrade.isUpgradePossible()) {//check if buyItem method returned true or false
                            //if true, buy item and update money
                            money = GameProgress.getInstance().getMoney();
                            constraintShopBar.updateMoney();
                            constraintShopBar.updateBar(currentUpgrade.getCost());
                            adapter.notifyDataSetChanged();
                            Sound.getInstance().playSound(6);
                        } else {
                            //if false
                            Toast.makeText(ShopActivity.this, "Upgrade already full",
                                    Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ShopActivity.this, "Not enough money", Toast.LENGTH_SHORT)
                                .show();
                    }
                } else {
                    Toast.makeText(ShopActivity.this, "No upgrade selected (*-*)",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //finish shopping
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        try {
            mp.play(this, R.raw.shop);
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
        setMusicButtons();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    /**
     * Enable sound button
     *
     * @author genia
     */
    public void enableSoundShop(View view) {
        try {
            mp.enable();
            mp.play(this, R.raw.shop);
            setMusicButtons();
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
    }
    /**
     * Disable sound button
     *
     * @author genia
     */
    public void disableSoundShop(View view) {
        mp.disable();
        setMusicButtons();
    }

    /**
     * Show music on/off buttons
     *
     * @author genia
     */
    public void setMusicButtons() {
        if (mp.isEnabled()) {
            enableMusic.setVisibility(View.GONE);
            disableMusic.setVisibility(View.VISIBLE);
        } else {
            disableMusic.setVisibility(View.GONE);
            enableMusic.setVisibility(View.VISIBLE);
        }
    }
}
