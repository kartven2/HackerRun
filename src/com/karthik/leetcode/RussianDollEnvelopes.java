/*
 * https://leetcode.com/problems/russian-doll-envelopes/
 *
 * You have a number of envelopes with widths and heights given
 * as a pair of integers (w, h). One envelope can fit into another
 * if and only if both the width and height of one envelope is greater than
 * the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll?
 * (put one inside other)
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]],
 * the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RussianDollEnvelopes {

    class Mail implements Comparable<Mail> {

        int w, h;

        private Mail(int w, int h) {
            this.w = w;
            this.h = h;
        }

        @Override
        public int compareTo(Mail b) {
            if (this == b) {
                return 0;
            }
            if (this.w != b.w) {
                return this.w - b.w;
            }
            return b.h - this.h;
        }

        @Override
        public String toString() {
            return "[" + this.w + ", " + this.h + "] ,";
        }
    }

    private int ceil(Mail[] a, int[] inc, int lo, int hi, Mail key) {
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (key.h == a[inc[mid]].h) {
                return mid;
            } else if (key.h < a[inc[mid]].h) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    public int maxEnvelopes(int[][] env) {
        if (env == null || env.length == 0) {
            return 0;
        }
        int n = env.length;
        Mail[] m = new Mail[n];
        for (int i = 0; i < n; i++) {
            m[i] = new Mail(env[i][0], env[i][1]);
        }
        Arrays.sort(m);
        int[] p = new int[n];
        int[] inc = new int[n];
        Arrays.fill(p, -1);
        Arrays.fill(inc, -1);
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || m[i].h > m[inc[len - 1]].h) {
                inc[len] = i;
                p[i] = i == 0 ? -1 : inc[len - 1];
                len++;
            } else {
                int idx = ceil(m, inc, 0, len - 1, m[i]);
                inc[idx] = i;
                p[i] = idx == 0 ? -1 : inc[idx - 1];
            }
        }
        int j = len, x = j > 0 ? inc[j - 1] : -1;
        while (--j > -1 && x > -1) {
            System.out.println(m[x]);
            x = p[j];
        }
        return len;
    }

    public static void main(String... args) {
        int[][] env = {{1, 1}, {1, 1}, {1, 1}};
        RussianDollEnvelopes rde = new RussianDollEnvelopes();
        System.out.println(rde.maxEnvelopes(env));
    }
}
