package Model;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class SoundContainer {

    private AudioInputStream injured_sound;
    private AudioInputStream squish_sound;
    private AudioInputStream theme_song;
    private AudioInputStream[] panda_voices = new AudioInputStream[8];
    private static SoundContainer sound = new SoundContainer();

    private static SoundContainer ourInstance = new SoundContainer();

    public static SoundContainer getInstance() {
        return ourInstance;
    }

    private SoundContainer() {

        try {
            injured_sound = AudioSystem.getAudioInputStream(SoundContainer.class.getResource("/res/injured_sound.wav"));
            squish_sound = AudioSystem.getAudioInputStream(SoundContainer.class.getResource("/res/squish_sound.wav"));
            theme_song = AudioSystem.getAudioInputStream(SoundContainer.class.getResource("/res/theme_song.wav"));
            for (int i = 0; i < 9; i++)
            {
                panda_voices[i] = AudioSystem.getAudioInputStream(SoundContainer.class.getResource("/res/panda_sound_" + i +".wav"));
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
