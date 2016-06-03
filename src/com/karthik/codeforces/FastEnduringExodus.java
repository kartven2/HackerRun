/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/645/C
 *
 * Find the minimum possible distance between Farmer John's room and his farthest cow.
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastEnduringExodus {

    class InputReader {

        private static final int INPUT_KB = 5 * 1024;
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

    private int[] input;
    private int n;
    private int k;

    private int nextFreeRoom(int fromIdx) {
        do {
            fromIdx++;
        } while (fromIdx < n && input[fromIdx] == 1);
        return fromIdx;
    }

    private void compute() {
        InputReader sc = new InputReader(System.in);
        n = sc.nextInt();
        input = new int[n];
        k = sc.nextInt();
        String s = sc.readNext();
        for (int i = 0; i < n; i++) {
            input[i] = s.charAt(i) == '0' ? 0 : 1;
        }
        int left = nextFreeRoom(-1);
        int john = left;
        int right = left;
        for (int i = 0; i < k; i++) {
            right = nextFreeRoom(right);
        }
        int result = Integer.MAX_VALUE;
        while (right < n) {
            while (Math.max(john - left, right - john) > Math.max(nextFreeRoom(john) - left, right - nextFreeRoom(john))) {
                john = nextFreeRoom(john);
            }
            result = Math.min(result, Math.max(john - left, right - john));
            left = nextFreeRoom(left);
            right = nextFreeRoom(right);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        FastEnduringExodus ff = new FastEnduringExodus();
        ff.compute();
    }
}
