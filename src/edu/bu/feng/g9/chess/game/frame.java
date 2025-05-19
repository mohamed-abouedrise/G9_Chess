package edu.bu.feng.g9.chess.game;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class frame extends JFrame{
    
    panel panel;
    JLabel clock,clock2;
        button1 newGameButton;
    button2 pauseButton;
    buttonsitting sittingButton;
    public frame(){
        clock = new clock();
        clock2 = new clock2();
        panel = new panel();
        pauseButton=new button2();
        sittingButton=new buttonsitting();
        newGameButton=new button1();
        this.getContentPane().setBackground(new Color(0x252321));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        panel.setLocation(0, 50);
        this.add(panel);
        this.add(clock);
        this.add(clock2);
        this.add(newGameButton);
        this.add(pauseButton);
        this.add(sittingButton);
        this.setSize(2000, 750);
        this.setLocationRelativeTo(null);        
        this.setVisible(true);
    }
    
}