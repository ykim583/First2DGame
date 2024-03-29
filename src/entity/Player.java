package entity;

import main.GamePanel;
import main.Motion;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// import java.awt.Color; (unused import, as we're not using the rectangle tile as our character, we have implemented image)

public class Player extends Entity {

    GamePanel gp;
    Motion key;

    public final int screenX; // Camera that follows the characters position change inside world map
    public final int screenY;

    public Player(GamePanel gp, Motion key) {

        this.gp = gp;
        this.key = key;

        // We subtract the tilesize/2 as the current camera location is on top left
        // corner of character

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // Solid Area where we initalized on player (Boundary where we detect collision
        // within player tile)
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    // From below, now we update the player's informations from GamePanel Class ->
    // Player Class (Encapsulation) {Methods we take (update), (SetDefaultValues),
    // (draw)}

    public void setDefaultValues() {

        // Starting point of player at the world map
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 23;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() { // Process of getting player image (not the square tile anymore)

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/character/boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { // Depending the player's key choice, we can move now. (But, the timeframe is
                           // too fast, we cannot see the change)

        // This first line of code (enables the charcter to make motion change only if
        // th key is being pressed)
        // CHECKS DIRECTION

        if (key.upPressed == true || key.downPressed == true || key.rightPressed == true || key.leftPressed == true) {

            if (key.upPressed == true) {
                direction = "up";
            } else if (key.downPressed == true) {
                direction = "down";
            } else if (key.leftPressed == true) {
                direction = "left";
            } else if (key.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            // Mechanism - Update method called 60times/second.
            // For every frame that's 10times/s, the update method increment counter
            // So counter incremented will increment spriteNum, so different image is
            // called.
            // Repetition of fast moving images = Animation of 2D walking

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

    }

    public void draw(Graphics2D g2) { // This method is built in method in Java to draw in JPanel
        // Graphis class = A class that has many functions to draw objects on
        // screen (If you want to use, import it)

        // g2.setColor(Color.white); // Colour of background
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // Size of rectangle

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // Image observer
    }
}