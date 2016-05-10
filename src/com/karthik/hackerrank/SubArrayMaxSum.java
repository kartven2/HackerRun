package com.karthik.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

public class SubArrayMaxSum {

    int noOfTests;

    long[] input;

    long[] output;

    int n;

    long m;

    Scanner sc;

    private void input() {
        //sc = new Scanner(System.in);
        sc = null;
        try {
            sc = new Scanner(new File("./resources/subarrma2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file not found" + ex.getMessage());
        }

        noOfTests = sc.nextInt();
    }

    private void process() {
        output = new long[noOfTests];
        for (int i = 0; i < noOfTests; i++) {
            n = sc.nextInt();
            m = sc.nextLong();
            input = new long[n];
            for (int j = 0; j < n; j++) {
                input[j] = sc.nextLong();
            }
            output[i] = findMaxSubArraySumMod();
        }
    }

    private void output() {
        for (long x : output) {
            System.out.println(x);
        }
    }

    private class Node {

        long value;
        Node left;
        Node right;

        public Node(long value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private class BST {

        Node root;

        public BST(Node root) {
            this.root = root;
        }

        public void insert(long n) {
            if (!contains(root, n)) {
                root = insert(root, n);
            }
        }

        public Long ceilOrN(long n) {
            Node x = ceil(root, n);
            if (x != null) {
                return x.value;
            }
            return null;
        }

        private Node ceil(Node x, long n) {
            if (x == null) {
                return null;
            }
            if (x.value == n) {
                return x;
            }
            if (x.value > n) {
                Node t = ceil(x.left, n);
                if (t == null) {
                    return x;
                }
                return t;
            }
            return ceil(x.right, n);
        }

        private Node insert(Node x, long n) {
            if (x == null) {
                return new Node(n, null, null);
            }

            if (x.value == n) {
                x.value = n;
            } else if (x.value > n) {
                x.left = insert(x.left, n);
            } else {
                x.right = insert(x.right, n);
            }
            return x;
        }

        private boolean contains(Node x, long n) {

            if (x == null) {
                return false;
            }

            if (x.value == n) {
                return true;
            } else if (x.value > n) {
                return contains(x.left, n);
            } else {
                return contains(x.right, n);
            }
        }
    }

    public static void main(String[] args) {
        SubArrayMaxSum s = new SubArrayMaxSum();
        s.input();
        s.process();
        s.output();
    }

    private long findMaxSubArraySumMod() {
        long[] modm = new long[n];
        modm[0] = input[0] % m;
        long max = modm[0];

        for (int i = 1; i < n; i++) {
            modm[i] = (modm[i - 1] + input[i]) % m;
            if (modm[i] > max) {
                max = modm[i];
            }
        }

        if (max == m - 1) {
            return max;
        }

        //TreeSet<Long> t = new TreeSet<Long>();
        //t.add(modm[0]);
        Node root = new Node(modm[0], null, null);
        BST bst = new BST(root);

        for (int i = 1; i < n; i++) {
            Long start = bst.ceilOrN(modm[i]);
            //Long start = t.ceiling(modm[i]);

            if (start != null) {
                max = Math.max(max, ((modm[i] - start) + m) % m);
            }

            //t.add(modm[i]);
            bst.insert(modm[i]);
        }

        return max;
    }
}
