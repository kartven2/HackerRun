package com.karthik.codeforces;

/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/665/E
 *
 * Find the subarrays whose xor value is atleast k.
 * 
 */
import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SubArrayXor {

    private int n;
    private int k;
    private Scanner sc;
    private long output;

    private static class Trie {

        private static int bitlength = 30;
        private int[][] trie;
        private int[] elemCount;
        private int nodeCount;

        Trie() {
            trie = new int[2][32 * (int) 1e6];
            trie[0][0] = trie[1][0] = -1;
            elemCount = new int[32 * (int) 1e6];
        }

        private int getCount(int x) {
            return x == -1 ? 0 : elemCount[x];
        }

        private int getBit(int x, int i) {
            return (x & (1 << i)) == 0 ? 0 : 1;
        }

        private void insert(int x) {
            int idx = 0;
            elemCount[idx]++;
            for (int i = bitlength; i >= 0; i--) {
                if (getBit(x, i) == 0) {
                    if (trie[0][idx] == -1) {
                        trie[0][idx] = ++nodeCount;
                        trie[0][nodeCount] = trie[1][nodeCount] = -1;
                    }
                    idx = trie[0][idx];
                } else {
                    if (trie[1][idx] == -1) {
                        trie[1][idx] = ++nodeCount;
                        trie[0][nodeCount] = trie[1][nodeCount] = -1;
                    }
                    idx = trie[1][idx];
                }
                elemCount[idx]++;
            }
        }

        private int getCurrentCount(int x, int k) {
            int idx = 0;
            int count = 0;
            for (int i = bitlength; i >= 0 && idx != -1; i--) {
                int gx = getBit(x, i);
                int gk = getBit(k, i);
                if (gk == 0) {
                    if (gx == 0) {
                        count += getCount(trie[1][idx]);
                        idx = trie[0][idx];
                    } else {
                        count += getCount(trie[0][idx]);
                        idx = trie[1][idx];
                    }
                } else {
                    if (gx == 0) {
                        idx = trie[1][idx];
                    } else {
                        idx = trie[0][idx];
                    }
                }
            }
            count += getCount(idx);
            return count;
        }
    }

    private void compute() {
        Trie trie = new Trie();
        trie.insert(0);
        int xor = 0;
        output = 0;

        for (int i = 0; i < n; i++) {
            xor ^= sc.nextInt();
            output += trie.getCurrentCount(xor, k);
            trie.insert(xor);
        }
    }

    private void input() {
        sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
    }

    private void output() {
        System.out.println(output);
    }

    public static void main(String[] args) {
        SubArrayXor b = new SubArrayXor();
        b.input();
        b.compute();
        b.output();
    }
}
