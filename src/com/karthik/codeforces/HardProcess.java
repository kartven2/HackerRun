/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/660/C
 *
 * Let's denote the length of the longest subsegment
 * of consecutive elements in a, consisting of only numbers one, as f(a). 
 * You can change no more than k zeroes to ones to maximize f(a).
 * On the first line print a non-negative integer z — the maximal value of f(a) 
 * after no more than k changes of zeroes to ones. On the second line print n integers
 * aj — the elements of the array a after the changes.
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
public class HardProcess {

    static class InputReader {

        private static final int inputkb = 5 * 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), inputkb);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String[] args) {
        InputReader sc = new InputReader(System.in);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println("0");
            return;
        }
        int k = sc.nextInt();
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        int wl = 0;
        int wr = 0;
        int best = 0;
        int bestl = 0;
        int bestr = -1;
        int zc = 0;
        while (wr < n) {
            if (input[wr] == 0) {
                if (zc < k) {
                    zc++;
                } else {
                    while (wl <= wr && input[wl++] == 1);
                }
            }
            if (best < wr - wl + 1) {
                best = wr - wl + 1;
                bestl = wl;
                bestr = wr;
            }
            wr++;
        }
        System.out.println(best);
        for (int i = 0; i < n; i++) {
            if (i >= bestl && i <= bestr) {
                System.out.print("1 ");
            } else {
                System.out.print(input[i] + " ");
            }
        }
    }
}
