/*-
 * HackerRank problem :
 * https://www.hackerrank.com/challenges/insertion-sort
 *
 * Find the sum of swaps that will be done for the given input
 * during insertion sort.
 */
package com.karthik.hackerrank;

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
public class InsertionSortSwapCount {

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

    class RbTree {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private Node root;

        class Node {

            private int key;
            private int count;
            private int freq;
            private boolean color;
            private int freqCount;
            private Node left;
            private Node right;

            Node(int key) {
                this.key = key;
                this.count = 1;
                this.color = RED;
                this.freq = 0;
                this.freqCount = 0;
            }
        }

        private boolean isRed(Node x) {
            if (x == null || x.color == BLACK) {
                return false;
            }
            return true;
        }

        private int size(Node x) {
            if (x == null) {
                return 0;
            }
            return x.count;
        }

        private int freq(Node x) {
            if (x == null) {
                return 0;
            }
            return x.freq;
        }

        private int freqCount(Node x) {
            if (x == null) {
                return 0;
            }
            return x.freqCount;
        }

        private void flipColors(Node x) {
            x.color = !x.color;
            x.left.color = !x.left.color;
            x.right.color = !x.right.color;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.count = h.count;
            x.freqCount = h.freqCount;
            x.color = h.color;
            h.color = RED;
            h.count = 1 + size(h.left) + size(h.right);
            h.freqCount = freqCount(h.left) + freqCount(h.right) + freq(h);
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            x.count = h.count;
            x.freqCount = h.freqCount;
            h.color = RED;
            h.count = 1 + size(h.left) + size(h.right);
            h.freqCount = freqCount(h.left) + freqCount(h.right) + freq(h);
            return x;
        }

        private void insert(int key) {
            root = insert(root, key);
            root.color = BLACK;
        }

        private Node insert(Node x, Integer newKey) {
            if (x == null) {
                return new Node(newKey);
            }

            int cmp = newKey.compareTo(x.key);
            if (cmp < 0) {
                x.left = insert(x.left, newKey);
            } else if (cmp > 0) {
                x.right = insert(x.right, newKey);
            } else {
                x.freq++;
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

            x.count = 1 + size(x.left) + size(x.right);
            x.freqCount = freqCount(x.left) + freqCount(x.right) + freq(x);
            return x;

        }

        private int countElementsGreaterThan(int newKey) {
            return countElementsGreaterThan(root, newKey, size(root));
        }

        private int countElementsGreaterThan(Node x, Integer newKey, int totalCount) {
            if (x == null) {
                return totalCount;
            }

            int cmp = newKey.compareTo(x.key);
            if (cmp == 0) {
                return totalCount - size(x) + size(x.right);
            }
            if (cmp > 0) {
                return countElementsGreaterThan(x.right, newKey, totalCount - size(x) + size(x.right));
            }
            return countElementsGreaterThan(x.left, newKey, totalCount);
        }

        private int countFrequencyOfElementsGreaterThan(int searchKey) {
            return countFrequencyOfElementsGreaterThan(root, searchKey, freqCount(root));
        }

        private int countFrequencyOfElementsGreaterThan(Node x, Integer searchKey, int totalFreq) {
            if (x == null) {
                return totalFreq;
            }

            int cmp = searchKey.compareTo(x.key);
            if (cmp == 0) {
                return totalFreq - freqCount(x) + freqCount(x.right);
            }
            if (cmp > 0) {
                return countFrequencyOfElementsGreaterThan(x.right, searchKey, totalFreq - freqCount(x) + freqCount(x.right));
            }
            return countFrequencyOfElementsGreaterThan(x.left, searchKey, totalFreq);
        }
    }

    private int t;
    private int n;
    private long sum;
    private InputReader sc;
    private RbTree rbTree;

    private void compute() {
        n = sc.nextInt();
        sum = 0;
        rbTree = new RbTree();
        for (int i = 0; i < n; i++) {
            int input = sc.nextInt();
            sum += rbTree.countElementsGreaterThan(input);
            sum += rbTree.countFrequencyOfElementsGreaterThan(input);
            rbTree.insert(input);
        }
        System.out.println(sum);
    }

    InsertionSortSwapCount() {
        //sc = new InputReader(System.in);
        try {
            sc = new InputReader(new FileInputStream("./resources/insertionsortswapcount5"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file not found" + ex.getMessage());
        }
        t = sc.nextInt();
    }

    public static void main(String[] args) {
        InsertionSortSwapCount ic = new InsertionSortSwapCount();
        for (int i = 0; i < ic.t; i++) {
            ic.compute();
        }
    }
}
