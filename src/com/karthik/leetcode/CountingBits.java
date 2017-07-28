/*
 * LeetCode: https://leetcode.com/problems/counting-bits/tabs/description
 *
 * Given a non negative integer number num.
 * For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * Follow up:
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CountingBits {

    public int[] countBits(int a) {
        int[] res = new int[a + 1];
        for (int i = 1; i <= a; i++) {
            res[i] = res[i / 2] + (i % 2);
        }
        return res;
    }

    public int[] countBits2(int a) {
        if (a < 0) {
            return null;
        }
        int[] res = new int[a + 1];
        for (int i = 1; i <= a; i++) {
            if ((i & (i - 1)) == 0) {
                res[i] = 1;
                continue;
            }
            for (int j = 0; j < 31; j++) {
                int val = 1 << j;
                if (val > i) {
                    break;
                }
                if ((i / val) % 2 == 1) {
                    res[i]++;
                }
            }
        }
        return res;
    }
}
