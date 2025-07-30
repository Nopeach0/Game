/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mygame;

/**
 *
 * @author behda
 */
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;
public class MyGame extends JFrame{
    
    public MyGame(String title){
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyGame game = new MyGame("2D game");
        GamePanel gamePanel = new GamePanel();
        //Updates the screen 60fps 60 times per seconds
        game.add(gamePanel);
        game.pack();
        game.setVisible(true);
        gamePanel.startGameThread();
    }
    
}
