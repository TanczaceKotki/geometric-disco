package shapes;

import java.util.ArrayList;

public class Collision {
    
    Shape shape1;
    Shape shape2;
    Shape included = null;
    public boolean overlap = false;
    
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
    
    public void setOverlapping(boolean newOverlap) {
        overlap = newOverlap;
    }
    
    public String getDesctiption(Shape shape) {
        Shape otherShape;
        String otherShapeName;
        if(shape == shape1) {
            otherShape = shape2;
        } else {
            otherShape = shape1;
        }
        otherShapeName = otherShape.getName();
        String description = "";
        
        if(overlap) {
            description += "Overlaps: "+otherShapeName;
            
        } else if(included != null) {
            if(included == otherShape) {
                description += "Contains: "+otherShape.name;
            } else {
                description += "Contained in: "+otherShape.name;
            }
            
        } else {
        
            ArrayList<CollisionEdge> filtered = getFilteredEdges();

            
            if(filtered.size() > 1) {
                description += "Intersection with "+otherShapeName+":\n";
                for(CollisionEdge edge : filtered) {
                    description += edge.getDescription() + "\n";
                }

            } else {
                if(filtered.size()>0) {
                    description += "tangential with "+otherShapeName+":\n";
                    description += filtered.get(0).getDescription();
                }
            }

        }
        
        description += "\n";
        return description;
    }
    
    public ArrayList<CollisionEdge> getFilteredEdges() {
        
        //Removing single points if they are already included in segments
        ArrayList<CollisionEdge> filtered = new ArrayList<CollisionEdge>();
        for(CollisionEdge edge : edges) {
            if(edge.getPointCount() > 1)
                filtered.add(edge);
        }
        
        for(CollisionEdge edge : edges) {
            if(edge.getPointCount() == 1) {
                boolean add = true;
                for(CollisionEdge e : filtered) {
                    for(Vector2 v : e.getPoints()) {
                        if(v.equals(edge.getPoints()[0]))
                            add = false;
                    }
                }
                if(add)
                   filtered.add(edge);
            }
        }
        return filtered;
    }
    
    public boolean concerns(Shape shape) {
        return (shape1 == shape || shape2 == shape);
    }
    
}
