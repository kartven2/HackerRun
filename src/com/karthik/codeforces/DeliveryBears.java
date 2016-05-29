/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/653/D
 *
 * Find the max flow in a directed network with some constratints.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DeliveryBears {

    private int n;
    private int m;
    private int x;
    private int[][] capacity;
    private int[][] testArr;
    private FixedList<Integer>[] adj;
    private int[] edgeTo;
    private boolean[] marked;
    private ResizingQueue<Integer> queue;

    class ResizingQueue<T> {

        private T[] items;
        private int last;
        private int first;
        private int size;

        ResizingQueue(int capacity) {
            this.items = (T[]) new Object[capacity];
        }

        void resize(int max) {
            T[] temp = (T[]) new Object[max];
            for (int i = 0; i < size; i++) {
                temp[i] = items[(first + i) % items.length];
            }
            items = temp;
            first = 0;
            last = size;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void enqueue(T value) {
            if (size == items.length) {
                resize(2 * items.length);
            }
            items[last++] = value;
            if (last == items.length) {
                last = 0;
            }
            size++;
        }

        T dequeue() {
            T item = items[first];
            items[first] = null;
            size--;
            first++;
            if (first == items.length) {
                first = 0;
            }
            return item;
        }
    }

    class FixedList<T> implements Iterable<T> {

        class FixedListIterator<T> implements Iterator<T> {

            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) items[i++];
            }

        }
        private T[] items;
        private int size;

        FixedList(int capacity) {
            this.items = (T[]) new Object[capacity];
        }

        void add(T value) {
            items[size++] = value;
        }

        @Override
        public Iterator<T> iterator() {
            return new FixedListIterator();
        }
    }

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
    }

    private boolean hasAugmentingPath() {
        marked = new boolean[n];
        edgeTo = new int[n];
        queue = new ResizingQueue<>(n);
        queue.enqueue(0);
        marked[0] = true;
        edgeTo[0] = -1;
        while (!queue.isEmpty() && !marked[n - 1]) {
            int v = queue.dequeue();
            for (int w : adj[v]) {
                if (testArr[v][w] > 0) {
                    if (!marked[w]) {
                        marked[w] = true;
                        edgeTo[w] = v;
                        queue.enqueue(w);
                    }
                }
            }
        }
        return marked[n - 1];
    }

    private long findMaxFlow() {
        long maxflow = 0;
        while (hasAugmentingPath()) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = n - 1; v != 0; v = edgeTo[v]) {
                bottle = Math.min(bottle, testArr[edgeTo[v]][v]);
            }
            for (int v = n - 1; v != 0; v = edgeTo[v]) {
                testArr[edgeTo[v]][v] -= bottle;
                testArr[v][edgeTo[v]] += bottle;
            }
            maxflow += bottle;
        }
        return maxflow;
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/deliverybears2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        m = sc.nextInt();
        x = sc.nextInt();
        capacity = new int[n][n];
        testArr = new int[n][n];
        adj = (FixedList<Integer>[]) new FixedList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new FixedList<>(n);
        }
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;
            capacity[from][to] = sc.nextInt();
            adj[from].add(to);
        }

        double lo = 0, hi = 1e7;
        for (int iter = 0; iter < 100; iter++) {
            double mid = (lo + hi) / 2;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    testArr[i][j] = (int) (capacity[i][j] / mid);
                }
            }
            if (findMaxFlow() >= x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        System.out.println(String.format("%.10f", lo * x));
    }

    public static void main(String[] args) {
        DeliveryBears db = new DeliveryBears();
        db.compute();
    }
}
