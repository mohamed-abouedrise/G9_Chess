package edu.bu.feng.g9.chess.game;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class frame extends JFrame{
    
    panel panel;
    JLabel clock,clock2;
    public frame(){
        clock = new clock();
        clock2 = new clock2();
        panel = new panel();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        panel.setLocation(0, 50);
        this.add(panel);
        this.add(clock);
        this.add(clock2);
        this.setSize(2000, 750);
        this.setLocationRelativeTo(null);        
        this.setVisible(true);
    }
    
}