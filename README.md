package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/*

Developed by: Kevin Bui, Daniel Li, Sebastian Kamal
Date of creation: 4/16/19
Last edited on: 4/16/19 By Sebastian

Update Log:

4/16/19:
- Created a frame with a 2d canvas
- Animated ball moves (repaints ball every 5 ms to create the movement when x & y positions are modified)
- Created a kind of working boundary
	- Bug: You can bypass the boundary of the border whilst holding down the direction in which you are moving, simply tapping the key will in fact deny you. Need to add a KEYDOWN listener so while the key is down it denies them, should fix the issue.

*/

public class Game extends JPanel implements ActionListener, KeyListener {

    Timer t = new Timer(5, this);
    double x = 0, y = 0, velx = 0, vely = 0;
    
    public Game() {
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.fill(new Ellipse2D.Double(x, y, 40, 40));
    }
    
    public void actionPerformed(ActionEvent e) {
        repaint();
        x += velx;
        y += vely;
    }
    
    public void up() {
        if (y >= 10) {
            vely = -1.5;
            velx = 0;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
    public void down() {
        if (y <= 500) {
            vely = 1.5;
            velx = 0;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
    public void left() {
        if (x >= 0) {
            vely = 0;
            velx = -1.5;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
    public void right() {
        if (x <= 609) {
            vely = 0;
            velx = 1.5;
            System.out.println("X: " + x + ", Y: " + y);
        }
    }
    
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
    
}

