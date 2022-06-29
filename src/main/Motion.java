package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Motion implements KeyListener{ // KeyListenr interface has three method for motion

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not used at all
    }

    @Override
    public void keyPressed(KeyEvent e) { // Detetcs the key being pressed and returns its action
        
        int code = e.getKeyCode(); // Returns the number/symbol of key being pressed

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) { // When we release the key, it becomes false after
       
        int code = e.getKeyCode();

        
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        } 
    }

    
}

