package nl.saxion.playground.template.oceancleanup.сonstraint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;
import nl.saxion.playground.template.oceancleanup.audio_player.Music;
import nl.saxion.playground.template.oceancleanup.ondraw.StatusBar;

/**
 * Show the status of the submarine
 *
 * @author genia
 */
public class ConstraintStatusBar extends ConstraintLayout {

    private int hp;
    private int fuel;
    private int money;
    private int time;
    private int maxTime;
    private Music mp = Music.getInstance();
    private ImageView enableMusic;
    private ImageView disableMusic;
    private TextView tvLives;

    public int getTime() {
        return time;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public ConstraintStatusBar(Context context) {
        super(context);
        init();
    }

    public ConstraintStatusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConstraintStatusBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.constraint_status_bar, this);
    }

    /**
     * Start param for the status bar
     */
    public void setParameters() {
        GameProgress gp = GameProgress.getInstance();
        this.hp = gp.getHeathMax();
        this.fuel = gp.getFuelMax();
        this.money = gp.getMoney();
        this.time = gp.getTime();
        this.maxTime = gp.getMaxTime();
        StatusBar hpBar = findViewById(R.id.hpBar);
        StatusBar fuelBar = findViewById(R.id.fuelBar);
        fuelBar.setFuel();
        TextView cash = findViewById(R.id.tvMoney);
        StatusBar timeBar = findViewById(R.id.timeBar);
        timeBar.setAsTime();
        timeBar.setMaxValue(maxTime);
        timeBar.setHp(time);
        hpBar.setMaxValue(hp);
        hpBar.setHp(hp);
        fuelBar.setHp(fuel);
        fuelBar.setMaxValue(fuel);
        cash.setText("" + money);
        enableMusic = findViewById(R.id.enableButt);
        disableMusic = findViewById(R.id.disableButt);
        tvLives = findViewById(R.id.textViewLives2);
        tvLives.setText("" + GameProgress.getInstance().getLives());
        setMusicButtons();
    }

    /**
     * update param for the status bar
     */
    public void changeParameters(int hp, int fuel, int money, int time) {
        if (money != this.money) {
            updateMoneyView(money);
        }
        if (hp != this.hp) {
            updateHpView(hp);
        }
        if (fuel != this.fuel) {
            updateFuelView(fuel);
        }
        if (time != this.time) {
            updateTimeView(time);
        }

    }

    /**
     * update fuel param for the status bar
     */
    private void updateFuelView(int fuel) {
        final StatusBar fuelBar = findViewById(R.id.fuelBar);
        final ValueAnimator animator2 = ValueAnimator.ofInt(this.fuel, fuel);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fuelBar.setHp((Integer) animator2.getAnimatedValue());
            }
        });
        this.fuel = fuel;
        animator2.setDuration(50);
        animator2.start();
    }

    /**
     * update hp param for the status bar
     */
    private void updateHpView(int hp) {
        final StatusBar hpBar = findViewById(R.id.hpBar);
        final ValueAnimator animator1 = ValueAnimator.ofInt(this.hp, hp);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                hpBar.setHp((Integer) animator1.getAnimatedValue());
            }
        });
        this.hp = hp;
        animator1.setDuration(500);
        animator1.start();
    }

    /**
     * update money param for the status bar
     */
    public void updateMoneyView(int money) {
        TextView cash = findViewById(R.id.tvMoney);
        cash.setText("" + money);
        this.money = money;
    }

    /**
     * update time param for the status bar
     */
    private void updateTimeView(int time) {
        final StatusBar timeBar = findViewById(R.id.timeBar);

        final ValueAnimator animator3 = ValueAnimator.ofInt(this.time, time);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                timeBar.setHp(((Integer) animator3.getAnimatedValue()));
            }
        });
        animator3.setDuration(500);
        animator3.start();
        this.time = time;
    }

    /**
     * music buttons visibility
     */
    public void setMusicButtons() {
        if (mp.isEnabled()) {

            disableMusic.setVisibility(GONE);
            enableMusic.setVisibility(VISIBLE);
        } else {
            enableMusic.setVisibility(GONE);
            disableMusic.setVisibility(VISIBLE);
        }
    }
}
