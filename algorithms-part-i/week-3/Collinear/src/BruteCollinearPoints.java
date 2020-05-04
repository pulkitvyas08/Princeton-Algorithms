import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<lineSegment> line = new ArrayList<>();

    private void pointVerifier(Point[] points) {
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Repeated Points Found");
    }

    public BruteCollinearPoints(Point[] points) {
        pointVerifier(points);

        Point[] clone = Arrays.copyOf(points, points.length);

        Arrays.sort(clone);

        double m1, m2, m3;
        for (int i = 0; i < clone.length - 3; i++) {
            for (int j = i + 1; j < clone.length - 2; j++) {
                for (int k = j + 1; k < clone.length - 1; k++) {
                    for (int l = k + 1; l < clone.length; l++) {

                        m1 = clone[i].slopeTo(clone[j]);
                        m2 = clone[i].slopeTo(clone[k]);
                        m3 = clone[i].slopeTo(clone[l]);

                        double e = 0.00000001;
                        if (Math.abs(m1 - m2) < e && Math.abs(m1 - m3) < e)
                            line.add(new LineSegment(clone[i], clone[l]));
                        else if (m1 == Double.POSITIVE_INFINITY && m2 == Double.POSITIVE_INFINITY
                                && m3 == Double.POSITIVE_INFINITY)
                            line.add(new LineSegment(clone[i], clone[l]));
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return line.size();
    }

    public LineSegment[] segments() {
        return line.toArray(new LineSegment[line.size()]);
    }
}
