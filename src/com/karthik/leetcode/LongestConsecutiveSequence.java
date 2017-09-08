/*
 * LeetCode: https://leetcode.com/problems/longest-consecutive-sequence/#/description
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
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
public class LongestConsecutiveSequence {

    class UF {

        private int[] parent;
        private int[] size;
        private int count;

        UF(int n) {
            parent = new int[n];
            size = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int getMaxSize() {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size.length; i++) {
                if (max < size[i]) {
                    max = size[i];
                }
            }
            return max;
        }

        private int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rp = find(p), rq = find(q);
            if (rp == rq) {
                return;
            }
            count--;
            if (size[rp] < size[rq]) {
                parent[rp] = rq;
                size[rq] += size[rp];
            } else {
                parent[rq] = rp;
                size[rp] += size[rq];
            }
        }
    }

    public int longestConsecutive(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length;
        UF uf = new UF(n);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(a[i])) {
                continue;
            }
            Integer pos = map.get(a[i] - 1);
            if (pos != null) {
                uf.union(i, pos);
            }
            pos = map.get(a[i] + 1);
            if (pos != null) {
                uf.union(i, pos);
            }
            map.put(a[i], i);
        }
        return uf.getMaxSize();
    }

    public int longestConsecutive2(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, ans = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(a[i]);
        }
        while (!set.isEmpty()) {
            int x = set.iterator().next();
            set.remove(x);
            int lb = x;
            while (set.contains(lb - 1)) {
                set.remove(lb - 1);
                lb--;
            }
            int ub = x;
            while (set.contains(ub + 1)) {
                set.remove(ub + 1);
                ub++;
            }
            ans = Math.max(ans, ub - lb + 1);
        }
        return ans;
    }
}
