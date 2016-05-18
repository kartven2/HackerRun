/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/675/D
 *
 * Construct a BST and print parents of each node.
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.File;
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
public class TreeConstruction {

    static class InputReader {

        private static final int inputKb = 10240;
        private BufferedReader bufferedReader;
        private StringTokenizer stringTokenizer;

        private InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), inputKb);
        }

        private String readNext() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return stringTokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    static class RbTree {

        private static final byte RED = 1;
        private static final byte BLACK = 0;
        private Node root;

        static class Node {

            private int idx;
            private int value;
            private byte color;
            private Node left;
            private Node right;

            public Node(int idx, int value, byte color) {
                this.idx = idx;
                this.value = value;
                this.color = color;
            }
        }

        RbTree() {
            InputReader sc = null;
            try {
                sc = new InputReader(new FileInputStream(new File("./resources/treecons2")));
            } catch (FileNotFoundException ex) {
                throw new IllegalStateException(ex);
            }
            //InputReader sc = new InputReader(System.in);
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                int input = sc.nextInt();
                if (i > 0) {
                    Node greatestMin = lower(input);
                    Node lowestMax = higher(input);
                    int result = (greatestMin == null && lowestMax == null) ? 0
                            : (greatestMin == null) ? lowestMax.value
                                    : (lowestMax == null) ? greatestMin.value
                                            : (greatestMin.idx > lowestMax.idx) ? greatestMin.value : lowestMax.value;
                    System.out.print(result + " ");
                }
                insert(i, input);
                if (i == 0) {
                    root.idx = 0;
                }
            }
        }

        private void insert(int idx, int value) {
            root = insert(root, idx, value);
            root.color = BLACK;
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

        private boolean isRed(Node h) {
            if (h == null) {
                return false;
            }
            return h.color == RED;
        }

        private Node insert(Node x, int idx, int value) {
            if (x == null) {
                return new Node(idx, value, RED);
            }

            if (x.value > value) {
                x.left = insert(x.left, idx, value);
            } else if (x.value < value) {
                x.right = insert(x.right, idx, value);
            } else {
                x.value = value;
                x.idx = idx;
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

        private Node lower(int value) {
            return lower(root, value);
        }

        private Node lower(Node x, int value) {
            if (x == null) {
                return null;
            }
            if (x.value < value) {
                Node t = lower(x.right, value);
                if (t != null) {
                    return t;
                }
                return x;
            } else if (x.value > value) {
                return lower(x.left, value);
            } else {
                return null;
            }
        }

        private Node higher(int value) {
            return higher(root, value);
        }

        private Node higher(Node x, int value) {
            if (x == null) {
                return null;
            }
            if (x.value > value) {
                Node t = higher(x.left, value);
                if (t != null) {
                    return t;
                }
                return x;
            } else if (x.value < value) {
                return higher(x.right, value);
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        RbTree rbTree = new RbTree();
    }
}
