/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/671/B
 *
 * Find the difference between richest and poorest persons wealth after value days.
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DiffAfterKDays {

    private int n;
    private int k;
    private InputReader sc;
    private int output;
    private int[] a;

    private void input() {
        sc = new InputReader(System.in);
        n = sc.nextInt();
        a = new int[n];
        k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
    }

    private void compute() {
        int lo = 1, hi = (int) 1e9 + 7, x = 1, y = 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            long rich = 0, poor = 0;
            for (int i : a) {
                if (i < mid) {
                    poor += mid - i;
                } else {
                    rich += i - mid;
                }
            }
            if (rich >= poor && poor <= k) {
                x = mid;
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        lo = 1;
        hi = (int) 1e9 + 7;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            long rich = 0, poor = 0;
            for (int i : a) {
                if (i < mid) {
                    poor += mid - i;
                } else {
                    rich += i - mid;
                }
            }
            if (poor >= rich && rich <= k) {
                y = mid;
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        output = y - x;
    }

    static class InputReader {

        private static final int inputKb = 32768;
        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        public InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), inputKb);
        }

        private String readNext() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return stringTokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private void output() {
        System.out.println(output);
    }

    public static void main(String[] args) {
        DiffAfterKDays b = new DiffAfterKDays();
        b.input();
        b.compute();
        b.output();
    }
}
