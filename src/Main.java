import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // Time needs to run in the game as real life
        // Video game is like a fast changing images
        // FPS 60 = The image change sixty seconds after.
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 'x' button click, the window is closed
        window.setResizable(false); // Window cannot be resized
        window.setTitle("Adventure in 2D");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // Adding GamePanel/JPanel features

        window.pack(); // Actually able to see game screen

        window.setLocationRelativeTo(null); // The window is displayed at centre of screen.
        window.setVisible(true); // The game window is visible to the developer

        gamePanel.startGameThread();
    }
}
