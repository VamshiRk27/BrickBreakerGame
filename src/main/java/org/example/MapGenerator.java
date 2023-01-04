package org.example;

import java.awt.*; // imports java AWT package
// we mainly import BasicStroke,Color,Graphics 2D classes from awt package
public class MapGenerator {
    public int map[][]; // A 2D Array to create the bricks
    public int bricksWidth,bricksHeight;// size of each individual brick
    public MapGenerator(int row,int col){ // A constructor to initialize map on screen.
        map=new int[row][col]; //initializing the map using row & col
        for (int[] map1:map){
            for(int j=0;j<map[0].length;j++){
                map1[j]=1;
            }
        }
        bricksWidth=540/col;
        bricksHeight=150/row;
    }
    public void draw(Graphics2D g){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    g.setColor(Color.red); // sets the red color for the map
                    g.fillRect(j*bricksWidth+80,i*bricksHeight+50,bricksWidth,bricksHeight); // fills the color in the rectangle map
                    g.setStroke(new BasicStroke(3)); // creates & sets a new BasicStroke with some defined width for Outline
                    g.setColor(Color.black); // sets the color for outline
                    g.drawRect(j*bricksWidth+80,i*bricksHeight+50,bricksWidth,bricksHeight); // Rectangles are drawn on the map using outline
                }
            }
        }
    }
    public void setBricksValue(int value,int row,int col){
        map[row][col]=value;
    }
}
