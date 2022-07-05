package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

import java.awt.Graphics2D;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) { // Constructor

        this.gp = gp;

        tile = new Tile[10];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // mapTileNum array will store all the value for Map.txt

        getTileImage();
        loadMap("/res/maps/WorldMap.txt");
        ;
    }

    public void getTileImage() { // Load images in this method

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/water.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tile/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void loadMap(String filePath) { // We can load map easily according to given txt file array produced now
                                           // For convenient multiple map creation, we passed over as parameter in
                                           // loadMap method

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" "); // Read String

                    int num = Integer.parseInt(numbers[col]); // Convert To Integer

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {

        // Initally, The map is just set to one fixed portion of the game
        // Now, we're updating to bigger world map, so setting the camera motion that
        // follows the character

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow]; // Everything is stored in the map tile Num array now

            int worldX = worldCol * gp.tileSize; // Step 1 : Capture the position of player
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // Step 2 : New Screen image display depending
                                                                         // on charcter position
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // Step 3 : Since charcter always centred on
                                                                         // screen,
            // There's exception when character might start from position (0,0), and blank
            // space appeared, + gp.player.screen.. offsets this

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null); // 보면
                                                                                                 // tile[tileNum].image
                                                                                                 // 가 더이상
            // fixed value 아니고 새로주어진거기때문에 다양한
            // 타일 모양 변형 가능

            worldCol++;

            if (worldCol == gp.maxWorldCol) { // When it reaches the maximum col, reset and go to next row and continue
                worldCol = 0;
                worldRow++;

            }
        }
    }

}
