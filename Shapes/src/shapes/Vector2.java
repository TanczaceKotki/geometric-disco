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
    
    public void normalize() {
        double len = length();
        this.x = this.x / len;
        this.y = this.y / len;
    }
    
    public String toString() {
        return "("+String.format("%.2f", x) +", "+String.format("%.2f", y) +")";
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

    public static Vector2 zero() {
        return new Vector2(0.0, 0.0);
    }
    
    @Override
    public String getDescription() {
        return "Point: "+this.toString();
    }

    @Override
    public Vector2[] getPoints() {
        Vector2[] points = new Vector2[1];
        points[0] = this;
        return  points;
    }
    
    public boolean equals(Vector2 other) {
        if(x == other.x && y == other.y) {
            return true;
        } else {
            return false;
        }
    }
    
    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    @Override
    public int getPointCount() {
        return 1;
    }
    
}
