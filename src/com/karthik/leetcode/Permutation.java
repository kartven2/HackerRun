/*
 * Print Permutation
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Permutation {

    private int count;

    public static void main(String... args) {
        Permutation p = new Permutation();
        p.permute(3);
        System.out.println("Total : " + p.count);
    }

    private void printPermutation(int[] a) {
        count++;
        StringBuilder sb = new StringBuilder();
        for (int x : a) {
            sb.append(x);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private void permute(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        build(a, 0, n);
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void build(int[] a, int start, int n) {
        printPermutation(a);
        for (int i = start; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(a, i, j);
                build(a, i + 1, n);
                swap(a, i, j);
            }
        }
    }
}
