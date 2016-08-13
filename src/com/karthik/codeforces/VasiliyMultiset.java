/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/706/D
 *
 * The first line of the input contains a single integer q (1 ≤ q ≤ 200 000)
 * — the number of queries Vasiliy has to perform.
 * Each of the following q lines of the input contains one of three characters
 * '+', '-' or '?' and an integer xi (1 ≤ xi ≤ 109). It's guaranteed that there
 * is at least one query of the third type.
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
public class VasiliyMultiset {

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

    class Trie {

        private static final int BIT_LEN = 30;
        private int n;
        private Node root;

        Trie() {
            root = new Node();
        }

        class Node {

            private Integer key;
            private Integer value;
            private Node[] nodes;

            Node() {
                nodes = new Node[2];
            }

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int getBit(int key, int i) {
            return ((1 << i) & key) == 0 ? 0 : 1;
        }

        private void insert(int key) {
            Node x = root;
            for (int i = BIT_LEN; i >= 0; i--) {
                int idx = getBit(key, i);
                if (x.nodes[idx] == null) {
                    x.nodes[idx] = new Node();
                }
                x = x.nodes[idx];
            }
            if (x.key == null) {
                x.key = key;
                x.value = 1;
                n++;
                return;
            }
            x.value++;
        }

        private void delete(int key) {
            root = delete(key, root, BIT_LEN);
        }

        private Node delete(int key, Node x, int d) {
            if (x == null) {
                return null;
            }

            if (d == -1) {
                if (x.value > 0) {
                    x.value--;
                } else {
                    x.value = null;
                }
                n--;
            } else {
                int idx = getBit(key, d);
                x.nodes[idx] = delete(key, x.nodes[idx], d - 1);
            }

            if (x.value != null && x.value > 0) {
                return x;
            }

            for (int i = 0; i < 2; i++) {
                if (x.nodes[i] != null) {
                    return x;
                }
            }

            return null;
        }

        private int maxXorTuple(int query) {
            Node x = root;
            for (int i = BIT_LEN; i >= 0; i--) {
                int idx = getBit(query, i);
                int inidx = idx ^ 1;
                x = x.nodes[inidx] != null ? x.nodes[inidx] : x.nodes[idx];
            }
            return query ^ x.key;
        }
    }

    private Trie trie;
    private InputReader sc;

    private void compute() {
        //sc = new InputReader(System.in);
        sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/vasilitymultiset"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        trie = new Trie();
        int q = sc.nextInt();
        trie.insert(0);
        while (q-- > 0) {
            String op = sc.readNext();
            switch (op) {
                case "+":
                    trie.insert(sc.nextInt());
                    break;
                case "-":
                    trie.delete(sc.nextInt());
                    break;
                case "?":
                    System.out.println(trie.maxXorTuple(sc.nextInt()));
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected character");
            }
        }
    }

    public static void main(String... args) {
        VasiliyMultiset vm = new VasiliyMultiset();
        vm.compute();
    }
}
