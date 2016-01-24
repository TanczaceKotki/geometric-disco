package shapes;

import java.awt.Graphics;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CanvasTest {
    
    public CanvasTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    Canvas instance;
    ArrayList<Vector2> points;
    
    @Before
    public void setUp() {
        instance = new Canvas();
        ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
        LineSegment segment1 = new LineSegment(
            new Vector2(10, 20),
            new Vector2(200, 150)
        );
        LineSegment segment2 = new LineSegment(
            new Vector2(100, 30),
            new Vector2(20, 220)
        );
        lines.add(segment1);
        lines.add(segment2);
        instance.setLines(lines);
        points = new ArrayList<Vector2>();
        points.add(segment1.left);
        points.add(segment1.right);
        points.add(segment2.left);
        points.add(segment2.right);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPrefferedWidth method, of class Canvas.
     */
    @Test
    public void testGetPrefferedWidth() {
        System.out.println("getPrefferedWidth");
        int expResult = 0;
        for(Vector2 point : points) {
            int x = (int)Math.round(point.x);
            if(x > expResult)
                expResult = x;
        }
        expResult += instance.margin;
        
        int result = instance.getPrefferedWidth();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrefferedHeight method, of class Canvas.
     */
    @Test
    public void testGetPrefferedHeight() {
        int expResult = 0;
        for(Vector2 point : points) {
            int y = (int)Math.round(point.y);
            if(y > expResult)
                expResult = y;
        }
        expResult += instance.margin;
        
        int result = instance.getPrefferedHeight();
        assertEquals(expResult, result);
    }

}
