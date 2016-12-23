/*
 * LeetCode: https://leetcode.com/problems/unique-substrings-in-wraparound-string/
 * Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz",
 * so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * Now we have another string p. Your job is to find out how many unique non-empty substrings of p
 * are present in s. In particular, your input is the string p and you need to output the number of
 * different non-empty substrings of p in the string s.
 * Note: p consists of only lowercase English letters and the size of p might be over 10000.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniqueSubstringsWraparoundStringII {

    public static void main(String... args) {
        UniqueSubstringsWraparoundStringII uw = new UniqueSubstringsWraparoundStringII();
        System.out.println(uw.findSubstringInWraproundString("zaba"));
    }

    private boolean isAdj(char a, char b) {
        if (a == b) {
            return false;
        } else if ((a == 'z' && b == 'a') || a + 1 == b) {
            return true;
        }
        return false;
    }

    public int findSubstringInWraproundString(String p) {
        if (p == null || p.trim().isEmpty()) {
            return 0;
        }
        int n = p.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = p.charAt(i);
            int val = 1;
            for (int j = i - 1, k = i; j >= 0 && isAdj(p.charAt(j), p.charAt(k)); j--, k--) {
                val++;
            }
            val = map.containsKey(c) ? Math.max(map.get(c), val) : val;
            map.put(c, val);
        }
        for (char x : map.keySet()) {
            ans += map.get(x);
        }
        return ans;
    }
}
