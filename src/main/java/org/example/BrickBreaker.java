package org.example;

import javax.swing.JFrame;

public class BrickBreaker {
    public static void main(String[] args) {
        JFrame obj=new JFrame(); // creates a new Frame
        GamePlay gameplay=new GamePlay(); // a new class to implement the game
        obj.setSize(700,600); // sets the dimension of Frame
        obj.setTitle("BrickBreaker Game"); // sets the Title to display for the Frame
        obj.setLocationRelativeTo(null); // the null value makes the element to appear in the center of the Frame/Window/Screen
        obj.setResizable(false); // the Frame can't be resized if set to false
        obj.setVisible(true); // the Frame will be visible
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Frame gets turned off on clicking exit
        obj.add(gameplay); //adds the gameplay class to implement the game
    }
}