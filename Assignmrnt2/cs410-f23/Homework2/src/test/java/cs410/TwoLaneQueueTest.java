package cs410;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TwoLaneQueueTest {
    private TwoLaneQueue queue;

    @BeforeEach
    public void setUp()
    {
        queue = new TwoLaneQueue();
    }
    @Test
    public void testDequeueWithEmptyQueue() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }
    @Test
    public void testEnqueueFastAndDequeue() {
        queue.enqueueFast("A");
        queue.enqueueSlow("X");
        queue.enqueueFast("B");
        queue.enqueueSlow("Y");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("X", queue.dequeue());
        assertEquals("Y", queue.dequeue());

        queue.enqueueFast("C");
        queue.enqueueSlow("Z");

        assertEquals("C", queue.dequeue());
        assertEquals("Z", queue.dequeue());
    }
    @Test
    public void testEnqueueSlowAndDequeue() {
        queue.enqueueSlow("X");
        queue.enqueueFast("A");
        queue.enqueueSlow("Y");
        queue.enqueueFast("B");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("X", queue.dequeue());
        assertEquals("Y", queue.dequeue());
    }
    @Test
    public void testDequeueOrder() {
        queue.enqueueFast("A");
        queue.enqueueFast("B");
        queue.enqueueFast("C");
        queue.enqueueSlow("X");
        queue.enqueueSlow("Y");
        queue.enqueueFast("D");
        queue.enqueueFast("E");
        queue.enqueueSlow("Z");
        queue.enqueueSlow("P");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());
        assertEquals("X", queue.dequeue());
        assertEquals("D", queue.dequeue());
        assertEquals("E", queue.dequeue());
        assertEquals("Y", queue.dequeue());
        assertEquals("Z", queue.dequeue());
        assertEquals("P", queue.dequeue());
    }
}
