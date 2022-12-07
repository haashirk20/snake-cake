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
            //lowerc.setMicrosecondPosition(500);

        }
        catch (Exception e){ // will catch the exceptioj

        }

    }
    public void startMusic() throws InterruptedException {

        lowerc.start();//starts the audio file
        lowerc.setMicrosecondPosition(10); // sets the sound of the clip back to 10 micros
        //lowerc.loop(Clip.LOOP_CONTINUOUSLY);


    }
    public void stopMusic(){
        lowerc.stop();

    }
}