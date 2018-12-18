
/**
 * Scores class defines the helper methods readFile and writeFile, getScoreArray, getSize, and insertion sorting the scores array that
 * are called in the Game Panel
 *
 * @author (Valeria Yang)
 * @version (12.18.18)
 */
import java.util.*;
import java.util.Scanner.*;
import java.io.*;
public class Scores
{
    private PrintWriter writer;
    private int scores[];
    private int index = 0;

    /**
     * Constructor for objects of class Scores, intializing the scores array
     */
    public Scores()
    {
        // initialise instance variables
        //test = new Integer[]{1,5,26,43,12,3};//1,3,5,12,26,43
        scores = new int[10];
    }
    
    /**
     * Method that expands the scores array when it has reached full capacity
     */
    public void expandCapacity(){
        int temp[] = new int[scores.length * 2];
        for (int i = 0; i < scores.length;i++){
            temp[i] = scores[i];
        }
        scores = temp;
    }

    /**
     * Writes the content contained in the scores array into a file. Catches potential files errors.
     * SLIGHTLY BUGGY: writes the score 3 times to file instead of only once. Same problem in 
     * GamePanel where new scores are added after the game.  
     *
     * @param String fileName that contains the name of the file that it will be written to 
     * @param int[] nums an array of scores that will be written into the file
     * 
     */
    public void writeFile(String fileName, int[] nums)
    {
        try{
            writer = new PrintWriter(new File(fileName));
            for (int i = nums.length-1; i>= 0; i--){ //writes the highest score first  and ends the file at the lowest score value in the array
                //if (scores[i] != 0) {
                writer.println(nums[i]);
                //}
            }
        }
        catch (IOException e){
            System.out.println("error");
        }
        writer.close();
    }
    
    /**
     * Reads the file that was written and addes them to the scores array. Catches potential files errors. 
     * @param String filename which is the name of the file that contains score history.
     */
    public void readFile (String fName){
        try{
            Scanner fileScan = new Scanner (new File(fName));
            //System.out.println("hello");
            while (fileScan.hasNext()){
                if (index == scores.length-1) expandCapacity(); //checks to make sure the array hasn't reached full capacity, and if it
                //has expand the size of the scores array
                int line = Integer.parseInt(fileScan.next());
                //System.out.println(line);

                scores[index] = line;
                //System.out.println(index);
                index++;
                //System.out.println(index);
            }
            fileScan.close();
        }
        catch (IOException e){
            System.out.println("error");
        }
        
    }
    
    /**
     * Helper method created (Rachel debugging) to return the scores array for the ScoresTab class to use.
     * @return scores an array of integers
     */
    public int[] getScoreArray() {
        //Integer[] sortedScores = Integer
        return scores;

    }

    /**
     * Helper method created (Peggy debugging) to return the size of the scores array
     * @return integer the scores array length
     */
    public int getSize(){
        return scores.length;
    }
    
    /**
     * Insertion sorting algorithm that sorts the array from least to greatest
     * @param int[] array that is sorted
     * @return the sorted array
     */
    public static int[] insertionSort (int[] intArray){
        for (int i = 1; i < intArray.length; i++){
            int key = intArray[i];
            int position = i;
            while (position > 0 && intArray[position - 1] -(key) > 0){
                intArray[position] = intArray[position - 1];
                position--;
            }
            intArray[position] = key;
        }
        return intArray;
    }
}
