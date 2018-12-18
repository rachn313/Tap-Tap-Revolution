
/**
 * Contains a method that returns a queue of all the notes in the song.
 * Helper class to simplify the code in our GamePanel. Reads in all the 
 * notes of the song from a txt file.
 *
 * @author (Peggy Wang)
 * @version (12.18.18)
 */
import javafoundations.*;
import javafoundations.exceptions.*;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Song
{
    private LinkedQueue<Note> notes;
    private int[] random_coord = {40, 200, 350, 500, 625, 800};
    private String notesFile = "notes.txt";
    private LinkedList<Note> chords;
    
    /**
     * Initialized the notes queue that will contain Note objects, chords linked list that will contain specified notes that go with 
     * specific notes in the queue to play chords. Imports Random class to randomly choose x coordinates from the 
     * random_coord array so each death star image icon falls in the regions of one of the 6 keys. It also reads from the file that contains
     * all the audio clip paths, so it can make a new Note Object and add it to the queue.
     * 
     * 
     */
    public Song(){
        notes = new LinkedQueue<Note>();
        chords = new LinkedList<Note>();
        Random rand = new Random();
        
        //fill in all the notes of the song      
        readFromFile();
    }
    
    /**
     * A class that reads from the Song File that contains all the audio clip files and makes them into Note Objects that are added 
     * to the queue. Rachel assisted with Linked List implementation, and some aspects of Random implementation. 
     */
    private void readFromFile(){
        Random rand = new Random();
        String audio = "";
        //y is always 0, x is randomyl generated
        int beat = 0;
        try{
            Scanner scan = new Scanner(new File(notesFile));
            //read note information from file 
            while(scan.hasNext()){
                //get audio file name
                audio = scan.next();
                try{ //try to read beat value (aka speed)
                    beat = Integer.parseInt(scan.next());
                }
                catch(NumberFormatException e){
                    scan.next();
                    continue;
                }
                //add note to queue if information read successfully
                notes.enqueue(new Note(audio, 
                random_coord[rand.nextInt(random_coord.length)], 0, beat));
                
                //also add chords to the chords linked list at certain points of the song
                if(notes.size() == 18 || notes.size() == 25){
                    chords.add(new Note("AudioFiles/Dnote.wav", 
                    random_coord[rand.nextInt(random_coord.length)], 0, beat));
                }
                else if(notes.size() == 34){
                    chords.add(new Note("AudioFiles/Cnote.wav", 
                    random_coord[rand.nextInt(random_coord.length)], 0, beat));
                }
                
                else if(notes.size() == 48){
                    chords.add(new Note("AudioFiles/Gnote.wav", 
                    random_coord[rand.nextInt(random_coord.length)], 0, beat));
                }
                else{ //add placeholder "junk chord"
                    chords.add(new Note("AudioFiles/sdfjklChord.wav", 
                    random_coord[rand.nextInt(random_coord.length)], 0, beat)); 
                }
            }
            
            scan.close();
        }
        catch(FileNotFoundException e){
            System.out.println(notesFile + " not found");
        }
        
    }
    
    /**
     * Allows us to get the queue from this class to use in the Game Panel class
     * @return notes the queue that contains all the note objects with audio clips
     */
    public LinkedQueue<Note> getSongQueue(){
            return notes;
    }
    
    /**
     * Allows us to have access to the Linked list that is used int the Game Panel class
     * @return chords a linked list that contains junk audio files that will not be played and the specific audio clips that will be played
     * at the right time.
     */
    public LinkedList<Note> getChordsList(){
        return chords;
    }
}
