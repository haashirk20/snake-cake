package musicPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.io.File;
import java.net.URL;

public class Eating_SFX {

    Clip lowerc;


    public Eating_SFX(String filename){
        try {
            File fileMusic = new File(filename); // gets file name of song
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(fileMusic); //sets the song to file
            lowerc = AudioSystem.getClip();
            lowerc.open(audioInput);

        }
        catch (Exception e){

        }

    }
    public void startMusic() throws InterruptedException {
        lowerc.start();
        //lowerc.loop(Clip.LOOP_CONTINUOUSLY);


    }
    public void stopMusic(){
        lowerc.stop();

    }
}