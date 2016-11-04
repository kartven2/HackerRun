/*
 * LeetCode: https://leetcode.com/problems/interleaving-string/
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null) {
            return s3 == null;
        }
        if (s1 == null || s1.isEmpty()) {
            return s3.equals(s2);
        }
        if (s2 == null || s2.isEmpty()) {
            return s3.equals(s1);
        }
        int i = 0, j = 0, k = 0, in = s1.length(), jn = s2.length(), kn = s3.length();
        if (kn != (in + jn)) {
            return false;
        }
        while (k < kn) {
            if (i >= in) {
                if (s2.charAt(j) != s3.charAt(k)) {
                    return false;
                }
                j++;
                k++;
            } else if (j >= jn) {
                if (s1.charAt(i) != s3.charAt(k)) {
                    return false;
                }
                i++;
                k++;
            } else if (s1.charAt(i) != s2.charAt(j)) {
                if (s1.charAt(i) == s3.charAt(k)) {
                    i++;
                    k++;
                } else if (s2.charAt(j) == s3.charAt(k)) {
                    j++;
                    k++;
                } else {
                    return false;
                }
            } else {
                int ck = k, ci = i, cj = j;
                StringBuilder sb = new StringBuilder();
                while (ci < in && cj < jn && s1.charAt(ci) == s2.charAt(cj)) {
                    ci++;
                    cj++;
                    ck++;
                    sb.append(s1.charAt(ci));
                }
                if(ci==in && cj==jn) {
                    return sb.toString().equals(s3.substring(ck));
                } else if (ci == in) {
                    if (s3.charAt(ck + 1) != s2.charAt(cj + 1)) {
                        return false;
                    }
                    k = ck + 2;
                    j = cj + 2;
                    i = ci;
                } else if (cj == jn) {
                    if (s3.charAt(ck + 1) != s1.charAt(ci + 1)) {
                        return false;
                    }
                    k = ck + 2;
                    i = ci + 2;
                    j = cj;
                } else {
                    if (s3.charAt(ck + 1) == s1.charAt(ci + 1)) {
                        k = ck + 2;
                        i = ci + 2;
                    } else if (s3.charAt(ck + 1) == s1.charAt(cj + 1)) {
                        k = ck + 2;
                        j = cj + 2;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
