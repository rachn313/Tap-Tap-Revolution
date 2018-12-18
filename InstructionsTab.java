
/**
 * Displaying the instructions in the Instructions tab
 *
 * @author (vyang)
 * @version (12/18/18)
 */

import java.awt.*;
import javax.swing.*;
public class InstructionsTab extends JPanel
{
    private JPanel top, mid, bot, bel;
    private JLabel l1, l2, l3, l4;
    private ImageIcon img2 = null;
    /**
     * Constructor for objects of class InstructionsTab
     */
    public InstructionsTab()
    {
        setBackground (Color.white);
        setLayout(new BorderLayout());

        add(makeNorthPanel(), BorderLayout.NORTH);
        add(makeCenterPanel(), BorderLayout.CENTER);
        add(makeSouthPanel(), BorderLayout.SOUTH);
    }
    /**
     * Panel holding the first instruction
     * @return northPanel
     */
    private JPanel makeNorthPanel() {
        JPanel northPanel = new JPanel();

        JLabel l1 = new JLabel("1.Match the falling circles on the screen to " + 
                "the right key. (NOTE: Use the following keys: S D F J K L)");
        northPanel.add(l1);
        return northPanel;
    }
    /**
     * Panel holding the second instruction
     * @return centerPanel
     */
    private JPanel makeCenterPanel() {
        JPanel centerPanel = new JPanel();

        JLabel l2 = new JLabel("2. Try to get as many matches as you can.");
        centerPanel.add(l2);
        return centerPanel;
    }
    /**
     * Panel holding a space
     * @return southPanel
     */
    private JPanel makeSouthPanel() {
        JPanel southPanel = new JPanel();
        JLabel l3 = new JLabel("");

        southPanel.add(l3);
        return southPanel;
    }
}
