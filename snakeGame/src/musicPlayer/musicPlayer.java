package musicPlayer;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class musicPlayer {

    Clip lowerc;


    public musicPlayer(String filename){
        try {
            File fileMusic = new File(filename); // gets file name of song
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(fileMusic); //sets the song to file
            lowerc = AudioSystem.getClip(); // gets teh audio clip
            lowerc.open(audioInput); //opens the audio file.

        }
        catch (Exception e){

        }

    }
    public void startMusic(){
        lowerc.start(); // starts the music.
        lowerc.loop(Clip.LOOP_CONTINUOUSLY);


    }
    public void stopMusic(){
        lowerc.stop();

    }
}