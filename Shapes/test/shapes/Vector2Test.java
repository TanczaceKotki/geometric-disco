package shapes;

import org.junit.Test;

import static java.lang.Math.PI;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class Vector2Test {
    Vector2 simpleVec;
    Vector2 complicatedVec;

    @Before
    public void setUp() {
        simpleVec = new Vector2(2.0, 2.0);
        complicatedVec = new Vector2(3.14, 4.2);
    }
    
    @Test
    public void rotateTest() {
        Vector2 a = simpleVec;
        Vector2 b = complicatedVec;
        
        a.rotate(2*PI);
        assertEquals(a.x, 2.0);
        assertEquals(a.y, 2.0);
        
        a.rotate(PI);
        assertEquals(a.x, -2.0);
        assertEquals(a.y, -2.0);
        
        b.rotate(2*PI);
        assertEquals(b.x, 3.14);
        assertEquals(b.y, 4.2);
        
        b.rotate(PI);
        assertEquals(b.x, -3.14);
        assertEquals(b.y, -4.2);
    }
    
    @Test
    public void translateTest() {
        Vector2 a = simpleVec;
        
        a.translate(complicatedVec);
        assertEquals(a.x, 5.14);
        assertEquals(a.x, 6.2);
    }
    
    @Test
    public void lengthTest() {
        assertEquals(simpleVec.length(), Math.sqrt(8));
        assertEquals(complicatedVec.length(), Math.sqrt(3.14*3.14 + 4.2*4.2));
    }
    
    @Test
    public void normalizeTest() {
        Vector2 a = simpleVec;
        double aLen = Math.sqrt(8);
        a.normalize();
        assertEquals(a.x, 2/aLen);
        assertEquals(a.y, 2/aLen);
        
        Vector2 b = complicatedVec;
        double bLen = Math.sqrt(3.14*3.14 + 4.2*4.2);
        assertEquals(b.x, 3.14/bLen);
        assertEquals(b.y, 4.2/bLen);
    }
    
    @Test
    public void diffTest() {
        Vector2 a = Vector2.diff(simpleVec, complicatedVec);
        assertEquals(a.x, -1.14);
        assertEquals(a.y, -2.2);
        
        Vector2 b = Vector2.diff(complicatedVec, complicatedVec);
        assertEquals(b.x, 0);
        assertEquals(b.y, 0);
        
        Vector2 c = Vector2.diff(complicatedVec, simpleVec);
        assertEquals(c.x, 1.14);
        assertEquals(c.y, 2.2);
    }
    
    @Test
    public void sumTest() {
        Vector2 a = Vector2.sum(simpleVec, complicatedVec);
        assertEquals(a.x, 5.14);
        assertEquals(a.y, 6.2);
        
        Vector2 b = Vector2.sum(complicatedVec, complicatedVec);
        assertEquals(b.x, 6.28);
        assertEquals(b.y, 8.4);
        
        Vector2 c = Vector2.sum(complicatedVec, simpleVec);
        assertEquals(c.x, 5.14);
        assertEquals(c.y, 6.2);
    }
    
    @Test
    public void multiplyTest() {
        Vector2 a = Vector2.multiply(simpleVec, 2.0);
        assertEquals(a.x, 4);
        assertEquals(a.y, 4);
        
        Vector2 b = Vector2.multiply(complicatedVec, 2.0);
        assertEquals(b.x, 6.28);
        assertEquals(b.y, 8.4);
        
        Vector2 c = Vector2.multiply(complicatedVec, 3.14);
        assertEquals(c.x, 3.14*3.14);
        assertEquals(c.y, 4.2*3.14);
    }
    
    @Test
    public void crossProductTest() {
        double a = Vector2.crossProduct(simpleVec, complicatedVec);
        assertEquals(a, 2.12);
        
        double b = Vector2.crossProduct(complicatedVec, complicatedVec);
        assertEquals(b, 0);
        
        double c = Vector2.crossProduct(complicatedVec, simpleVec);
        assertEquals(c, -2.12);
    }
    
    @Test
    public void dotProductTest() {
        double a = Vector2.dotProduct(simpleVec, complicatedVec);
        assertEquals(a, 14.68);
        
        double b = Vector2.dotProduct(complicatedVec, complicatedVec);
        assertEquals(b, 68794/2500);
        
        double c = Vector2.dotProduct(complicatedVec, simpleVec);
        assertEquals(c, 14.68);
    }
    
    @Test
    public void equalsTest() {
        assertTrue(simpleVec.equals(simpleVec));
        
        assertTrue(simpleVec.equals(new Vector2(2.0, 2.0)));
        
        assertTrue(complicatedVec.equals(complicatedVec));
        
        assertFalse(complicatedVec.equals(simpleVec));
    }
    
    @Test
    public void getPointsTest() {
        Vector2[] a = simpleVec.getPoints();
        assertTrue(simpleVec.equals(a[0]));
        
        Vector2[] b = complicatedVec.getPoints();
        assertTrue(complicatedVec.equals(b[0]));
    }
}
