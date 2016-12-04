/*
 * LeetCode problem : MaximumProductWordLengths
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j])
 * where the two words do not share common letters.
 * You may assume that each word will contain only lower case letters.
 * If no such two words exist, return 0.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MaximumProductWordLengths {

    public int maxProduct(String[] words) {
        if (words == null || words.length < 2) {
            return 0;
        }
        int n = words.length;
        int[] codes = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                codes[i] |= 1 << ((words[i].charAt(j) - 'a') + 1);
            }
        }
        int max = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((codes[i] & codes[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
