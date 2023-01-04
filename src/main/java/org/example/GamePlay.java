package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play=false; // sets the initial condition of game as stop if want to start we need to set the variable as true
    private int score=0; // calculating score
    private int totalBricks=21; // sets the total no of bricks in our map
    private Timer Timer; // to calculate the time
    private int delay=8; // to generate some friction (generally in milliseconds)
    private int playerX=310; // position of slider on our map
    private int ballPosX=347;
    private int ballPosY=500;
    private int ballXDir=-3;
    private int ballYDir=-5;
    private MapGenerator map;
    public GamePlay(){
        map=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true); // used to maintain everything remains in Focus
        setFocusTraversalKeysEnabled(false);
        Timer=new Timer(delay,this);
        Timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.black); // sets the Frame color as black
        g.fillRect(1,1,692,592); // fills the Frame with the setColor black

        map.draw((Graphics2D) g); // calls the draw function to generate the Map
        g.setColor(Color.yellow); // color for Frame Border
        // dimensions/co-ordinates for bordering the Frame
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        g.setColor(Color.white); // color for Score
        g.setFont(new Font("Times New Roman",Font.BOLD,25)); // creating a new Font Style for Score
        g.drawString(""+score,590,30);

        g.setColor(Color.yellow); // Color for player or Slider
        g.fillRect(playerX,550,100,8); // Slider

        g.setColor(Color.green); // color for ball
        g.fillOval(ballPosX,ballPosY,20,20); // Ball creation
        if(ballPosY>570){ // case 1
            play=false;
            ballXDir=0;
            ballYDir=0;
            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman",Font.BOLD,30));
            g.drawString("Game Over Score: "+score,190,300);
            g.setFont(new Font("Times New Roman",Font.BOLD,30));
            g.drawString("Press Enter to Restart",190,375);
        }
        if(totalBricks==0){
            play=false;
            ballYDir=-5;
            ballXDir=-3;
            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman",Font.BOLD,30));
            g.drawString("Game Over Score: "+score,190,300);
            g.setFont(new Font("Times New Roman",Font.BOLD,30));
            g.drawString("Press ENTER to Restart the Game ",190,375);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,20,30).intersects(new Rectangle(playerX,550,100,8))){
                ballYDir=-ballYDir;
            }
            A:
            for (int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickX=j*map.bricksWidth+80;
                        int brickY=i* map.bricksHeight+50;
                        int bricksWidth=map.bricksWidth;
                        int bricksHeight=map.bricksHeight;
                        Rectangle rect=new Rectangle(brickX,brickY,bricksWidth,bricksHeight);
                        Rectangle ballRect=new Rectangle(ballPosX,ballPosY,20,20);
                        Rectangle brickrect=rect;

                        if(ballRect.intersects(brickrect)){
                            map.setBricksValue(0,i,j);
                            totalBricks--;
                            score+=5;
                            if(ballPosX+19<=brickrect.x || ballPosX+1 >=brickrect.x+bricksWidth){
                                ballXDir=-ballXDir;
                            }
                            else{
                                ballYDir=-ballYDir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX+=ballXDir;
            ballPosY+=ballYDir;
            if(ballPosX<0){
                ballXDir=-ballXDir;
            }
            if(ballPosY<0){
                ballYDir=-ballYDir;
            }
            if(ballPosX>670){
                ballXDir=-ballXDir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX=600;
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX=10;
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                ballPosX=120;
                ballPosY=350;
                ballXDir=-3;
                ballYDir=-5;
                score=0;
                playerX=310;
                totalBricks=21;
                map=new MapGenerator(3,7);
                repaint();
            }
        }
    }
    public void moveRight(){
        play=true;
        playerX+=50;
    }
    public void moveLeft(){
        play=true;
        playerX-=50;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
