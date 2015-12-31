package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    
    public Canvas() {
        lines = new ArrayList<LineSegment>();
        collisionEdges = new ArrayList<CollisionEdge>();
    }
    
    int prefferedWidth = 0;
    int prefferedHeight = 0;
    ArrayList<LineSegment> lines;
    ArrayList<CollisionEdge> collisionEdges;
    
    public void setLines( ArrayList<LineSegment> newLines) {
        lines = newLines;
        prefferedWidth = 0;
        prefferedHeight = 0;
        for(LineSegment ls : lines) {
            
            if(ls.left.x > prefferedWidth)
                prefferedWidth = (int)ls.left.x;
            if(ls.right.x > prefferedWidth)
                prefferedWidth = (int)ls.right.x;
            
            if(ls.left.y > prefferedHeight)
                prefferedHeight = (int)ls.left.y;
            if(ls.right.y > prefferedHeight)
                prefferedHeight = (int)ls.right.y;

        }
        prefferedWidth += 5;
        prefferedHeight += 5;
    }
    
    public void setCollisiondata(ArrayList<CollisionEdge> newCollisionEdges) {
        collisionEdges = newCollisionEdges;
    }
    
    public int getPrefferedWidth() {
        return prefferedWidth;
    }
    
    public int getPrefferedHeight() {
        return prefferedHeight;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.white);
        g.clearRect(0, 0, prefferedWidth, prefferedHeight);
        g.setColor(Color.black);
 
        for(LineSegment ls : lines) {
            g.drawLine((int)Math.round(ls.left.x), (int)Math.round(ls.left.y), (int)Math.round(ls.right.x), (int)Math.round(ls.right.y));
        }
        g.setColor(Color.red);
        for(CollisionEdge ce : collisionEdges) {
            Vector2 pref = null;            
            for(Vector2 point : ce.getPoints()) {
                if(ce.getPointCount()==1)
                    g.fillOval((int)Math.round(point.x)-4, (int)Math.round(point.y)-4, 8, 8);
                if(pref != null) {
                    g.drawLine((int)Math.round(point.x), (int)Math.round(point.y), (int)Math.round(pref.x), (int)Math.round(pref.y));
                } else {
                    pref = point;
                }
            } 
        }
    }
}