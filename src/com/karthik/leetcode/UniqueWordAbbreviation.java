/*-
* LeetCode problem : https://leetcode.com/problems/unique-word-abbreviation/description/
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UniqueWordAbbreviation {

    Map<String, Set<String>> map = new HashMap<>();

    private String convert(String x) {
        return (x == null || x.length() < 3) ? x : "" + x.charAt(0) + (x.length() - 2) + x.charAt(x.length() - 1);
    }

    public UniqueWordAbbreviation(String[] dict) {
        if (dict == null || dict.length == 0) {
            return;
        }
        for (String x : dict) {
            String y = convert(x);
            Set<String> set = map.get(y);
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(x);
            map.put(y, set);
        }
    }

    public boolean isUnique(String x) {
        String y = convert(x);
        if (!map.containsKey(y)) {
            return true;
        }
        Set<String> set = map.get(y);
        return set.size() == 1 && set.contains(x);
    }
}
