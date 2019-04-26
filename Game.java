package game;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
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
- Added gravity! The ball will always fall back down! (Seb)
- Added jumping! (Non parabolic)(Seb)
- Fixed the gravity and jumping. (Seb)

4/25/19:
- Added obstacles! They don't have collisions. (Seb)
- Fixed the gravity and the jumping. (Seb)
- Improved obstacle difficulty. (Seb)

4/26/19:
- Added collisions with objects! (Seb & Kevin)
- Added God mode. (Seb)
- Added Flight mode. (Seb)
- Added more obstacles (Doubled, it's still not even hard unless you're a noob).

FOR KEVIN AND DANIEL:
- Create a start menu
- Create a game over menu with restart or main menu options
- Modify the code and make it as good as possible (if needed)!

*/

public class Game extends JPanel implements ActionListener, KeyListener {
    // Creates a refresher (the actionlistener in the class ^^ is updating every 5ms)
    Timer t = new Timer(5, this);
    // Movement conditions
    public static boolean canJump = true;
    public static boolean gameEnded = false;
    // Cheat conditions
    public static boolean godMode = false;
    public static boolean flightMode = false;
    
    /* Object properties */
    // Player
    public static int x = 360, y = 498, velx = 0, vely = 0;
    // Obstacles
    public static int x1, y1 = 10, vel1 = 0;
    public static int x2, y2 = 10, vel2 = 0;
    public static int x3, y3 = 10, vel3 = 0;
    public static int x4, y4 = 10, vel4 = 0;
    public static int x5, y5 = 10, vel5 = 0;
    public static int x6, y6 = 10, vel6 = 0;
    public static int x7, y7 = 10, vel7 = 0;
    public static int x8, y8 = 10, vel8 = 0;
    public static int x9, y9 = 10, vel9 = 0;
    public static int x10, y10 = 10, vel10 = 0;
    // Width for ALL objects
    public static int width = 25, height = 25;
    
    public Game() {
        t.start(); // Starts the timer
        addKeyListener(this); // Adds a keyListener for key press inputs
        setFocusable(true); // Focuses on the program so it can be interacted with
    }
    
    // Graphic renderer. Displays all graphic images.
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Creating the objects
        g2.setColor(new Color(28,147,1)); // Platform colour
        g2.fill(new Rectangle2D.Double(0, 523, 795, 50)); // Platform
        g2.setColor(new Color(244,185,66)); // Player colour
        g2.fill(new Ellipse2D.Double(x, y, width, height)); // Player
        g2.setColor(new Color(0,0,255)); // Obstacle colours
        g2.fill(new Rectangle2D.Double(x1, y1, width, height)); // Obstacle 1
        g2.fill(new Rectangle2D.Double(x2, y2, width, height)); // Obstacle 2
        g2.fill(new Rectangle2D.Double(x3, y3, width, height)); // Obstacle 3
        g2.fill(new Rectangle2D.Double(x4, y4, width, height)); // Obstacle 4
        g2.fill(new Rectangle2D.Double(x5, y5, width, height)); // Obstacle 5
        g2.fill(new Rectangle2D.Double(x6, y6, width, height)); // Obstacle 6
        g2.fill(new Rectangle2D.Double(x7, y7, width, height)); // Obstacle 7
        g2.fill(new Rectangle2D.Double(x8, y8, width, height)); // Obstacle 8
        g2.fill(new Rectangle2D.Double(x9, y9, width, height)); // Obstacle 9
        g2.fill(new Rectangle2D.Double(x10, y10, width, height)); // Obstacle10
    }
    
    // Borders
    public void borders() {
        if (!flightMode) {
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
        } else {
            // X Borders
            if ((x + velx >= 0 && x + velx <= 770)) {
                // Allows movement
            } else {
               velx = 0;
            }

            // Y Borders
            if (y + vely <= 498 && y + vely >= -1) {
                // Allows movement
            } else {
                vely = 0;
            }
        }
    }
    
    // Player
    public void player() {
        if (!gameEnded) {
            x += velx;
            y += vely;
        }
    }
    
    // Gravity
    public void gravity() {
        if (!flightMode) {
            if (y < 498) {
                if (!gameEnded) {
                    y += 4;
                } else {
                    y += 0;
                }
                // Disables jumping while in air
                if (!flightMode) {
                    canJump = false;
                }
            } else {
                // Means the ball has landed because it has reached the ground coords and is able to jump!
                canJump = true;
                // The gravity tends to pull the ball past because of it's number setup with the ground so this fixes that.
                if (y > 498) {
                    y = 498;
                }
            }
        }
    }
    
    // Obstacles
    public void obstacle1() {
        if (!gameEnded) {
            // Generating random numbers for the obstacle to spawn in when it's at the top of the map
            if (y1 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x1 = randomX;
                vel1 = randomY;
            }
            y1 += vel1;
            // If it reaches the ground it goes back up and repeats
            if (y1 >= 498) {
                y1 = 10;
            }
        }
    }
    
    public void obstacle2() {
        if (!gameEnded) {
            if (y2 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x2 = randomX;
                vel2 = randomY;
            }
            y2 += vel2;
            if (y2 >= 498) {
                y2 = 10;
            }
        }
    }
    
    public void obstacle3() {
        if (!gameEnded) {
            if (y3 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x3 = randomX;
                vel3 = randomY;
            }
            y3 += vel3;
            if (y3 >= 498) {
                y3 = 10;
            }
        }
    }
    
    public void obstacle4() {
        if (!gameEnded) {
            if (y4 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x4 = randomX;
                vel4 = randomY;
            }
            y4 += vel4;
            if (y4 >= 498) {
                y4 = 10;
            }
        }
    }
    
    public void obstacle5() {
        if (!gameEnded) {
            if (y5 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x5 = randomX;
                vel5 = randomY;
            }
            y5 += vel5;
            if (y5 >= 498) {
                y5 = 10;
            }
        }
    }
    
    public void obstacle6() {
        if (!gameEnded) {
            if (y6 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x6 = randomX;
                vel6 = randomY;
            }
            y6 += vel6;
            if (y6 >= 498) {
                y6 = 10;
            }
        }
    }
    
    public void obstacle7() {
        if (!gameEnded) {
            if (y7 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x7 = randomX;
                vel7 = randomY;
            }
            y7 += vel7;
            if (y7 >= 498) {
                y7 = 10;
            }
        }
    }
    
    public void obstacle8() {
        if (!gameEnded) {
            if (y8 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x8 = randomX;
                vel8 = randomY;
            }
            y8 += vel8;
            if (y8 >= 498) {
                y8 = 10;
            }
        }
    }
    
    public void obstacle9() {
        if (!gameEnded) {
            if (y9 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x9 = randomX;
                vel9 = randomY;
            }
            y9 += vel9;
            if (y9 >= 498) {
                y9 = 10;
            }
        }
    }
    
    public void obstacle10() {
        if (!gameEnded) {
            if (y10 == 10) {
                int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
                int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
                x10 = randomX;
                vel10 = randomY;
            }
            y10 += vel10;
            if (y10 >= 498) {
                y10 = 10;
            }
        }
    }

    public void collisions() {
        // Creating objects associated with the necessary rendered shapes
        Rectangle player = new Rectangle(x, y, width, height);
        Rectangle o1 = new Rectangle(x1, y1, width, height);
        Rectangle o2 = new Rectangle(x2, y2, width, height);
        Rectangle o3 = new Rectangle(x3, y3, width, height);
        Rectangle o4 = new Rectangle(x4, y4, width, height);
        Rectangle o5 = new Rectangle(x5, y5, width, height);
        Rectangle o6 = new Rectangle(x6, y6, width, height);
        Rectangle o7 = new Rectangle(x7, y7, width, height);
        Rectangle o8 = new Rectangle(x8, y8, width, height);
        Rectangle o9 = new Rectangle(x9, y9, width, height);
        Rectangle o10 = new Rectangle(x10, y10, width, height);
        
        if (!godMode) {
            // Executing collisions
            if (player.intersects(o1)) {
                stopGame();
            }
            if (player.intersects(o2)) {
                stopGame();
            }
            if (player.intersects(o3)) {
                stopGame();
            }
            if (player.intersects(o4)) {
                stopGame();
            }
            if (player.intersects(o5)) {
                stopGame();
            }
            if (player.intersects(o6)) {
                stopGame();
            }
            if (player.intersects(o7)) {
                stopGame();
            }
            if (player.intersects(o8)) {
                stopGame();
            }
            if (player.intersects(o9)) {
                stopGame();
            }
            if (player.intersects(o10)) {
                stopGame();
            }
        }
    }
    
    public void stopGame() {
        gameEnded = true;
    }
    
    /* Everytime the actionlistener is called for through the timer it redoes this which allows for constant frame painting (refreshing) */
    public void actionPerformed(ActionEvent e) {
        borders();
        player();
        gravity();
        obstacle1();
        obstacle2();
        obstacle3();
        obstacle4();
        obstacle5();
        obstacle6();
        obstacle7();
        obstacle8();
        obstacle9();
        obstacle10();
        collisions();
        repaint();
    }
    
    // Creates controllable movement for the ball
    public void keyPressed(KeyEvent e) {
        if (!gameEnded) {
            int code = e.getKeyCode();
            // Movements
            if (code == KeyEvent.VK_UP && canJump) {
                if (!flightMode) {
                    vely = -9;
                } else {
                    vely = (int) -4.5;
                }
            }
            if (code == KeyEvent.VK_DOWN && flightMode) {
                vely = (int) 4.5;
            }
            if (code == KeyEvent.VK_LEFT) {
                velx = (int) -4.5;
            }
            if (code == KeyEvent.VK_RIGHT) {
                    velx = (int) 4.5;
            }
            // God mode
            if (code == KeyEvent.VK_G) {
                if (!godMode) {
                    godMode = true;
                } else {
                    godMode = false;
                }
                System.out.println("GODMODE: " + godMode);
            }
            // Flight mode
            if (code == KeyEvent.VK_F) {
                if (!flightMode) {
                    canJump = true;
                    vely = 0;
                    flightMode = true;
                } else {
                    flightMode = false;
                }
                System.out.println("FLIGHTMODE: " + flightMode);
            }
            // System.out.println("X: " + x + ", Y: " + y); // Displays X and Y coords on key press!
        }
    }
    
    // Stops movement if a key is released
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP && flightMode) {
            vely = 0;
        }
        if (code == KeyEvent.VK_DOWN && flightMode) {
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
    public void keyTyped(KeyEvent e) {
        // Not supported
    }
    
    public static void main(String [] args) {
        Game game = new Game();
        
        JFrame f = new JFrame();
        f.setTitle("Game");
        f.add(game);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);
    }

     
}
