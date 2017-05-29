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
        long startTime = System.nanoTime();
        p.permute(5, true);
        System.out.println("Total : " + p.count);
        long duration = System.nanoTime() - startTime;
        System.out.println("Duration : " + duration + " nano secs");
        p.count = 0;
        startTime = System.nanoTime();
        p.permute(5, false);
        System.out.println("Total : " + p.count);
        duration = System.nanoTime() - startTime;
        System.out.println("Duration : " + duration + " nano secs");
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

    private void permute(int n, boolean tc) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        if (tc) {
            build(a, 0, n);
        } else {
            build2(a, 0, n);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void build(int[] a, int i, int n) {
        if (i == n - 1) {
            printPermutation(a);
            return;
        }
        for (int j = i; j < n; j++) {
            swap(a, i, j);
            build(a, i + 1, n);
            swap(a, i, j);
        }
    }

    private void build2(int[] a, int start, int n) {
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
