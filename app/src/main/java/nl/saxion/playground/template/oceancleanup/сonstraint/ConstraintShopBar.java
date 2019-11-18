package nl.saxion.playground.template.oceancleanup.сonstraint;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import nl.saxion.playground.template.R;

public class ConstraintShopBar extends ConstraintLayout {

    public ConstraintShopBar(Context context) {
        super(context);
        init();
    }

    public ConstraintShopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConstraintShopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.constraint_shop_bar, this);
    }

    private int money, cost;

    public void setParameters(int money) {
        this.money = money;
        TextView balance = findViewById(R.id.tvShopMoney);

        balance.setText("" + money);
    }

    public void updateBar(int cost) {
        this.cost = cost;
        Button btBuy = findViewById(R.id.btBuy);
        if (cost > money) {
            btBuy.setBackgroundColor(Color.GRAY);
        } else {
            btBuy.setBackgroundColor(Color.GREEN);
        }
    }

    public void updateMoney() {
        money -= cost;
        TextView balance = findViewById(R.id.tvShopMoney);
        balance.setText("" + money);
    }
}
