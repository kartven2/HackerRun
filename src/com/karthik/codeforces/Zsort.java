/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/652/B
 *
 * Make the array z-sorted.
 *
 * ai ≥ ai - 1 for all even i,
 * ai ≤ ai - 1 for all odd i > 1.
 * 
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
public class Zsort {

    private int[] input;
    private int[] aux;
    private int n;

    class InputReader {

        private static final int inputkb = 10240;
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

    private void sort(int[] src, int[] dst, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = (lo + hi) >> 1;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        if (src[mid + 1] <= src[mid]) {
            for (int k = lo; k <= hi; k++) {
                dst[k] = src[k];
            }
            return;
        }
        merge(src, dst, lo, mid, hi);
    }

    private void merge(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (src[i] > src[j]) {
                dst[k] = src[i++];
            } else {
                dst[k] = src[j++];
            }
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/zsort"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        input = new int[n];
        aux = new int[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            input[i] = x;
            aux[i] = x;
        }
        sort(aux, input, 0, n - 1);
        int first = 0;
        int next = 0;
        if (n % 2 == 0) {
            first = n - 1;
            next = n - 2;
        } else {
            first = n - 2;
            next = n - 1;
        }
        int ptr = 0;
        for (int k = first; k >= 0; k -= 2) {
            aux[k] = input[ptr++];
        }
        for (int k = next; k >= 0; k -= 2) {
            aux[k] = input[ptr++];
        }
        for (int m : aux) {
            System.out.print(m + " ");
        }
    }

    public static void main(String args[]) {
        Zsort z = new Zsort();
        z.compute();
    }
}
