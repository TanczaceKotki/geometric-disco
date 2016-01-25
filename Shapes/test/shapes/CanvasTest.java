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
        CollisionDomain domain = new CollisionDomain();
        instance.setDomain(domain);
        Rect exampleRect = new Rect(100, 50);
        domain.addShape(exampleRect);
        points = new ArrayList<Vector2>();
        for(LineSegment ls : exampleRect.getPolyLine()) {
            points.add(ls.left);
            points.add(ls.right);
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPrefferedWidth method, of class Canvas.
     */
    @Test
    public void testGetPrefferedWidth() {
        instance.recalculateSize();
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
        instance.recalculateSize();
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
