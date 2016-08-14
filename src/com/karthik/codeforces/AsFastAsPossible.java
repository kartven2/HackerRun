/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/700/A
 *
 * Print the real number — the minimum time in which
 * all pupils can reach the place of excursion.
 * Your answer will be considered correct if its absolute
 * or relative error won't exceed 10^- 6.
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
public class AsFastAsPossible {

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/asfastaspossible"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        double l = (double) sc.nextInt();
        double v1 = (double) sc.nextInt();
        double v2 = (double) sc.nextInt();
        int k = sc.nextInt();
        int ngs = (n / k);
        if (n % k > 0) {
            ngs++;
        }
        double lo = 0, mid, hi = l / v1, tp, x, y;
        for (; hi - lo >= 1e-7;) {
            mid = (lo + hi) / 2;
            tp = x = y = 0;
            for (int i = 0; i < ngs; i++) {
                //v1*tp + v2*x + v1*(mid-x-tp) = l
                //v1*tp + v2*x + v1*mid - v1*x - v1*tp = l
                //v2*x + v1*mid - v1*x = l
                //x = (l-v1*mid) / (v2-v1)
                x = (l - v1 * mid) / (v2 - v1);
                tp += x;
                if (i < ngs - 1) {
                    //v1*x + v1*y = v2*x - v2*y
                    //y = (v2*x - v1*x) / (v1+v2)
                    //y = x*(v2-v1) / (v1+v2)
                    y = (x * (v2 - v1)) / (v1 + v2);
                    tp += y;
                }
            }
            if (tp > mid) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        System.out.print(lo);
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
        AsFastAsPossible afap = new AsFastAsPossible();
        afap.compute();
    }
}
