package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Console;
import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    
    int margin = 5;
    int prefferedWidth = 0;
    int prefferedHeight = 0;
    CollisionDomain collisionDomain;
    private LineComponent line;
    ArrayList<Vector2> midPos;
    int tempDraggedShape;
    
    public Canvas() {
        line = new LineComponent(0,0,0,0);
        addMouseListener(new MouseListener() {

          @Override
          public void mouseClicked(MouseEvent e) { }

          @Override
          public void mousePressed(MouseEvent e) {  
            loadMidPosTable();
            line.changePos(e.getX(), e.getY(), e.getX(), e.getY());
            findMidPos(e.getX(), e.getY());
            repaint();
          }

          @Override
          public void mouseReleased(MouseEvent e) { 
            line.changePos(0,0,0,0);
            moveShape(e.getX(), e.getY());
            repaint();
          }

          @Override
          public void mouseEntered(MouseEvent e) { }

          @Override
          public void mouseExited(MouseEvent e) { }

        });
        addMouseMotionListener(new MouseMotionListener() {

          @Override
          public void mouseDragged(MouseEvent e) {            
            line.changePos(e.getX(), e.getY());
            repaint();
            //setLocation(myX + deltaX, myY + deltaY);
          }

          @Override
          public void mouseMoved(MouseEvent e) { }

        });
    }
    
    public void loadMidPosTable() {
        midPos = new ArrayList<>();
        for (int i=0; i < collisionDomain.getShapeCount(); ++i) {
            Shape sTemp = collisionDomain.getShape(i);
            midPos.add(sTemp.getPosition());
        }
    }
    
    public void findMidPos(int x, int y) {
        tempDraggedShape = -1;
        for (int i = 0; i < midPos.size(); ++i) {
            if (abs(midPos.get(i).x - x) < 4 && abs(midPos.get(i).y - y) < 4) {
                tempDraggedShape = i;
            }
        }
        
    }
    
    public void moveShape(int x, int y) {
        if (tempDraggedShape > -1) collisionDomain.updateShape(tempDraggedShape, new Vector2(x, y));
    }
    
    public void setDomain(CollisionDomain domain) {
        collisionDomain = domain;
    }
    
    public int getPrefferedWidth() {
        return prefferedWidth;
    }
    
    public int getPrefferedHeight() {
        return prefferedHeight;
    }
    
    public void recalculateSize() {
        
        prefferedWidth = 0;
        prefferedHeight = 0;
        
        for(int i=0; i<collisionDomain.getShapeCount(); i++) {
            Shape shape = collisionDomain.getShape(i);
            for(LineSegment ls :shape.getPolyLine()) {
                
                if((int)ls.left.x > prefferedWidth)
                    prefferedWidth = (int)ls.left.x;
                if((int)ls.right.x > prefferedWidth)
                    prefferedWidth = (int)ls.right.x;
                
                if((int)ls.left.y > prefferedHeight)
                    prefferedHeight = (int)ls.left.y;
                if((int)ls.right.y > prefferedHeight)
                    prefferedHeight = (int)ls.right.y;
                
            }
        }
        
        prefferedWidth += margin;
        prefferedHeight += margin;
    }
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(collisionDomain != null) {
            //Cleaning
            g.setColor(Color.white);
            g.clearRect(0, 0, prefferedWidth, prefferedHeight);


            //Shapes
            for(int i=0; i<collisionDomain.getShapeCount(); i++) {
                Shape shape = collisionDomain.getShape(i);
                g.setColor(shape.getColor());
                for(LineSegment ls :shape.getPolyLine()) {
                    g.drawLine((int)Math.round(ls.left.x), (int)Math.round(ls.left.y), (int)Math.round(ls.right.x), (int)Math.round(ls.right.y));
                }
                g.fillRect((int)Math.round(shape.getPosition().x)-2, (int)Math.round(shape.getPosition().y)-2, 4, 4);
            }

            //Collisions
            g.setColor(Color.red);
            for(CollisionEdge ce : collisionDomain.getCollisionEdges()) {
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
            
            g.setColor(Color.YELLOW);
            g.drawLine(line.x1, line.y1, line.x2, line.y2);
        }
    }
    
    
}