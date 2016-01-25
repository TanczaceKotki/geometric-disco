
package shapes;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class DetectorTest {
    Detector instance;
    
    @Before
    public void setUp() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("test.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance = new Detector(image);
    }
   

    @Test
    public void testAngle() {
        Point p = new Point(0,0);
        Point p0 = new Point(1,0);
        Point p45 = new Point(1,1);
        Point p90 = new Point(0,1);
        Point p135 = new Point(-1,1);
        Point p180 = new Point(-1,0);
        Point p225 = new Point(-1,-1);
        Point p270 = new Point(0,-1);
        Point p315 = new Point(1,-1);
        assertEquals(0, Detector.getAngle(p, p0));
        assertEquals(45, Detector.getAngle(p, p45));
        assertEquals(90, Detector.getAngle(p, p90));
        assertEquals(135, Detector.getAngle(p, p135));
        assertEquals(180, Detector.getAngle(p, p180));
        assertEquals(225, Detector.getAngle(p, p225));
        assertEquals(270, Detector.getAngle(p, p270));
        assertEquals(315, Detector.getAngle(p, p315));
    }

}
