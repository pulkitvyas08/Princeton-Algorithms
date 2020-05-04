import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void draw() {
        stdDraw.point(x, y);
    }

    void drawTo(Point toDraw) {
        stdDraw.line(x, y, toDraw.x, toDraw.y);
    }

    public double slopeTo(Point toSlope) {
        if (x == toSlope.x && y == toSlope.y)
            return Double.NEGATIVE_INFINITY;
        if (x == toSlope.x)
            return Double.POSITIVE_INFINITY;
        if (y == toSlope.y)
            return 0;
        return 1.0 * (y - toSlope.y) / (x - toSlope.x);
    }

    public int compareTo(Point comp) {
        if (x == comp.x && y == comp.y)
            return 0;
        if ((y == comp.y && x < comp.x) || (y < comp.y))
            return -1;
        return 1;
    }

    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                double res = slopeTo(o1) - slopeTo(o2);
                if (res > 0)
                    return 1;
                if (res < 0)
                    return -1;
                return 0;
            }
        };
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        Point o1 = new Point(1, 1);
        Point o2 = new Point(1, 2);
        System.out.println(o1.slopeTo(o2));
    }
}
