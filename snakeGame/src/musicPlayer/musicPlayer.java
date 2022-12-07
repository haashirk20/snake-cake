package musicPlayer;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class musicPlayer {

    Clip lowerc;


    public musicPlayer(String filename){
        try {
            File fileMusic = new File(filename);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(fileMusic);
            lowerc = AudioSystem.getClip();
            lowerc.open(audioInput);

        }
        catch (Exception e){

        }

    }
    public void startMusic(){
        lowerc.start();


    }
    public void stopMusic(){
        lowerc.stop();

    }
}