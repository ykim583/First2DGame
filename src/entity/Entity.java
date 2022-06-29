// All characters/nps/bots are created
// Abstract class (Parent class for all the entities)
package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    //Used to store our image files into game

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
