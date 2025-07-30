/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author behda
 */
public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_W){
            upPressed = true;
        } else if (code == KeyEvent.VK_S){
            downPressed = true;
        } else if (code == KeyEvent.VK_A){
            leftPressed = true;
        } else if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
         if (code == KeyEvent.VK_W){
            upPressed = false;
        } else if (code == KeyEvent.VK_S){
            downPressed = false;
        } else if (code == KeyEvent.VK_A){
            leftPressed = false;
        } else if (code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
    
}
