package cs410;
public class Rectangle {
    private final int a, b; // Coordinates of one corner
    private final int c, d; // Coordinates of the opposite corner

    private Rectangle(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public static Rectangle of(int a, int b, int c, int d) {
        return new Rectangle(a, b, c, d);
    }

    /**
     *  Calculates the area of the rectangle.
     *
     * @return area of the rectangle object
     */
    public int area() {
        int length = c - a;
        int width = d - b;
        return Math.abs(length * width);
    }

    public boolean contains(int x, int y) {
        boolean xInRange = (x >= Math.min(a, c)) && (x <= Math.max(a, c));
        boolean yInRange = (y >= Math.min(b, d)) && (y <= Math.max(b, d));
        return xInRange && yInRange;
    }

    public boolean overlaps(Rectangle other) {
        int thisLeft = Math.min(a, c);
        int thisRight = Math.max(a, c);
        int thisBottom = Math.min(b, d);
        int thisTop = Math.max(b, d);

        int otherLeft = Math.min(other.a, other.c);
        int otherRight = Math.max(other.a, other.c);
        int otherBottom = Math.min(other.b, other.d);
        int otherTop = Math.max(other.b, other.d);

        return !(thisRight < otherLeft || thisLeft > otherRight || thisTop < otherBottom || thisBottom > otherTop);
    }
}
