
/**
 * ScoresTab opens the score history text file and sorts the scores, and adds the scores in the panel. (Top 5 scores)
 *
 * @author (Valeria Yang with help from Rachel Navarrette and Peggy Wang)
 * @version (12.18.18)
 */

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
public class ScoresTab extends JPanel
{
    private JLabel space, l0, l1, l2, l3, l4, l5;
    private int[] getScores;
    private ImageIcon image = null;
    private ImageIcon img = null;
    /**
     * Constructor for objects of class InstructionsTab
     */
    public ScoresTab()
    {
        img = new ImageIcon("rainbow.png"); //designed by Rachel 

        JPanel listPane = new JPanel();
        BoxLayout boxLayout = new BoxLayout(listPane, BoxLayout.Y_AXIS);
        listPane.setLayout(boxLayout);
        Scores score_class = new Scores();

        //Rachel and Peggy added this following code to call helper methods defined in scoresTab
        //Also assisted in debugging this feature
        score_class.readFile("scoreHistory.txt"); 
        getScores = score_class.insertionSort(score_class.getScoreArray());

        l1 = new JLabel ("1. " + getScores[getScores.length-1]);
        l0 = new JLabel("Highest Score: " + getScores[getScores.length-1]);
        space = new JLabel(" ");
        l2 = new JLabel ("2. " + getScores[getScores.length-2]);
        l3 = new JLabel("3. " + getScores[getScores.length-3]);
        l4 = new JLabel("4. " + getScores[getScores.length-4]);
        l5 = new JLabel("5. " + getScores[getScores.length-5]);

        listPane.add(l0);
        listPane.add(space);
        listPane.add(l1);
        listPane.add(l2);
        listPane.add(l3);
        listPane.add(l4);
        listPane.add(l5);

        add(listPane);
    }

    /**
     * Displays colorful background for scores page
     * @param Graphics page
     */
    public void paintComponent(Graphics page){
        super.paintComponent(page);
        img.paintIcon(this, page, 0, 0);

    }
}
