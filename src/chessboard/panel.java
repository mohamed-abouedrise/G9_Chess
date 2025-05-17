/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chessboard;

import java.awt.Color;
import static java.awt.Color.green;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class panel extends JPanel {
    
//    int xplus=50;
//    int yplus=50;
//    int x=0;
//    int y=0;
//              
    Image whiteking;
    Image whitequeen;
    Image whitebishob;
    Image whiteknight;
    Image whitepawn;
    Image whiterrock;
    Image blackking;
    Image blackqueen;
    Image blackbishob;
    Image blackknight;
    Image blackpawn;
    Image blackrrock;
  
    public panel(){
        
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
    
    public void paint (Graphics g){

        Graphics2D g2d=(Graphics2D) g;
        
        whiteking=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_klt45.svg.png").getImage();
        whitequeen=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_qlt45.svg.png").getImage();
        whitebishob=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_blt45.svg.png").getImage();
        whiteknight=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_nlt45.svg.png").getImage();
        whitepawn=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_plt45.svg.png").getImage();
        whiterrock=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_rlt45.svg.png").getImage();
        blackking=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_fdt45.svg.png").getImage();
        blackqueen=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_gdt45.svg.png").getImage();
        blackbishob=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_Bdt45.svg.png").getImage();
        blackknight=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_Ndt45.svg.png").getImage();
        blackpawn=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_hdt45.svg.png").getImage();
        blackrrock=new ImageIcon("D:\\java\\projects\\chessBoard\\pieces\\Chess_mdt45.svg.png").getImage();


        g2d.setPaint(green);

        int i,n;
        
        for(i=0;i<8;i++){
            for(n=0;n<4;n++){

            g2d.fillRect(n*150+75*((i+1)%2),i*75, 75, 75);
//            g2d.fillRect(50+i*100,n*100+50, 50, 50);
           


            }
            
            
        }
        
        
            g2d.setPaint(white);
        for(i=0;i<8;i++){
            for(n=0;n<4;n++){

            g2d.fillRect(n*150+75*((i)%2),i*75, 75, 75);
//            g2d.fillRect(50+i*100,n*100+50, 50, 50);

            }
            
            
        }
        g2d.setFont(new Font("Times New Roman",Font.PLAIN,20));
        g2d.setPaint(Color.black);
        g2d.drawString("8", 0, 20);
        
            
        g2d.setPaint(Color.black);
        g2d.drawString("7", 0, 95);
           
        g2d.setPaint(Color.black);
        g2d.drawString("6", 0, 170);
              
        g2d.setPaint(Color.black);
        g2d.drawString("5", 0, 245);     
        
        g2d.setPaint(Color.black);
        g2d.drawString("4", 0, 320);
              
        g2d.setPaint(Color.black);
        g2d.drawString("3", 0, 395);
        
             
        g2d.setPaint(Color.black);
        g2d.drawString("2", 0, 470);
        
            
        g2d.setPaint(Color.black);
        g2d.drawString("1", 0, 545);
        
             
        g2d.setPaint(Color.black);
        g2d.drawString("a", 60, 590);
        
           
        g2d.setPaint(Color.black);
        g2d.drawString("b", 135, 590);
        
            
        g2d.setPaint(Color.black);
        g2d.drawString("c", 210, 590);
        
             
        g2d.setPaint(Color.black);
        g2d.drawString("d", 285, 590);
        
            
        g2d.setPaint(Color.black);
        g2d.drawString("e", 360, 590);
        
        
             
        g2d.setPaint(Color.black);
        g2d.drawString("f", 435, 590);
        
             
        g2d.setPaint(Color.black);
        g2d.drawString("g", 510, 590);
        
        
             
        g2d.setPaint(Color.black);
        g2d.drawString("h", 585, 590);
        
        
//        
//        
//        
//        g2d.drawImage(whiteking, 303, 535, 69, 69, null);
//        g2d.drawImage(whitequeen, 303-75, 528, 69, 69, null);
//        g2d.drawImage(whitebishob, 303+75, 528, 69, 69, null);   
//        g2d.drawImage(whitebishob, 303-150, 528, 69, 69, null);
//        g2d.drawImage(whiteknight, 303-225, 528, 69, 69, null);        
//        g2d.drawImage(whiteknight, 303+150, 528, 69, 69, null);
//        g2d.drawImage(whiterrock, 303+225, 528, 69, 69, null);
//        g2d.drawImage(whiterrock, 303-300, 528, 69, 69, null);
//        
//        
//        g2d.drawImage(whitepawn, 303, 528-75, 69, 69, null);        
//        g2d.drawImage(whitepawn, 303-75, 528-75, 69, 69, null);
//        g2d.drawImage(whitepawn, 303+75, 528-75, 69, 69, null);
//        g2d.drawImage(whitepawn, 303-150, 528-75, 69, 69, null);
//        g2d.drawImage(whitepawn, 303-225, 528-75, 69, 69, null);        
//        g2d.drawImage(whitepawn, 303-300, 528-75, 69, 69, null);
//        g2d.drawImage(whitepawn, 303+150, 528-75, 69, 69, null);
//        g2d.drawImage(whitepawn, 303+225, 528-75, 69, 69, null);                 
//        
//
//        g2d.drawImage(blackking, 303, 528-7*75, 69, 64, null);
//        g2d.drawImage(blackqueen, 303-75, 528-7*75, 69, 69, null);
//        g2d.drawImage(blackbishob, 303+75, 528-7*75, 69, 69, null);   
//        g2d.drawImage(blackbishob, 303-150, 528-7*75, 69, 69, null);
//        g2d.drawImage(blackknight, 303-225, 528-7*75, 69, 69, null);        
//        g2d.drawImage(blackknight, 303+150, 528-7*75, 69, 69, null);
//        g2d.drawImage(blackrrock, 303+225, 528-7*75, 69, 69, null);
//        g2d.drawImage(blackrrock, 303-300, 528-7*75, 69, 69, null);
//        
//        
//        g2d.drawImage(blackpawn, 303, 528-6*75, 69, 69, null);        
//        g2d.drawImage(blackpawn, 303-75, 528-6*75, 69, 69, null);
//        g2d.drawImage(blackpawn, 303+75, 528-6*75, 69, 69, null);
//        g2d.drawImage(blackpawn, 303-150, 528-6*75, 69, 69, null);
//        g2d.drawImage(blackpawn, 303-225, 528-6*75, 69, 69, null);        
//        g2d.drawImage(blackpawn, 303-300, 528-6*75, 69, 69, null);
//        g2d.drawImage(blackpawn, 303+150, 528-6*75, 69, 69, null);
//        g2d.drawImage(blackpawn, 303+225, 528-6*75, 69, 69, null);           
//        
//        
        
        
        
        for (int y=0;y<8;y++){
            
            for (int x=0;x<8;x++){
                
                g2d.drawImage(whiteking, 3+75*x, 528-75*y, 69, 69, null);
                
                
                
                
                
            }
                    
            
            
            
            
        }
        
        
        
        
        
        
        
        
        
    }
            
    
}
