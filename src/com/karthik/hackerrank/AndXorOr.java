/*-
 * HackerRank problem :
 * https://www.hackerrank.com/challenges/and-xor-or
 *
 * Find the maximum subsequence value in an array for 
 * Si = (((M1 AND M2) XOR (M1 OR M2)) AND (M1 XOR M2))
 * 
 */
package com.karthik.hackerrank;

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
public class AndXorOr {

    private int n;
    private int[] input;
    private int maxSum;
    private FixedStack<Integer> stack;
    private FixedStack<Integer> rstack;

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

    class FixedStack<T> {

        private T[] stack;
        private int n;

        FixedStack(int capacity) {
            stack = (T[]) new Object[capacity];
        }

        void push(T value) {
            stack[n++] = value;
        }

        boolean isEmpty() {
            return n == 0;
        }

        T pop() {
            return stack[--n];
        }

        T peek() {
            return stack[n - 1];
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/andxoror3"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        maxSum = Integer.MIN_VALUE;
        stack = new FixedStack<>(n);
        rstack = new FixedStack<>(n);
        computeMax(true);
        int j = 0;
        while (!rstack.isEmpty()) {
            input[j++] = rstack.pop();
        }
        stack = new FixedStack<>(n);
        computeMax(false);
        System.out.println(maxSum);
    }

    private void computeMax(boolean captureReverse) {
        for (int x : input) {
            while (!stack.isEmpty() && stack.peek() > x) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                int top = stack.peek();
                maxSum = Math.max(maxSum, (((top & x) ^ (top | x)) & (top ^ x)));
            }
            stack.push(x);
            if (captureReverse) {
                rstack.push(x);
            }
        }
    }

    public static void main(String[] args) {
        AndXorOr c = new AndXorOr();
        c.compute();
    }
}
