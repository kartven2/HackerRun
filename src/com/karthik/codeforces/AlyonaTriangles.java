/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/682/E
 *
 * Print the coordinates of three points 
 * Vertices of a triangle which contains all n points and which area doesn't exceed 4S.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AlyonaTriangles {

    private int n;
    private long s;
    private Point[] points;
    private Point[] hull;
    private Point[] maxAreaTriangle;

    class Point implements Comparable {

        private long x;
        private long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Point)) {
                return false;
            }
            Point other = (Point) o;
            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 89 * hash + (int) (this.x ^ (this.x >>> 32));
            hash = 89 * hash + (int) (this.y ^ (this.y >>> 32));
            return hash;
        }

        @Override
        public int compareTo(Object o) {
            Point other = (Point) o;
            if (this.x == other.x) {
                return Long.compare(this.y, other.y);
            }
            return Long.compare(this.x, other.x);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    private static long area2(Point a, Point b, Point c) {
        return a.x * (b.y - c.y) - a.y * (b.x - c.x) + (b.x * c.y - b.y * c.x);
    }

    private static boolean isRightTurnOrCollinear(Point a, Point b, Point c) {
        return area2(a, b, c) <= 0;
    }

    private static long absArea2(Point a, Point b, Point c) {
        return Math.abs(area2(a, b, c));
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/alyonatriangles2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        s = sc.nextLong();
        points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(sc.nextLong(), sc.nextLong());
        }
        computeConvexHull();
        findTrianglePointsWithMaxArea();
        printAnticomplimentaryTrianglePoints();
    }

    private void printAnticomplimentaryTrianglePoints() {
        System.out.println(new Point((-maxAreaTriangle[0].x + maxAreaTriangle[1].x + maxAreaTriangle[2].x),
                (-maxAreaTriangle[0].y + maxAreaTriangle[1].y + maxAreaTriangle[2].y)).toString());
        System.out.println(new Point((maxAreaTriangle[0].x - maxAreaTriangle[1].x + maxAreaTriangle[2].x),
                (maxAreaTriangle[0].y - maxAreaTriangle[1].y + maxAreaTriangle[2].y)).toString());
        System.out.println(new Point((maxAreaTriangle[0].x + maxAreaTriangle[1].x - maxAreaTriangle[2].x),
                (maxAreaTriangle[0].y + maxAreaTriangle[1].y - maxAreaTriangle[2].y)).toString());
    }

    private void findTrianglePointsWithMaxArea() {
        maxAreaTriangle = new Point[3];
        int m = hull.length;
        int a = 0, b = 1, c = 2;
        int ba = a, bb = b, bc = c;
        while (true) { //loop A
            while (true) { //loop B
                while (absArea2(hull[a], hull[b], hull[c])
                        <= absArea2(hull[a], hull[b], hull[(c + 1) % m])) { //loop C
                    c = (c + 1) % m;
                }
                if (absArea2(hull[a], hull[b], hull[c])
                        <= absArea2(hull[a], hull[(b + 1) % m], hull[c])) {
                    b = (b + 1) % m;
                } else {
                    break;
                }
            }
            if (absArea2(hull[a], hull[b], hull[c]) > absArea2(hull[ba], hull[bb], hull[bc])) {
                ba = a;
                bb = b;
                bc = c;
            }
            a = (a + 1) % m;
            if (a == b) {
                b = (b + 1) % m;
            }
            if (b == c) {
                c = (c + 1) % m;
            }
            if (a == 0) {
                break;
            }
        }
        maxAreaTriangle[0] = hull[ba];
        maxAreaTriangle[1] = hull[bb];
        maxAreaTriangle[2] = hull[bc];
    }

    private void computeConvexHull() {
        Arrays.sort(points);
        int[] hullIdx = new int[n + 1];
        int hullPtr = 0;
        for (int i = 0; i < n; i++) {
            while (hullPtr > 1 && isRightTurnOrCollinear(points[hullIdx[hullPtr - 2]], points[hullIdx[hullPtr - 1]], points[i])) {
                hullPtr--;
            }
            hullIdx[hullPtr++] = i;
        }

        for (int i = n - 2, t = hullPtr; i >= 0; i--) {
            while (hullPtr > t && isRightTurnOrCollinear(points[hullIdx[hullPtr - 2]], points[hullIdx[hullPtr - 1]], points[i])) {
                hullPtr--;
            }
            hullIdx[hullPtr++] = i;
        }

        hullPtr--;
        hull = new Point[hullPtr];
        for (int i = 0; i < hullPtr; i++) {
            hull[i] = points[hullIdx[i]];
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        private Integer nextInt() {
            return Integer.parseInt(readNext());
        }

        private Long nextLong() {
            return Long.parseLong(readNext());
        }
    }

    public static void main(String... args) {
        AlyonaTriangles at = new AlyonaTriangles();
        at.compute();
    }
}
