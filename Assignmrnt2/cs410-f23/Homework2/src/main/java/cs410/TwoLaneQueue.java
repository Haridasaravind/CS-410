package cs410;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
public class TwoLaneQueue {
    private Queue<String> fastLane;
    private Queue<String> slowLane;
    private int fastLaneTurns;
    public TwoLaneQueue() {
        fastLane = new LinkedList<>();
        slowLane = new LinkedList<>();
        fastLaneTurns = 0;
    }
    public void enqueueFast(String item) {
        fastLane.add(item);
    }
    public void enqueueSlow(String item) {
        slowLane.add(item);
    }
    public String dequeue() {
        if (!fastLane.isEmpty()) {
            if (fastLaneTurns >= 3 && !slowLane.isEmpty()) {
                fastLaneTurns = 0;
                return slowLane.poll();
            }
            fastLaneTurns++;
            return fastLane.poll();
        } else if (!slowLane.isEmpty()){
            fastLaneTurns = 0;
            return slowLane.poll();
        } else {
            throw new NoSuchElementException("Both lanes are empty");
        }
    }
}
