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
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastHeapOperations {

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

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/heapoperations2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        long count = 0;
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> mh = new PriorityQueue<>(((int) 1e5 + 2));
        int ops = sc.nextInt();
        while (ops-- > 0) {
            String command = sc.readNext();
            switch (command) {
                case "insert":
                    int y = sc.nextInt();
                    mh.add(y);
                    sb.append(command).append(" ").append(y).append("\n");
                    count++;
                    break;
                case "getMin":
                    int x = sc.nextInt();
                    while (!mh.isEmpty() && mh.peek() < x) {
                        sb.append("removeMin\n");
                        count++;
                        mh.poll();
                    }
                    if (mh.isEmpty() || mh.peek() > x) {
                        sb.append("insert").append(" ").append(x).append("\n");
                        count++;
                        mh.add(x);
                    }
                    sb.append(command).append(" ").append(x).append("\n");
                    count++;
                    break;
                case "removeMin":
                    if (mh.isEmpty()) {
                        sb.append("insert 0\n");
                        count++;
                    } else {
                        mh.poll();
                    }
                    sb.append(command).append("\n");
                    count++;
                    break;
                default:
                    break;
            }
        }
        System.out.println(count);
        System.out.println(sb.toString());
    }

    public static void main(String... args) {
        FastHeapOperations ho = new FastHeapOperations();
        ho.compute();
    }
}
