package com.testmicroservices.sample.sb;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Random;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;



public class Treasure {

    private final static int SIZE = 8;
    private final static int IMG_LENGTH = 80;

    public static void main(String[] args) throws IOException {

         //Create a basic JFrame window.
        JFrame frame = new JFrame("Treasure Hunt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit app when closing window
       
        //Load images.
        Image cellImage = ImageIO.read(new File("cell.png"));
        ImageIcon cellIcon = new ImageIcon(cellImage);
        Image treasureImage = ImageIO.read(new File("treasure.png"));
        ImageIcon treasureIcon = new ImageIcon(treasureImage);

        //Pick a random location for the treasure location.
        Random rnd = new Random();
        int spotX = rnd.nextInt(SIZE);
        int spotY = rnd.nextInt(SIZE);
       
        
        //Set the proper window size.
        Container ctr = frame.getContentPane();
        ctr.setPreferredSize(new Dimension(SIZE * IMG_LENGTH, SIZE * IMG_LENGTH));
        frame.pack(); // update window size

        //Create grid
        ctr.setLayout(new GridLayout(SIZE, SIZE));
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JLabel cell = new JLabel();
                cell.setIcon(cellIcon);
                cell.setHorizontalTextPosition(JLabel.CENTER);
                cell.setVerticalTextPosition(JLabel.CENTER);
                int dist = Math.abs(spotX - col) + Math.abs(spotY - row);
                cell.putClientProperty("dist", dist);
                ctr.add(cell);
            }
        }
        //Handle mouse click and determine the result
        ctr.addMouseListener(new MouseAdapter() {
            private boolean play = true;

            public void mousePressed(MouseEvent event) {
                if (play) {
                    Component comp = ctr.getComponentAt(event.getX(), event.getY());
                    if (comp instanceof JLabel) { // to be safe
                        JLabel cell = (JLabel)comp;
                        int dist = (Integer)cell.getClientProperty("dist");
                        if (dist == 0) {
                            play = false;
                            cell.setIcon(treasureIcon);
                             showMessageDialog(frame, "You found the treasure!");
                        } else {
                            cell.setText(String.valueOf(dist));
                        }
                    }
                    
                }
                
            }
            
        });
        
        
        frame.setVisible(true);
        
    }
         
         
}
