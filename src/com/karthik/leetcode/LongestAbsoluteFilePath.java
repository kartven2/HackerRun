/*
 * leetCode Problem: https://leetcode.com/problems/longest-absolute-file-path/#/description
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestAbsoluteFilePath {

    public static void main(String... args) {
        LongestAbsoluteFilePath lafp = new LongestAbsoluteFilePath();
        lafp.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext");
    }

    public int lengthLongestPath(String a) {
        if (a == null || a.trim().isEmpty()) {
            return 0;
        }
        int ans = 0, curLen = 0;
        Stack<Integer> stk = new Stack<>();
        String[] s = a.split("\n");
        for (String x : s) {
            String xs = x.replace("\t", "");
            int lvl = x.length() - xs.length();
            while (stk.size() > lvl) {
                curLen -= stk.pop();
            }
            int len = xs.length() + 1;
            curLen += len;
            if (xs.contains(".") && curLen - 1 > ans) {
                ans = curLen - 1;
            }
            stk.push(len);
        }
        return ans;
    }
}
