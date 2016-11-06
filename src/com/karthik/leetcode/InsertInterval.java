/*
 * https://leetcode.com/problems/insert-interval/
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
* This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].  
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class InsertInterval {

    private boolean intsct(Interval a, Interval b) {
        if (a.start == b.start) {
            return true;
        }
        return a.end >= b.start;
    }

    private Interval merge(Interval a, Interval b) {
        a.start = Math.min(a.start, b.start);
        a.end = Math.max(a.end, b.end);
        return a;
    }

    private int binSearch(int[] a, int lo, int hi, int k) {
        while (lo <= hi) {
            int mid = lo + hi >> 1;
            if (a[mid] == k) {
                if (lo <= mid - 1 && a[mid - 1] == k) {
                    hi = mid - 1;
                } else {
                    return mid;
                }
            } else if (a[mid] < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new LinkedList<>();
        if (intervals == null || intervals.isEmpty()) {
            result.add(newInterval);
            return result;
        }
        if (newInterval == null) {
            return intervals;
        }
        Interval[] arr = new Interval[intervals.size() + 1];
        int[] st = new int[intervals.size()];
        int sp = 0;
        for (Interval x : intervals) {
            st[sp++] = x.start;
        }
        sp = binSearch(st, 0, st.length - 1, newInterval.start);
        for (int i = 0; i < sp; i++) {
            arr[i] = intervals.get(i);
        }
        arr[sp] = newInterval;
        int j = sp, m = sp;
        while (j < intervals.size()) {
            arr[++m] = intervals.get(j++);
        }
        int top = 1;
        for (int k = 1; k < arr.length; k++) {
            Interval ci = arr[k];
            arr[k] = null;
            if (intsct(arr[top - 1], ci)) {
                arr[top - 1] = merge(arr[top - 1], ci);
            } else {
                arr[top++] = ci;
            }
        }
        for (int i = 0; i < top; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}

class Interval {

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
