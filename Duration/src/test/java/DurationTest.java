public class DurationTest
{

    @Test
    void testFactoryMethodFromTotalSeconds() {
        Duration duration = Duration.of(4500); // 4500 seconds = 1 hour, 15 minutes
        assertEquals("1:15:00", duration.toString());
    }

    @Test
    void testFactoryMethodFromComponents() {
        Duration duration = Duration.of(2, 30, 15); // 2 hours, 30 minutes, 15 seconds
        assertEquals("2:30:15", duration.toString());
    }

    @Test
    void testAdd() {
        Duration duration1 = Duration.of(1, 30, 45);
        Duration duration2 = Duration.of(0, 45, 30);
        Duration result = duration1.add(duration2);
        assertEquals("2:16:15", result.toString());
    }

    @Test
    void testSeconds() {
        Duration duration = Duration.of(3, 45, 30);
        assertEquals(13530, duration.seconds());
    }

    @Test
    void testToString() {
        Duration duration = Duration.of(2, 0, 5);
        assertEquals("2:00:05", duration.toString());
    }
}
