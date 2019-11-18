package nl.saxion.playground.template.oceancleanup.audio_player;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import nl.saxion.playground.template.R;
import nl.saxion.playground.template.oceancleanup.Activity;

@SuppressWarnings("deprecation")
/**
 * Player for in game sounds
 * @author genia
 */
public class Sound {
    private static SoundPool soundPool;
    private static Sound s;
    private static int sharkHit, garbage, powerFuel, powerHp, death, sold, win;
    private static final int MAX_VOLUME = 100;
    private static float volume = 0.6f;

    private Sound() {
    }

    /**
     * Get instance
     *
     * @return SoundPool instance
     */
    public static Sound getInstance() {
        if (s == null) {
            s = new Sound();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build();

                soundPool = new SoundPool.Builder()
                        .setMaxStreams(8)
                        .setAudioAttributes(audioAttributes)
                        .build();
            } else {
                soundPool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
            }
            sharkHit = soundPool.load(Activity.getContext(), R.raw.hit, 1);
            death = soundPool.load(Activity.getContext(), R.raw.rip, 1);
            garbage = soundPool.load(Activity.getContext(), R.raw.garbage, 1);
            powerHp = soundPool.load(Activity.getContext(), R.raw.hp, 1);
            powerFuel = soundPool.load(Activity.getContext(), R.raw.fuel, 1);
            sold = soundPool.load(Activity.getContext(), R.raw.sold, 1);
            win = soundPool.load(Activity.getContext(), R.raw.win, 1);

        }
        return s;
    }

    /**
     * Get volume without the instance
     *
     * @return int value of volume
     */
    public static int getVolumeInt() {

        return (int) (volume * 100);
    }

    public float getVolume() {
        return volume;
    }

    /**
     * Play sound
     *
     * @param id of preloaded sounds
     */
    public void playSound(int id) {
        if (Music.getInstance().isEnabled()) {
            int number = 0;
            switch (id) {
                case 1:
                    number = sharkHit;
                    break;
                case 2:
                    number = death;
                    break;
                case 3:
                    number = garbage;
                    break;
                case 4:
                    number = powerHp;
                    break;
                case 5:
                    number = powerFuel;
                    break;
                case 6:
                    number = sold;
                    break;
                case 7:
                    number = win;
                    break;
            }
            soundPool.play(number, volume, volume, 0, 0, 1);
        }
    }

    /**
     * Set the volume
     *
     * @param vol int
     */
    public static void setVolume(int vol) {
        volume = (float) vol / 100f;
    }
}
