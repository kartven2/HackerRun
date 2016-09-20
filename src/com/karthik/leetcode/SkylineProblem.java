/*
 * LeetCode problem :
 * https://leetcode.com/problems/the-skyline-problem/
 *
 * Output is a list of "key points" (red dots in Figure B)
 * in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SkylineProblem {

    class Point implements Comparable<Point> {

        private int x, y, id;
        private boolean sp;

        Point(int x, int y, int id, boolean sp) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.sp = sp;
        }

        @Override
        public int compareTo(Point that) {
            if (this.x != that.x) {
                return this.x - that.x;
            }
            if (this.sp && that.sp) {
                return that.y - this.y;
            }
            if (!this.sp && !that.sp) {
                return this.y - that.y;
            }
            return this.sp ? -1 : 1;
        }
    }

    class IndexMaxPq {

        private int n;
        private Point[] points;
        private int[] pq, qp;

        IndexMaxPq(int m) {
            points = new Point[++m];
            pq = new int[m];
            qp = new int[m];
            Arrays.fill(qp, -1);
        }

        private boolean isEmpty() {
            return n == 0;
        }

        private void insert(int i, Point point) {
            n++;
            qp[i] = n;
            points[i] = point;
            pq[n] = i;
            swim(n);
        }

        private void swim(int k) {
            while (k > 1 && less(k >> 1, k)) {
                exchange(k, k >> 1);
                k >>= 1;
            }
        }

        private boolean less(int i, int j) {
            int iidx = pq[i], jidx = pq[j];
            if (points[iidx] == null) {
                return true;
            }
            if (points[jidx] == null) {
                return false;
            }
            if (points[iidx].y != points[jidx].y) {
                return points[iidx].y < points[jidx].y;
            }
            return points[iidx].x < points[jidx].x;
        }

        private void exchange(int i, int j) {
            int temp = pq[i];
            pq[i] = pq[j];
            pq[j] = temp;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }

        private Point maxPoint() {
            return points[pq[1]];
        }

        private void delete(int i) {
            int temp = qp[i];
            exchange(temp, n--);
            swim(temp);
            sink(temp);
            qp[i] = -1;
            points[i] = null;
        }

        private void sink(int k) {
            while (k << 1 <= n) {
                int j = k << 1;
                if (j < n && less(j, j + 1)) {
                    j++;
                }
                if (less(j, k)) {
                    break;
                }
                exchange(j, k);
                k = j;
            }
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new LinkedList<>();
        int n = buildings.length;
        if (n == 0) {
            return result;
        }
        Point[] p = new Point[2 * n];
        int pptr = 0;
        for (int i = 0; i < n; i++) {
            p[pptr++] = new Point(buildings[i][0], buildings[i][2], i, true);
            p[pptr++] = new Point(buildings[i][1], buildings[i][2], i, false);
        }
        Arrays.sort(p);
        IndexMaxPq pq = new IndexMaxPq(n);
        for (int i = 0; i < pptr; i++) {
            int cx = p[i].x, cy = p[i].y, cid = p[i].id;
            boolean csp = p[i].sp;
            if (csp) {
                if (pq.isEmpty() || cy > pq.maxPoint().y) {
                    result.add(new int[]{cx, cy});
                }
                pq.insert(cid, p[i]);
            } else {
                pq.delete(cid);
                if (pq.isEmpty()) {
                    result.add(new int[]{cx, 0});
                } else if (pq.maxPoint().y < cy) {
                    result.add(new int[]{cx, pq.maxPoint().y});
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        SkylineProblem skp = new SkylineProblem();
        //int[][] input = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        //int[][] input = {{0, 5, 7}, {5, 10, 7}, {5, 10, 12}, {10, 15, 7}, {15, 20, 7}, {15, 20, 12}, {20, 25, 7}};
        int[][] input = {{0, 5, 7}, {5, 10, 7}, {5, 10, 12}, {10, 15, 7}, {15, 20, 7}, {15, 20, 12}, {20, 25, 7}};
        List<int[]> ans = skp.getSkyline(input);
        for (int[] x : ans) {
            System.out.println(x[0] + "," + x[1]);
        }

    }
}
