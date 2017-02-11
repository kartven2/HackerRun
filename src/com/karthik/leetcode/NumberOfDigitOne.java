/*
 * https://leetcode.com/problems/number-of-digit-one/
 *
 * Given an integer n, count the total number of digit 1
 * appearing in all non-negative integers less than or equal to n.
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NumberOfDigitOne {

    private static final int[] DP = new int[(int) 5e8 + 1];
    private static final int MAX = Integer.MAX_VALUE;

    public int countDigitOne(int n) {
        for (int i = 9, j = 1, k = 0; i <= (MAX / 10 - 9); i = (i * 10) + 9, j++, k++) {
            DP[j] = DP[j - 1] * 10 + (int) Math.pow(10, k);
        }
        
        return eval(n);
    }
    
    private int eval(int n) {
        if(n<=0) {
            return 0;
        }
        if(n<=9) {
            return 1;
        }
        int m = 1;
        for(;m<=n;m*=10);
        int dpIdx = Integer.toString(m-1).length(), fd = n/m;
        int ans = DP[dpIdx]*fd;
        ans += fd > 1 ? m : 0;
        ans += fd == 1 ? 
        
        
        
    }
}
