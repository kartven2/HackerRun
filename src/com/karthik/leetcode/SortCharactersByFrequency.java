/*-
 * LeetCode: https://leetcode.com/problems/sort-characters-by-frequency/#/description
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        if (s == null || s.trim().length() == 0) {
            return s;
        }
        int max = 0;
        char c[] = s.toCharArray();
        int[] freq = new int[256];
        for (int i = 0; i < c.length; i++) {
            freq[c[i]]++;
            max = Math.max(max, freq[c[i]]);
        }
        List<Character>[] list = (List<Character>[]) new LinkedList[max + 1];
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                if (list[freq[i]] == null) {
                    list[freq[i]] = new LinkedList<>();
                }
                list[freq[i]].add((char) i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = max; i > 0; i--) {
            if (list[i] != null) {
                for (char x : list[i]) {
                    for (int j = 0; j < i; j++) {
                        sb.append(x);
                    }
                }
            }
        }
        return sb.toString();
    }
}
