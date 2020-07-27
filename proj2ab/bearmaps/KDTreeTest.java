package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KDTreeTest {

    private static Random random = new Random(11);

    private Point randomPoint() {
        double x = random.nextDouble();
        double y = random.nextDouble();
        return new Point(x, y);
    }

    private List<Point> randomList(int size) {
        List<Point> rList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            rList.add(randomPoint());
        }
        return rList;
    }

    private void testBasic(int numPoint, int numQuery) {
        List<Point> points = randomList(numPoint);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);

        List<Point> queries = randomList(numQuery);
        for (Point q: queries) {
            Point expect = nps.nearest(q.getX(), q.getY());
            Point actual = kdt.nearest(q.getX(), q.getY());
            assertEquals(expect, actual);
        }
    }

    @Test
    public void test1() {
        testBasic(1000, 200);
    }

    @Test
    public void test2() {
        testBasic(10000, 2000);
    }

    @Test
    public void timingCompare() {
        List<Point> points = randomList(100000);
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);
        List<Point> queries = randomList(10000);

        Stopwatch sw = new Stopwatch();
        for (Point q:queries) {
            nps.nearest(q.getX(), q.getY());
        }
        double time1 = sw.elapsedTime();
        System.out.println("10000 queries on 100000 points by NPS: " + time1);

        sw = new Stopwatch();
        for (Point q:queries) {
            kdt.nearest(q.getX(), q.getY());
        }
        double time2 = sw.elapsedTime();
        System.out.println("10000 queries on 100000 points by KDTree: " + time2);
    }

    private void timingKDT(int numPoint, int numQuery) {
        List<Point> points = randomList(numPoint);
        KDTree kdt = new KDTree(points);
        List<Point> queries = randomList(numQuery);
        Stopwatch sw = new Stopwatch();
        for (Point q:queries) {
            kdt.nearest(q.getX(), q.getY());
        }
        double time = sw.elapsedTime();
        System.out.println(numQuery + " queries on " + numPoint + " points by KDTree: " + time);
    }

    @Test
    public void timing() {
        List<Integer> numPoint = List.of(5000, 50000, 500000, 5000000);
        for (int n: numPoint) {
            timingKDT(n, 5000);
        }
    }
}
