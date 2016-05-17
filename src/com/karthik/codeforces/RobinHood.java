/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/671/B
 *
 * Find the difference between richest and poorest persons wealth after value days.
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RobinHood {

    private int n;
    private int k;
    //private InputReader sc;
    private Scanner sc;
    private int output;

    private void input() {
        //sc = new InputReader(System.in);
        //sc = new Scanner(System.in);
        try {
            sc = new Scanner(new File("./resources/robinhood2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file not found" + ex.getMessage());
        }

        n = sc.nextInt();
        k = sc.nextInt();
    }

    private void output() {
        System.out.println(output);
    }

    public static void main(String[] args) {
        RobinHood b = new RobinHood();
        b.input();
        b.compute();
        b.output();
    }

    private void compute() {
        Rbtree m = new Rbtree(n, sc);
        int i = 0;
        while (i < k) {
            m.moveOneDay();
            i++;
        }
        output = m.max() - m.min();
    }

    static class InputReader {

        private static final int inputkb = 32768;
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

        public int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    static class Rbtree {

        private static final byte RED = 1;
        private static final byte BLACK = 0;
        private Node root;

        private void printTree() {
            printTree(root);
        }

        private void printTree(Node h) {
            if (h == null) {
                return;
            }
            printTree(h.left);
            printTree(h.right);
        }

        static class Node {

            private int value;
            private byte color;
            private int count;
            private Node left;
            private Node right;

            private Node(int value, byte color) {
                this.value = value;
                this.color = color;
                this.count++;
            }
        }

        public Rbtree(int n, Scanner sc) {
            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                insert(x);
            }
            printTree();
        }

        private void moveOneDay() {
            int min = min();
            int max = max();
            if (min != max) {
                removeMin();
                removeMax();
                insert(min + 1);
                insert(max - 1);
            }
        }

        private void insert(int x) {
            root = insert(root, x);
            root.color = BLACK;
        }

        private Node insert(Node h, int x) {
            if (h == null) {
                return new Node(x, RED);
            }

            if (h.value > x) {
                h.left = insert(h.left, x);
            } else if (h.value < x) {
                h.right = insert(h.right, x);
            } else {
                h.count++;
            }

            if (isRed(h.right) && !isRed(h.left)) {
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }

            return h;
        }

        private boolean isRed(Node h) {
            if (h == null) {
                return false;
            }
            return h.color == RED;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = x.left.color;
            x.left.color = RED;
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = x.right.color;
            x.right.color = RED;
            return x;
        }

        private void flipColors(Node h) {
            h.color = isRed(h) ? BLACK : RED;
            h.left.color = isRed(h.left) ? BLACK : RED;
            h.right.color = isRed(h.right) ? BLACK : RED;
        }

        private Node min(Node h) {
            if (h.left == null) {
                return h;
            }
            return min(h.left);
        }

        private Node max(Node h) {
            if (h.right == null) {
                return h;
            }
            return max(h.right);
        }

        private Node removeMin(Node h) {
            if (h.left == null) {
                if (h.count == 1) {
                    return null;
                } else {
                    h.count--;
                    return h;
                }
            }

            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }

            h.left = removeMin(h.left);
            return balance(h);
        }

        private Node moveRedLeft(Node h) {
            flipColors(h);
            if (isRed(h.right.left)) {
                h.right = rotateRight(h.right);
                h = rotateLeft(h);
                flipColors(h);
            }
            return h;
        }

        private Node balance(Node h) {
            if (isRed(h.right)) {
                h = rotateLeft(h);
            }
            if (isRed(h.left) && isRed(h.left.left)) {
                h = rotateRight(h);
            }
            if (isRed(h.left) && isRed(h.right)) {
                flipColors(h);
            }
            return h;
        }

        private Node removeMax(Node h) {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }

            if (h.right == null) {
                if (h.count == 1) {
                    return null;
                } else {
                    h.count--;
                    return h;
                }
            }

            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }

            h.right = removeMax(h.right);
            return balance(h);
        }

        private Node moveRedRight(Node h) {
            flipColors(h);
            if (isRed(h.left.left)) {
                h = rotateRight(h);
                flipColors(h);
            }
            return h;
        }

        public void removeMax() {
            if (root == null) {
                return;
            }
            if (!isRed(root.left) && !isRed(root.right)) {
                root.color = RED;
            }
            root = removeMax(root);
            if (root != null) {
                root.color = BLACK;
            }
        }

        public void removeMin() {
            if (root == null) {
                return;
            }
            if (!isRed(root.left) && !isRed(root.right)) {
                root.color = RED;
            }
            root = removeMin(root);
            if (root != null) {
                root.color = BLACK;
            }
        }

        public int min() {
            Node x = min(root);
            return x == null ? 0 : x.value;
        }

        public int max() {
            Node x = max(root);
            return x == null ? 0 : x.value;
        }
    }
}
