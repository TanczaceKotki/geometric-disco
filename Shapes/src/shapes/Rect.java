package shapes;

import static shapes.Ellipse.counter;

public class Rect extends Shape {
    
    static int counter = 0;
    double width, height;

    public Rect(double newWidth, double newHeight) {
        super();
        counter++;
        name = "Rect "+counter;
        width = newWidth;
        height = newHeight;
        position = new Vector2(0.0, 0.0);
        rotation = 0.0;
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
        if(testPoint.x <= width/2 && testPoint.x >= -width/2) {
            if(testPoint.y <= height/2 && testPoint.y >= -height/2) {
                return true;
            }
        }
        return false;
    }
}
