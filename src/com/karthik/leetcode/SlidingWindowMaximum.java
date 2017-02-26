/*
 * LeetCode: https://leetcode.com/problems/sliding-window-maximum/
 *
 * Given an array nums, there is a sliding window of size k which
 * is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 1) {
            return nums;
        }
        int n = nums.length;
        PriorityQueue<Integer> q = new PriorityQueue<>(16, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int[] max = new int[n - k + 1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                max[j++] = q.peek();
                q.remove(nums[i - k]);
            }
            q.add(nums[i]);

        }
        max[j++] = q.peek();
        return max;
    }

    class Multiset {

        Map<Integer, List<Integer>> map = new HashMap<>();

        private void add(int key, int pos) {
            List<Integer> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(pos);
            map.put(key, list);
        }

        private boolean contains(int key) {
            return map.containsKey(key);
        }

        private boolean remove(int key) {
            List<Integer> list = map.get(key);
            if (list == null) {
                return false;
            }
            list.remove(0);
            if (list.isEmpty()) {
                map.remove(key);
                return true;
            }
            return false;
        }
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return nums;
        }
        int n = nums.length, mval = Integer.MIN_VALUE, j = 0;
        Multiset m = new Multiset();
        int[] max = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (i < k) {
                if (nums[i] > mval) {
                    mval = nums[i];
                    m.add(nums[i], i);
                }
            } else {
                int oldNum = nums[i - k], newNum = nums[i];
                max[j++] = mval;
                boolean x = m.remove(oldNum);
                if (newNum >= mval) {
                    mval = newNum;
                    m.add(nums[i], i);
                } else if (oldNum == mval) {
                    if (x) {
                        int sm = Integer.MIN_VALUE, smi = 0;
                        for (int l = i; l > i - k; l--) {
                            if (nums[l] > sm) {
                                sm = nums[l];
                                smi = l;
                            }
                        }
                        if (!m.contains(sm)) {
                            m.add(sm, smi);
                        }
                        mval = sm;
                    }
                }
            }
        }
        max[j++] = mval;
        return max;
    }
}