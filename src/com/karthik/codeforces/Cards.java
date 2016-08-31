/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/701/A
 *
 * Print n / 2 pairs of integers, the i-th pair denote
 * the cards that should be given to the i-th player.
 * Each card should be given to exactly one player.
 * Cards are numbered in the order they appear in the input.
 * It is guaranteed that solution exists.
 * If there are several correct answers, you are allowed to print any of them.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Cards {

    class SimpleStack {

        private int[] list;
        private int i;

        SimpleStack(int c) {
            list = new int[c];
        }

        void add(int key) {
            list[i++] = key;
        }

        boolean isEmpty() {
            return i == 0;
        }

        int remove() {
            return list[--i];
        }
    }

    private void sort(int[] x) {
        sort(x, 0, x.length - 1);
    }

    private void sort(int[] x, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, gt = hi, v = x[lo], i = lo;
        while (i <= gt) {
            if (x[i] < v) {
                exchange(x, i++, lt++);
            } else if (x[i] > v) {
                exchange(x, i, gt--);
            } else {
                i++;
            }
        }
        sort(x, lo, lt - 1);
        sort(x, gt + 1, hi);
    }

    private void exchange(int[] x, int i, int j) {
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/cards2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        SimpleStack[] ss = new SimpleStack[101];
        int n = sc.nextInt();
        int[] values = new int[n];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int a = sc.nextInt();
            if (ss[a] == null) {
                ss[a] = new SimpleStack(101);
            }
            ss[a].add(i);
            values[i - 1] = a;
        }
        sort(values);
        for (int i = 0; i < n / 2; i++) {
            System.out.println(ss[values[i]].remove() + " " + ss[values[n - 1 - i]].remove());
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        Cards c = new Cards();
        c.compute();
    }
}
