package shapes;


public class Ellipse extends Shape {
    
    static int counter = 0;
    double width, height;

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
        double scale = height/width;
        testPoint.x = testPoint.x * scale;
        if(testPoint.length() <= height) {
            return true;
        }
        return false;
        
    }
}
