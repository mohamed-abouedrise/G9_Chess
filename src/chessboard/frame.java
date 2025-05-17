/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessboard;

import java.awt.Dimension;
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
