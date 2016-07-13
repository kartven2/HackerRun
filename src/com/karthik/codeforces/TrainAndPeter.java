/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/8/A
 *
 * Output one of the four words without inverted commas (forward,backward,both,fantasy).
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
public class TrainAndPeter {

    private static final int R = 256;

    private InputReader sc;
    private char[] text;
    private String pattern1;
    private String pattern2;
    private int[][] dfa;
    private int m;
    private int n;

    private void buildDfa(char[] pattern) {
        m = pattern.length;
        dfa = new int[R][m];
        dfa[pattern[0]][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][x];
            }
            dfa[pattern[j]][j] = j + 1;
            x = dfa[pattern[j]][x];
        }
    }

    private int search(int from, int to, boolean forward) {
        int i = from, j = 0;
        if (forward) {
            for (; i < to && j < m; i++) {
                j = dfa[text[i]][j];
            }
            if (j == m) {
                return i - m;
            }
            return -1;
        }
        for (; i > to && j < m; i--) {
            j = dfa[text[i]][j];
        }
        if (j == m) {
            return i + m;
        }
        return -1;

    }

    private void compute() {
        //sc = new InputReader(System.in);
        try {
            sc = new InputReader(new FileInputStream("./resources/trainandpeter"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        text = sc.readNext().toCharArray();
        n = text.length;
        pattern1 = sc.readNext();
        pattern2 = sc.readNext();
        buildDfa(pattern1.toCharArray());
        int patpos1 = search(0, n, true);
        int patpos2 = search(n - 1, -1, false);
        if (patpos1 == -1 && patpos2 == -1) {
            System.out.println("fantasy");
            System.exit(0);
        }
        buildDfa(pattern2.toCharArray());
        int patpos3 = -1;
        if (patpos1 > -1) {
            patpos3 = search(patpos1 + pattern1.length(), n, true);
        }
        int patpos4 = -1;
        if (patpos2 > -1) {
            patpos4 = search(patpos2 - pattern1.length(), -1, false);
        }
        if (patpos3 == -1 && patpos4 == -1) {
            System.out.println("fantasy");
            System.exit(0);
        }
        if (patpos1 > -1 && patpos2 > -1 && patpos3 > -1 && patpos4 > -1) {
            System.out.println("both");
            System.exit(0);
        }
        if (patpos1 > -1 && patpos3 > -1) {
            System.out.println("forward");
            System.exit(0);
        }
        if (patpos2 > -1 && patpos4 > -1) {
            System.out.println("backward");
            System.exit(0);
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;

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
    }

    public static void main(String... args) {
        TrainAndPeter tp = new TrainAndPeter();
        tp.compute();
    }
}
