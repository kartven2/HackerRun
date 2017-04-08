/*
 * LeetCode: https://leetcode.com/problems/matchsticks-to-square/#/description
 *
 * Remember the story of Little Match Girl? By now,
 * you know exactly what matchsticks the little match girl has,
 * please find out a way you can make one square by using up all those matchsticks.
 * You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
 * Your input will be several matchsticks the girl has,
 * represented with their stick length.
 * Your output will either be true or false, to represent
 * whether you could make one square using all the matchsticks the little match girl has.
 * Input: [1,1,2,2,2]
 * Output: true
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * 
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MatchsticksSquare {

    public static void main(String... args) {
        MatchsticksSquare mss = new MatchsticksSquare();
        int[] a = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        System.out.println(mss.makesquare(a));
    }

    private void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void sort(int[] a, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        int lt = lo, gt = hi, v = a[lo], i = lo + 1;
        while (i <= gt) {
            if (a[i] > v) {
                exchange(a, i++, lt++);
            } else if (a[i] < v) {
                exchange(a, i, gt--);
            } else {
                i++;
            }
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public boolean makesquare(int[] a) {
        if (a == null || a.length == 0) {
            return false;
        }
        int n = a.length, sum = 0, side = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
        side = sum / 4;
        sort(a, 0, a.length - 1);
        int[] arr = new int[4];
        return dfs(side, arr, a, 0, n);
    }

    private boolean dfs(int tgt, int[] arr, int[] a, int idx, int n) {
        if (idx == n) {
            for (int i = 0; i < 4; i++) {
                if (arr[i] != tgt) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < 4; i++) {
            if (arr[i] + a[idx] <= tgt) {
                arr[i] += a[idx];
                if (dfs(tgt, arr, a, idx + 1, n)) {
                    return true;
                }
                arr[i] -= a[idx];
            }
        }
        return false;
    }
}
