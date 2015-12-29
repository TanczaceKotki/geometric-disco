package shapes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    
    public Canvas() {
        lines = new ArrayList<LineSegment>();
        
    }
    int prefferedWidth = 0;
    int prefferedHeight = 0;
    ArrayList<LineSegment> lines;
    
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
    
    public int getPrefferedWidth() {
        return prefferedWidth;
    }
    
    public int getPrefferedHeight() {
        return prefferedHeight;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Update preffered size
        
        
        g.clearRect(0, 0, prefferedWidth, prefferedHeight);
        for(LineSegment ls : lines) {
            g.drawLine((int)Math.round(ls.left.x), (int)Math.round(ls.left.y), (int)Math.round(ls.right.x), (int)Math.round(ls.right.y));
        }

    }
}