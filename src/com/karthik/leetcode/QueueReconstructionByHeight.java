/*
 * LeetCode Problem: https://leetcode.com/problems/queue-reconstruction-by-height/description/
 *
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people
 * in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 * Note:
 * The number of people is less than 1,100.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] a) {
        if (a == null || a.length == 0 || a[0].length != 2) {
            return a;
        }
        Arrays.sort(a, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                if (x[0] != y[0]) {
                    return y[0] - x[0];
                }
                return x[1] - y[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            list.add(a[i][1], a[i]);
        }
        int k = 0;
        for (int[] x : list) {
            a[k++] = x;
        }
        return a;
    }
}
