/*
 * LeetCode: https://leetcode.com/problems/count-of-range-sum/#/description
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 *
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * Example:
 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
 * Return 3.
 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 *
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CountRangeSum {

    public static void main(String... args) {
        CountRangeSum crs = new CountRangeSum();
        int[] nums = {-2, 5, -1};
        int lower = -2, upper = 2;
        crs.countRangeSum(nums, lower, upper);
    }

    class Node {

        private long min;
        private long max;
        private int count;

        public Node(long min, long max) {
            this.min = min;
            this.max = max;
        }
        private Node left, right;
    }

    private Node buildTree(Long[] arr, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        Node x = new Node(arr[lo], arr[hi]);
        if (lo == hi) {
            return x;
        }
        int mid = (lo + hi) >> 1;
        x.left = buildTree(arr, lo, mid);
        x.right = buildTree(arr, mid + 1, hi);
        return x;
    }

    private void updateCount(Node x, long val) {
        if (x == null) {
            return;
        }
        if (val >= x.min && val <= x.max) {
            x.count++;
            updateCount(x.left, val);
            updateCount(x.right, val);
        }
    }

    private int getCount(Node x, long min, long max) {
        if (x == null) {
            return 0;
        }
        if (min > x.max || max < x.min) {
            return 0;
        }
        if (min <= x.min && max >= x.max) {
            return x.count;
        }
        return getCount(x.left, min, max) + getCount(x.right, min, max);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long sum = 0;
        int n = nums.length;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            set.add(sum);
        }
        Long[] sval = set.toArray(new Long[set.size()]);
        Arrays.sort(sval);
        Node root = buildTree(sval, 0, sval.length - 1);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            updateCount(root, sum);
            sum -= nums[i];
            ans += getCount(root, sum + lower, sum + upper);
        }
        return ans;
    }
}
