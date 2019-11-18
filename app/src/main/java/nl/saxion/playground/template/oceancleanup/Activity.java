package nl.saxion.playground.template.oceancleanup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.lib.GameView;
import nl.saxion.playground.template.oceancleanup.audio_player.Music;
import nl.saxion.playground.template.oceancleanup.audio_player.Sound;
import nl.saxion.playground.template.oceancleanup.model.EntityCreator;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;
import nl.saxion.playground.template.oceancleanup.model.Submarine;
import nl.saxion.playground.template.oceancleanup.сonstraint.ConstraintDeathMenu;
import nl.saxion.playground.template.oceancleanup.сonstraint.ConstraintLvEndMenu;
import nl.saxion.playground.template.oceancleanup.сonstraint.ConstraintStatusBar;

/**
 * @author genia, artem
 */
public class Activity extends AppCompatActivity {

    private static Game game;
    private static GameView gameView;
    private static ConstraintStatusBar constraintStatusBar;
    private static ConstraintDeathMenu constraintDeathMenu;
    private static ConstraintLvEndMenu constraintLvEndMenu;
    private static TextView victoryMessage;
    private static TextView lives;
    private static Button cont;
    private Music mp = Music.getInstance();

    public static Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.containsKey("game")) {
            game = (Game) savedInstanceState.getSerializable("game");
        } else {
            game = new Game();
        }
        context = this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setContentView(R.layout.activity_oceancleanup);
        gameView = findViewById(R.id.oceanCleanUp);
        constraintStatusBar = findViewById(R.id.constraintStatusBar);
        constraintDeathMenu = findViewById(R.id.deathMenu);
        constraintLvEndMenu = findViewById(R.id.constraintLvEndMenu);
        lives = findViewById(R.id.textViewLives);
        victoryMessage = findViewById(R.id.lvFinishMessege);
        victoryMessage.setText("You finished " + GameProgress.getInstance().getLv() + "Lv!");
        cont = findViewById(R.id.deathButtonContinue);
        constraintDeathMenu.setVisibility(gameView.GONE);
        constraintLvEndMenu.setVisibility(gameView.GONE);
        constraintStatusBar.setVisibility(View.VISIBLE);
        gameView.setGame(game);
        try {
            mp.play(this, R.raw.game);
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.setGame(null);
        mp.stop();
    }


    /**
     * Check the condotion of the submarine
     *
     * @author genia
     */
    public static void update() {
        Submarine submarine = game.getEntity(Submarine.class);
        float time = ((float) EntityCreator.getTickCount()) / game.ticksPerSecond() * 100f;
        int timeInt = Math.round(time);
        constraintStatusBar.changeParameters(submarine.getHealth(), submarine.getFuel(),
                GameProgress.getInstance().getMoney(), timeInt);
        if (constraintStatusBar.getTime() >= constraintStatusBar.getMaxTime()) {
            endLvl(submarine);
        } else if (submarine.getFuel() <= 0 || submarine.getHealth() <= 0) {
            lost(timeInt);
        } else {
            constraintDeathMenu.setVisibility(gameView.GONE);
            constraintStatusBar.setVisibility(View.VISIBLE);
            EntityCreator.tickFreeze = false;
        }
        constraintStatusBar.setMusicButtons();
    }

    /**
     * Proceed to lvl end menu
     *
     * @param submarine
     * @author genia
     */
    private static void endLvl(Submarine submarine) {
        EntityCreator.resetTicks();
        EntityCreator.tickFreeze = true;
        game.removeEntity(submarine);
        Sound.getInstance().playSound(7);
        constraintLvEndMenu.setVisibility(View.VISIBLE);
        constraintStatusBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Proceed to the death screen
     *
     * @param timeInt time to save for continue button
     * @author genia
     */
    private static void lost(int timeInt) {
        EntityCreator.tickFreeze = true;
        game.deleteEntities();
        GameProgress.getInstance().setTime(timeInt);
        int livesInt = GameProgress.getInstance().getLives();
        lives.setText("" + livesInt);
        if (livesInt <= 0) {
            cont.setEnabled(false);
        } else {
            cont.setEnabled(true);
        }
        Sound.getInstance().playSound(2);
        try {
            Music.getInstance().play(context, R.raw.dead);
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
        constraintStatusBar.setVisibility(View.INVISIBLE);
        constraintDeathMenu.setVisibility(gameView.VISIBLE);
    }

    /**
     * Proceed to the shop screen button
     *
     * @author genia
     */
    public void goToTheShop(View view) {
        EntityCreator.resetTicks();
        game.deleteEntities();
        GameProgress.getInstance().increaseDifficultyLv();
        startActivity(new Intent(Activity.this, ShopActivity.class));
    }

    /**
     * Continue gme button
     *
     * @author genia
     */
    public void continueGame(View view) {
        EntityCreator.tickFreeze = false;
        GameProgress.getInstance().takeLife();
        try {
            mp.play(this, R.raw.game);
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
        game.start();
    }

    /**
     * Finish game button
     *
     * @author genia
     */
    public void finishGame(View view) {
        EntityCreator.tickFreeze = false;
        GameProgress.getInstance().wipeProgress();
        constraintStatusBar.setParameters();
        EntityCreator.resetTicks();
        finish();
    }

    public static void setUp() {
        constraintStatusBar.setParameters();
    }

    public static Game getGame() {
        return game;
    }

    /**
     * Sound on
     *
     * @author genia
     */
    public void enableSound(View view) {
        try {
            mp.enable();
            mp.play(this, R.raw.game);
            constraintStatusBar.setMusicButtons();
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
    }

    /**
     * Sound off
     *
     * @author genia
     */
    public void disableSound(View view) {
        mp.disable();
        constraintStatusBar.setMusicButtons();
    }
}
