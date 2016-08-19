/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/675/E
 *
 * Print the sum of ρi,j among all pairs of 1 ≤ i < j ≤ n.
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
public class FastTrainsAndStatistics {

    int t[] = new int[(int) 1e5 + 5];
    int maxpos[] = new int[(int) 1e5 + 5];
    int ind[] = new int[(int) 1e5 + 5];
    long ans[] = new long[(int) 1e5 + 5];
    int n;

    private void update(int pos, int val) {
        for (; pos <= n; pos += (pos & -pos)) {
            t[pos] = Math.max(t[pos], val);
        }
    }

    private int query(int pos) {
        int max = 0;
        for (; pos > 0; pos -= (pos & -pos)) {
            max = Math.max(max, t[pos]);
        }
        return max;
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/trainsandstatistics"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        for (int i = 1; i < n; i++) {
            maxpos[i] = sc.nextInt();
        }
        update(n, n);
        ind[n] = n;

        for (int i = n - 1; i > 0; i--) {
            int nextpos = query(maxpos[i]);
            int nextind = ind[nextpos];
            ans[i] = (n - i) - (maxpos[i] - nextind);
            ans[i] += ans[nextind];
            ind[maxpos[i]] = i;
            update(i, maxpos[i]);
        }

        long result = 0;
        for (int i = n - 1; i > 0; --i) {
            result += ans[i];
        }
        System.out.print(result);
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
        FastTrainsAndStatistics tas = new FastTrainsAndStatistics();
        tas.compute();
    }
}
