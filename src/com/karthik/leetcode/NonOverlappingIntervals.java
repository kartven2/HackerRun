/*-
 * LeetCode: https://leetcode.com/problems/non-overlapping-intervals/#/description
 * Given a collection of intervals, find the minimum number of intervals you need to
 * remove to make the rest of the intervals non-overlapping.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NonOverlappingIntervals {

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
        NonOverlappingIntervals nop = new NonOverlappingIntervals();
        Interval ia = new Interval(1, 100);
        Interval ib = new Interval(1, 3);
        Interval ic = new Interval(1, 104);
        Interval[] ivs = {ia, ib, ic};
        System.out.println(nop.eraseOverlapIntervals(ivs));
    }

    public int eraseOverlapIntervals(Interval[] intvls) {
        if (intvls == null || intvls.length == 0) {
            return 0;
        }
        Arrays.sort(intvls, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return (a.start - b.start);
            }
        });
        int end = intvls[0].end, count = 0;
        for (int i = 1; i < intvls.length; i++) {
            if (end > intvls[i].start) {
                count++;
                end = Math.min(end, intvls[i].end);
            } else {
                end = intvls[i].end;
            }
        }
        return count;
    }
}
