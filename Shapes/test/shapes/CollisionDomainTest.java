package shapes;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CollisionDomainTest {
    
    CollisionDomain instance;
    @Before
    public void setUp() {
        instance = new CollisionDomain();
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of updateShape method, of class CollisionDomain.
     */
    @Test
    public void testUpdateShape() {
        System.out.println("updateShape");
        instance.shapes.clear();
        
        Shape shape1 = new Rect(20, 20);
        instance.addShape(shape1);
        Shape shape2 = new Rect(50, 50);
        instance.addShape(shape2);
        instance.updateShape(1, "Custom", new Vector2(20, 20), 45);
        
        assertEquals(45, shape2.rotation, 0.0);
        assertTrue(shape2.name.equals("Custom"));
        assertTrue(shape2.position.equals(new Vector2(20, 20)));
        
    }

    /**
     * Test of getShapeCount method, of class CollisionDomain.
     */
    @Test
    public void testGetShapeCount() {
        System.out.println("getShapeCount");
        instance.shapes.clear();
        assertEquals(0, instance.getShapeCount());
        instance.addShape(new Rect(20, 20));
        assertEquals(1, instance.getShapeCount());
    }

    /**
     * Test of collisionCheck method, of class CollisionDomain.
     */
    @Test
    public void testCollisionCheck() {
        System.out.println("collisionCheck");
        instance.shapes.clear();
        
        Shape shape1 = new Rect(100, 100);
        Shape shape2 = new Rect(100, 100);
        
        CollisionEdge collisionPoint1 = new Vector2(50, 0);
        CollisionEdge collisionPoint2 = new Vector2(0, 50);
        CollisionEdge collisionSection = new LineSegment(new Vector2(50, -50), new Vector2(50, 50));
        
        //Overlapping
        Collision result = instance.collisionCheck(shape1, shape2);
        assertTrue(result.overlap);
        shape2.setPosition(new Vector2(50, 50));
        result = instance.collisionCheck(shape1, shape2);
        assertFalse(result.overlap);
        
        //Points
        assertTrue(result.getFilteredEdges().contains(collisionPoint1));
        assertTrue(result.getFilteredEdges().contains(collisionPoint2));
        assertFalse(result.edges.contains(collisionSection));
        
        //Edge
        shape2.setPosition(new Vector2(100, 0));
        result = instance.collisionCheck(shape1, shape2);
        assertFalse(result.getFilteredEdges().contains(collisionPoint1));
        assertFalse(result.getFilteredEdges().contains(collisionPoint2));
        assertTrue(result.edges.contains(collisionSection));
        
        //Containing
        Shape shape3 = new Ellipse(400, 300);
        result = instance.collisionCheck(shape1, shape3);
        assertEquals(result.included, shape1);
    }
    
    
    /**
     * Test of getCollisions method, of class CollisionDomain.
     */
    @Test
    public void testGetCollisions() {
        
        System.out.println("getCollisions");
        instance.shapes.clear();
        Shape shape1 = new Ellipse(100, 100);
        instance.addShape(shape1);
        Shape shape2 = new Ellipse(200, 80);
        instance.addShape(shape2);
        Shape shape3 = new Rect(90, 90);
        instance.addShape(shape3);
        
        assertEquals(2, instance.getCollisions(shape1).size());
        assertEquals(2, instance.getCollisions(shape2).size());
        assertEquals(2, instance.getCollisions(shape3).size());
    }
    
    /**
     * Test of getCollisionEdges method, of class CollisionDomain.
     */
    @Test
    public void testGetCollisionEdges() {
        System.out.println("getCollisionEdges");
        instance.shapes.clear();
        
        Shape shape1 = new Rect(50, 50);
        instance.addShape(shape1);
        Shape shape2 = new Rect(50, 50);
        instance.addShape(shape2);
        Shape shape3 = new Rect(50, 50);
        shape3.setPosition(new Vector2(10, 0));
        instance.addShape(shape3);
        
        assertEquals(8, instance.getCollisionEdges().size());
    }
    
    
}
