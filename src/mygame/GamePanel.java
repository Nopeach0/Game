/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mygame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author behda
 */
public class GamePanel extends JPanel implements Runnable{
    public final int originalTileSize = 16; // 16 * 16 pixel tile
    public final int scaleTile = 3;
    public final int tileSize = originalTileSize * scaleTile; // 48pixel
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol; //768 pixels
    public final int screenheight = tileSize * maxScreenRow;//576 pixels
    
    //FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    
    Thread gameThread;
    
    //Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenheight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;//0.00166 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (gameThread != null){
            //System.out.println("The game loop is Running");
            //1: Update information such as character position;
            update();
            //2: Draw the screen with the updated informatoin
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (keyH.upPressed == true){
            playerY -= playerSpeed;
        } else if (keyH.downPressed == true){
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true){
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true){
            playerX += playerSpeed;
        }
        
    }
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2 = (Graphics2D)g;
       
       g2.setColor(Color.white);
       
       g2.fillRect(playerX, playerY, tileSize, tileSize);
       
       g2.dispose();
    }
}
