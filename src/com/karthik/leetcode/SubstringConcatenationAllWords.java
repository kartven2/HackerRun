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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SubstringConcatenationAllWords {

    public static void main(String... args) {
        SubstringConcatenationAllWords sca = new SubstringConcatenationAllWords();
        List<Integer> res = sca.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
    }

    private void addToResult(List<Integer> result, Map<Integer, String> idxMap, Map<String, List<Integer>> posMap,
            int start, int increment, boolean[] marked, int strLen, int numberOfWords) {
        int i = 0, j = start;
        while (i < numberOfWords && j < strLen) {
            if (!idxMap.containsKey(j)) {
                return;
            }
            String key = idxMap.get(j);
            List<Integer> keyIdxs = posMap.get(key);
            boolean allMarked = true;
            for (int y : keyIdxs) {
                if (!marked[y]) {
                    allMarked = false;
                    marked[y] = true;
                    i++;
                    j += increment;
                    break;
                }
            }
            if (allMarked) {
                return;
            }
        }
        if (i == numberOfWords) {
            result.add(start);
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        if (s == null || words == null || words.length == 0) {
            return result;
        }
        int strLen = s.length(), wl = words[0].length();
        if (strLen < wl * words.length) {
            return result;
        }

        Map<String, Integer> wstart = new HashMap<>();
        for (String w : words) {
            Integer count = wstart.get(w);
            count = count == null ? 1 : count + 1;
            wstart.put(w, count);
        }

        Map<Integer, String> idxMap = new HashMap<>();
        for (int i = 0; i < strLen - wl + 1; i++) {
            String str = s.substring(i, i + wl);
            if (wstart.containsKey(str)) {
                wstart.put(str, wstart.get(str) - 1);
                idxMap.put(i, str);
            }
        }

        int nw = 0;
        Map<String, List<Integer>> posMap = new HashMap<>();
        for (String w : words) {
            if (wstart.get(w) > 0) {
                return result;
            }
            List<Integer> wordsId = posMap.get(w);
            if (wordsId == null) {
                wordsId = new LinkedList<>();
            }
            wordsId.add(nw++);
            posMap.put(w, wordsId);
        }

        for (int start : idxMap.keySet()) {
            boolean[] marked = new boolean[nw];
            addToResult(result, idxMap, posMap, start, wl, marked, strLen, nw);
        }
        return result;
    }
}
