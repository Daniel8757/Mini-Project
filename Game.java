package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.geom.*;


/*
Developed by: Kevin Bui, Daniel Li, Sebastian Kamal
Date of creation: 4/16/19
Last edited on: 4/28/19
Keybinds:
    Space - Pause
    R - Restart game
    G - Godmode
    F - Flightmode
    C - Toggle obstacles
Update Log:
4/16/19:
- Created a moving ball. (Seb)
- Created solid boundaries. 100% working! (Seb)
4/23/19:
- Individual colours for objects. (Seb)
- Fixed border movement. (Seb)
4/24/19:
- Added player gravity. The ball will always fall back down! (Seb)
- Added jumping! (Non parabolic)(Seb)
- Improved general game mechanics. (Seb)
4/25/19:
- Added obstacles! They don't have collisions. (Seb)
- Fixed the gravity and the jumping. (Seb)
- Improved obstacle difficulty. (Seb)
4/26/19:
- Added collisions with player & obstacles! (Seb & Kevin)
- Added God mode. (Seb)
- Added Flight mode. (Seb)
- Added Obstacle toggler. (Seb)
- Fixed general movement. (Seb)
- Added more obstacles (Doubled, it's still not even hard unless you're a noob :P).
4/28/19:
- Improved the code (less repition for game end instances, it's all in one inside the actionPerformed now, much better). (Seb)
- Added a game pauser keybind. (Seb)
- Resets obstacles' positions when obstacles are disabled. (Seb)
- Added a restart function. (Seb)
- Added GUI functions. (Seb)
- Refined code as much as possible. (Seb)
4/29/19:
- Fixed the label updater. Made them global to be updated on keypress as well as opposed to being constantly updated through a
do {} while (true); loop which lagged computers.
*/

public class Game extends JPanel implements ActionListener, KeyListener {
    
    
    public static enum STATE{
        MENU,
        GAME
    }
    
    public static STATE state = STATE.MENU;
    public static boolean a = false;
    
    
    // Creates a refresher (the actionlistener in the class ^^ is updating every 5ms)
    Timer t = new Timer(5, this);
    // Movement conditions
    public static boolean canJump = true;
    public static boolean gamePaused = true;
    public static boolean gameEnded = false;
    // Cheat conditions
    public static boolean godMode = false;
    public static boolean flightMode = false;
    public static boolean noObstacles = false;
    // Labels to display variable states
    public static JLabel pausedLabel = new JLabel("Paused: " + gamePaused + ", ");
    public static JLabel godLabel = new JLabel("Godmode: " + godMode + ", ");
    public static JLabel flightLabel = new JLabel("Flightmode: " + flightMode + ", ");
    public static JLabel obstaclesLabel = new JLabel("No obstacles: " + noObstacles);
    
    /* Object properties */
    // Player
    public static int x = 370, y = 498, velx = 0, vely = 0;
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
        if (!noObstacles) {
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
    }
    
    // Game mechanics
    // Borders
    public void boundaries() {
        // X Borders
        if ((x + velx >= 0 && x + velx <= 770)) {
            // Allows movement
        } else {
           velx = 0;
        }

        if (!flightMode) {
            // Y Borders
            if (y + vely <= 498 && y >= 350) {
                // Allows movement
            } else {
                vely = 0;
            }
        } else {
            // Y Borders
            if (y + vely <= 498 && y + vely >= -1) {
                // Allows movement
            } else {
                vely = 0;
            }
        }
    }
    
    // Player
    public void updatePlayer() {
        x += velx;
        y += vely;
        if (y < 498) {
            if (!flightMode) {
                y += 4;
                // Disables jumping while in air
                canJump = false;
            }
        } else {
            // Means the ball has landed because it has reached the ground coords and is able to jump.
            canJump = true;
            // Bug fix, ignore this (Sets the ball to be level with platform no matter what)
            if (y > 498) {
                y = 498;
            }
        }
    }
    
    // Obstacles
    public void obstacle1() {
        // Generating random numbers for the obstacle to spawn in when it's at the top of the map
        if (y1 == 10) {
            int randomX = (int) (Math.random()*((760-5) + 1)) + 5;
            int randomY = (int) (Math.random()*((5-2) + 1)) + 2;
            // Setting X and speed of falling to random values
            x1 = randomX;
            vel1 = randomY;
        }
        // Making the obstacle fall at random speed
        y1 += vel1;
        // If it reaches the ground it goes back up and repeats
        if (y1 >= 498) {
            y1 = 10;
        }
    }
    
    public void obstacle2() {
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
    
    public void obstacle3() {
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
    
    public void obstacle4() {
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
    
    public void obstacle5() {
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
    
    public void obstacle6() {
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
    
    public void obstacle7() {
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
    
    public void obstacle8() {
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
    
    public void obstacle9() {
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
    
    public void obstacle10() {
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
    
    public static void up() {
        if (canJump) {
            if (!flightMode) {
                vely = -7;
            } else {
                vely = (int) -3.5;
            }
        }
    }
    
    public static void down() {
        if (flightMode) {
            vely = (int) 3.5;
        }
    }
    
    public static void left() {
        velx = (int) -3.5;
    }
    
    public static void right() {
        velx = (int) 3.5;
    }
    
    // Cheat methods
    public static void toggleGod() {
        godMode = !godMode;
        godLabel.setText("Godmode: " + godMode + ", ");
        System.out.println("GODMODE: " + godMode);
    }
    
    public static void toggleFlight() {
        if (!flightMode) {
            flightMode = true;
            canJump = true;
            vely = 0;
        } else {
            flightMode = false;
        }
        flightLabel.setText("Flightmode: " + flightMode + ", ");
        System.out.println("FLIGHTMODE: " + flightMode);
    }
    
    public static void toggleObstacles() {
        noObstacles = !noObstacles;
        System.out.println("No obstacles: " + noObstacles);
        obstaclesLabel.setText("No obstacles: " + noObstacles + ", ");
    }
    
    public static void pauseGame() {
        if (!gameEnded) {
            gamePaused = !gamePaused;
            pausedLabel.setText("Paused: " + gamePaused + ", ");
            System.out.println("Paused: " + gamePaused);
        } else {
            resetGame();
        }
    }
    
    public static void resetPlayer() {
        x = 370; y = 498; velx = 0; vely = 0;
    }
    
    public static void resetObstacles() {
        x1 = (int) ((Math.random()*((760-5) + 1)) + 5); y1 = 0;
        x2 = (int) ((Math.random()*((760-5) + 1)) + 5); y2 = 0;
        x3 = (int) ((Math.random()*((760-5) + 1)) + 5); y3 = 0;
        x4 = (int) ((Math.random()*((760-5) + 1)) + 5); y4 = 0;
        x5 = (int) ((Math.random()*((760-5) + 1)) + 5); y5 = 0;
        x6 = (int) ((Math.random()*((760-5) + 1)) + 5); y6 = 0;
        x7 = (int) ((Math.random()*((760-5) + 1)) + 5); y7 = 0;
        x8 = (int) ((Math.random()*((760-5) + 1)) + 5); y8 = 0;
        x9 = (int) ((Math.random()*((760-5) + 1)) + 5); y9 = 0;
        x10 = (int) ((Math.random()*((760-5) + 1)) + 5); y10 = 0;
    }
    
    public static void resetGame() {
        resetPlayer();
        resetObstacles();
        gameEnded = false;
        System.out.println("Game reset!");
    }
    
    public void stopGame() {
        gameEnded = true;
    }
    
    // Creates controllable movement for the ball
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (!gameEnded) {
            // Movements
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
        // Game pauser
        if (code == KeyEvent.VK_SPACE) {
            pauseGame();
        }
        // Reset game
        if (code == KeyEvent.VK_R) {
            resetGame();
        }
        // Cheats keybinds
        // God mode
        if (code == KeyEvent.VK_G) {
            toggleGod();
        }
        // Flight mode
        if (code == KeyEvent.VK_F) {
            toggleFlight();
        }
        // No obstacles
        if (code == KeyEvent.VK_C) {
            toggleObstacles();
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
    
    /* Everytime the actionlistener is called for through the timer it redoes this which allows for constant frame painting (refreshing) */
    public void actionPerformed(ActionEvent e) {
        if (!gameEnded && !gamePaused) {
            boundaries();
            updatePlayer();
            if (!noObstacles) {
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
                if (!godMode) {
                    collisions();
                }
            }
            repaint();
        }
    }
    
    public static void startGame(){
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // Defining the screen to get and use its dimensions
               JFrame f = new JFrame();
               Game game = new Game();


               Settings setting = new Settings();

               // Controls
               JFrame controls = new JFrame(); // Window
               JPanel controlsPanel = new JPanel(); // Panel that exists inside window



               // Buttons
               // Pause button
               JButton pauseButton = new JButton("START / STOP") {
                   {
                       setSize(500, 300);
                       addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               pauseGame();
                               //pausedLabel.setText("Paused: " + gamePaused + ", ");
                               f.setVisible(true);
                           }
                       });
                   }
               };

               // Reset button
               JButton resetButton = new JButton("RESET") {
                   {
                       setSize(500, 300);
                       addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               resetGame();
                               f.setVisible(true);
                           }
                       });
                   }
               };

               // God button
               JButton godButton = new JButton("GOD") {
                   {
                       setSize(500, 300);
                       addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               toggleGod();
                               //godLabel.setText("Godmode: " + godMode + ", ");
                               f.setVisible(true);
                           }
                       });
                   }
               };

               // Flight button
               JButton flightButton = new JButton("FLIGHT") {
                   {
                       setSize(500, 300);
                       addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               toggleFlight();
                               //flightLabel.setText("Flightmode: " + flightMode + ", ");
                               f.setVisible(true);
                           }
                       });
                   }
               };

               // Obstacles button
               JButton obstaclesButton = new JButton("Obstacles") {
                   {
                       setSize(500, 300);
                       addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               toggleObstacles();
                               // obstaclesLabel.setText("No obstacles: " + noObstacles + ", ");
                               f.setVisible(true);
                           }
                       });
                   }
               };

               // Controls panel
               // Adding buttons
               controlsPanel.add(pauseButton);
               controlsPanel.add(resetButton);
               controlsPanel.add(godButton);
               controlsPanel.add(flightButton);
               controlsPanel.add(obstaclesButton);
               // Adding labels
               controlsPanel.add(pausedLabel);
               controlsPanel.add(godLabel);
               controlsPanel.add(flightLabel);
               controlsPanel.add(obstaclesLabel);

               // Controls window
               controls.setTitle("Control panel");
               controls.add(controlsPanel); // Adding buttons
               controls.setSize(500,600);
               controls.setLocation(screen.width/6-controls.getSize().width/2, screen.height/2-controls.getSize().height/2);
               controls.setVisible(true);
               controls.setResizable(false);
               controls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               // Game window
               f.setTitle("Avoid the blue obstacles!");
               f.add(game);
               f.setSize(800, 600);
               f.setLocation(screen.width/2-f.getSize().width/2, screen.height/2-f.getSize().height/2);
               f.setVisible(true);
               f.setResizable(false);
               f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String [] args) {
        
               StartScreen startScreen = new StartScreen();
               startScreen.setVisible(true);
    }
            
            
         
 }
