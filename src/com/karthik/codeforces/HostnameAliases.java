/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/644/C
 *
 * Determine the groups of server names that correspond
 * to one website. 
 * Ignore groups consisting of the only server name.
 *
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
public class HostnameAliases {

    class InputReader {

        private static final int inputkb = 1024;
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
    }

    class RbTree {

        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private Node root;

        class Node {

            private String key;
            private Node left, right;
            private boolean color;
            private int sz;

            Node(String key, boolean color) {
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

        private void put(String key) {
            root = put(root, key);
            root.color = BLACK;
        }

        private Node put(Node x, String key) {
            if (x == null) {
                return new Node(key, RED);
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x.left = put(x.left, key);
            } else if (cmp > 0) {
                x.right = put(x.right, key);
            } else {
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

        private int rank(String key) {
            return rank(root, key);
        }

        private int rank(Node x, String key) {
            if (x == null) {
                return 0;
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                return rank(x.left, key);
            } else if (cmp > 0) {
                return 1 + size(x.left) + rank(x.right, key);
            } else {
                return size(x.left);
            }
        }

        private String select(int rank) {
            return select(root, rank);
        }

        private String select(Node x, Integer rank) {
            if (x == null) {
                return null;
            }
            int cmp = rank.compareTo(size(x.left));
            if (cmp < 0) {
                return select(x.left, rank);
            } else if (cmp > 0) {
                return select(x.right, rank - 1 - size(x.left));
            } else {
                return x.key;
            }
        }
    }

    class Tst {

        private int capacity;
        private Node root;

        Tst(int capacity) {
            this.capacity = capacity;
        }

        class Node {

            private char c;
            private Node l;
            private Node m;
            private Node r;
            private int[] serverRankIdx;
            private int ptr;

            Node(char c) {
                this.c = c;
            }
        }

        private void put(String key, int value) {
            root = put(root, key, value, 0);
        }

        private Node put(Node x, String key, int value, int d) {
            char e = key.charAt(d);
            if (x == null) {
                x = new Node(e);
            }
            if (x.c > e) {
                x.l = put(x.l, key, value, d);
            } else if (x.c < e) {
                x.r = put(x.r, key, value, d);
            } else if (d < key.length() - 1) {
                x.m = put(x.m, key, value, d + 1);
            } else {
                if (x.serverRankIdx == null) {
                    x.serverRankIdx = new int[capacity];
                }
                x.serverRankIdx[x.ptr++] = value;
            }
            return x;
        }

        private Node getValuesForKeys(String key) {
            return get(root, key, 0);
        }

        private Node get(Node x, String key, int d) {
            if (x == null) {
                return null;
            }
            char m = key.charAt(d);
            if (x.c > m) {
                return get(x.l, key, d);
            } else if (x.c < m) {
                return get(x.r, key, d);
            } else if (d < key.length() - 1) {
                return get(x.m, key, d + 1);
            } else {
                return x;
            }
        }
    }

    class BinaryTrie {

        private static final int R = 2;
        private Node root;
        private int capacity;

        BinaryTrie(int capacity) {
            this.capacity = capacity;
        }

        class Node {

            private int[] serverRankIdx;
            private int ptr;
            private Node[] next = new Node[R];
        }

        private void insert(String bitString, int value) {
            root = insert(root, bitString, value, 0);
        }

        private Node insert(Node x, String bitString, int value, int d) {
            if (x == null) {
                x = new Node();
            }
            if (d == bitString.length()) {
                if (x.serverRankIdx == null) {
                    x.serverRankIdx = new int[capacity];
                }
                x.serverRankIdx[x.ptr++] = value;
                return x;
            }
            if (bitString.charAt(d) == '0') {
                x.next[0] = insert(x.next[0], bitString, value, d + 1);
            } else {
                x.next[1] = insert(x.next[1], bitString, value, d + 1);
            }
            return x;
        }

        private Node get(String bitString) {
            return get(root, bitString, 0);
        }

        private Node get(Node x, String bitString, int d) {
            if (x == null) {
                return null;
            }
            if (d == bitString.length()) {
                return x;
            }
            if (bitString.charAt(d) == '0') {
                return get(x.next[0], bitString, d + 1);
            } else {
                return get(x.next[1], bitString, d + 1);
            }
        }

        private void collect(Output o) {
            collect(root, o);
        }

        private void collect(Node x, Output o) {
            if (x == null) {
                return;
            }
            collect(x.next[0], o);
            if (x.ptr > 1) {
                o.addKeys(x.serverRankIdx, x.ptr);
            }
            collect(x.next[1], o);
        }
    }

    class Output {

        private int[][] data;
        private int rowIdx;
        private int colIdx;
        private RbTree rbTree;

        Output(RbTree rbTree) {
            this.rbTree = rbTree;
            data = new int[rbTree.size()][rbTree.size()];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(rowIdx);
            sb.append("\n");
            for (int i = 0; i < rowIdx; i++) {
                for (int j = 0; j < colIdx; j++) {
                    if (data[i][j] > 0) {
                        sb.append("http://");
                        sb.append(rbTree.select(data[i][j] - 1));
                        sb.append(" ");
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        private void addKeys(int[] serverRankIdx, int ptr) {
            for (int i = 0; i < ptr; i++) {
                data[rowIdx][colIdx++] = serverRankIdx[i];
            }
            rowIdx++;
        }
    }

    private int n;

    private String getPath(String x) {
        return x.indexOf('/') == -1 ? "#" : x.substring(x.indexOf('/'));
    }

    private String getServer(String x) {
        return x.indexOf('/') == -1 ? x.substring(0, x.length()) : x.substring(0, x.indexOf('/'));
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/hostnamealiases2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        RbTree severTree = new RbTree();
        RbTree pathTree = new RbTree();
        RbTree input = new RbTree();

        for (int i = 0; i < n; i++) {
            String fullPath = sc.readNext().substring(7);
            input.put(fullPath);
            severTree.put(getServer(fullPath));
            pathTree.put(getPath(fullPath));
        }

        Tst tst = new Tst(severTree.size());
        for (int i = 0; i < input.size(); i++) {
            tst.put(getPath(input.select(i)), severTree.rank(getServer(input.select(i))));
        }

        input = null;
        System.gc();

        byte[][] adjMatrix = new byte[severTree.size()][pathTree.size()];
        for (int j = 0; j < pathTree.size(); j++) {
            Tst.Node serverRanks = tst.getValuesForKeys(pathTree.select(j));
            if (serverRanks != null) {
                for (int i = 0; i < serverRanks.ptr; i++) {
                    adjMatrix[serverRanks.serverRankIdx[i]][j] = 1;
                }
            }
        }

        BinaryTrie trie = new BinaryTrie(severTree.size());
        for (int i = 0; i < severTree.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < pathTree.size(); j++) {
                sb.append(adjMatrix[i][j]);
            }
            trie.insert(sb.toString(), i + 1);
        }

        Output output = new Output(severTree);
        trie.collect(output);
        System.out.println(output.toString());
    }

    public static void main(String... args) {
        HostnameAliases h = new HostnameAliases();
        h.compute();
    }
}
