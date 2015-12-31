package shapes;

import java.util.ArrayList;

public abstract class Shape {
    
    String name = "";
    Vector2 position;
    double rotation;
    ArrayList<LineSegment> polyLine;
    ArrayList<ShapeObserver> observers;
    boolean dirty = true;
    
    public Shape() {
        polyLine = new ArrayList<LineSegment>();
        observers = new ArrayList<ShapeObserver>();
    }
    
    //Observers
    public void registerObserver(ShapeObserver observer) {
        observers.add(observer);
    }
    public void unregisterObserver(ShapeObserver observer) {
        observers.remove(observer);
    }  
    
    //Meta data
    abstract String getDescription();
    public String getName() {return name;}
    public void setName(String newName) {name = newName;}
    
    //Geometry
    public ArrayList<LineSegment> getPolyLine() {
        if(dirty) {
            recalculatePolyLine();
            dirty = false;
        }
        return polyLine;
    }
    public Vector2 getPosition() {return position;}
    public void setPosition(Vector2 newPosition) {
        position = newPosition;
        notifyGeometryModif();
    }
    public double getRotation() {return rotation;}
    public void setRotation(double newRotation) {
        rotation = newRotation;
        notifyGeometryModif();
    }
    public void setEulerRotation(double eulerAngle) {
        rotation = eulerAngle * Math.PI / 180.0;
    }
    
    abstract void recalculatePolyLine();
    
    void notifyGeometryModif() {
        dirty = true;
        callObservers();
    }
    
    public abstract boolean insideTest(Vector2 point);
    
    void callObservers() {
        for(ShapeObserver observer : observers) {
            observer.onModify(this);
        }
    }
    
}
