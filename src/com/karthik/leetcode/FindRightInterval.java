/*
 * LeetCode: https://leetcode.com/problems/find-right-interval/#/description
 * Given a set of intervals, for each of the interval i, check if there exists an interval j
 * whose start point is bigger than or equal to the end point of the interval i,
 * which can be called that j is on the "right" of i.
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j
 * has the minimum start point to build the "right" relationship for interval i.
 * If the interval j doesn't exist, store -1 for the interval i.
 * Finally, you need output the stored value of each interval as an array.
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindRightInterval {

    static class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String... args) {
        Interval i1 = new Interval(3, 4);
        Interval i2 = new Interval(2, 3);
        Interval i3 = new Interval(1, 2);
        FindRightInterval fr = new FindRightInterval();
        fr.findRightInterval(new Interval[]{i1, i2, i3});

    }

    class Node {

        int a, b;

        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private int binsearch(Node[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid].a == key) {
                return a[mid].b;
            } else if (a[mid].a < key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo == a.length ? -1 : a[lo].b;
    }

    public int[] findRightInterval(Interval[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        int n = a.length, sp = 0;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        if (n < 2) {
            return res;
        }
        Node[] nds = new Node[n];
        for (Interval x : a) {
            nds[sp] = new Node(x.start, sp++);
        }
        Arrays.sort(nds, (Node o1, Node o2) -> o1.a - o2.a);
        for (int i = 0; i < n; i++) {
            int key = a[i].end;
            res[i] = binsearch(nds, key);
        }
        return res;
    }
}
