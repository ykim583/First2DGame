package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable { // The class to set up screen

    // Screen Settings
    final int originalTileSize = 16; // 16 x 16 tile is created
    final int scale = 3; // As modern computer have high resolution size, and current 2D image is low. we
                         // scale up

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile size scaled up (NOTE Part 3 - Made it to
                                                          // public so Player class accessable)
    public final int maxScreenCol = 16; // 16 tiles Horizontal
    public final int maxScreenRow = 12; // 12 tiles Vertical
    public final int screenWidth = tileSize * maxScreenCol; // 48 x 16 = 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 48 x 12 = 576 pixels

    // WORLD Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    Thread gameThread; // This keeps the program running until it exit the game

    Motion key = new Motion(); // New class we are gonna use for character's motion

    public CollisonChecker cChecker = new CollisonChecker(this); // Collision Detect Class

    public Player player = new Player(this, key); // Player added inside

    // Set Player's default position (Now all inside Player class so commented out
    // here)

    // int PlayerX = 100;
    // int PlayerY = 100;
    // int PlayerSpeed = 4;

    // Now creating a constructor of gamePanel

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // If set to true, (Game rendering performance improved)
        this.addKeyListener(key); // Now we can also recognize the character's motion for key
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this); // Create new instance of Thread
        gameThread.start(); // Start this thread
    }

    @Override
    public void run() { // When gameThread being called, run method is automatically called

        double drawInterval = 1000000000 / FPS; // Total time executalble (60 draw per second), before, too fast so
                                                // cannot see the motion change
        double nextDrawTime = System.nanoTime() + drawInterval; // When nextDrawTime is reached, we repeat the process

        while (gameThread != null) {

            // We need to update the Player's movement
            update();
            // We also need to draw the screen with the updated information
            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime(); // Time before next action can be used
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) { // Exception
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); // We make it sleep until next run time starts

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() { // Depending the player's key choice, we can move now. (But, the timeframe is
                           // too fast, we cannot see the change)

        player.update(); // Call Player class
    }

    public void paintComponent(Graphics g) { // This method is built in method in Java to draw in JPanel
                                             // Graphis class = A class that has many functions to draw objects on
                                             // screen (If you want to use, import it)

        super.paintComponent(g); // As we know GamePanel is subclass of JPanel

        Graphics2D g2 = (Graphics2D) g; // Graphics2D extends graphics class usage

        tileM.draw(g2); // <WARNING> Draw Tile image before the player (Ad player should override the
                        // image of background)

        player.draw(g2); // Call Player class

        g2.dispose(); // Good for saving memory
    }

}
