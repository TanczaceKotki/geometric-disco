package shapes;

import java.awt.Color;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;

public abstract class Shape {
    
    String name = "";
    Vector2 position;
    Color color;
    double rotation;
    ArrayList<LineSegment> polyLine;
    ArrayList<ShapeObserver> observers;
    boolean dirty = true;
    
    public Shape() {
        polyLine = new ArrayList<LineSegment>();
        observers = new ArrayList<ShapeObserver>();
        color = Shape.getRandomColor();
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
    
    public Color getColor() {
        return color;
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
    
    static Random random = new Random();
    public static Color getRandomColor() {
        float hue = random.nextFloat();
        float saturation = 0.9f;
        float luminance = 0.4f;
        Color color = Color.getHSBColor(hue, saturation, luminance);
        return color;
    }
    
    
}
