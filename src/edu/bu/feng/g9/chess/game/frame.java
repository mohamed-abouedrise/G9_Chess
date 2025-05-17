package edu.bu.feng.g9.chess.game;

import javax.swing.JFrame;

public class frame extends JFrame{
    
    panel panel;

    public frame(){
        panel = new panel();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);        
        this.setLayout(null);
        this.setVisible(true);
    }
    
}