/*
 * Leetcode: https://leetcode.com/problems/permutation-sequence/
 *
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * Given n and k, return the kth permutation sequence.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PermutationSequence {

    private String getString(char[] c) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            sb.append(c[i]);
        }
        return sb.toString();
    }

    public String getPermutation(int n, int k) {
        char[] s = new char[n];
        char[] num = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int i = 1; i <= n; i++) {
            s[i - 1] = num[i];
        }
        for (int j = 1; j < k; j++) {
            nextPermutation(s);
        }
        return getString(s);
    }

    private void nextPermutation(char[] s) {
        int n = s.length, k = -1;
        for (int i = n - 2; i >= 0; i--) {
            if (s[i] < s[i + 1]) {
                k = i;
                break;
            }
        }
        if (k == -1) {
            return;
        }
        for (int i = n - 1; i > k; i--) {
            if (s[i] > s[k]) {
                char temp = s[i];
                s[i] = s[k];
                s[k] = temp;
                break;
            }
        }
        for (int i = k + 1, j = n - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
