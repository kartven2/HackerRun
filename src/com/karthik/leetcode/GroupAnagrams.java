/*
 * LeetCode: https://leetcode.com/problems/anagrams/#/description
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"]
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] a) {
        List<List<String>> result = new ArrayList<>();
        if (a == null || a.length == 0) {
            return result;
        }
        int n = a.length;
        Map<String, List<String>> grp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int[] x = new int[26];
            for (int j = 0; j < a[i].length(); j++) {
                x[a[i].charAt(j) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < x.length; j++) {
                if (x[j] > 0) {
                    sb.append((char) (j + 'a'));
                    sb.append(x[j]);
                }
            }
            String key = sb.toString();
            List<String> list = grp.get(key);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(a[i]);
            grp.put(key, list);
        }
        for (List<String> list : grp.values()) {
            result.add(list);
        }
        return result;
    }
}
