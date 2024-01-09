package gamecontroller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundHandler {

    Clip clip;
    URL[] soundURL = new URL[30];

    public SoundHandler() {
        soundURL[0] = getClass().getResource("/sounds/GirlAdventure.wav");
        soundURL[1] = getClass().getResource("/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/sounds/dooropen.wav");
        soundURL[4] = getClass().getResource("/sounds/congratulation.wav");
        soundURL[5] = getClass().getResource("/sounds/gameover.wav");
        soundURL[6] = getClass().getResource("/sounds/scarybackground.wav");
        soundURL[7] = getClass().getResource("/sounds/sadgameover.wav");
        soundURL[8] = getClass().getResource("/sounds/collectkey.wav");
        soundURL[9] = getClass().getResource("/sounds/keyopendoor.wav");
    }

    // Open audio files
    public void setFile(int index) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL[index]);
            clip = AudioSystem.getClip();
            clip.open(audioInput);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

}