/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/689/D
 *
 * Print the only integer number — the number of occasions
 * the robot will count, thus for how many pairs  is satisfied.
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
public class FriendsAndSubsequences {

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

    class Deque {

        private int[] q;
        private int n;
        private int f;
        private int b;
        private int c;

        Deque(int capacity) {
            q = new int[capacity];
            f = 1;
            c = capacity - 1;
        }

        private boolean empty() {
            return n == 0;
        }

        private void inLast(int value) {
            n++;
            q[b--] = value;
            b = (b % c + c) % c;
        }

        private void inFirst(int value) {
            n++;
            q[f++] = value;
            f = (f % c + c) % c;
        }

        private int outLast() {
            n--;
            b++;
            b = (b % c + c) % c;
            return q[b];
        }

        private int outFirst() {
            n--;
            f--;
            f = (f % c + c) % c;
            return q[f];
        }

        private int last() {
            return q[((b + 1) % c + c) % c];
        }

        private int first() {
            return q[((f - 1) % c + c) % c];
        }
    }

    void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/friendsandsubsequences"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int i = 1;
        while (i <= n) {
            a[i++] = sc.nextInt();
        }
        i = 1;
        while (i <= n) {
            b[i++] = sc.nextInt();
        }
        i = 1;
        int j = 1;
        long ans = 0;
        Deque x = new Deque((int) 2e5 + 3);
        Deque y = new Deque((int) 2e5 + 3);
        for (; i <= n; i++) {
            while (!x.empty() && a[x.last()] <= a[i]) {
                x.outLast();
            }
            while (!y.empty() && b[y.last()] >= b[i]) {
                y.outLast();
            }
            x.inLast(i);
            y.inLast(i);
            while (j <= i && a[x.first()] - b[y.first()] > 0) {
                j++;
                while (!x.empty() && x.first() < j) {
                    x.outFirst();
                }
                while (!y.empty() && y.first() < j) {
                    y.outFirst();
                }
            }
            if (!x.empty() && !y.empty() && a[x.first()] == b[y.first()]) {
                ans += Math.min(x.first(), y.first()) - j + 1;
            }
        }
        System.out.println(ans);
    }

    public static void main(String... args) {
        FriendsAndSubsequences fs = new FriendsAndSubsequences();
        fs.compute();
    }
}
