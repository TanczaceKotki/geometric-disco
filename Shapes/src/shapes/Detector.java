package shapes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;


public class Detector {

    final int alpha = -16777216;
    final int mask = 16777215;
    final int black = 0;
    final int white = 16777215;
    final int red = 16711680;
    final int green = 65280;
    final int blue = 255;
    Image img;
    List<Shape> shapes;

    private class Image{
        BufferedImage image;

        public Image(BufferedImage image){
            this.image = image;
        }

        public void setPixel(int x, int y, int val){
            image.setRGB(x, y, (val | alpha));
        }

        public void setPixel(Point p, int val){
            image.setRGB(p.x, p.y, (val | alpha));
        }

        public void setArea(Point p, int val, int size){
            for(int i = p.x - size; i<= p.x + size; ++i){
                for(int j = p.y - size; j<= p.y + size; ++j){
                    image.setRGB(i, j, (val | alpha));
                }
            }
        }

        public int getPixel(int x, int y){
            return (image.getRGB(x, y) & mask);
        }

        public int getPixel(Point p){
            return (image.getRGB(p.x, p.y) & mask);
        }
    }

    private double getAngle(Point previous, Point current){
        int x = current.x - previous.x;
        int y = current.y - previous.y;
        if(y >= 0){
            double tmp = Math.atan2(y, x);
            double angle = (tmp * (180 / Math.PI));
            //System.out.println(previous.x + "," + previous.y + " -> " + current.x + "," + current.y + " :" + angle);
            return angle;
        }else{
            double tmp = Math.atan2(-y, -x);
            double angle = (tmp * (180 / Math.PI)) + 180;
            //System.out.println(previous.x + "," + previous.y + " -> " + current.x + "," + current.y + " :" + angle);
            return angle;
        }
    }

    private double getLocalAngle(List<Point> points, int length){
        if(points.size() >= length){
            return getAngle(points.get(points.size() - 1), points.get(points.size() - length));
        }else{
            return getAngle(points.get(points.size() - 1), points.get(0));
        }
    }

    private double getLocalAngle(List<Point> points, Point nextPoint, int length){
        if(points.size() >= length){
            return getAngle(nextPoint, points.get(points.size() - length));
        }else{
            return getAngle(nextPoint, points.get(0));
        }
    }

    private double getDeltaAngle(List<Point> points, int length, int delta){
        double a = getLocalAngle(points, length);
        double b;
        if(points.size() >= length + delta){
            b = getAngle(points.get(points.size() - 1 - delta), points.get(points.size() - length - delta));
        }else{
            b = getAngle(points.get(points.size() - 1), points.get(0));
        }
        return ((a - b + 180) % 360 - 180);
    }

    private List<Point> getNearby(Point p){
        List<Point> nearby = new ArrayList<Point>();
        int x = p.x;
        int y = p.y;

        if(img.getPixel(x - 1, y - 1) != white){
            nearby.add(new Point(x - 1, y - 1));
        }
        if(img.getPixel(x - 1, y) != white){
            nearby.add(new Point(x - 1, y));
        }
        if(img.getPixel(x - 1, y + 1) != white){
            nearby.add(new Point(x - 1, y + 1));
        }

        if(img.getPixel(x, y - 1) != white){
            nearby.add(new Point(x, y - 1));
        }
        if(img.getPixel(x, y + 1) != white){
            nearby.add(new Point(x, y + 1));
        }

        if(img.getPixel(x + 1, y - 1) != white){
            nearby.add(new Point(x + 1, y - 1));
        }
        if(img.getPixel(x + 1, y) != white){
            nearby.add(new Point(x + 1, y));
        }
        if(img.getPixel(x + 1, y + 1) != white){
            nearby.add(new Point(x + 1, y + 1));
        }

        return nearby;
    }

    /*
    private void redraw(){
        img.image = new BufferedImage(getImage().getWidth(), getImage().getHeight(), BufferedImage.TYPE_INT_ARGB);
        for(Shape s : shapes){
            for(Point p : s.getPoints()){
                try {
                    img.setPixel(p, s.color);
                    img.setArea(s.getCenter(), s.color, 2);
                }catch(Exception e){

                }
            }
        }
    }
    */
    
    private void compute(BufferedImage image){
        Random random = new Random();
        random.setSeed(633478);
        List<Point> rawShapes = new ArrayList<Point>();
        int width = image.getWidth();
        int height = image.getHeight();
        img = new Image(image);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(img.getPixel(i,j) == black){
                    rawShapes.add(new Point(i,j));
                }
            }
        }

        for(Point point : rawShapes){
            if(img.getPixel(point) == black){
                List<Point> nearby = getNearby(point);
                Point nextPixel = null;
                for(Point p : nearby){
                    if(img.getPixel(p) == black){
                        nextPixel = p;
                    }
                }
                if(nextPixel == null){
                    continue;
                }
                int color = random.nextInt(16777213) + 1;
                
                img.setPixel(point, color);
                ArrayList<Point>points = new ArrayList<>();
                points.add(point);
                double angle;
                while(true){
                    Point currentPoint = nextPixel;
                    nearby = getNearby(currentPoint);
                    img.setPixel(currentPoint, color);
                    angle = getLocalAngle(points, 4);
                    double bestDiff = 9999;
                    int count = 0;
                    for(Point p : nearby){
                        if(img.getPixel(p) == color){
                            ++count;
                            continue;
                        }
                        double tmpAngle = getAngle(p, currentPoint);
                        double diff =  180 - Math.abs(Math.abs(angle - tmpAngle) - 180);
                        if(diff < bestDiff){
                            bestDiff = diff;
                            nextPixel = p;
                        }
                    }
                    points.add(currentPoint);
                    if((nearby.size() - count) == 0){
                        break;
                    }
                }
                
                
                
                //Creating points with continous coordinates
                ArrayList<Vector2> continousPoints = new ArrayList<Vector2>();
                for(Point p: points) {
                    continousPoints.add(new Vector2(p.x, p.y));      
                }
                
                //Calculating middle of the mass
                Vector2 center = Vector2.zero();
                for(Vector2 p : continousPoints) {
                    center = Vector2.sum(center, p);
                }
                center = Vector2.multiply(center, 1.0/continousPoints.size());

                //Determining shape type
                Vector2 minLenR = Vector2.diff(continousPoints.get(0), center);
                Vector2 maxLenR = Vector2.diff(continousPoints.get(0), center);
                
                for(Vector2 p : continousPoints) {
                    Vector2 r = Vector2.diff(p, center);
                    if(r.length() < minLenR.length()) {
                        minLenR = r;
                    }
                    if(r.length() > maxLenR.length()) {
                        maxLenR = r;
                    }
                }
                minLenR.normalize();
                maxLenR.normalize();
                
                double minMaxAngle = Math.acos(Vector2.dotProduct(minLenR, maxLenR));
                minMaxAngle *= 180.0 / Math.PI;
            
                //Shape creation
                Shape shape;
                if(Math.abs(minMaxAngle - 90.0) > 5.0) {
                    shape = new Rect(continousPoints, center);
                } else {
                    shape = new Ellipse(continousPoints, center);
                }
                

                
                shapes.add(shape);
            }
        }
        //redraw();
    }

    public Detector(BufferedImage image){
        shapes = new ArrayList<Shape>();
        compute(image);
    }

    public BufferedImage getImage(){
        return img.image;
    }

    /*
    public void move(int id, int dx, int dy){
        shapes.get(id).move(dx,dy);
        redraw();
    }

    public void rotate(int id, int angle){
        shapes.get(id).rotate(angle);
        redraw();
    }
    */
    
}
