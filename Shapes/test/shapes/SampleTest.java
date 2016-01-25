/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapes;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class SampleTest {

    @Test
    public void test1() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("test.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Detector det = new Detector(image);
        List<Shape> s = det.shapes;

        assertEquals(s.get(0).position.x, 303);
        assertEquals(s.get(0).position.y, 223);

        assertEquals(s.get(1).position.x, 178);
        assertEquals(s.get(1).position.y, 126);

        assertEquals(s.get(2).position.x, 180);
        assertEquals(s.get(2).position.y, 249);

        assertEquals(s.get(3).position.x, 381);
        assertEquals(s.get(3).position.y, 106);

        assertEquals(s.get(4).position.x, 305);
        assertEquals(s.get(4).position.y, 187);

        assertEquals(s.get(5).position.x, 432);
        assertEquals(s.get(5).position.y, 281);

        assertEquals(s.get(6).position.x, 509);
        assertEquals(s.get(6).position.y, 184);
    }
}
