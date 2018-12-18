
/**
 * Tap Tap Layout allows for the Start Menu, Instructions, Scores, and Game Panel to co-exist together and allow for the user to play the 
 * game. This is the class that needs to run for the GUI to pop up. 
 *
 * @author (Valeria Yang)
 * @version (12.18.18)
 */ 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TapTapLayout
{
    
    private JButton instrButton;
    /**
     * Initilizing jframes to make the Tap Tap Revolution
     */
    public TapTapLayout()
   {
    
      JFrame frame = new JFrame ("TapTap");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
      JTabbedPane tp = new JTabbedPane();
      tp.addTab ("Start Menu", new StartPanel());
      
      tp.addTab ("Instructions", new InstructionsTab());
      tp.addTab ("Scores", new ScoresTab());
      frame.getContentPane().add(tp);
      frame.pack();
      frame.setVisible(true);
      frame.setResizable(false);
      
   }
   
   /**
    * Testing
    */
   public static void main(String [] args) {
       TapTapLayout test = new TapTapLayout();
       
    }
}

