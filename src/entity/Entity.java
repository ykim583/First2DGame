// All characters/nps/bots are created
// Abstract class (Parent class for all the entities)
package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {

    public int worldX, worldY; // Indiacates the different co-ordinates (Mechanism of Game Camera)
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    // Used to store our image files into game

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
