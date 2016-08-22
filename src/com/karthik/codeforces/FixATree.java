/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/698/B
 *
 * In the first line print the minimum number of elements to change,
 * in order to get a valid sequence.
 * In the second line, print any valid sequence possible to get
 * from (a1, a2, ..., an) in the minimum number of changes.
 * If there are many such sequences, any of them will be accepted.
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
public class FixATree {

    int n;
    int sn;
    int[] pi;
    int[] mi;
    int[] ri;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/fixatree"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        pi = new int[n + 1];
        mi = new int[n + 1];
        ri = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            pi[i] = sc.nextInt();
            if (sn == 0 && i == pi[i]) {
                sn = i;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (mi[i] == 0) {
                dfs(i);
            }
        }
        int result = 0;
        if (sn == 0) {
            result++;
            sn = pi[ri[1]] = ri[1];
        }
        for (int i = 1; i <= n; i++) {
            if (sn != pi[ri[i]]) {
                pi[ri[i]] = sn;
                result++;
            }
        }
        System.out.println(result);
        for (int i = 1; i <= n; i++) {
            System.out.print(pi[i] + " ");
        }
    }

    private int dfs(int i) {
        mi[i] = 1;
        int pmi = mi[pi[i]];
        int sr;
        switch (pmi) {
            case 0:
                sr = dfs(pi[i]);
                break;
            case 1:
                sr = i;
                break;
            case 2:
            default:
                sr = ri[pi[i]];
                break;
        }
        mi[i] = 2;
        return ri[i] = sr;
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
        FixATree fat = new FixATree();
        fat.compute();
    }
}
