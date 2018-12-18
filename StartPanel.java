
/**
 * What the user sees when the program is first opened. Contains buttons to
 *  view instruction or play the game. 
 *
 * @author (Valeria Yang)
 * @version (12.18.18)
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.event.ActionListener.*;
public class StartPanel extends JPanel
{
    // instance variables
    private JPanel picPanel,upPanel, downPanel;
    private JButton instrButton, startButton;
    private InstructionsTab instr;
    private ImageIcon image = null;
    private JLabel label;

    /**
     * Constructor for objects of class StartPanel.
     */
    public StartPanel()
    {
        setLayout(new BorderLayout());
        StartPicture picture = new StartPicture();
        add(makeCenterPanel(), BorderLayout.CENTER); //adds the panels in a border layout fashion
        add(makeDownPanel(), BorderLayout.SOUTH);
    }
    /**
     * Makes the center panel that contains the logo image icon
     * @return StartPicture center that contains the image logo
     */
    private JPanel makeCenterPanel(){
        StartPicture center = new StartPicture();
        return center;
    }
    /**
     * Makes the bottom panel that contains the start and instruction buttons.
     * @return JPanel on the bottom of the start page
     */
    private JPanel makeDownPanel(){
        JPanel downPanel = new JPanel();

        startButton = new JButton("START");
        downPanel.add(startButton);
        startButton.addActionListener(new ButtonListener());


        instrButton = new JButton("HOW TO PLAY");
        downPanel.add(instrButton);
        instrButton.addActionListener(new ButtonListener());
        
        return downPanel;
    }
    public class ButtonListener implements ActionListener
    {
        /**
         * Opens different frames depending on which button was clicked.
         * If start button was clicked, start game. 
         * If instruction button was clicked, display instructions. 
         * @param ActionEvent mouse click event
         */
        public void actionPerformed (ActionEvent event)
        {
            if(event.getSource() == instrButton){ 
                //make new instructions panel
                JFrame frame = new JFrame ("Instructions");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

                InstructionsTab instructions = new InstructionsTab();
                instructions.setPreferredSize(new Dimension(1000, 1000));

                frame.getContentPane().add(instructions);
                frame.pack();
                frame.setVisible(true);
            } 
            
            if(event.getSource() == startButton){ 
                //make new instructions panel
                JFrame frame = new JFrame ("Game");
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

                GamePanel game = new GamePanel();
                game.setPreferredSize(new Dimension(900, 950));

                frame.getContentPane().add(game);
                frame.pack();
                frame.setVisible(true);
            } 
        }
    }
}
