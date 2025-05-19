/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.bu.feng.g9.chess.game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;


public class button2 extends JButton {

    public button2() {
                JButton pauseButton = new JButton();
                
                                this.setOpaque(true);
                this.setText("Pause");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBackground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBounds(850, 220, 300, 60);
    }
    
    
    
}
