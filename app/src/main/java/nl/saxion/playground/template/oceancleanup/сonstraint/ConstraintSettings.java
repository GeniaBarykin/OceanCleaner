package nl.saxion.playground.template.oceancleanup.сonstraint;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.SeekBar;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.oceancleanup.audio_player.Music;
import nl.saxion.playground.template.oceancleanup.audio_player.Sound;

/**
 * Show settings menu
 *
 * @author genia
 */
public class ConstraintSettings extends ConstraintLayout {
    private SeekBar soundBar, musicBar;

    public ConstraintSettings(Context context) {
        super(context);
        init();
    }

    public ConstraintSettings(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ConstraintSettings(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.contraint_settings, this);

        soundBar = findViewById(R.id.seekBarSound);
        soundBar.setMax(100);
        soundBar.setProgress(Sound.getVolumeInt());
        soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                Sound.setVolume(Integer.parseInt(soundBar.getProgress() + ""));
            }
        });

        musicBar = findViewById(R.id.seekBarMusic);
        musicBar.setMax(100);
        System.out.println("!!!" + Music.getVolumeInt());
        musicBar.setProgress(Music.getVolumeInt());
        musicBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                int progr = musicBar.getProgress();
                Music.setVolume(progr);

            }
        });
    }
}
