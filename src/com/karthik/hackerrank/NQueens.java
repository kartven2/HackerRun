/*
 * N-Queens practice n-by-n board
 */
package com.karthik.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NQueens {

    private int[] solution;
    private int n;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        //n = sc.nextInt();
        n = 8;
        solution = new int[n];
        solve(0);
        for (int i = 0; i < solution.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(solution[i]);
            if (i < solution.length - 1) {
                sb.append(",");
            }
            System.out.print(sb.toString());
        }
    }

    private void solve(int x) {
        for (int y = 0; y < n && solution[n - 1] == 0; y++) {
            if (canAdd(x, y)) {
                solution[x] = y;
                solve(x + 1);
            }
        }
    }

    private boolean canAdd(int x, int y) {
        for (int row = 0; row < x; row++) {
            if (solution[row] == y || (x + y) == (row + solution[row]) || (x - y) == (row - solution[row])) {
                return false;
            }
        }
        return true;
    }

    class InputReader {

        private static final int INPUT_KB = 5 * 1024;
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
        NQueens nq = new NQueens();
        nq.compute();
    }
}