package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/*

hi! :D

Developed by: Kevin Bui, Daniel Li, Sebastian Kamal
Date of creation: 4/16/19
Last edited on: 4/16/19

Update Log:

4/16/19:
- Fixed solid borders. 100% working!
- Added optional pacman styled borders

*/

public class Game extends JPanel implements ActionListener, KeyListener {

    /* Creates a refresher (the actionlistener in the class ^^ is updating every 5ms) */
    Timer t = new Timer(5, this);
    /* x and y are current points, the vel points are to be added to the x & y to redraw the ball every 5ms which is basically how the movement is done */
    double x = 360, y = 260, velx = 0, vely = 0;
    
    public Game() {
        /* Starts the timer */
        t.start();
        /* Setting properties */
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    /* Creating the 2d graphics renderer */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0,0,255));
        g2.fill(new Ellipse2D.Double(x, y, 25, 25)); // Ball
        g2.fill(new Rectangle2D.Double(0, 521, 800, 50)); // Status bar
    }
    
    /* Everytime ANYTHING happens (ie. a keypress), it's changing the x & y coords and repainting the canvas with the new ball */
    public void actionPerformed(ActionEvent e) {
        
        // Solid borders
        if ((x + velx >= 0 && x + velx <= 761) && (y + vely >= -1 && y + vely <= 536)) {
            // Allows movement
        } else {
            velx = 0;
            vely = 0;
        }
        
        /* Pacman borders (Unlikely to use but cool!)
        if ((x + velx >= 830)) {
            x = -35;
        }
        if ((x + velx <= -68)) {
            x = 830;
        }
        if ((y + vely >= 605)) {
            y = -35;
        }
        if ((y + vely <= -68)) {
            y = 600;
        }
        */
        
        repaint();
        x += velx;
        y += vely;
        
    }
    
    /* Directional movement handlers */
    public void up() {
        vely = -4.5;
    }
    
    public void down() {
        vely = 4.5;
    }
    
    public void left() {
        velx = -4.5;
    }
    
    public void right() {
        velx = 4.5;
    }
    
    /* Key press handling starts here */
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            up();
        }
        if (code == KeyEvent.VK_DOWN) {
            down();
        }
        if (code == KeyEvent.VK_LEFT) {
            left();
        }
        if (code == KeyEvent.VK_RIGHT) {
            right();
        }
        // System.out.println("X: " + x + ", Y: " + y);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            vely = 0;
        }
        if (code == KeyEvent.VK_DOWN) {
            vely = 0;
        }
        if (code == KeyEvent.VK_LEFT) {
            velx = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            velx = 0;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Key is not yet implimented in the game!");
    }
    
    public static void main(String [] args) {
        
        JButton devMode = new JButton("Dev");
        
        Game game = new Game();
        
        JFrame f = new JFrame();
        f.setTitle("Game");
        f.add(game);
        // f.add(devMode); // This is blured because it keeps filling the entire screen
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
    }
     
    /* End of program */
}
