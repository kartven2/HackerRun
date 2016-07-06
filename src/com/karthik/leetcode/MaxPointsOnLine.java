/*
 * Leetcode: https://leetcode.com/problems/max-points-on-a-line/
 *
 * Given n points on a 2D plane, find the maximum number of points
 * that lie on the same straight line.
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaxPointsOnLine {

    private static final double MAX = Double.MAX_VALUE;
    private static final double MIN = Double.MIN_VALUE;
    Double[] slope;
    int slopeptr;

    class Point {

        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    class Node {

        private Point key;
        private int count;
        private Node left;
        private Node right;
        private boolean color;

        Node(Point key, boolean color) {
            this.key = key;
            this.count = 1;
            this.color = color;
        }
    }

    class Rbtree {

        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private Node root;
        private int size;

        private boolean isRed(Node node) {
            return node != null && node.color == RED;
        }

        private int getFreq(Point key) {
            Node node = getFreq(root, key);
            if (node == null) {
                return 0;
            }
            return node.count;
        }

        private Node getFreq(Node node, Point currPoint) {
            if (node == null) {
                return null;
            }
            int cmp = (node.key.x == currPoint.x) ? node.key.y - currPoint.y : node.key.x - currPoint.x;
            if (cmp < 0) {
                return getFreq(node.left, currPoint);
            } else if (cmp > 0) {
                return getFreq(node.right, currPoint);
            } else {
                return node;
            }
        }

        private void insert(Point key) {
            root = insert(root, key);
            root.color = BLACK;
        }

        private Node insert(Node node, Point currPoint) {
            if (node == null) {
                size++;
                return new Node(currPoint, RED);
            }

            int cmp = (node.key.x == currPoint.x) ? node.key.y - currPoint.y : node.key.x - currPoint.x;
            if (cmp < 0) {
                node.left = insert(node.left, currPoint);
            } else if (cmp > 0) {
                node.right = insert(node.right, currPoint);
            } else {
                node.count++;
            }

            if (isRed(node.right) && !isRed(node.left)) {
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }
            return node;
        }

        private void flipColors(Node node) {
            node.color = !node.color;
            node.left.color = !node.left.color;
            node.right.color = !node.right.color;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }
    }

    private double slope(Point a, Point b) {
        if (b.y != a.y && b.x == a.x) {
            return MAX;
        }
        if (b.y == a.y) {
            return 0;
        }
        return (double) (b.y - a.y) / (b.x - a.x);
    }

    private boolean samePoint(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }

    public int maxPoints(Point[] points) {
        int n = points.length;
        if (n < 3) {
            return n;
        }
        Rbtree rbTree = new Rbtree();
        for (int i = 0; i < points.length; i++) {
            rbTree.insert(points[i]);
        }
        if (rbTree.size < 3) {
            return n;
        }
        int maxPoints = Integer.MIN_VALUE;
        for (int i = 0; i < points.length - 1; i++) {
            slope = new Double[points.length - i - 1];
            slopeptr = 0;
            for (int j = i + 1; j < points.length; j++) {
                slope[slopeptr++] = samePoint(points[i], points[j]) ? MIN : slope(points[i], points[j]);
            }
            Arrays.sort(slope);
            int k = 0;
            while (k < slopeptr && slope[k] == MIN) {
                k++;
            }
            int spcount = rbTree.getFreq(points[i]) + 1;
            k++;
            while (k < slopeptr) {
                spcount = Double.compare(slope[k], slope[k - 1]) == 0 ? spcount + 1 : rbTree.getFreq(points[i]) + 1;
                if (maxPoints < spcount) {
                    maxPoints = spcount;
                }
                k++;
            }
        }
        return maxPoints;
    }

    private void compute() {
        //Point[] points = {new Point(0, 0), new Point(1, 1), new Point(0, 0)};
        //Point[] points = {new Point(84, 250), new Point(0, 0), new Point(1, 0), new Point(0, -70), new Point(0, -70),
        //new Point(1, -1), new Point(21, 10), new Point(42, 90), new Point(-42, -230)};
        Point[] points = {new Point(2, 3), new Point(3, 3), new Point(-5, 3)};
        System.out.println(maxPoints(points));
    }

    public static void main(String... args) {
        MaxPointsOnLine mp = new MaxPointsOnLine();
        mp.compute();
    }
}
