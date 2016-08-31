/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/702/B
 *
 * Print the number of pairs of indexes i, j (i < j)
 * that ai + aj is a power of 2.
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
public class PowersOfTwo {

    class Node {

        private int key;
        private int value;
        private Node next;

        public Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    class SimpleList {

        private Node first;
        private int n;

        private boolean increment(int key) {
            for (Node x = first; x != null; x = x.next) {
                if (x.key == key) {
                    x.value++;
                    return false;
                }
            }
            first = new Node(key, 1, first);
            n++;
            return true;
        }

        private boolean put(int key, int value) {
            for (Node x = first; x != null; x = x.next) {
                if (x.key == key) {
                    x.value = value;
                    return false;
                }
            }
            first = new Node(key, value, first);
            n++;
            return true;
        }

        private int get(int key) {
            for (Node x = first; x != null; x = x.next) {
                if (x.key == key) {
                    return x.value;
                }
            }
            return 0;
        }

        private int size() {
            return n;
        }

        private Node[] getAll() {
            Node[] result = new Node[n];
            int i = 0;
            for (Node x = first; x != null; x = x.next) {
                result[i++] = x;
            }
            return result;
        }
    }

    class SimpleHash {

        private static final int INIT_CAPACITY = 32768;
        private int n;
        private int m;
        private SimpleList[] simpleList;

        SimpleHash() {
            this(INIT_CAPACITY);
        }

        SimpleHash(int m) {
            this.m = m;
            this.simpleList = new SimpleList[m];
        }

        private void resize(int chains) {
            SimpleHash temp = new SimpleHash(chains);
            for (int i = 0; i < m; i++) {
                if (simpleList[i] != null) {
                    Node[] nodeArr = simpleList[i].getAll();
                    for (int j = 0; j < nodeArr.length; j++) {
                        temp.put(nodeArr[j].key, nodeArr[j].value);
                    }
                }
            }
            this.m = temp.m;
            this.n = temp.n;
            this.simpleList = temp.simpleList;
        }

        private void put(int key, int value) {
            int keyIdx = indexFor(key);
            if (simpleList[keyIdx] == null) {
                simpleList[keyIdx] = new SimpleList();
            }
            if (simpleList[keyIdx].put(key, value)) {
                n++;
            }
        }

        private void increment(int key) {
            if (n >= 10 * m) {
                resize(2 * m);
            }
            int keyIdx = indexFor(key);
            if (simpleList[keyIdx] == null) {
                simpleList[keyIdx] = new SimpleList();
            }
            if (simpleList[keyIdx].increment(key)) {
                n++;
            }
        }

        private int get(int key) {
            int keyIdx = indexFor(key);
            if (simpleList[keyIdx] == null) {
                return 0;
            }
            return simpleList[keyIdx].get(key);
        }

        private int size() {
            return n;
        }

        private int indexFor(int key) {
            return (new Integer(key).hashCode()) & (m - 1);
        }
    }

    private static int[] POWOF2 = new int[30];

    private void populatePowerOf2() {
        int max = 1 << 30;
        for (int i = 0; i < 30; i++, max >>= 1) {
            POWOF2[i] = max;
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/powersof2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        populatePowerOf2();
        SimpleHash sh = new SimpleHash();
        int n = sc.nextInt();
        long ans = 0;
        for (; n > 0; n--) {
            int key = sc.nextInt();
            int j = 29;
            for (; j >= 0; j--) {
                ans += sh.get(POWOF2[j] - key);
            }
            sh.increment(key);
        }
        System.out.println(ans);
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        PowersOfTwo pt = new PowersOfTwo();
        pt.compute();
    }
}
