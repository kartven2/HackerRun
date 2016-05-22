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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReberlandLinguistics {

    private String input;
    private Tst tst;
    private Set<SuffixInstance> createdSet;

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
        InputReader sc = new InputReader(System.in);
        input = sc.readNext();
        compute();
    }

    private void compute() {
        int result = 0;
        if (input.length() <= 6) {
            System.out.println(result);
            return;
        }
        tst = new Tst();
        createdSet = new HashSet<>();
        compute("", input.length());
        System.out.println(tst.n);
        tst.print();
    }

    private void compute(String suffix, int idx) {
        SuffixInstance suffixInstance = new SuffixInstance(suffix, idx);
        if (!createdSet.add(suffixInstance)) {
            return;
        }

        if (idx >= 7) {
            String stride2Entry = input.substring(idx - 2, idx);
            if (!suffix.equals(stride2Entry)) {
                tst.insert(stride2Entry);
                compute(stride2Entry, idx - 2);
            }
        }

        if (idx >= 8) {
            String stride3Entry = input.substring(idx - 3, idx);
            if (!suffix.equals(stride3Entry)) {
                tst.insert(stride3Entry);
                compute(stride3Entry, idx - 3);
            }
        }
    }

    static class SuffixInstance {

        private String suffix;
        private int idx;

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 89 * hash + Objects.hashCode(this.suffix);
            hash = 89 * hash + this.idx;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SuffixInstance other = (SuffixInstance) obj;
            if (!Objects.equals(this.suffix, other.suffix)) {
                return false;
            }
            if (this.idx != other.idx) {
                return false;
            }
            return true;
        }

        public SuffixInstance(String suffix, int idx) {
            this.suffix = suffix;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        ReberlandLinguistics reberlandLinguistics = new ReberlandLinguistics();
        reberlandLinguistics.input();
    }
}
