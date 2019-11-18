package nl.saxion.playground.template;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import nl.saxion.playground.template.oceancleanup.Activity;
import nl.saxion.playground.template.oceancleanup.audio_player.Music;
import nl.saxion.playground.template.oceancleanup.progress.GameProgress;
import nl.saxion.playground.template.oceancleanup.сonstraint.ConstraintAuthors;
import nl.saxion.playground.template.oceancleanup.сonstraint.ConstraintSettings;

/**
 * @author genia, artem
 */
public class MainActivity extends AppCompatActivity {

    private Button btNewGame, btSettings, btDonate, btCreators;
    private ImageView enableMusic, disableMusic;
    private Music mp = Music.getInstance();
    private ConstraintSettings cSett;
    private boolean showSettings, showCreators;
    private ConstraintAuthors creators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_oceancleanup);

        btNewGame = findViewById(R.id.btNewGame);
        btSettings = findViewById(R.id.btSettings);
        btDonate = findViewById(R.id.btDonate);
        btCreators = findViewById(R.id.buttonAuthors);
        cSett = findViewById(R.id.constraintSettings);
        creators = findViewById(R.id.constrAuthors);

        btNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameProgress.getInstance().wipeProgress();
                startActivity(new Intent(MainActivity.this, Activity.class));
            }
        });

        btDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://theoceancleanup.com/donate/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        disableAll();
        btSettings.setBackgroundColor(Color.rgb(255, 64, 129));
        btCreators.setBackgroundColor(Color.rgb(255, 64, 129));
        try {
            mp.enable();
            mp.play(this, R.raw.main);
        } catch (Exception e) {
            System.out.println("Music error " + e.getMessage());
        }
        enableMusic = findViewById(R.id.imageViewEnable);
        disableMusic = findViewById(R.id.imageViewDisable);
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
    public void enableSoundMain(View view) {
        try {
            mp.enable();
            mp.play(this, R.raw.main);
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
    public void disableSoundMain(View view) {
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

    /**
     * Open settings button
     *
     * @author genia
     */
    public void openSettings(View view) {
        if (showSettings) {
            showSettings = false;
            cSett.setVisibility(View.GONE);
            btSettings.setBackgroundColor(Color.rgb(255, 64, 129));
        } else {
            if (showCreators) {
                openCreators(view);
            }
            showSettings = true;
            cSett.setVisibility(View.VISIBLE);
            btSettings.setBackgroundColor(Color.CYAN);
        }
    }

    /**
     * Open creators button
     *
     * @author genia
     */
    public void openCreators(View view) {
        if (showCreators) {
            showCreators = false;
            creators.setVisibility(View.GONE);
            btCreators.setBackgroundColor(Color.rgb(255, 64, 129));
        } else {
            if (showSettings) {
                openSettings(view);
            }
            showCreators = true;
            creators.setVisibility(View.VISIBLE);
            btCreators.setBackgroundColor(Color.CYAN);
        }
    }

    /**
     * For changing activity
     *
     * @author genia
     */
    private void disableAll(){
            showCreators = false;
            creators.setVisibility(View.GONE);
            btCreators.setBackgroundColor(Color.rgb(255, 64, 129));
            showSettings = false;
            cSett.setVisibility(View.GONE);
            btSettings.setBackgroundColor(Color.rgb(255, 64, 129));
    }
}
