package shapes;

public class LineSegment implements CollisionEdge {
    
    Vector2 left, right;
    
    public LineSegment(Vector2 p1, Vector2 p2) {
        if(p1.x < p2.x) {
            left = p1;
            right = p2;
        } else if(p1.x > p2.x) {
            left = p2;
            right = p1;
        } else {
            if((p1.y < p2.y)) {
                left = p1;
                right = p2;
            } else {
                left = p2;
                right = p1;
            }
        }
    }
    
    double clampNormal(double value) {
        if(value < 0.0) {
            return 0.0;
        } else if(value > 1.0) {
            return 1.0;
        }
        return value;
    }
    
    public CollisionEdge intersects(LineSegment other) {
        
        Vector2 p = this.left;
        Vector2 r = Vector2.diff(right, p);
        Vector2 q = other.left;
        Vector2 s = Vector2.diff(other.right, q);
        Vector2 qmp = Vector2.diff(q, p);
        
        //Crossing each other
        double rxs = Vector2.crossProduct(r, s);
        if(rxs != 0.0) {
            double t = Vector2.crossProduct(qmp, s) / rxs;
            double u = Vector2.crossProduct(qmp, r) / rxs;
            if(t >= 0.0 & t <= 1.0 & u >= 0.0 & u <= 1.0) {
                return Vector2.sum(p, Vector2.multiply(r, t));
            }
        } else {
            double qmpxr = Vector2.crossProduct(qmp, r);
            //Colinear
            if (qmpxr == 0.0) {
                double t0 = Vector2.dotProduct(qmp, r) / Vector2.dotProduct(r, r);
                double t1 = Vector2.dotProduct(Vector2.sum(qmp, s), r) / Vector2.dotProduct(r, r);
                
                boolean overlap_t0 = false;
                boolean overlap_t1 = false;
                
                if(t0 >= 0.0 & t0 <=1.0) {
                    overlap_t0 = true;
                } else {
                    t0 = clampNormal(t0);
                }
                if(t1 >= 0.0 & t1 <=1.0) {
                    overlap_t1 = true;
                } else {
                    t1 = clampNormal(t1);
                }
                
                if(overlap_t0 || overlap_t1) {
                    return new LineSegment(Vector2.sum(p, Vector2.multiply(r, t0)), Vector2.sum(p, Vector2.multiply(r, t1)));
                }
                
            }
        }
        
        //Not intersecting
        return null;
    }

    @Override
    public String getDescription() {
        return "Odcinek: "+left.toString()+", "+right.toString();
    }
    
}
