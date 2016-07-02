/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/685/A
 *
 * Print one integer in decimal notation — the number
 * of different pairs of hour and minute, such that all digits
 * displayed on the watches are distinct.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RobbersWatch {

    private long maxn;
    private long maxm;
    private int nlen;
    private int mlen;
    private int totallen;
    private Set<String> visited;

    private void compute() {
        InputReader sc = new InputReader(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        maxn = findBase7(n - 1);
        maxm = findBase7(m - 1);

        nlen = Long.toString(maxn).length();
        mlen = Long.toString(maxm).length();
        totallen = nlen + mlen;

        if (totallen > 7) {
            System.out.println(0);
            System.exit(0);
        }

        visited = new HashSet<>();
        System.out.println(permute("", "0123456"));
    }

    private long permute(String prefix, String x) {
        int n = x.length();
        if (n == 0) {
            String hr = prefix.substring(0, nlen);
            String mn = prefix.substring(nlen, totallen);
            String key = hr + mn;
            if (visited.contains(key)) {
                return 0;
            }
            visited.add(key);
            return (Integer.parseInt(hr) <= maxn && Integer.parseInt(mn) <= maxm) ? 1 : 0;
        }
        long count = 0;
        for (int i = 0; i < n; i++) {
            count += permute(prefix + x.charAt(i), x.substring(0, i) + x.substring(i + 1, n));
        }
        return count;
    }

    private long findBase7(int x) {
        int pow = 0;
        long value = 0;
        while (true) {
            value += Math.pow(10, pow++) * (x % 7);
            x /= 7;
            if (x < 7) {
                value += Math.pow(10, pow) * x;
                return value;
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

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
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
        RobbersWatch rb = new RobbersWatch();
        rb.compute();
    }
}
