package shapes;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollisionTest {
    
    public CollisionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hasEdges method, of class Collision.
     */
    @Test
    public void testHasEdges() {
        System.out.println("hasEdges");
        Collision instance = null;
        boolean expResult = false;
        boolean result = instance.hasEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCollisionEdge method, of class Collision.
     */
    @Test
    public void testAddCollisionEdge() {
        System.out.println("addCollisionEdge");
        CollisionEdge ce = null;
        Collision instance = null;
        instance.addCollisionEdge(ce);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDesctiption method, of class Collision.
     */
    @Test
    public void testGetDesctiption() {
        System.out.println("getDesctiption");
        Shape shape = null;
        Collision instance = null;
        String expResult = "";
        String result = instance.getDesctiption(shape);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilteredEdges method, of class Collision.
     */
    @Test
    public void testGetFilteredEdges() {
        System.out.println("getFilteredEdges");
        Collision instance = null;
        ArrayList<CollisionEdge> expResult = null;
        ArrayList<CollisionEdge> result = instance.getFilteredEdges();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of concerns method, of class Collision.
     */
    @Test
    public void testConcerns() {
        System.out.println("concerns");
        Shape shape = null;
        Collision instance = null;
        boolean expResult = false;
        boolean result = instance.concerns(shape);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
