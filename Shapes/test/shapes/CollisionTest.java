package shapes;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollisionTest {
    
    Collision instance;
    Shape shape1;
    Shape shape2;
    
    @Before
    public void setUp() {
        shape1 = new Rect(100, 100);
        shape2 = new Rect(100, 100);
        shape2.setPosition(new Vector2(50, 50));
        instance = new Collision(shape1, shape2);
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

        boolean result = instance.hasEdges();
        assertEquals(false, result);
        
        instance.addCollisionEdge(new Vector2(100, 50));
        instance.addCollisionEdge(new Vector2(50, 100));
        result = instance.hasEdges();
        assertEquals(true, result);
    }

    /**
     * Test of addCollisionEdge method, of class Collision.
     */
    @Test
    public void testAddCollisionEdge() {
        System.out.println("addCollisionEdge");
        CollisionEdge ce = new LineSegment(new Vector2(100, 0), new Vector2(100, 100));
        assertEquals(false, instance.edges.contains(ce));
        
        instance.addCollisionEdge(ce);
        assertEquals(true, instance.edges.contains(ce));
        
    }


    /**
     * Test of getFilteredEdges method, of class Collision.
     */
    @Test
    public void testGetFilteredEdges() {
        System.out.println("getFilteredEdges");
        instance.edges.clear();
        instance.addCollisionEdge(new Vector2(10, 10));
        instance.addCollisionEdge(new Vector2(10, 10));
        instance.addCollisionEdge(new Vector2(20, 20));
        assertEquals(2, instance.getFilteredEdges().size());
        
        instance.addCollisionEdge(new LineSegment(new Vector2(10, 10), new Vector2(20, 20)));
        assertEquals(1, instance.getFilteredEdges().size());
        
    }

    /**
     * Test of concerns method, of class Collision.
     */
    @Test
    public void testConcerns() {
        System.out.println("concerns");
        Shape anotherShape = new Rect(200, 200);

        assertEquals(false, instance.concerns(anotherShape));
        assertEquals(true, instance.concerns(shape1));
        assertEquals(true, instance.concerns(shape2));

    }
    
}
