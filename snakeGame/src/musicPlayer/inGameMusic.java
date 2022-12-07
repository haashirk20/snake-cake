package musicPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class inGameMusic {
    Clip lowerc;

    /*
    Initalizes the Eating_SFX class object
    First grabs fie name, converts filename to audioinputstream and opens the file to be played from
     */
    public inGameMusic(String filename){
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
    starts the music, loops continously for background music
     */
    public void startMusic(){
        lowerc.start(); // starts the music.
        lowerc.loop(Clip.LOOP_CONTINUOUSLY);


    }
    /*
    used for stopping the song, used when game is over
     */
    public void stopMusic(){
        lowerc.stop();

    }
}

