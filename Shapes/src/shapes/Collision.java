package shapes;

import java.util.ArrayList;

public class Collision {
    
    Shape shape1;
    Shape shape2;
    
    public Collision(Shape newShape1, Shape newShape2) {
        edges = new ArrayList<CollisionEdge>();
        shape1 = newShape1;
        shape2 = newShape2;
        
    }
    
    ArrayList<CollisionEdge> edges;
 
    public boolean hasEdges() {
        return edges.size() > 0;
    }
    
    public void addCollisionEdge(CollisionEdge ce) {
        edges.add(ce);
    }
    
    public String getDesctiption(Shape shape) {
        String otherShapeName;
        if(shape == shape1) {
            otherShapeName = shape2.getName();
        } else {
            otherShapeName = shape1.getName();
        }
        
        String description = "";
        if(edges.size() > 1) {
            description += "Intersection: "+otherShapeName+":\n";
        } else {
            description += "tangential: "+otherShapeName+":\n";
        }
        
        return description;
    }
    
    public boolean concerns(Shape shape) {
        return (shape1 == shape || shape2 == shape);
    }
    
}
