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
    private int[] dp = new int[10];
    
    public int countDigitOne(int n) {
     if(n<=0) {
         return 0;
     }
     if(n<=9) {
         return 1;
     }
     int m=1;
     for(int i=n/10; i>0; i/=10) {
         m*=10;
     }
     for(int i=1,j=0; i<=Integer.toString(m-1).length(); i++, j=(j*10)+9) {
         dp[i] = dp[i-1] * 10 + (j+1); 
     }
     return eval(n, m);
    }
    
    private int eval(int n, int m) {
        if(n<=0) {
            return 0;
        }
        if(n<=9) {
            return 1;
        }
        int fd = n/m;
        int ans = dp[Integer.toString(m-1).length()] * fd;
        ans += fd > 1 ? m : fd == 1 ? (n%m)+1 : 0;
        ans += eval(n%m, m/10);
        return ans;
    }

    public static void main(String... args) {
        NumberOfDigitOne ndo = new NumberOfDigitOne();
        System.out.println(ndo.countDigitOne(1410065408));
    }
}
