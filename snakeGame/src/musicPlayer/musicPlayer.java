package musicPlayer;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.desktop.OpenFilesHandler;
import java.io.File;
import java.util.Objects;

public class musicPlayer {

    Clip lowerc;

    /*
    Initalizes the musicPlayer class object
    First grabs fie name, converts filename to audioinputstream and opens the file to be played from
     */
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

    /*
    starts the audio clip, and loops it
     */
    public void startMusic(){
        lowerc.start(); // starts the music.
        lowerc.loop(Clip.LOOP_CONTINUOUSLY);


    }
    /*
    stops the audio clip.
     */
    public void stopMusic(){
        lowerc.stop();

    }
    public void setLowerC(){
        this.lowerc = lowerc;
    }
    public Clip getLowerc(){
        return this.lowerc;
    }
    public static void setVolume (Clip lowerc, int leve0l){
        Objects.requireNonNull(lowerc);
        FloatControl volume = (FloatControl) lowerc.getControl(FloatControl.Type.VOLUME);
        if (volume!= null){
            volume.setValue(leve0l/100);
        }

    }
}