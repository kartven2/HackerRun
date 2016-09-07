/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/682/B
 *
 * Print one positive integer — the maximum possible
 * value of mex of the array after Alyona applies some (possibly none) operations.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AlyonaAndMex {

    private static final int CUTOFF = 7;
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    private InputReader sc = new InputReader(System.in);

    private void compute() {
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sort(a);
        int j = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] >= j) {
                j++;
            }
        }
        pw.print(j);
        pw.flush();
    }

    private void sort(int[] a) {
        int[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
    }

    private void sort(int[] src, int[] dst, int lo, int hi) {
        if (hi <= lo) {
            //if (hi <= lo + CUTOFF) {
            //insertionSort(dst, lo, hi);
            return;
        }
        int mid = (lo + hi) >> 1;
        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        if (src[mid] <= src[mid + 1]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }

    private void insertionSort(int[] dst, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && dst[j] < dst[j - 1]; j--) {
                exchange(dst, j, j - 1);
            }
        }
    }

    private void exchange(int[] dst, int i, int j) {
        int tmp = dst[i];
        dst[i] = dst[j];
        dst[j] = tmp;
    }

    private void merge(int[] src, int[] dst, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dst[k] = src[j++];
            } else if (j > hi) {
                dst[k] = src[i++];
            } else if (src[i] > src[j]) {
                dst[k] = src[j++];
            } else {
                dst[k] = src[i++];
            }
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        AlyonaAndMex am = new AlyonaAndMex();
        am.compute();
    }

}
