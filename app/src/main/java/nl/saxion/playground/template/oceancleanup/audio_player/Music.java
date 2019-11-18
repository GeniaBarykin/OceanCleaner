package nl.saxion.playground.template.oceancleanup.audio_player;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Background music player
 *
 * @author genia
 */
public class Music {
    private static MediaPlayer mp;
    private static Music music;
    private boolean playing;

    private boolean enabled;

    private static final int MAX_VOLUME = 100;
    private static float volume = 0.6f;

    private Music() {
    }

    /**
     * Get volume without the instance
     *
     * @return int value of volume
     */
    public static int getVolumeInt() {

        return (int) (volume * 100);
    }

    /**
     * Get instance
     *
     * @return MediaPlayer instance
     */
    public static Music getInstance() {
        if (music == null) {
            music = new Music();
        }
        return music;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Play music
     *
     * @param context of activity to play in
     * @param id      Id of the track to play
     * @return int value of volume
     */
    public void play(Context context, int id) throws Exception {
        if (enabled) {
            if (playing) {
                mp.stop();
            }
            if (mp != null) {
                mp = null;
            }
            mp = MediaPlayer.create(context, id);
            mp.setLooping(true);
            mp.setVolume(volume, volume);
            mp.start();
            playing = true;
        }
    }

    /**
     * Off the music player
     */
    public void disable() {
        enabled = false;
        playing = false;
        mp.stop();
    }

    /**
     * On the music player
     */
    public void enable() {
        enabled = true;
    }

    /**
     * Temporally stop the player
     */
    public void stop() {
        mp.stop();
        playing = false;
    }

    /**
     * Set the volume
     *
     * @param vol int
     */
    public static void setVolume(int vol) {

        volume = (float) vol / 100f;
        mp.setVolume(volume, volume);
    }
}
