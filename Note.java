import java.applet.AudioClip;
import java.net.URL;
import javax.swing.*;
//import java.awt.*;
/**
 * Creates a note object that contains the audioclip file, x and y coordinates, and the beat (speed)
 *
 * @author (Rachel Navarrette)
 * @version (12.18.18)
 */
public class Note
{
    // instance variables - replace the example below with your own
    private AudioClip note;
    private URL url;
    private AudioClip[] music;
    private int x, y, beat;
    private String fileName;

    /**
     * Constructor for objects of class Note that is used in the game panel
     * @param String filename the name of the audioclip file
     * @param int x, int y that are the x and y coordinates of the note
     * @param int beat, that contains the speed that the image icon is moving
     */
    public Note(String fileName, int X, int Y, int beat)
    {
        try {
            url = new URL("file", "localhost", fileName);

        } catch (Exception exception) {
            System.out.println("Error");
        }
        x = X;
        y = Y;
        this.beat = beat;
        //image = img;
        //music = new AudioClip[1];
        note = JApplet.newAudioClip (url); //imports JAppelet to create the audio clip and make it playable
        this.fileName = fileName;

    }

    /**
     * Getter method that allows access to the note object
     */
    public AudioClip getNote() {
        return note;
    }

    /**
     * Returns a string of the audio file name (used for linked list implementation)
     * @return fileName which is the string of the name 
     */
    public String getFileName(){
        return this.fileName;
    }

    /**
     * Getter method that returns the x coordinate of the note object
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter method that returns the y coordinate of the note object
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter method that returns the beat (speed) of the note object
     * @return int speed
     */
    public int getBeat() {
        return this.beat;
    }
}
