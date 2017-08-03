/*
 * Leetcode: https://leetcode.com/problems/shuffle-an-array/description/
 * Shuffle a set of numbers without duplicates
 */
package com.karthik.leetcode;

import java.util.Random;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ShuffleArray {

    private int[] a;

    public ShuffleArray(int[] a) {
        this.a = a;
    }

    public int[] reset() {
        return a;
    }

    public int[] shuffle() {
        int n = a.length;
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = i;
        }
        for (int i = n - 1; i > 0; i--) {
            int r = new Random().nextInt(i + 1);
            int tmp = s[r];
            s[r] = s[i];
            s[i] = tmp;
        }
        for (int i = 0; i < n; i++) {
            s[i] = a[s[i]];
        }
        return s;
    }
}
