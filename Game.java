package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

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
- Added jumping! (Non parabolic)(Seb)

*/

public class Game extends JPanel implements ActionListener, KeyListener {
    
    public static boolean canJump = false;
    /* Creates a refresher (the actionlistener in the class ^^ is updating every 5ms) */
    Timer t = new Timer(5, this);
    /* x and y are current points, the vel points are to be added to the x & y to redraw the ball every 5ms which is basically how the movement is done */
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
        g2.setColor(new Color(0,0,0)); // Status bar colour
        g2.fill(new Rectangle2D.Double(0, 523, 795, 50)); // Status bar
    }
    
    /* Everytime ANYTHING happens (ie. a keypress), it's changing the x & y coords and repainting the canvas with the new ball */
    public void actionPerformed(ActionEvent e) {
        
        // X Borders
        if ((x + velx >= 0 && x + velx <= 770)) {
            // Allows movement
        } else {
           velx = 0;
        }
        
        // Y Borders
        if (y + vely <= 498 && y + vely >= 350) {
            // Allows movement
        } else {
            vely = 0;
        }
        
        x += velx;
        y += vely;
        if (y < 498) {
            y += 10;
            canJump = false;
        } else {
            canJump = true;
        }
        repaint();
        
    }
    
    // Creates controllable movement for the ball
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
           if (canJump) {
               vely = -20;
           }
        }
        if (code == KeyEvent.VK_LEFT) {
            velx = -5.5;
        }
        if (code == KeyEvent.VK_RIGHT) {
            velx = 5.5;
        }
    }
    
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
