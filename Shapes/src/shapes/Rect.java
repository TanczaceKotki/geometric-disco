package shapes;

import java.util.ArrayList;
import static shapes.Ellipse.counter;

public class Rect extends Shape {
    
    static int counter = 0;
    double width, height;

    public Rect(ArrayList<Vector2> pixels, Vector2 center) {
       
       super();
       assignName();
       
       Vector2 negativeCenter = Vector2.diff(Vector2.zero(), center);
       for(Vector2 pixel : pixels) {
           pixel.translate(negativeCenter);
       }
       
       
       double step = Math.PI / 180.0;
       double minDist = Double.MAX_VALUE;
       
       double minDistAngle = 0.0;
       double angle;
       
       for(int i=0; i<360; i++) {
           angle = i*step;
           
           double minX = Double.MAX_VALUE;
           double maxX = -Double.MAX_VALUE;
           
           for(Vector2 pixel : pixels) {
               Vector2 px = new Vector2(pixel.x, pixel.y);
               px.rotate(angle);
               if(px.x < minX)
                   minX = px.x;
               if(px.x > maxX)
                   maxX = px.x;
           }
           
           double dist = maxX - minX;
           if(dist < minDist) {
               minDist = dist;
               minDistAngle = angle;
           }
       }
       
       
       double flipMinX = Double.MAX_VALUE;
       double flipMaxX = -Double.MAX_VALUE;
       
       for(Vector2 pixel : pixels) {
            Vector2 px = new Vector2(pixel.x, pixel.y);
            px.rotate(minDistAngle + Math.PI / 2.0);
            if(px.x < flipMinX)
                flipMinX = px.x;
            if(px.x > flipMaxX)
                flipMaxX = px.x;
           }
       
       this.width = minDist;
       this.height = flipMaxX - flipMinX;
       this.rotation = minDistAngle;
       this.position = center;
       
    }

    
    public Rect(double newWidth, double newHeight) {
        super();
        assignName();
        
        width = newWidth;
        height = newHeight;
        position = new Vector2(0.0, 0.0);
        rotation = 0.0;
    }
    
    void assignName() {
        counter++;
        name = "Rect "+counter;
    }
    
    public double getWidth() {return width;}
    public void setWidth(double newWidth) {
        width = newWidth;
        notifyGeometryModif();
    }
    public double getHeight() {return height;}
    public void setHeight(double newHeight) {
        height = newHeight;
        notifyGeometryModif();
    }
    
    @Override
    String getDescription() {
        return "Rect("+width+", "+height+")";
    }

    @Override
    void recalculatePolyLine() {
        
        polyLine.clear();
        
        Vector2[] corners = new Vector2[4];
        corners[0] = new Vector2(-width/2, -height/2);
        corners[1] = new Vector2(-width/2, height/2);
        corners[2] = new Vector2(width/2, height/2);
        corners[3] = new Vector2(width/2, -height/2);
        
        
        
        for(Vector2 corner : corners) {
            corner.rotate(rotation);
            corner.translate(position);
        }
        
        
        polyLine.add(new LineSegment(corners[0], corners[1]));
        polyLine.add(new LineSegment(corners[1], corners[2]));
        polyLine.add(new LineSegment(corners[2], corners[3]));
        polyLine.add(new LineSegment(corners[3], corners[0]));
        
    }

    @Override
    public boolean insideTest(Vector2 point) {
        Vector2 testPoint = new Vector2(point.x, point.y);
        testPoint.translate(new Vector2(-position.x, -position.y));
        testPoint.rotate(-rotation);
        
        if(testPoint.x <= width/2 && testPoint.x >= -width/2) {
            if(testPoint.y <= height/2 && testPoint.y >= -height/2) {
                return true;
            }
        }
        return false;
    }
}
