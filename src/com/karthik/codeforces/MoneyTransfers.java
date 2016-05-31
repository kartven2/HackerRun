/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/675/C
 *
 * Find the minimum number of money transfer between neighboring 
 * banks required to bring all account balances to 0.
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
public class MoneyTransfers {

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

        private long nextLong() {
            return Long.parseLong(readNext());
        }
    }

    class RbTree {

        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private Node root;

        class Node {

            private Long key;
            private Integer value;
            private Node left, right;
            private boolean color;

            Node(Long key, Integer value, boolean color) {
                this.key = key;
                this.value = value;
                this.color = color;
            }
        }

        private Integer get(Long key) {
            Node x = get(root, key);
            if (x == null) {
                return null;
            }
            return x.value;
        }

        private Node get(Node x, Long key) {
            if (x == null) {
                return null;
            }
            int cmp = (key > x.key) ? 1 : key < x.key ? -1 : 0;
            if (cmp < 0) {
                return get(x.left, key);
            } else if (cmp > 0) {
                return get(x.right, key);
            } else {
                return x;
            }
        }

        private void put(Long key) {
            root = put(root, key);
            root.color = BLACK;
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
            return x;
        }

        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private void flipColors(Node h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }

        private Node put(Node x, Long key) {
            if (x == null) {
                return new Node(key, 1, RED);
            }
            int cmp = (key > x.key) ? 1 : key < x.key ? -1 : 0;
            if (cmp < 0) {
                x.left = put(x.left, key);
            } else if (cmp > 0) {
                x.right = put(x.right, key);
            } else {
                x.value++;
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
            return x;
        }
    }

    private int n;
    private int output;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/moneytransfer"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        RbTree rb = new RbTree();
        n = sc.nextInt();
        output = n - 1;
        long cum = 0;
        for (int i = 0; i < n; i++) {
            cum += sc.nextInt();
            rb.put(cum);
            output = Math.min(output, n - rb.get(cum));
        }
        System.out.println(output);
    }

    public static void main(String[] args) {
        MoneyTransfers m = new MoneyTransfers();
        m.compute();
    }
}
