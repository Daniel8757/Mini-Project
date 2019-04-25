package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*

Developed by: Kevin Bui, Daniel Li, Sebastian Kamal
Date of creation: 4/16/19
Last edited on: 4/16/19

Update Log:

4/16/19:
- Fixed solid borders. 100% working! (Seb)
- Added optional pacman styled borders. (Seb)

4/23/19:
- Individual colours for objects (Seb)
- Fixed border movement. (Seb)
- Removed pacman borders. They were uneeded (Seb).

4/24/19:
- Added gravity! The ball will always fall back down! (Seb)
- Added jumping! (Non parabolic)(Seb)
- Fixed the gravity and jumping. (Seb)

*/

public class Game extends JPanel implements ActionListener, KeyListener {
    
    // Jumping condition (If in air, jumping isn't allowed. THE BALL IS NOT A JEDI! :D)
    public static boolean canJump = true;
    /* Creates a refresher (the actionlistener in the class ^^ is updating every 5ms) */
    Timer t = new Timer(5, this);
    /* x and y are current points, the vel points are to be added to the x & y to redraw the ball every 5ms which is how the movement is done! */
    double x = 360, y = 498, velx = 0, vely = 0;
    
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
        g2.setColor(new Color(244,185,66)); // Ball colour
        g2.fill(new Ellipse2D.Double(x, y, 25, 25)); // Ball
        g2.setColor(new Color(28,147,1)); // Platform colour
        g2.fill(new Rectangle2D.Double(0, 523, 795, 50)); // Platform
    }
    
    /* Everytime ANYTHING happens (ie. a keypress), it's changing the x & y coords and repainting the canvas with the updated coordinates */
    public void actionPerformed(ActionEvent e) {
        
        // X Borders
        if ((x + velx >= 0 && x + velx <= 770)) {
            // Allows movement
        } else {
           velx = 0;
        }
        
        // Y Borders
        if (y + vely <= 498 && y >= 350) {
            // Allows movement
        } else {
            vely = 0;
        }
        
        // Painting the ball
        x += velx;
        y += vely;
        
        // Gravity for the ball
        if (y < 498) {
            y += 7;
            // Disables jumping while in air
            canJump = false;
        } else {
            // Means the ball has landed because it has reached the ground coords and is able to jump!
            canJump = true;
            // The gravity tends to pull the ball past because of it's number setup with the ground so this fixes that.
            if (y > 498) {
                y = 498;
            }
        }
        repaint();
        
    }
    
    // Creates controllable movement for the ball
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
           if (canJump) {
               vely = -13;
           }
        }
        if (code == KeyEvent.VK_LEFT) {
            velx = -4.5;
        }
        if (code == KeyEvent.VK_RIGHT) {
            velx = 4.5;
        }
        // System.out.println("X: " + x + ", Y: " + y); // Displays X and Y coords
    }
    
    // Stops movement if a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            velx = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            velx = 0;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Not yet implemented!");        
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
     
}
