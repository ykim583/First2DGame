import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel { // The class to set up screen

    // Screen settings 
    final int originalTileSize = 16; //  16 x 16 tile is created
    final int scale = 3; // As modern computer have high resolution size, and current 2D image is low. we scale up

    final int tileSize = originalTileSize * scale; // 48 x 48 tile size scaled up
    final int maxScreenCol = 16; // 16 tiles Horizontal
    final int maxScreenRow = 12; // 12 tiles Vertical
    final int screenWidth = tileSize * maxScreenCol; // 48 x 16 = 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 48 x 12 = 576 pixels

    // Now creating a constructor of gamePanel

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // If set to true, (Game rendering performance improved)
    }
}
