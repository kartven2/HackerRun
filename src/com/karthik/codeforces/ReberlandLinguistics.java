/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/666/A
 *
 * Find the number of distinct possible suffixes of length 2 or 3 for a
 * given string with minimum length 4 or more.
 * Two identical suffixes should not be appended. 
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
public class ReberlandLinguistics {

    private String input;

    static class InputReader {

        private static final int inputkb = 10240;
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
    }

    static class Tst {

        private Node root;
        private int n;

        static class Node {

            private char c;
            private Node l;
            private Node m;
            private Node r;
            private boolean eos;
        }

        private void insert(String m) {
            root = insert(root, 0, m);
        }

        private Node insert(Node x, int d, String m) {
            char c = m.charAt(d);
            if (x == null) {
                x = new Node();
                x.c = c;
            }

            if (c < x.c) {
                x.l = insert(x.l, d, m);
            } else if (c > x.c) {
                x.r = insert(x.r, d, m);
            } else if (d < m.length() - 1) {
                x.m = insert(x.m, d + 1, m);
            } else if (!x.eos) {
                n++;
                x.eos = true;
            }

            return x;
        }

        private void print(Node x, StringBuilder prefix) {
            if (x == null) {
                return;
            }
            print(x.l, prefix);
            if (x.eos) {
                System.out.println(prefix.toString() + x.c);
            }
            print(x.m, prefix.append(x.c));
            prefix.deleteCharAt(prefix.length() - 1);
            print(x.r, prefix);
        }

        private void print() {
            print(root, new StringBuilder());
        }
    }

    private void input() {
        //InputReader sc = new InputReader(System.in);
        //input = sc.readNext();
        input = "oawtxikrpvfuzugjweki";
        compute();
    }

    private void compute() {
        int result = 0;
        if (input.length() <= 6) {
            System.out.println(result);
            return;
        }

        Tst tst = new Tst();

        for (int i = input.length() - 1; i - 3 >= 3; i -= 2) {
            if (i - 3 == 3) {
                tst.insert(input.substring(i - 1, i + 1));
                break;
            }

            if (input.charAt(i - 2) == input.charAt(i)
                    && input.charAt(i - 1) == input.charAt(i - 3)) {
                break;
            }

            tst.insert(input.substring(i - 1, i + 1));
        }

        for (int i = input.length() - 4; i - 3 >= 3; i -= 2) {
            if (i - 3 == 3) {
                tst.insert(input.substring(i - 1, i + 1));
                break;
            }

            if (input.charAt(i - 2) == input.charAt(i)
                    && input.charAt(i - 1) == input.charAt(i - 3)) {
                break;
            }

            tst.insert(input.substring(i - 1, i + 1));
        }

        for (int i = input.length() - 1; i - 5 >= 3; i -= 3) {
            if (i - 5 == 3) {
                tst.insert(input.substring(i - 2, i + 1));
                break;
            }

            if (input.charAt(i - 3) == input.charAt(i)
                    && input.charAt(i - 1) == input.charAt(i - 4)
                    && input.charAt(i - 2) == input.charAt(i - 5)) {
                break;
            }

            tst.insert(input.substring(i - 2, i + 1));
        }

        for (int i = input.length() - 3; i - 5 >= 2; i -= 2) {
            if (i - 5 == 2) {
                tst.insert(input.substring(i - 2, i + 1));
                break;
            }

            if (input.charAt(i - 3) == input.charAt(i)
                    && input.charAt(i - 1) == input.charAt(i - 4)
                    && input.charAt(i - 2) == input.charAt(i - 5)) {
                break;
            }

            tst.insert(input.substring(i - 2, i + 1));
        }

        for (int i = input.length() - 3; i - 5 >= 2; i -= 3) {
            if (i - 5 == 2) {
                tst.insert(input.substring(i - 2, i + 1));
                break;
            }

            if (input.charAt(i - 3) == input.charAt(i)
                    && input.charAt(i - 1) == input.charAt(i - 4)
                    && input.charAt(i - 2) == input.charAt(i - 5)) {
                break;
            }

            tst.insert(input.substring(i - 2, i + 1));
        }

        System.out.println(tst.n);
        tst.print();
    }

    public static void main(String[] args) {
        ReberlandLinguistics r = new ReberlandLinguistics();
        r.input();
    }
}
