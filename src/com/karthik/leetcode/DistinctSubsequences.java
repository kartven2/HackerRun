/*
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Given a string S and a string T,
 * count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed
 * from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions
 * of the remaining characters. (ie, "ACE" is a subsequence of
 * "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.trim().isEmpty() || t.trim().isEmpty()) {
            return 0;
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> list = map.get(c);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(c, list);
        }
        return numDistinct(t, map, 0, t.length(), null);
    }

    private int numDistinct(String t,
            Map<Character, List<Integer>> map, int ti, int length,
            Stack<Integer> iList) {
        if (ti == length) {
            return 1;
        }
        char c = t.charAt(ti);
        if (!map.containsKey(c)) {
            return 0;
        }
        int ans = 0;
        for (int si : map.get(c)) {
            if (ti == 0) {
                iList = new Stack<>();
            } else if (iList.peek() >= si) {
                continue;
            }
            iList.push(si);
            ans += numDistinct(t, map, ti + 1, length, iList);
            iList.pop();
        }
        return ans;
    }

    public static void main(String... args) {
        DistinctSubsequences ds = new DistinctSubsequences();
        System.out.println(ds.numDistinct("rabbbit", "rabbit"));
    }
}
