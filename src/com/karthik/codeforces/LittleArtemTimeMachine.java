/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/641/E
 *
 * For each ask operation output the number of instances
 * of integer being queried at the given moment of time.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LittleArtemTimeMachine {

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

    class Entry implements Comparable<Entry> {

        int idx;
        int type;
        int time;
        int value;
        int action;

        Entry(int a, int b, int c, int d) {
            idx = a;
            type = b;
            time = c;
            value = d;
            action = type == 1 ? 1 : type == 2 ? -1 : 0;
        }

        @Override
        public int compareTo(Entry that) {
            int cmp = this.value - that.value;
            if (cmp == 0) {
                return this.time - that.time;
            }
            return cmp;
        }
    }

    class HeapSort {

        private boolean less(Comparable[] pq, int i, int j) {
            return pq[i - 1].compareTo(pq[j - 1]) < 0;
        }

        private void exchange(Comparable[] pq, int i, int j) {
            Comparable temp = pq[i - 1];
            pq[i - 1] = pq[j - 1];
            pq[j - 1] = temp;
        }

        private void sink(Comparable[] pq, int k, int n) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && less(pq, j, j + 1)) {
                    j++;
                }
                if (less(pq, j, k)) {
                    break;
                }
                exchange(pq, j, k);
                k = j;
            }
        }

        private void sort(Comparable[] pq) {
            int n = pq.length;
            for (int i = n / 2; i >= 1; i--) {
                sink(pq, i, n);
            }
            while (n > 1) {
                exchange(pq, 1, n--);
                sink(pq, 1, n);
            }
        }
    }

    class FenwickTree {

        int[] array;

        FenwickTree(int a) {
            array = new int[a + 1];
        }

        private int rsq(int i) {
            int sum = 0;
            while (i > 0) {
                sum += array[i];
                i -= i & (-i);
            }
            return sum;
        }

        private void update(int i, int value) {
            while (i < array.length) {
                array[i] += value;
                i += i & (-i);
            }
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/littleatremtimemachine"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        Entry[] e = new Entry[n];
        for (int i = 0; i < n; i++) {
            e[i] = new Entry(i + 1, sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        HeapSort hs = new HeapSort();
        hs.sort(e);
        FenwickTree ft = new FenwickTree(n);
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n;) {
            int j = i;
            for (; j < n && e[j].value == e[i].value;) {
                j++;
            }
            for (int k = i; k < j; k++) {
                Entry ee = e[k];
                if (ee.action == 0) {
                    ans[ee.idx - 1] = ft.rsq(ee.idx);
                } else {
                    ft.update(ee.idx, ee.action);
                }
            }
            for (int k = i; k < j; k++) {
                Entry ee = e[k];
                if (ee.action != 0) {
                    ft.update(ee.idx, -ee.action);
                }
            }
            i = j;
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] >= 0) {
                System.out.println(ans[i]);
            }
        }
    }

    public static void main(String... args) {
        LittleArtemTimeMachine latm = new LittleArtemTimeMachine();
        latm.compute();
    }
}
