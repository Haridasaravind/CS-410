package cs410;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleTest{
    @Test
    public void testArea()
    {
        Rectangle rectangle = Rectangle.of(2, 3, 5, 7);
        assertEquals(12, rectangle.area());
    }
    @Test
    public void testContainsPoint()
    {
        Rectangle rect = Rectangle.of(2, 3, 4, 5);
        assertTrue(rect.contains(3, 4));
        assertFalse(rect.contains(6, 7));
        assertFalse(rect.contains(1, 2));
    }
    @Test
    public void testOverlap()
    {
        Rectangle rA = Rectangle.of(1, 2, 3, 4);
        Rectangle rB = Rectangle.of(2, 3, 4, 5);
        Rectangle rC = Rectangle.of(5, 6, 7, 8);
        assertTrue(rA.overlaps(rB));
        assertFalse(rA.overlaps(rC));
    }
}


