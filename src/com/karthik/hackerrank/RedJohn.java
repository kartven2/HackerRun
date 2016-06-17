/*-
 * HackerRank problem :
 * https://www.hackerrank.com/challenges/red-john-is-back
 *
 * Find the number of prime numbers (say P) up to M.
 */
package com.karthik.hackerrank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RedJohn {

    class InputReader {

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

    private static final int MAX = (int) 1e7;
    private BigInteger[][] combinationmemo;
    private BitSet primecalc;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/redjohn2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int t = sc.nextInt();
        combinationmemo = new BigInteger[41][11];
        primecalc = new BitSet(MAX);
        while (t-- > 0) {
            int n = sc.nextInt();
            BigInteger m = findNumberOfWays(n);
            primecalc.flip(0, MAX);
            BigInteger ans = getPrimes(m.intValue());
            System.out.println(ans);
        }
    }

    private BigInteger getPrimes(int n) {
        BigInteger count = BigInteger.ZERO;
        if (n < 2) {
            return BigInteger.ZERO;
        }
        n = ((n & 1) + n) >> 1;
        BitSet x = new BitSet(n + 1);
        int i = 4, p = 3, q = 4;
        for (; q < n; q += p >> 1 << 2) {
            if (!x.get(q)) {
                x.set(q);
                for (i = q + p; i < n; i += p) {
                    x.set(i);
                }
            }
            p += 2;
        }
        count = count.add(BigInteger.ONE);
        for (i = 1; i < n; i++) {
            if (!x.get(i)) {
                count = count.add(BigInteger.ONE);
            }
        }
        return count;
    }

    private BigInteger findNumberOfWays(int n) {
        int groups = n / 4;
        BigInteger numberOfWays = BigInteger.valueOf(0);
        for (int i = 0; i <= groups; i++) {
            numberOfWays = numberOfWays.add(combination((n - (3 * i)), i));
        }
        return numberOfWays;
    }

    private BigInteger combination(int x, int y) {
        if (combinationmemo[x][y] != null) {
            return combinationmemo[x][y];
        }
        if (x <= 1 || y == 0) {
            return combinationmemo[x][y] = BigInteger.valueOf(1);
        }
        if (y == 1) {
            return combinationmemo[x][y] = BigInteger.valueOf(x);
        }
        combinationmemo[x][0] = BigInteger.valueOf(1);
        for (int k = 0; k < y; k++) {
            combinationmemo[x][k + 1] = combinationmemo[x][k].multiply(BigInteger.valueOf(x - k)).divide(BigInteger.valueOf(k + 1));
        }
        return combinationmemo[x][y];
    }

    public static void main(String... args) {
        RedJohn rj = new RedJohn();
        rj.compute();
    }
}
