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

import java.util.LinkedList;
import java.util.Queue;

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
}
