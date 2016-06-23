/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/681/C
 *
 * The first line of the output should contain a single integer 
 * m — the minimum possible number of records in the modified sequence of operations.
 * Next m lines should contain the corrected sequence of records following the format of the input 
 * (described in the statement), one per line and in the order they are applied.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class HeapOperations {

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/heapoperations2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        MinHeap mh = new MinHeap((int) 1e6 + 1);
        List<String> opList = new ArrayList<>();
        int ops = sc.nextInt();
        while (ops-- > 0) {
            String command = sc.readNext();
            switch (command) {
                case "insert":
                    int y = sc.nextInt();
                    mh.insert(y);
                    opList.add(command + " " + y);
                    break;
                case "getMin":
                    int x = sc.nextInt();
                    while (!mh.isEmpty() && mh.min() < x) {
                        opList.add("removeMin");
                        mh.removeMin();
                    }
                    if (mh.isEmpty() || mh.min() > x) {
                        opList.add("insert " + x);
                        mh.insert(x);
                    }
                    opList.add(command + " " + x);
                    break;
                case "removeMin":
                    if (mh.isEmpty()) {
                        opList.add("insert 0");
                    } else {
                        mh.removeMin();
                    }
                    opList.add(command);
                    break;
                default:
                    break;
            }
        }
        printOutput(opList);
    }

    private void printOutput(List<String> opList) {
        System.out.println(opList.size());
        for (String x : opList) {
            System.out.println(x);
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
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

    class MinHeap {

        private int[] heap;
        private int N;

        MinHeap(int initCapacity) {
            heap = new int[initCapacity + 1];
            heap[0] = -1;
        }

        private boolean isEmpty() {
            return N == 0;
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && less(j + 1, j)) {
                    j++;
                }
                if (less(k, j)) {
                    break;
                }
                exchange(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && less(k, k / 2)) {
                exchange(k, k / 2);
                k = k / 2;
            }
        }

        private boolean less(int i, int j) {
            return heap[i] < heap[j];
        }

        private void exchange(int i, int j) {
            int tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;
        }

        private void insert(int x) {
            heap[++N] = x;
            swim(N);
        }

        private int min() {
            return heap[1];
        }

        private int removeMin() {
            int x = heap[1];
            exchange(1, N);
            heap[N--] = 0;
            sink(1);
            return x;
        }
    }

    public static void main(String... args) {
        HeapOperations ho = new HeapOperations();
        ho.compute();
    }
}
