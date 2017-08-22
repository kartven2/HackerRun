/*
 * LeetCode: https://leetcode.com/problems/minimum-time-difference/description/
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the
 * minimum minutes difference between any two time points in the list.
 * The number of time points in the given list is at least 2 and won't exceed 20000.
 * The input time is legal and ranges from 00:00 to 23:59.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumTimeDifference {

    static class Time implements Comparable<Time> {

        int h, m;

        Time(int h, int m) {
            this.h = h;
            this.m = m;
        }

        @Override
        public int compareTo(Time b) {
            if (this.h != b.h) {
                return this.h - b.h;
            }
            return this.m - b.m;
        }

        public static int timeDiff(Time a, Time b) {
            if (a.h <= b.h) {
                int ans = (60 - a.m);
                ans += b.m;
                if (a.h == b.h) {
                    return ans % 60;
                }
                ans += (b.h - (a.h + 1)) * 60;
                return ans;
            }
            return timeDiff(a, new Time(b.h + 24, b.m));
        }
    }

    public int findMinDifference(List<String> a) {
        if (a == null || a.size() < 2) {
            return 0;
        }
        List<Time> list = new ArrayList<>();
        for (String x : a) {
            String[] str = x.split(":");
            Time t = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
            list.add(t);
        }
        Collections.sort(list);
        int min = Time.timeDiff(list.get(list.size() - 1), list.get(0));
        for (int i = list.size() - 1; i > 0; i--) {
            min = Math.min(min, Time.timeDiff(list.get(i - 1), list.get(i)));
        }
        return min;
    }
}
