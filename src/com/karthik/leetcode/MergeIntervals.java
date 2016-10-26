/*
 * Leetcode: https://leetcode.com/problems/merge-intervals/
 *
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MergeIntervals {

    public class Interval {

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

    private Interval[] sort(List<Interval> intervals) {
        Interval[] result = new Interval[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            result[i] = intervals.get(i);
        }
        sort(result, 0, result.length - 1);
        return result;
    }

    private void sort(Interval[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int lt = lo, gt = hi, i = lt + 1;
        Interval v = a[lo];
        while (i <= gt) {
            if (v.start > a[i].start) {
                exchange(a, lt++, i++);
            } else if (v.start < a[i].start) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private void exchange(Interval[] a, int v, int w) {
        Interval temp = a[v];
        a[v] = a[w];
        a[w] = temp;
    }

    private boolean intersect(Interval a, Interval b) {
        return a.end >= b.start;
    }

    private Interval merge(Interval a, Interval b) {
        return new Interval(a.start, Math.max(a.end, b.end));
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return intervals;
        }
        Stack<Interval> stack = new Stack<>();
        Interval[] intvlArr = sort(intervals);
        stack.push(intvlArr[0]);
        for (int i = 1; i < intvlArr.length; i++) {
            Interval b = intvlArr[i];
            if (!stack.isEmpty()) {
                Interval a = stack.peek();
                if (intersect(a, b)) {
                    Interval c = merge(a, b);
                    stack.pop();
                    stack.push(c);
                } else {
                    stack.push(b);
                }
            } else {
                stack.push(b);
            }
        }
        List<Interval> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
