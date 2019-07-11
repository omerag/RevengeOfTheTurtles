package Model;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public enum SoundContainer {
    THEME("/res/theme_song.wav"),
    INJURED("/res/injured_sound.wav"),
    SQUISH("/res/squish_sound.wav"),
    PANDA1("/res/panda_sound_1.wav"),
    PANDA2("/res/panda_sound_2.wav"),
    PANDA3("/res/panda_sound_3.wav"),
    PANDA4("/res/panda_sound_4.wav"),
    PANDA5("/res/panda_sound_5.wav"),
    PANDA6("/res/panda_sound_6.wav"),
    PANDA7("/res/panda_sound_7.wav"),
    PANDA8("/res/panda_sound_8.wav"),
    PANDA9("/res/panda_sound_9.wav");

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    private Clip clip;

    SoundContainer(String soundFileName) {
        try {

            URL url = SoundContainer.class.getResource(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public static void init() {
        values();
    }
}