/*
 * LeetCode Problem: https://leetcode.com/problems/magical-string/description/
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
 * The first few elements of string S is the following: S = "1221121221221121122……"
 * If we group the consecutive '1's and '2's in S, it will be:
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * and the occurrences of '1's or '2's in each group are:
 * 1 2	2 1 1 2 1 2 2 1 2 2 ......
 * You can see that the occurrence sequence above is the S itself.
 * Given an integer N as input, return the number of '1's in the first N number in the magical string S.
 * Note: N will not exceed 100,000.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MagicalString {

    public static void main(String... args) {
        MagicalString ms = new MagicalString();
        System.out.println(ms.magicalString(6));
    }

    public int magicalString(int n) {
        if (n <= 1) {
            return n;
        }
        int[] a = new int[n];
        a[0] = 1;
        a[1] = 2;
        int l = 0, r = 0, val = 1, ans = 0;
        while (l < n && r < n) {
            int cnt = a[l++];
            while (r < n && cnt > 0) {
                a[r++] = val;
                cnt--;
                if (val == 1) {
                    ans++;
                }
            }
            val = val == 1 ? 2 : 1;
        }
        return ans;
    }
}
