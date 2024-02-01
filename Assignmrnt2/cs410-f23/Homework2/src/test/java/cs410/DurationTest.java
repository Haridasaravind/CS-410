package cs410;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs410.Duration;
public class DurationTest
{
    @Test
    public void testAdd() {
        Duration duration1 = Duration.of(1, 30, 45);
        Duration duration2 = Duration.of(0, 45, 30);
        Duration result = duration1.add(duration2);
        assertEquals("2:16:15", result.toString());
    }

    @Test
    public void testSeconds() {
        Duration duration = Duration.of(3, 45, 30);
        assertEquals(13530, duration.seconds());
    }

    @Test
    public void testToString() {
        Duration duration = Duration.of(2, 0, 5);
        assertEquals("2:00:05", duration.toString());
        duration = Duration.of(duration.seconds());
        assertEquals("2:00:05", duration.toString());
    }
}