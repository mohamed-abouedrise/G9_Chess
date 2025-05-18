/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.bu.feng.g9.chess.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class clock extends JLabel {

    public clock() {
        this.setBounds(0, 650, 150, 50);
        this.setFont(new Font("NEW TIMES ROMAN", TOP, 15));
//           this.setText("timer");
           
           
           
           this.setOpaque(true);
           this.setBackground(Color.LIGHT_GRAY);
           this.setForeground(Color.BLACK);
           this.setText("WHITE TIMER");
           
           
                  
           
           


    }
    
    
    
   
    
}
