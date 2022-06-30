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

        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; // mapTileNum array will store all the value for Map.txt

        getTileImage();
        loadMap("/res/maps/Map.txt");
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

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void loadMap(String filePath) { // We can load map easily according to given txt file array produced now
                                           // For convenient multiple map creation, we passed over as parameter in
                                           // loadMap method

        try {
            InputStream is = getClass().getResourceAsStream("/res/maps/Map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" "); // Read String

                    int num = Integer.parseInt(numbers[col]); // Convert To Integer

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row]; // Everything is stored in the map tile Num array now

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null); // 보면 tile[tileNum].image 가 더이상
                                                                                     // fixed value 아니고 새로주어진거기때문에 다양한
                                                                                     // 타일 모양 변형 가능

            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) { // When it reaches the maximum col, reset and go to next row and continue
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}
