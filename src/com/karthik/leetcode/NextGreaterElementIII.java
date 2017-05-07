/*
 * LeetCode: https://leetcode.com/problems/next-greater-element-iii/#/description
 *
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
 * which has exactly the same digits existing in the integer n and is greater in
 * value than n. If no such positive 32-bit integer exists, you need to return -1.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NextGreaterElementIII {

    public static void main(String... args) {
        NextGreaterElementIII ng = new NextGreaterElementIII();
        ng.nextGreaterElement(101);
    }

    public int nextGreaterElement(int n) {
        if (n <= 11 || n == Integer.MAX_VALUE) {
            return -1;
        }
        int slen = (n + "").length();
        int[] d = new int[slen];
        int i = slen - 1;
        int a = n;
        while (a >= 10) {
            d[i--] = a % 10;
            a /= 10;
        }
        d[i] = a;
        i = slen - 2;
        while (i >= 0 && d[i] >= d[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = slen - 1;
        while (j > i && d[j] <= d[i]) {
            j--;
        }
        int tmp = d[i];
        d[i] = d[j];
        d[j] = tmp;
        i++;
        j = slen - 1;
        while (i < j) {
            tmp = d[i];
            d[i] = d[j];
            d[j] = tmp;
            i++;
            j--;
        }
        long ans = d[slen - 1], k = 10;
        i = slen - 2;
        while (i >= 0) {
            ans += d[i] * k;
            k *= 10;
            i--;
        }
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    public int nextGreaterElement2(int n) {
        if (n <= 11 || n >= Integer.MAX_VALUE) {
            return -1;
        }
        char[] s = Integer.toString(n).toCharArray();
        int i = s.length - 2, ix = 0, jx = 0;
        for (; i >= 0; i--) {
            jx = s[i + 1] - '0';
            ix = s[i] - '0';
            if (ix < jx) {
                break;
            }
        }
        if (i < 0) {
            return -1;
        }
        int j = i;
        i = s.length - 1;
        for (; i > j; i--) {
            if (s[i] - '0' > s[j] - '0') {
                char tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
                break;
            }
        }
        for (int k = j + 1, l = s.length - 1; k < l; k++, l--) {
            char tmp = s[k];
            s[k] = s[l];
            s[l] = tmp;
        }
        String ans = new String(s);
        long res = Long.parseLong(ans);
        return res <= Integer.MAX_VALUE ? (int) res : -1;
    }
}
