import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FastCollinearPoints {

    private ArrayList<LineSegment> line = new ArrayList<>();
    private HashMap<Double, ArrayList<Point>> newLine = new HashMap<>();

    private void pointVerifier(Point[] points) {
        for (int i = 0; i < points.length - 1; i++)
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Repeated Points Found");
    }

    private boolean lineCreator(double m, ArrayList<Point> pointsOnLine) {

        ArrayList<Point> newPoints = newLine.get(m);

        Collections.sort(pointsOnLine);

        Point p1 = pointsOnLine.get(0);
        Point p2 = pointsOnLine.get(pointsOnLine.size() - 1);

        if (newPoints == null) {
            line.add(new LineSegment(p1, p2));
            newPoints = new ArrayList<>();
            newPoints.add(p2);
            newLine.put(m, newPoints);
        } else {
            for (Point x : newPoints)
                if (x.compareTo(p2) == 0)
                    return false;

            line.add(new LineSegment(p1, p2));
            newPoints.add(p2);
            newLine.put(m, newPoints);
        }
        return true;
    }

    public FastCollinearPoints(Point[] points) {

        Point[] clone = Arrays.copyOf(points, points.length);

        pointVerifier(points);

        if (points.length >= 4) {
            for (int i = 0; i < points.length; i++) {

                Arrays.sort(clone, points[i].slopeOrder());

                double mLast = Double.NEGATIVE_INFINITY;
                double mCurrent = Double.NEGATIVE_INFINITY;

                ArrayList<Point> pointsOnLine = new ArrayList<>();

                int tracker = 1;
                pointsOnLine.add(points[i]);
                for (int j = 1; j < clone.length; j++) {
                    mCurrent = points[i].slopeTo(clone[j]);
                    double e = 0.00000001;

                    if (mCurrent == mLast || Math.abs(mCurrent - mLast) < e)
                        pointsOnLine.add(clone[j]);
                    else {
                        pointsOnLine.add(clone[tracker]);

                        if (pointsOnLine.size() > 3)
                            lineCreator(mLast, pointsOnLine);

                        pointsOnLine.clear();
                        pointsOnLine.add(points[i]);
                        tracker = j;
                    }
                    mLast = mCurrent;
                }

                pointsOnLine.add(clone[tracker]);
                if (pointsOnLine.size() > 3)
                    lineCreator(mCurrent, pointsOnLine);
            }
        }
    }

    public int numberOfSegments() {
        return line.size();
    }

    public LineSegment[] segments() {
        return line.toArray(new LineSegment[line.size()]);
    }

    public static void main(String[] args) {

        System.out.println(Double.POSITIVE_INFINITY == Double.POSITIVE_INFINITY);

        In input = new In("collinear-testing/collinear/vertical25.txt");
        int n = input.readInt();
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            int x = input.readInt();
            int y = input.readInt();
            points[i] = new Point(x, y);
        }

        StdOut.println("BruteCollinearPoints");
        BruteCollinearPoints bruteColTest = new BruteCollinearPoints(points);
        for (LineSegment segment : bruteColTest.segments())
            StdOut.println(segment);
        StdOut.println(bruteColTest.numberOfSegments());

        StdOut.println("\nFastCollinearPoints");
        FastCollinearPoints fastColTest = new FastCollinearPoints(points);
        for (LineSegment segment : fastColTest.segments())
            StdOut.println(segment);
        StdOut.println(fastColTest.numberOfSegments());
    }
}
