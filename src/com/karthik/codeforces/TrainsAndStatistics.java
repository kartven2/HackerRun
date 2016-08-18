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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class TrainsAndStatistics {

    private static final int MAX = Integer.MAX_VALUE;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/trainsandstatistics2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        List<Integer>[] pre = new ArrayList[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            pre[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int a = sc.nextInt();
            for (int j = i + 1; j <= a; j++) {
                pre[j].add(i);
                dp[i][j] = 1;
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (dp[i][j] == 0) {
                    int min = MAX;
                    for (int k : pre[j]) {
                        if (k > i && k < j) {
                            min = Math.min(min, dp[i][k] + dp[k][j]);
                        }
                    }
                    dp[i][j] = min;
                }
                ans += dp[i][j];
            }
        }
        System.out.println(ans);
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
        TrainsAndStatistics tas = new TrainsAndStatistics();
        tas.compute();
    }
}
