/*
 * Leetcode: https://leetcode.com/problems/perfect-rectangle/
 *
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 * Return false. Because there is a gap between the two rectangular regions.
 * Return false. Because there is a gap in the top center.
 * Return false. Because two of the rectangles overlap with each other.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PerfectRectangle {

    public static void main(String[] args) {
        PerfectRectangle pr = new PerfectRectangle();
        int[][] rectangles = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        System.out.println(pr.isRectangleCover(rectangles));
    }

    class Point {

        private String key;
        private int type;

        Point(String key, int type) {
            this.key = key;
            this.type = type;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + this.key.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final Point other = (Point) obj;
            return (this.key).equals(other.key);
        }
    }

    enum Status {
        NEW, EXISTS, CONFLICT;
    }

    class SimpleList {

        private static final int INIT_CAPACITY = 32;
        private Point[] points;
        private int n;

        SimpleList() {
            points = new Point[INIT_CAPACITY];
        }

        private int size() {
            return n;
        }

        private void resize(int x) {
            Point[] temp = new Point[x];
            for (int i = 0; i < n; i++) {
                temp[i] = points[i];
            }
            points = temp;
        }

        private void copy(Point p) {
            points[n++] = p;
            if (n == points.length) {
                resize(2 * n);
            }
        }

        private Status add(Point p) {
            for (int i = 0; i < n; i++) {
                if (points[i] != null && points[i].key.equals(p.key)) {
                    if ((points[i].type & p.type) > 0) {
                        return Status.CONFLICT;
                    }
                    points[i].type |= p.type;
                    return Status.EXISTS;
                }
            }
            points[n++] = p;
            if (n == points.length) {
                resize(2 * n);
            }
            return Status.NEW;
        }

        private Point[] getAll() {
            return points;
        }
    }

    class SimpleHash {

        private static final int INIT_CAPACITY = 16;
        private SimpleList[] sl;
        private int n;
        private int m;

        SimpleHash() {
            this(INIT_CAPACITY);
        }

        SimpleHash(int m) {
            this.m = m;
            this.sl = new SimpleList[m];
        }

        private int index(Point p) {
            if (p == null) {
                return 0;
            }
            return p.hashCode() & m - 1;
        }

        private void resize(int chains) {
            SimpleHash temp = new SimpleHash(chains);
            for (int i = 0; i < m; i++) {
                if (sl[i] != null) {
                    for (Point p : sl[i].getAll()) {
                        temp.copy(p);
                    }
                }
            }
            this.n = temp.n;
            this.m = temp.m;
            this.sl = temp.sl;
        }

        private void copy(Point p) {
            int idx = index(p);
            if (sl[idx] == null) {
                sl[idx] = new SimpleList();
            }
            sl[idx].copy(p);
            n++;
        }

        private Status add(Point p) {
            if (n >= 10 * m) {
                resize(2 * m);
            }
            int idx = index(p);
            if (sl[idx] == null) {
                sl[idx] = new SimpleList();
            }
            Status result = sl[idx].add(p);
            if (result == Status.NEW) {
                n++;
            }
            return result;
        }

        private Point[] getAll() {
            Point[] points = new Point[n];
            for (int i = 0, k = 0; i < m; i++) {
                if (sl[i] != null) {
                    Point[] ptemp = sl[i].getAll();
                    if (ptemp != null) {
                        for (int j = 0; j < sl[i].n; j++) {
                            points[k++] = ptemp[j];
                        }
                    }
                }
            }
            return points;
        }
    }

    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) {
            return false;
        }
        long area = 0;
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE, t = r, b = l;
        SimpleHash sh = new SimpleHash();
        for (int[] coord : rectangles) {
            if (sh.add(new Point(coord[0] + " " + coord[1], 1)) == Status.CONFLICT) {
                return false;
            }
            if (sh.add(new Point(coord[2] + " " + coord[3], 4)) == Status.CONFLICT) {
                return false;
            }
            if (sh.add(new Point(coord[0] + " " + coord[3], 8)) == Status.CONFLICT) {
                return false;
            }
            if (sh.add(new Point(coord[2] + " " + coord[1], 2)) == Status.CONFLICT) {
                return false;
            }
            int w = coord[2] - coord[0], h = coord[3] - coord[1];
            area += w * h;
            l = Math.min(l, coord[0]);
            r = Math.max(r, coord[2]);
            t = Math.max(t, coord[3]);
            b = Math.min(b, coord[1]);
        }
        if ((r - l) * (t - b) != area) {
            return false;
        }
        int corners = 0;
        for (Point p : sh.getAll()) {
            if (p.type != 3 && p.type != 5 && p.type != 6
                    && p.type != 9 && p.type != 10 && p.type != 12
                    && p.type != 15) {
                corners++;
            }
        }
        return corners == 4;
    }
}
