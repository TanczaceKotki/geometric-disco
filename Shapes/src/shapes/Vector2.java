package shapes;

public class Vector2 implements CollisionEdge {
    
    public double x;
    public double y;
    
    public Vector2(double newX, double newY) {
        x = newX;
        y = newY;
    }
    
    public void rotate(double angle) {
        
        double tmpX = x;
        double tmpY = y;
        x = tmpX * Math.cos(angle) - tmpY * Math.sin(angle);
        y = tmpX * Math.sin(angle) + tmpY * Math.cos(angle);
        
    }
    
    
    public void translate(Vector2 vector) {
        this.x += vector.x;
        this.y += vector.y;
    }
    

    
    public String toString() {
        return "("+x+", "+y+")";
    }
    
    public static Vector2 diff(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }
    
    public static Vector2 sum(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }
    
    public static Vector2 multiply(Vector2 v, double scalar) {
        return new Vector2(v.x * scalar, v.y * scalar);
    }
    
    public static double crossProduct(Vector2 v1, Vector2 v2) {
        return v1.x*v2.y - v1.y*v2.x;
    }
    
    public static double dotProduct(Vector2 v1, Vector2 v2) {
        return v1.x*v2.x + v1.y*v2.y;
    }


    @Override
    public String getDescription() {
        return "Punkt: "+this.toString();
    }
    
    
}
