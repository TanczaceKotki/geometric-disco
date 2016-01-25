package shapes;

public class LineComponent{
    int x1; 
    int y1;
    int x2;
    int y2;   

    public LineComponent (int a1, int b1, int a2, int b2) {
        this.x1 = a1;
        this.y1 = b1;
        this.x2 = a2;
        this.y2 = b2;
    }

    public void changePos (int a1, int b1, int a2, int b2) {
        this.x1 = a1;
        this.y1 = b1;
        this.x2 = a2;
        this.y2 = b2;
    }
    
    public void changePos(int x, int y) {
        x2 = x;
        y2 = y;
    }
}