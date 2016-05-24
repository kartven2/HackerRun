/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/651/B
 *
 * Find the maximum possible number of indices i (1 ≤ i ≤ n - 1), 
 * such that ai + 1 > ai.
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
public class BeautifulPaintings {

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

    private static final int RANGE = 10001;
    private int[] input;
    private int[] count;
    private int[] keyIdx;
    private int n;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/beautifulpaintings2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        input = new int[n];
        count = new int[RANGE];
        keyIdx = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        shuffle();
        sort(0, n - 1);
        int idx = 0;
        for (int i = 0; i < n; i++) {
            count[input[i]]++;
            if (i == 0) {
                keyIdx[idx++] = input[i];
            } else if (input[i] != input[i - 1]) {
                keyIdx[idx++] = input[i];
            }
        }
        int j = 0;
        int result = 0;
        while (j < n) {
            int lastKey = -1;
            for (int k = 0; k < idx; k++) {
                if (count[keyIdx[k]] > 0) {
                    if (lastKey > -1) {
                        result++;
                    }
                    count[keyIdx[k]]--;
                    j++;
                    lastKey = keyIdx[k];
                }
            }
        }
        System.out.println(result);
    }

    private void shuffle() {
        for (int i = n - 1; i > 0; i--) {
            int r = (int) (Math.random() * (i + 1));
            exchange(i, r);
        }
    }

    private void exchange(int x, int y) {
        int tmp = input[x];
        input[x] = input[y];
        input[y] = tmp;
    }

    private void sort(int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo, gt = hi;
        int i = lo;
        int v = input[lo];
        while (i <= gt) {
            if (v > input[i]) {
                exchange(lt++, i++);
            } else if (v < input[i]) {
                exchange(i, gt--);
            } else {
                i++;
            }
        }
        sort(lo, lt - 1);
        sort(gt + 1, hi);
    }

    public static void main(String[] args) {
        BeautifulPaintings b = new BeautifulPaintings();
        b.compute();
    }
}
