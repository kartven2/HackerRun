/*
 * Leetcode: https://leetcode.com/problems/word-break/
 * Given a string s and a dictionary of words dict,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WordBreak {

    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.trim().isEmpty()) {
            return true;
        }
        if (wordDict.contains(s)) {
            return true;
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j]) {
                    String nextStr = s.substring(j, i);
                    if (wordDict.contains(nextStr)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n];
    }

    public static void main(String... args) {
        WordBreak wb = new WordBreak();
        String[] sample = {"cbc", "bcda", "adb", "ddca", "bad", "bbb", "dad", "dac", "ba", "aa",
            "bd", "abab", "bb", "dbda", "cb", "caccc", "d", "dd", "aadb", "cc",
            "b", "bcc", "bcd", "cd", "cbca", "bbd", "ddd", "dabb", "ab", "acd", "a",
            "bbcc", "cdcbd", "cada", "dbca", "ac", "abacd", "cba", "cdb", "dbac", "aada", "cdcda",
            "cdc", "dbc", "dbcb", "bdb", "ddbdd", "cadaa", "ddbc", "babb"};
        Set<String> wordDict = new HashSet<>();
        for (String y : sample) {
            wordDict.add(y);
        }
        System.out.println(wb.wordBreak("bccdbacdbdacddabbaaaadababadad", wordDict));
    }
}
