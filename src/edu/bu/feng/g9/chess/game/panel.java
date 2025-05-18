package edu.bu.feng.g9.chess.game;

import java.awt.Color;
import static java.awt.Color.green;
import static java.awt.Color.white;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class panel extends JPanel implements MouseListener,MouseMotionListener{

    private Board board = Board.defaultBoard();
    
        private Point startingPoint;
        private Point currentPoint;

        
        
    
    public panel(){
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void paint (Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(green);

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 4; n++) {
                g2d.fillRect(n * 150 + 75 * ((i + 1) % 2), i * 75, 75, 75);
            }
        }
        g2d.setPaint(white);

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 4; n++) {
                g2d.fillRect(n * 150 + 75 * ((i) % 2), i * 75, 75, 75);
            }
        }

        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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

        
        
        
        
        
        
        
        
        if (currentPoint!=null){
            
            int x=(int) (startingPoint.getX()/75);        
            int y=7-(int) (startingPoint.getY()/75);

                if(this.board.isWhiteKing(x, y))
                    g2d.drawImage(this.board.getPiece(x, y), (int) currentPoint.getX()-34, (int)currentPoint.getY()-32, 69, 64, null);
                else if(this.board.isBlackKing(x, y))
                    g2d.drawImage(this.board.getPiece(x, y), (int) currentPoint.getX()-34, (int)currentPoint.getY()-32, 69, 64, null);
                else
                    g2d.drawImage(this.board.getPiece(x, y), (int) currentPoint.getX()-34, (int)currentPoint.getY()-34, 69, 69, null);            
        }
        
        
        
        
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
               
                if (currentPoint!=null  && startingPoint!=null && x==(int) (startingPoint.getX()/75) && y==7-(int) (startingPoint.getY()/75)){
                    
                    continue;
                    
                    
                    
                }
                
               
                
                if(this.board.isWhiteKing(x, y))
                    g2d.drawImage(this.board.getPiece(x, y), 3 + 75 * x, 535 - 75 * y, 69, 64, null);
                else if(this.board.isBlackKing(x, y))
                    g2d.drawImage(this.board.getPiece(x, y), 3 + 75 * x, 528 - 75 * y, 69, 64, null);
                else
                    g2d.drawImage(this.board.getPiece(x, y), 3 + 75 * x, 528 - 75 * y, 69, 69, null);
            }
        }
        
        
        
        this.getParent().repaint();
        
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

        startingPoint=e.getPoint();
        
        

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        currentPoint=null;
        repaint();
        
        
        }

    @Override
    public void mouseEntered(MouseEvent e) {



    }

    @Override
    public void mouseExited(MouseEvent e) {



    }

    @Override
    public void mouseDragged(MouseEvent e) {

        currentPoint =e.getPoint();
        repaint();
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {




    }
    
}
