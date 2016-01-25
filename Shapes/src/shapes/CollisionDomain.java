package shapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class CollisionDomain implements ShapeObserver {
    
    ArrayList<Shape> shapes;
    ArrayList<Collision> collisions;
    
    public CollisionDomain() {
        shapes = new ArrayList<Shape>();
        collisions = new ArrayList<Collision>();
    }

    
    public Shape getShape(int index) {
        return shapes.get(index);
    }
    
    public void addShape(Shape shape) {
        shape.registerObserver(this);        
        shapes.add(shape);
        shape.notifyGeometryModif();
    }
    
    public void updateShape(int index, String name, Vector2 position, double rotation) {
        Shape shape = shapes.get(index);
        shape.setName(name);
        shape.setPosition(position);
        shape.setRotation(rotation);
    }
    
    public int getShapeCount() {
        return shapes.size();
    }    

    
    Collision collisionCheck(Shape shape1, Shape shape2) {
        
        Collision collision = new Collision(shape1, shape2);

        for(LineSegment ls1 : shape1.getPolyLine()) {
            for(LineSegment ls2 : shape2.getPolyLine()) {
                CollisionEdge ce = ls1.intersects(ls2);
                if(ce != null) {
                    collision.addCollisionEdge(ce);
                } 
            }
        }
        
        boolean onlySegments = true;
        ArrayList<CollisionEdge> filtered = collision.getFilteredEdges();
        for(CollisionEdge ce : filtered) {
            if(ce.getPointCount() == 1)
                onlySegments = false;
        }
        
        if(onlySegments) {
            if(shape1.getPolyLine().size() == shape2.getPolyLine().size()) {
                if(shape1.getPolyLine().size() == filtered.size()) {
                    collision.setOverlapping(true);
                }
            }
        }
        
        if(collision.hasEdges()) {
            return collision;
        } else {
            if(shape1.insideTest(shape2.getPolyLine().get(0).left)) {
                collision.included = shape2;
                return collision;
            } else if(shape2.insideTest(shape1.getPolyLine().get(0).left)) {
                collision.included = shape1;
                return collision;
            }
            return null;
        }
    }
    
    
    ArrayList<Collision> getCollisions(Shape shape) {
        ArrayList<Collision> result = new ArrayList<Collision>();
        for(Collision collision : collisions) {
            if(collision.concerns(shape))
                result.add(collision);
        }
        return result;
    }

    ArrayList<CollisionEdge> getCollisionEdges() {
        ArrayList<CollisionEdge> edges = new ArrayList<CollisionEdge>();
        for(Collision collision : collisions) {
            for(CollisionEdge edge : collision.getFilteredEdges()) {
                edges.add(edge);
            }
        }
        return edges;
    }
      
    
    public String getCollisionsDesc(Shape shape) {
        String result = "";
        for(Collision collision : getCollisions(shape)) {
            result += collision.getDesctiption(shape);
        }
        return result;
    }
    
    @Override
    public void onModify(Shape shape) {
        collisions.removeIf(collision -> collision.concerns(shape));
        for(Shape sh : shapes) {
            if(sh != shape) {
                Collision collision = collisionCheck(shape, sh);
                if(collision != null) {
                    collisions.add(collision);
                }
            }
        }
        
    }
    
    
}
