/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/678/E
 *
 * Output a real number — the probability that Jedi Ivan
 * will stay alive after the Tournament.
 * Absolute or relative error of the answer must not exceed 10pow-6.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AnotherSithTournament {

    private InputReader sc = new InputReader(System.in);
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    private int n;
    private double[] dp;
    private double[][] prob;

    private void compute() {
        sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/anothersithtournament"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        prob = new double[n][n];
        dp = new double[(1 << n) + 7];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                prob[i][j] = sc.nextDouble();
            }
        }
        pw.print(winprob(0, -1));
        pw.flush();
    }

    private double winprob(int mask, int lastlooser) {
        if (lastlooser == 0) {
            return 0;
        }
        if (dp[mask] > -1) {
            return dp[mask];
        }
        if (Integer.bitCount(mask) == n - 1) {
            return 1;
        }
        double max = 0;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                for (int j = 0; j < n; j++) {
                    if (i != j && (mask & (1 << j)) == 0) {
                        double p = winprob(mask | (1 << j), j) * prob[i][j];
                        double q = winprob(mask | (1 << i), i) * prob[j][i];
                        max = Math.max(max, p + q);
                    }
                }
            }
        }
        return dp[mask] = max;
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
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        double nextDouble() {
            return Double.parseDouble(readNext());
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        AnotherSithTournament ast = new AnotherSithTournament();
        ast.compute();
    }
}
