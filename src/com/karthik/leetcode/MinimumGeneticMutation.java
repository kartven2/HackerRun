/*
 * Leetcode: https://leetcode.com/problems/minimum-genetic-mutation/description/
 *
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is
 * defined as ONE single character changed in the gene string.
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 * Also, there is a given gene "bank", which records all the valid gene mutations.
 * A gene must be in the bank to make it a valid gene string.
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number
 * of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 * Note:
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MinimumGeneticMutation {

    class Entry {

        String str;
        int dist;

        private Entry(String str, int dist) {
            this.str = str;
            this.dist = dist;
        }
    }

    private boolean canConvert(String a, String b) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) {
            return -1;
        }
        int n = bank.length;
        boolean[] marked = new boolean[n];
        Queue<Entry> q = new LinkedList<>();
        q.add(new Entry(start, 0));
        while (!q.isEmpty()) {
            Entry v = q.remove();
            for (int i = 0; i < bank.length; i++) {
                if (!marked[i] && canConvert(v.str, bank[i])) {
                    marked[i] = true;
                    if (bank[i].equals(end)) {
                        return v.dist + 1;
                    }
                    q.add(new Entry(bank[i], v.dist + 1));
                }
            }
        }
        return -1;
    }

    public int minMutation2(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String x : bank) {
            set.add(x);
        }
        set.add(start);
        if (!set.contains(end)) {
            return -1;
        }
        Set<String> set1 = new HashSet<>();
        set1.add(start);
        Set<String> set2 = new HashSet<>();
        set2.add(end);
        return bfs(set1, set2, set, 0, new char[]{'A', 'C', 'T', 'G'});
    }

    private int bfs(Set<String> set1, Set<String> set2, Set<String> dict, int lvl, char[] s) {
        if (set1.isEmpty()) {
            return -1;
        }
        if (set1.size() > set2.size()) {
            return bfs(set2, set1, dict, lvl, s);
        }
        for (String x : set1) {
            dict.remove(x);
        }
        for (String x : set2) {
            dict.remove(x);
        }
        Set<String> set = new HashSet<>();
        for (String x : set1) {
            char[] p = x.toCharArray();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    char temp = p[i];
                    p[i] = s[j];
                    String y = new String(p);
                    if (set2.contains(y)) {
                        return lvl + 1;
                    }
                    if (dict.contains(y)) {
                        set.add(y);
                    }
                    p[i] = temp;
                }
            }
        }
        return bfs(set2, set, dict, lvl + 1, s);
    }

}
