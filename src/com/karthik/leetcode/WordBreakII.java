/*
 * Leetcode: https://leetcode.com/problems/word-break-ii/#/description
 * Given a non-empty string s and a dictionary wordDict containing a list
 * of non-empty words, add spaces in s to construct a sentence where each
 * word is a valid dictionary word.
 * You may assume the dictionary does not contain duplicate words.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WordBreakII {

    public static void main(String... args) {
        WordBreakII wb = new WordBreakII();
        String s = "catsanddog";
        String[] arr = {"cat", "cats", "and", "sand", "dog"};
        List<String> word = Arrays.asList(arr);
        wb.wordBreak(s, word);
    }

    class Sample {

        private int idx;
        private String str;

        Sample(int idx, String str) {
            this.idx = idx;
            this.str = str;
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.trim().length() == 0) {
            return result;
        }
        int n = s.length();
        Set<String> dict = new HashSet<>();
        for (String x : wordDict) {
            dict.add(x);
        }
        List<Integer>[] dp = (List<Integer>[]) new ArrayList[n + 1];
        dp[0] = new ArrayList<>();
        dp[0].add(0);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] != null) {
                    String sub = s.substring(j, i);
                    if (dict.contains(sub)) {
                        if (dp[i] == null) {
                            dp[i] = new ArrayList<>();
                        }
                        dp[i].add(j);
                    }
                }
            }
        }
        if (dp[n] == null) {
            return result;
        }
        //dfs(result, dp, n, "", s);
        Stack<Sample> stk = new Stack<>();
        stk.push(new Sample(n, ""));
        while (!stk.isEmpty()) {
            Sample v = stk.pop();
            if (dp[v.idx] == null) {
                continue;
            }
            if (v.idx == 0) {
                result.add(v.str.trim());
                continue;
            }
            for (int x : dp[v.idx]) {
                stk.push(new Sample(x, s.substring(x, v.idx) + " " + v.str));
            }
        }
        return result;
    }

    private void dfs(List<String> result, List<Integer>[] dp, int end, String x, String s) {
        if (end == 0) {
            result.add(x.trim());
            return;
        }
        if (dp[end] == null) {
            return;
        }
        for (int start : dp[end]) {
            dfs(result, dp, start, s.substring(start, end) + " " + x, s);
        }
    }
}
