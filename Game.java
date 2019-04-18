package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// Oofo!GFYGDFDGFYGYDSF AGAGGFGAGGA

/*

Developed by: Kevin Bui, Daniel Li, Sebastian Kamal
Date of creation: 4/16/19
Last edited on: 4/16/19

Update Log:

4/16/19:
- Created a frame and a canvas to draw on
- Created a moving ball
- Created somewhat of a boundary system (the window)
    - Bug: If you are far from a boundary and hold a button to head towards it, you can pass it because there is no KEYDOWN listener only a KEYPRESS so tapping it will present you with a locked motion.

*/

public class Game extends JPanel implements ActionListener, KeyListener {

    /* Creates a refresher (the actionlistener in the class ^^ is updating every 5ms) */
    Timer t = new Timer(5, this);
    /* x and y are current points, the vel points are to be added to the x & y to redraw the ball every 5ms which is basically how the movement is done */
    double x = 0, y = 0, velx = 0, vely = 0;
    
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
        g2.fill(new Ellipse2D.Double(x, y, 40, 40));
    }
    
    /* Everytime ANYTHING happens (ie. a keypress), it's changing the x & y coords and repainting the canvas with the new ball */
    public void actionPerformed(ActionEvent e) {
        repaint();
        x += velx;
        y += vely;
    }
    
    /* Directional movement handlers */
    public void up() {
        if (y >= 10) {
            vely = -4.5;
            velx = 0;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
    public void down() {
        if (y <= 500) {
            vely = 4.5;
            velx = 0;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
    public void left() {
        if (x >= 0) {
            vely = 0;
            velx = -4.5;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
    public void right() {
        if (x <= 609) {
            vely = 0;
            velx = 4.5;
            System.out.println("X: " + x + ", Y: " + y);
        }
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
    }
    
    public static void main(String [] args) {
        JFrame f = new JFrame();
        Game game = new Game();
        f.add(game);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            vely = 0;
            velx = 0;
        }
        if (code == KeyEvent.VK_DOWN) {
            vely = 0;
            velx = 0;
        }
        if (code == KeyEvent.VK_LEFT) {
            vely = 0;
            velx = 0;
        }
        if (code == KeyEvent.VK_RIGHT) {
            vely = 0;
            velx = 0;
        }
    }
     
    /* End of program */
}
