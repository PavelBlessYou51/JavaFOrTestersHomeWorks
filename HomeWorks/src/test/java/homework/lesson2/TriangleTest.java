package homework.lesson2;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void perimetrTest() {
        Triangle t = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(12.0, t.perimetr());
    }

    @Test
    void squareTest() {
        Triangle t = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(6.0, t.square());
    }

}
