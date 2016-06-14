/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/652/C
 *
 * Count the number of different intervals (x, y) (1 ≤ x ≤ y ≤ n) 
 * that do not contain any foe pairs.
 *
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FoePairs {

    static class InputReader {

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

        private long nextLong() {
            return Integer.parseInt(readNext());
        }
    }

    class RbTree {

        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private Node root;
        private Comparator<Interval> comparator;

        RbTree(Comparator<Interval> comparator) {
            this.comparator = comparator;
        }

        class Node {

            private Interval key;
            private Node left, right;
            private boolean color;
            private int sz;

            Node(Interval key, boolean color) {
                this.key = key;
                this.color = color;
                this.sz = 1;
            }
        }

        private int size() {
            return size(root);
        }

        private int size(Node x) {
            if (x == null) {
                return 0;
            }
            return x.sz;
        }

        private boolean isRed(Node x) {
            if (x == null) {
                return false;
            }
            return x.color;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            x.sz = h.sz;
            h.sz = 1 + size(h.left) + size(h.right);
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            x.sz = h.sz;
            h.sz = 1 + size(h.left) + size(h.right);
            return x;
        }

        private void flipColors(Node h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }

        private void put(Interval key) {
            root = put(root, key);
            root.color = BLACK;
        }

        private Node put(Node x, Interval key) {
            if (x == null) {
                return new Node(key, RED);
            }
            int cmp = comparator.compare(key, x.key);
            if (cmp < 0) {
                x.left = put(x.left, key);
            } else if (cmp > 0) {
                x.right = put(x.right, key);
            } else {
                return x;
            }

            if (isRed(x.right) && !isRed(x.left)) {
                x = rotateLeft(x);
            }
            if (isRed(x.left) && isRed(x.left.left)) {
                x = rotateRight(x);
            }
            if (isRed(x.left) && isRed(x.right)) {
                flipColors(x);
            }
            x.sz = 1 + size(x.left) + size(x.right);
            return x;
        }

        private Interval floor(int query) {
            Node x = floor(root, query);
            if (x == null) {
                return null;
            }
            return x.key;
        }

        private Node floor(Node x, int key) {
            if (x == null) {
                return null;
            }
            int cmp = key < x.key.rposIdx ? -1 : key == x.key.rposIdx ? 0 : 1;
            if (cmp == 0) {
                return x;
            }
            if (cmp < 0) {
                return floor(x.left, key);
            }
            Node t = floor(x.right, key);
            return t != null ? t : x;
        }

        private Interval ceil(int query) {
            Node x = ceil(root, query);
            if (x == null) {
                return null;
            }
            return x.key;
        }

        private Node ceil(Node x, int key) {
            if (x == null) {
                return null;
            }
            int cmp = key < x.key.lposIdx ? -1 : key == x.key.lposIdx ? 0 : 1;
            if (cmp == 0) {
                return x;
            }
            if (cmp > 0) {
                return ceil(x.right, key);
            }
            Node t = ceil(x.left, key);
            return t != null ? t : x;
        }
    }

    class Interval {

        int lposIdx;
        int rposIdx;

        Interval(int lposIdx, int rposIdx) {
            this.lposIdx = lposIdx;
            this.rposIdx = rposIdx;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 67 * hash + this.lposIdx;
            hash = 67 * hash + this.rposIdx;
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
            final Interval other = (Interval) obj;
            return (this.lposIdx == other.lposIdx && this.rposIdx == other.rposIdx);
        }
    }

    static class MinEndpointComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval a, Interval b) {
            return a.lposIdx < b.lposIdx ? -1 : a.lposIdx > b.lposIdx ? 1 : a.rposIdx < b.rposIdx ? -1 : 1;
        }
    }

    static class MaxEndpointComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval a, Interval b) {
            return a.rposIdx < b.rposIdx ? -1 : a.rposIdx > b.rposIdx ? 1 : a.lposIdx < b.lposIdx ? -1 : 1;
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/foepairs2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        long n = sc.nextLong();
        int m = sc.nextInt();
        int[] pos = new int[(int) (n + 1)];
        for (int i = 1; i <= n; i++) {
            pos[sc.nextInt()] = i;
        }
        RbTree rbTreeL = new RbTree(new MinEndpointComparator());
        RbTree rbTreeR = new RbTree(new MaxEndpointComparator());
        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (pos[l] > pos[r]) {
                int tmp = l;
                l = r;
                r = tmp;
            }
            rbTreeL.put(new Interval(pos[l], pos[r]));
            rbTreeR.put(new Interval(pos[l], pos[r]));
        }
        long ans = (n * (n + 1)) / 2;

        for (int i = 1; i <= n; i++) {
            Interval first = rbTreeL.ceil(i);
            if (first != null) {
                Interval second = first;
                Interval tmp = rbTreeR.floor(second.rposIdx - 1);
                while (tmp != null && tmp.rposIdx > i) {
                    if (tmp.lposIdx >= i) {
                        second = tmp;
                    }
                    tmp = rbTreeR.floor(tmp.rposIdx - 1);
                }
                ans -= (n - (second.rposIdx - 1));
            }
        }
        System.out.println(ans);
    }

    public static void main(String... args) {
        FoePairs fp = new FoePairs();
        fp.compute();
    }
}
