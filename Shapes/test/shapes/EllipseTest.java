package shapes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class EllipseTest {
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setWidth method, of class Ellipse.
     */
    @Test
    public void testConstructors() {
        System.out.println("setWidth");
        double newWidth = 0.0;
        Ellipse instance = null;
        instance.setWidth(newWidth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    /**
     * Test of setWidth method, of class Ellipse.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        double newWidth = 0.0;
        Ellipse instance = null;
        instance.setWidth(newWidth);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of setHeight method, of class Ellipse.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        double newHeight = 0.0;
        Ellipse instance = null;
        instance.setHeight(newHeight);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of recalculatePolyLine method, of class Ellipse.
     */
    @Test
    public void testRecalculatePolyLine() {
        System.out.println("recalculatePolyLine");
        Ellipse instance = null;
        instance.recalculatePolyLine();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insideTest method, of class Ellipse.
     */
    @Test
    public void testInsideTest() {
        System.out.println("insideTest");
        Vector2 point = null;
        Ellipse instance = null;
        boolean expResult = false;
        boolean result = instance.insideTest(point);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
