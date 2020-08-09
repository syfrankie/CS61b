package bearmaps.proj2ab;

import java.util.List;

public class KDTree {

    private Node root;

    private class Node {
        private Point val;
        private Node left, right;
        private boolean vertical;

        private Node(Point p, boolean v) {
            val = p;
            vertical = v;
        }
    }

    private int compare(Point a, Point b, boolean v) {
        double x1 = a.getX();
        double x2 = b.getX();
        double y1 = a.getY();
        double y2 = b.getY();
        if (x1 == x2 && y1 == y2) return 0;
        if (!v) {
            if (x1 < x2) return -1;
        } else {
            if (y1 < y2) return -1;
        }
        return 1;
    }

    private Node addNode(Node curr, Point p, boolean v) {
        if (curr == null) return new Node(p, v);
        int cmp = compare(p, curr.val, curr.vertical);
        if (cmp == -1) curr.left = addNode(curr.left, p, !v);
        else if (cmp == 1) curr.right = addNode(curr.right, p, !v);
        return curr;
    }

    public KDTree(List<Point> points) {
        root = null;
        for (Point p: points) {
            root = addNode(root, p, false);
        }
    }

    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return helper(root, target, root.val);
    }

    private Point helper(Node curr, Point goal, Point best) {
        if (curr == null) return best;
        if (Point.distance(curr.val, goal) < Point.distance(best, goal)) {
            best = curr.val;
        }
        Node good, bad;
        int cmp = compare( goal, curr.val, curr.vertical);
        if (cmp == -1) {
            good = curr.left;
            bad = curr.right;
        } else {
            good = curr.right;
            bad = curr.left;
        }
        best = helper(good, goal, best);
        if (usefulEdge(curr, goal, best)) {
            best = helper(bad, goal, best);
        }
        return best;
    }

    private boolean usefulEdge(Node curr, Point goal, Point best) {
        double shortest = Point.distance(best, goal);
        Point temp;
        if (!curr.vertical) {
            temp = new Point(curr.val.getX(), goal.getY());
        } else {
            temp = new Point(goal.getX(), curr.val.getY());
        }
        return (Point.distance(temp, goal) < shortest);
    }

    /*
    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);
        KDTree mytree = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point ans = mytree.nearest(0, 7);
        System.out.println(ans.getX());
        System.out.println(ans.getY());
    } */

}
