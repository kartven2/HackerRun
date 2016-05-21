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
    private char[] ic;

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
            n++;
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
            } else {
                x.eos = true;
            }

            return x;
        }
        
        private void print(Node x, StringBuilder prefix) {
          if(x==null) {
              return;
          }
          if(x.l != null) {
              print(x.l, prefix);
          }
          if(x.eos) {
              System.out.println(prefix.toString());
          }
          if(x.m != null) {
              print(x.m, prefix.append(x.c));
          }
          prefix.deleteCharAt(prefix.length()-1);
          if(x.r != null) {
              print(x.r, prefix);
          }
        }

        private void print() {
            print(root, new StringBuilder());
        }
    }

    private void input() {
        InputReader sc = new InputReader(System.in);
        input = sc.readNext();
        ic = input.toCharArray();
        compute();
    }

    private void compute() {
        int result = 0;
        if (input.length() <= 6) {
            System.out.print(result);
            return;
        }
        
        Tst st2d = new Tst();
        Tst st3d = new Tst();
        
        for(int i=ic.length-1; i>5; i--) {
        }
    }
}
