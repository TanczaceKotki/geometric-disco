package shapes;

import java.util.ArrayList;


public class Ellipse extends Shape {
    
   static int counter = 0;
   double width, height;
   
   public Ellipse(ArrayList<Vector2> pixels, Vector2 center) {
       
       super();
       counter++;
       name = "Ellipse "+counter;
      
       
       Vector2 negativeCenter = Vector2.diff(Vector2.zero(), center);
       for(Vector2 pixel : pixels) {
           pixel.translate(negativeCenter);
       }
       
       
       double step = Math.PI / 180.0;
       double minDist = Double.MAX_VALUE;
       double maxDist = -Double.MAX_VALUE;
       
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
            if(dist > maxDist) {
                maxDist = dist;
            }
       }
       
       this.width = minDist;
       this.height = maxDist;
       this.rotation = minDistAngle;
       this.position = center;
       
   }
   
   
   public Ellipse(double newWidth, double newHeight) {
        super();
        counter++;
        name = "Ellipse "+counter;
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
        return "Ellipse("+width+", "+height+")";
    }

    @Override
    void recalculatePolyLine() {
        
        polyLine.clear();
        
        double a = height / 2;
        double b = width / 2;
        Vector2[] points = new Vector2[360];
        
        for(int i=0; i<points.length; i++) {
            double fi = ((double)i / 180.0) * Math.PI;
            double r = (a*b) / (Math.sqrt( Math.pow((b*Math.cos(fi)), 2) + Math.pow((a*Math.sin(fi)), 2) ));
            Vector2 p = new Vector2(0, r);
            p.rotate(fi);
            p.rotate(rotation);
            p.translate(position);
            points[i] = p;
            
        }
        
        for(int i=0; i<points.length-1; i++) {
            polyLine.add(new LineSegment(points[i], points[i+1]));
        }
        polyLine.add(new LineSegment(points[points.length-1], points[0]));
        
        
    }

    @Override
    public boolean insideTest(Vector2 point) {
        Vector2 testPoint = new Vector2(point.x, point.y);
        testPoint.translate(new Vector2(-position.x, -position.y));
        testPoint.rotate(-rotation);
        
        double scale = height/width;
        testPoint.x = testPoint.x * scale;
        
        if(testPoint.length() <= height/2) {
            return true;
        }
        return false;
        
    }
}
