/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/5/E
 *
 * One watchman could see the signal of another watchman,
 * if on the circle arc connecting the two hills there was no hill
 * higher than any of the two. As for any two hills there are two different circle arcs
 * connecting them, the signal was seen if the above mentioned condition was satisfied on at least one of the arcs.
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
public class BindianSignalizing {

    private static final int MAX_ARR = (int) 1e6 + 10;

    private int n;
    private InputReader sc;
    private int[] lt, rt, eq, input, height;
    private int max = Integer.MIN_VALUE, maxIdx;

    private void compute() {
        sc = new InputReader(System.in);
        lt = new int[MAX_ARR];
        rt = new int[MAX_ARR];
        eq = new int[MAX_ARR];
        n = sc.nextInt();
        input = new int[n];
        height = new int[n + 1];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
            if (max < input[i]) {
                max = input[i];
                maxIdx = i;
            }
        }
        for (int i = 0; i < n; i++) {
            height[i] = input[(i + maxIdx) % n];
        }
        height[n] = input[maxIdx];
        calculateLeft();
        calculateRight();
        solve();
    }

    private void calculateLeft() {
        for (int i = 1; i < n; i++) {
            lt[i] = i - 1;
            while (lt[i] > 0 && height[i] >= height[lt[i]]) {
                lt[i] = lt[lt[i]];
            }
        }
    }

    private void calculateRight() {
        eq[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            rt[i] = i + 1;
            while (rt[i] < n && height[i] > height[rt[i]]) {
                rt[i] = rt[rt[i]];
            }
            if (rt[i] < n && height[i] == height[rt[i]]) {
                eq[i] = eq[rt[i]] + 1;
                rt[i] = rt[rt[i]];
            }
        }
    }

    private void solve() {
        long result = 0;
        for (int i = 1; i < n; i++) {
            result += eq[i];
            result += 2;
            if (lt[i] == 0 && rt[i] == n) {
                result--;
            }
        }
        System.out.println(result);
        System.exit(0);
    }

    class InputReader {

        private static final int INPUT_KB = 1024;

        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        BindianSignalizing bs = new BindianSignalizing();
        bs.compute();
    }
}
