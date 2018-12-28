/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import com.sun.javafx.application.PlatformImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioInputStream;
import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioSystem.getClip;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author P01004090
 */
public class Eas7Sounds {

    String PATH = "src\\game\\sounds\\";
    String[] gameSounds = {"hit1.wav", "destroyd.wav", "hit2.wav", "explo1.wav", "a.WAV"};
    MediaPlayer mediaPlayer;
    ArrayList<Media> hitList = new ArrayList<>();
    AudioStream theStream = null;

    public Eas7Sounds() {
        PlatformImpl.startup(() -> {});
//        new MySounds();
//        Media hitList = new Media(new File(PATH + gameSounds[0]).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(hitList);
//        mediaPlayer.play();
        for (int i = 0; i < gameSounds.length; i++) {
            hitList.add(new Media(new File(PATH + gameSounds[i]).toURI().toString()));
        }

    }

    public void update() {

    }

    public void funktioniert() {
        try {
            theStream = new AudioStream(new FileInputStream(PATH + "beat.wav"));
        } catch (IOException ex) {
            Logger.getLogger(Eas7Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioPlayer.player.start(theStream);
    }

    public void playMusik() {
        File audioFile = new File(PATH + "beat.wav");
        // Läuft bereits in einem eigenen Thread
        playLoop(audioFile);
    }

    void playLoop(File audioFile){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = getAudioInputStream(audioFile);
            Clip clip = getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Eas7Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                audioInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Eas7Sounds.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void playSound(int i) {
        mediaPlayer = new MediaPlayer(hitList.get(i));
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
}
