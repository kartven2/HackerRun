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

    class LinkListSt {

        private int N;
        private Node first;

        class Node {

            private String key;
            private Node next;

            Node(String key, Node next) {
                this.key = key;
                this.next = next;
            }
        }

        int size() {
            return N;
        }

        Node get(String key) {
            if (key == null || key.isEmpty()) {
                return null;
            }

            for (Node p = first; p != null; p = p.next) {
                if (p.key.equals(key)) {
                    return p;
                }
            }
            return null;
        }

        boolean put(String key) {
            if (key == null || key.isEmpty()) {
                return false;
            }

            for (Node p = first; p != null; p = p.next) {
                if (key.equals(p.key)) {
                    return false;
                }
            }

            first = new Node(key, first);
            N++;
            return true;
        }

        String[] keys() {
            String[] allKeys = new String[N];
            int i = 0;
            for (Node x = first; x != null; x = x.next) {
                allKeys[i++] = x.key;
            }
            return allKeys;
        }
    }

    class HashSt {

        private int N;
        private int M;
        private LinkListSt[] st;

        HashSt(int capacity) {
            M = capacity;
            st = new LinkListSt[M];
            for (int i = 0; i < M; i++) {
                st[i] = new LinkListSt();
            }
        }

        int size() {
            return N;
        }

        int hash(String key) {
            return (key.hashCode() & 0x7fffffff) % M;
        }

        boolean contains(String key) {
            if (key == null || key.isEmpty()) {
                return false;
            }
            int i = hash(key);
            return st[i].get(key) != null;
        }

        void put(String key) {
            if (key == null || key.isEmpty()) {
                return;
            }
            int i = hash(key);
            if (st[i].put(key)) {
                N++;
            }
        }

        String[] keys() {
            String[] allKeys = new String[N];
            int ptr = 0;
            for (int i = 0; i < st.length; i++) {
                for (String key : st[i].keys()) {
                    allKeys[ptr++] = key;
                }
            }
            return allKeys;
        }
    }

    class RbTree {

        private static final boolean RED = true;
        private static final boolean BLACK = false;
        private Node root;

        class Node {

            private String key;
            private Integer value;
            private Node left, right;
            private boolean color;

            Node(String key, Integer value, boolean color) {
                this.key = key;
                this.value = value;
                this.color = color;
            }
        }

        private Integer get(String key) {
            Node x = get(root, key);
            if (x == null) {
                return null;
            }
            return x.value;
        }

        private Node get(Node x, String key) {
            if (x == null) {
                return null;
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                return get(x.left, key);
            } else if (cmp > 0) {
                return get(x.right, key);
            } else {
                return x;
            }
        }

        private void put(String key, Integer value) {
            root = put(root, key, value);
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

        private Node put(Node x, String key, Integer value) {
            if (x == null) {
                return new Node(key, value, RED);
            }
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x.left = put(x.left, key, value);
            } else if (cmp > 0) {
                x.right = put(x.right, key, value);
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
            return x;
        }
    }

    class Tst {

        private Node root;
        private int hshStSz;

        Tst(int hshStSz) {
            this.hshStSz = hshStSz;
        }

        class Node {

            private char c;
            private Node l;
            private Node m;
            private Node r;
            private HashSt set;

            Node(char c) {
                this.c = c;
            }
        }

        private void put(String key, String value) {
            root = put(root, key, value, 0);
        }

        private Node put(Node x, String key, String value, int d) {
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
                if (x.set == null) {
                    x.set = new HashSt(hshStSz);
                }
                x.set.put(value);
            }
            return x;
        }

        private String[] getValuesForKeys(String prefix) {
            Node x = get(prefix);
            if (x != null && x.set != null) {
                return x.set.keys();
            }
            return null;
        }

        private Node get(String prefix) {
            return get(root, prefix, 0);
        }

        private Node get(Node x, String prefix, int d) {
            if (x == null) {
                return null;
            }
            char m = prefix.charAt(d);
            if (x.c > m) {
                return get(x.l, prefix, d);
            } else if (x.c < m) {
                return get(x.r, prefix, d);
            } else if (d < prefix.length() - 1) {
                return get(x.m, prefix, d + 1);
            } else {
                return x;
            }
        }
    }

    class Trie {

        private static final int R = 2;
        private Node root;

        class Node {

            private LinkListSt lst;
            private Node[] next = new Node[R];
        }

        private void insert(String bitString, String value) {
            root = insert(root, bitString, value, 0);
        }

        private Node insert(Node x, String bitString, String value, int d) {
            if (x == null) {
                x = new Node();
            }
            if (d == bitString.length() - 1) {
                if (x.lst == null) {
                    x.lst = new LinkListSt();
                }
                x.lst.put(value);
                return x;
            }
            if (bitString.charAt(d) == '0') {
                x.next[0] = insert(x.next[0], bitString, value, d + 1);
            } else {
                x.next[1] = insert(x.next[1], bitString, value, d + 1);
            }
            return x;
        }

        private String[] get(String bitString) {
            String[] result = null;
            Node x = get(root, bitString, 0);
            if (x != null && x.lst != null) {
                result = x.lst.keys();
            }
            return result;
        }

        private varArrOfStr collectAllGroupsWithMoreThanOneElem(HashSt binaryKey, int serversLength) {
            String[] binString = binaryKey.keys();
            varArrOfStr result = new varArrOfStr(binString.length, serversLength);
            for (int i = 0; i < binString.length; i++) {
                String[] servers = get(binString[i]);
                if (servers != null && servers.length > 1) {
                    for (int j = 0; j < servers.length; j++) {
                        result.addKey(servers[j]);
                        result.nextCol();
                    }
                    result.nextRow();
                }
            }
            return result;
        }

        private Node get(Node x, String bitString, int d) {
            if (x == null) {
                return null;
            }
            if (d == bitString.length() - 1) {
                return x;
            }
            if (bitString.charAt(d) == '0') {
                return get(x.next[0], bitString, d + 1);
            } else {
                return get(x.next[1], bitString, d + 1);
            }
        }
    }

    class varArrOfStr {

        private String[][] data;
        private int rowIdx;
        private int colIdx;

        varArrOfStr(int rcapacity, int ccapacity) {
            data = new String[rcapacity][ccapacity];
        }

        void addKey(String key) {
            data[rowIdx][colIdx] = key;
        }

        void nextRow() {
            rowIdx++;
        }

        void nextCol() {
            colIdx++;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(rowIdx);
            sb.append("\n");
            for (int i = 0; i < rowIdx; i++) {
                for (int j = 0; j < colIdx; j++) {
                    if (data[i][j] != null) {
                        sb.append("http://");
                        sb.append(data[i][j]);
                        sb.append(" ");
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    private int n;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/hostnamealiases"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        Tst tst = new Tst(n);
        HashSt pathst = new HashSt(n);
        HashSt serverst = new HashSt(n);
        RbTree sreverIdx = new RbTree();
        for (int i = 0; i < n; i++) {
            String a = sc.readNext().substring(7);
            String server = a.indexOf('/') == -1 ? a.substring(0, a.length()) : a.substring(0, a.indexOf('/'));
            String path = a.indexOf('/') == -1 ? "#" : a.substring(a.indexOf('/'));
            serverst.put(server);
            pathst.put(path);
            tst.put(path, server);
        }
        int[][] result = new int[serverst.size()][pathst.size()];
        String[] servers = serverst.keys();
        String[] paths = pathst.keys();
        for (int i = 0; i < servers.length; i++) {
            sreverIdx.put(servers[i], i);
        }

        for (int j = 0; j < paths.length; j++) {
            String[] serverGroups = tst.getValuesForKeys(paths[j]);
            if (serverGroups != null) {
                for (int k = 0; k < serverGroups.length; k++) {
                    result[sreverIdx.get(serverGroups[k])][j] = 1;
                }
            }
        }

        Trie trie = new Trie();
        HashSt binaryKey = new HashSt(result.length);
        for (int i = 0; i < result.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < result[i].length; j++) {
                sb.append(result[i][j]);
            }
            trie.insert(sb.toString(), servers[i]);
            binaryKey.put(sb.toString());
        }

        varArrOfStr output = trie.collectAllGroupsWithMoreThanOneElem(binaryKey, servers.length);
        System.out.println(output.toString());
    }

    private void printArr(String[] arr) {
        for (String x : arr) {
            System.out.println(x);
        }
    }

    public static void main(String... args) {
        HostnameAliases h = new HostnameAliases();
        h.compute();
    }
}
