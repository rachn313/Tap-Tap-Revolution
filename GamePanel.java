
/**
 * GamePanel where you play our game. Contains the keyboard and falling notes.
 *
 * @author (Peggy Wang and Rachel Navarrette)
 * @version (12.18.18)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javafoundations.*;
import javafoundations.exceptions.*;
import java.applet.AudioClip;
import java.net.URL;
import java.util.LinkedList;
import java.util.Vector;

public class GamePanel extends JPanel
{
    private final int LENGTH = 900; 
    private final int WIDTH = 950;
    private ImageIcon s, d, f, j, k, l; //pressed colored keys
    private ImageIcon blank1, blank2, blank3, blank4, blank5, blank6; //blank piano keys
    private ImageIcon deathStarNote; //the circluar object that will be moving down the screen
    private LinkedQueue<Note> notes; //will contain the queue of notes from song class
    private LinkedList<Note> chords; //linked list of chords
    private Note currentNote, chordNote;
    private int score; //player score
    private JLabel displayScore, ranking;

    //animation variables
    private final int DELAY = 20, IMAGE_SIZE = 35;
    private Timer timer;
    private int x, y, moveY, chordX, chordY; 
    private int counter = 0;
    //counter keeps track of linked list index for chords
    /**
     * Constructor for Game Panel. Initializes all image files, gets all the notes from the Song class, 
     *  and initializes timer for animation. 
     */
    public GamePanel(){
        //keyboard listener
        addKeyListener(new PianoListener());

        //initialize piano key images when the user presses the key, it will change the white keys to the follow colors
        s = new ImageIcon("purple_key.jpg");
        d = new ImageIcon("blue_key.jpg");
        f = new ImageIcon("green_key.jpg");
        j = new ImageIcon("yellow_key.jpg");
        k = new ImageIcon("orange_key.jpg");
        l = new ImageIcon("red_key.jpg");

        //intialize the default key images 
        blank1 = new ImageIcon("white_key_S.jpg");
        blank2 = new ImageIcon("white_key_D.jpg");
        blank3 = new ImageIcon("white_key_F.jpg");
        blank4 = new ImageIcon("white_key_J.jpg");
        blank5 = new ImageIcon("white_key_K.jpg");
        blank6 = new ImageIcon("white_key_L.jpg");

        setPreferredSize(new Dimension(LENGTH, WIDTH));
        setBackground(Color.black);
        setFocusable(true);

        //add all notes to queue
        notes = new LinkedQueue<Note>();
        //all the notes are in the Song class 
        //used separate Song class for concise code here
        Song noteCollection = new Song();
        notes = noteCollection.getSongQueue();

        //initialize chords
        chords = new LinkedList<Note>();
        Song noteCollection2 = new Song();
        chords = noteCollection2.getChordsList();

        //animation timer
        timer = new Timer(DELAY, new NoteListener());
        deathStarNote = new ImageIcon("deathStar.png");
        timer.start();

        //get first note
        currentNote = notes.dequeue();
        x = currentNote.getX();
        y = currentNote.getY();
        moveY = currentNote.getBeat();

        //initialize chord information
        chordX = 0;
        chordY = 0;
        chordNote = chords.get(counter);
        counter++;

        //score information
        score = 0;
        displayScore = new JLabel("Score: " + score);
        add(displayScore);
        displayScore.setForeground(Color.yellow);
        displayScore.setFont(new Font("SansSerif", 1, 30));

        //skill ranking will be displayed after the game
        ranking = new JLabel("");
        add(ranking);
        ranking.setForeground(Color.yellow);
        ranking.setFont(new Font("SansSerif", 1, 30));

    }

    /**
     * Paint components allow the game panel to paint the image icons initialized in the previous method and paint it exactly at the
     * right coordinates.It also draws line to help the user better percieve which key should be pressed.
     * @param Graphics page that will be constantly repainted throughout game play 
     */
    public void paintComponent (Graphics page){
        super.paintComponent(page);
        ImageIcon galaxy = new ImageIcon("galaxy.jpg");
        galaxy.paintIcon(this, page, 0, 0);
        //falling note
        deathStarNote.paintIcon(this, page, x, y);

        //paint falling chord if it's valid
        if(isValidChord())
            deathStarNote.paintIcon(this, page, chordX, chordY);

        //add piano keys
        blank1.paintIcon(this, page, 0, 650);
        blank2.paintIcon(this, page, 150, 650);
        blank3.paintIcon(this, page, 300, 650);
        blank4.paintIcon(this, page, 450, 650);
        blank5.paintIcon(this, page, 600, 650);
        blank6.paintIcon(this, page, 750, 650);

        //separate keys with lines
        page.setColor(Color.pink);
        page.drawLine(150, 0, 150, 950);
        page.drawLine(300, 0, 300, 950);
        page.drawLine(450, 0, 450, 950);
        page.drawLine(600, 0, 600, 950);
        page.drawLine(750, 0, 750, 950);
        
        //horizontal line to help with timing
        page.drawLine(0, 570, 950, 570);

    }

    /**
     * Helper function that checks whether the chord is valid (meaning it does not equal the junk chored that is used as a place holder
     * @return boolean expression that returns true/false whether the audio clip is a junk audio. 
     */
    public boolean isValidChord(){
        if(counter != chords.size())
            return !chords.get(counter).getFileName().equals("AudioFiles/sdfjklChord.wav");

        return false;
    }

    public class PianoListener implements KeyListener{
        /**
         * Implements the "Rules" of the game
         * When a piano key is pressed, make the key change color
         * If the key is pressed at the right time, play the next note of the song
         *  and initialize the next note
         *  
         * @param KeyEvent event 
         */
        public void keyPressed(KeyEvent event){
            switch (event.getKeyCode()){
                case KeyEvent.VK_S:
                blank1 = s;
                if(x >= 0 && x <=75) //plays note if the key pressed matches the x coordinates of the death star image icon
                    playNote();                        
                break;

                case KeyEvent.VK_D:
                blank2 = d;
                if(x >= 150 && x <=225) //plays note if the key pressed matches the x coordinates of the death star image icon
                    playNote();             
                break;

                case KeyEvent.VK_F:
                blank3 = f;
                if(x>=300 && x<=375) //plays note if the key pressed matches the x coordinates of the death star image icon
                    playNote();                
                break;

                case KeyEvent.VK_J:
                blank4 = j;
                if(x>=450 && x<=525) //plays note if the key pressed matches the x coordinates of the death star image icon
                    playNote();
                break;

                case KeyEvent.VK_K:
                blank5 = k;
                if(x>=600 && x<=675) //plays note if the key pressed matches the x coordinates of the death star image icon
                    playNote();
                break;

                case KeyEvent.VK_L:
                if(x>=750 && x<=825) //plays note if the key pressed matches the x coordinates of the death star image icon
                    playNote();      
                blank6 = l;
                break;
            }

            //initialize the next falling note
            if(y > 500 && !notes.isEmpty()) {
                currentNote = notes.dequeue();
                x = currentNote.getX();
                y = currentNote.getY();
                moveY = currentNote.getBeat();

                //initialize chord note every time
                chordNote = chords.get(counter);
                chordX = chordNote.getX();
                chordY = chordNote.getY();     

                counter++;
            }

            //updates the panel so the user sees the image icons constantly moving position
            repaint();

        }

        /**
         * Method is part of the KeyListener interface that keeps track of the key typed
         * @param KeyEvent event which is the action the user takes when typing a key using the keyboard
         */
        public void keyTyped(KeyEvent event){}

        /**
         * When key is released, repaint all the piano keys to white
         * @param KeyEvent event which is the action the user takes when releasing a key using the keyboard
         */
        public void keyReleased(KeyEvent event){
            //repaint all piano keys to white 
            blank1 = new ImageIcon("white_key_S.jpg");
            blank2 = new ImageIcon("white_key_D.jpg");
            blank3 = new ImageIcon("white_key_F.jpg");
            blank4 = new ImageIcon("white_key_J.jpg");
            blank5 = new ImageIcon("white_key_K.jpg");
            blank6 = new ImageIcon("white_key_L.jpg");
            repaint();
        }

        /**
         * Checks whether or not the note is at the correct position to be played
         * If the note or chord was pressed at the right time, play the audio file
         *  and increase the Score.
         */
        public void playNote(){
            if (y > 500 && y < 700){ //get coordinates from notes
                currentNote.getNote().play();
                score += 10;
                //update score label
                displayScore.setText("Score: " + score);
            }

            if(isValidChord() && !chords.isEmpty()){ //checks to see whether the chords is not empty and the chord note is valid
                chordNote.getNote().play();
            }
        }
    }

    public class NoteListener implements ActionListener{   
        /**
         * actionPerformed method from the ActionListener interface
         * Used to "animate" notes falling down the page
         * Also checks if the game is over so appropriate text could be displayed
         * @param ActionEvent
         */
        public void actionPerformed(ActionEvent e){
            y += moveY;
            repaint();
            if(isValidChord() && !chords.isEmpty())
                chordY += moveY;

            //check for game over:
            if(notes.isEmpty() && y >900 && y<940){

                displayScore.setText("Game Over! Your Score is : " + score);
                ranking.setText(yourRank());
                updateScore();
            }
        }

        /**
         * Reads previous scores from file, add new score to file, 
         * rewrite file with new score. 
         * CODE IS SLIGHTLY BUGGY: each time, the score is written 3 times to the file. 
         * We tried to break out of the loop after assiging the score to one index of the array 
         * but it still did not work. 
         */
        public void updateScore(){
            Scores score_class = new Scores();
            score_class.readFile("scoreHistory.txt");
            int[] getScores = score_class.getScoreArray();
            if(getScores.length == score_class.getSize()){
                int currentIndex = getScores.length-1;
                score_class.expandCapacity();
                getScores[currentIndex] = score;
            }
            else{
                for (int i = 0; i < getScores.length; i++) {
                    if (getScores[i] == 0) { //find next free spot
                        getScores[i] = score;
                        break; //break out of for loop
                    }
                }            
            }

            getScores = score_class.insertionSort(getScores);
            score_class.writeFile("scoreHistory.txt", getScores);

        }

        /**
         * Helper method determining the ranking text when the game is over.
         * @return String description of your rank
         */
        private String yourRank(){
            if(score <= 300){
                return "You're a padawan. Keep trying!";
            }
            else if(score <= 450)
                return "You're a Jedi-in-training!";

            else{
                return "You're a Jedi! May the Force be with You!";
            }
        }

    }

}
