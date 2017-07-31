/*
 * Leetcode: https://leetcode.com/problems/concatenated-words/
 * Given a list of words (without duplicates), please write a program that
 * returns all concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely
 * of at least two shorter words in the given array.
 *
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ConcatenatedWords {

    private boolean canAdd(String x, Set<String> set) {
        if (x.length() == 0) {
            return true;
        }
        for (int k = 1; k <= x.length(); k++) {
            String cw = x.substring(0, k);
            if (set.contains(cw) && canAdd(x.substring(k), set)) {
                return true;
            }
        }
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        if (words == null || words.length < 2) {
            return new LinkedList<>();
        }
        Arrays.sort(words, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        Set<String> set = new HashSet<>();
        List<String> result = new LinkedList<>();
        for (String x : words) {
            if (!set.isEmpty() && canAdd(x, set)) {
                result.add(x);
            }
            set.add(x);
        }
        return result;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new LinkedList<>();
        if (words == null || words.length < 2) {
            return result;
        }
        int minLen = Integer.MAX_VALUE;
        Map<Integer, Set<String>> map = new HashMap<>();
        for (String x : words) {
            int len = x.length();
            if (len < minLen) {
                minLen = len;
            }
            Set<String> wds = map.get(len);
            if (wds == null) {
                wds = new HashSet<>();
            }
            wds.add(x);
            map.put(len, wds);
        }
        for (String x : words) {
            verifyConcatenated(result, map, x, x, minLen);
        }
        return result;
    }

    private boolean verifyConcatenated(List<String> result,
            Map<Integer, Set<String>> map, String cword, String oword, int minLen) {
        if (cword.length() == 0) {
            if (oword.length() > 0) {
                result.add(oword);
            }
            return true;
        }
        if (cword.length() >= minLen) {
            for (int k = 1; k <= cword.length(); k++) {
                Set<String> wrds = map.get(k);
                if (wrds != null) {
                    String currWord = cword.substring(0, k);
                    if (!oword.equals(currWord) && wrds.contains(currWord) && verifyConcatenated(result, map, cword.substring(k), oword, minLen)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String... args) {
        ConcatenatedWords cw = new ConcatenatedWords();
        String[] input = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "ratcatdogcat"};
        List<String> ans = cw.findAllConcatenatedWordsInADict2(input);
        for (String x : ans) {
            System.out.println(x);
        }
    }
}
