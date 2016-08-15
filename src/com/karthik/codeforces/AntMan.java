/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/704/B
 *
 * Print the minimum amount of time Scott needs
 * to get to the Cross while visiting each chair exactly once.
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
public class AntMan {

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

        long nextLong() {
            return Long.parseLong(readNext());
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    static class Chair {

        private long idx, x, a, b, c, d;

        private static long moveFrom(Chair i, Chair j) {
            long abd = Math.abs(i.x - j.x);
            return i.idx < j.idx ? abd + i.d + j.a : abd + i.c + j.b;
        }
    }

    private void readInput(int what, Chair[] chair, int n, InputReader sc) {
        if (what == 6) {
            return;
        }

        for (int i = 1; i <= n; i++) {
            switch (what) {
                case 1:
                    chair[i] = new Chair();
                    chair[i].idx = i;
                    chair[i].x = sc.nextLong();
                    break;
                case 2:
                    chair[i].a = sc.nextLong();
                    break;
                case 3:
                    chair[i].b = sc.nextLong();
                    break;
                case 4:
                    chair[i].c = sc.nextLong();
                    break;
                case 5:
                    chair[i].d = sc.nextLong();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Input");
            }
        }

        readInput(what + 1, chair, n, sc);
    }

    private static final long MAX = Long.MAX_VALUE;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/antman"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int sidx = sc.nextInt();
        int didx = sc.nextInt();
        Chair[] chair = new Chair[n + 1];
        readInput(1, chair, n, sc);
        int[] next = new int[n + 1];
        next[sidx] = didx;
        long ans = Chair.moveFrom(chair[sidx], chair[didx]), currTime, bestTime;
        int bestPre = 0;
        for (int i = 1; i <= n; i++) {
            if (i != sidx && i != didx) {
                bestTime = MAX;
                for (int j = sidx; j != didx; j = next[j]) {
                    currTime = Chair.moveFrom(chair[j], chair[i]) + Chair.moveFrom(chair[i], chair[next[j]]) - Chair.moveFrom(chair[j], chair[next[j]]);
                    if (currTime < bestTime) {
                        bestTime = currTime;
                        bestPre = j;
                    }
                }
                ans += bestTime;
                next[i] = next[bestPre];
                next[bestPre] = i;
            }
        }
        System.out.print(ans);
    }

    public static void main(String... args) {
        AntMan am = new AntMan();
        am.compute();
    }
}
