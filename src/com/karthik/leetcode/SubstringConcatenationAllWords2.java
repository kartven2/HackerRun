/*
 * LeetCode: https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in
 * words exactly once and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * You should return the indices: [0,9].
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SubstringConcatenationAllWords2 {

    public static void main(String... args) {
        SubstringConcatenationAllWords2 sc2 = new SubstringConcatenationAllWords2();
        sc2.findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }
        Map<String, Integer> map = new HashMap<>();
        int strlen = s.length(), nw = words.length, wl = words[0].length();
        if (strlen < nw * wl) {
            return result;
        }
        for (int i = 0; i < nw; i++) {
            map.put(words[i], !map.containsKey(words[i]) ? 1 : map.get(words[i]) + 1);
        }
        int max = strlen - (nw * wl) + 1;
        for (int i = 0; i < max; i++) {
            Map<String, Integer> mark = new HashMap<>(map);
            int j = i, k = nw;
            while (k > 0) {
                String key = s.substring(j, j + wl);
                if (!mark.containsKey(key) || mark.get(key) == 0) {
                    break;
                }
                mark.put(key, mark.get(key) - 1);
                k--;
                j += wl;
            }
            if (k == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
