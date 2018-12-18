
/**
 * Adds the logo picture to the start menu.
 *
 * @author (Valeria Yang)
 * @version (12.18.18)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StartPicture extends JPanel
{
    // instance variables - replace the example below with your own
    private JPanel picPanel,upPanel, downPanel;
    private JButton instrButton, startButton;
    private InstructionsTab instr;
    private ImageIcon image = null;
    private JLabel label;
    /**
     * Constructor for objects of class StartPanel
     */
    public StartPicture()
    {
        image = new ImageIcon("taptap.png"); //Rachel designed this logo using the Canva website
        setPreferredSize(new Dimension(900, 950));
        setBackground(Color.black);
        setFocusable(true);
        
        // JButton instrButton = new JButton("HOW TO PLAY");
        // add(instrButton);
    }
    
    /**
     * paints the image when called 
     * @param Graphics page that paints the image on the page
     */
    public void paintComponent(Graphics page){
        super.paintComponent(page);
        image.paintIcon(this, page, 0, 0);
    
    }
}
