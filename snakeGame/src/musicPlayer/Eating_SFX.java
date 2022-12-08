package musicPlayer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import java.io.File;
import java.net.URL;

public class Eating_SFX implements musicInterface {

    Clip lowerc;

    /*
    Initalizes the Eating_SFX class object
    First grabs fie name, converts filename to audioinputstream and opens the file to be played from
     */
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
    /*
    starts the sound effect, and sets the microsecond position to 10, to ensure sound gets
    played multiple times.
     */
    public void startMusic() {

        lowerc.start();//starts the audio file
        lowerc.setMicrosecondPosition(10); // sets the sound of the clip back to 10 micros
        //lowerc.loop(Clip.LOOP_CONTINUOUSLY);

    }
    /*
    Plays the sound, used for playing sound effects.
     */
    public void playSound() throws InterruptedException{
        lowerc.start();
    }
    /*
    stops the clip audio
     */
    public void stopMusic(){
        lowerc.stop();

    }
}