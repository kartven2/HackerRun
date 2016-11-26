/*
 * LeetCode: https://leetcode.com/problems/4sum/
 *
 * Given an array S of n integers,
 * are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note: The solution set must not contain duplicate quadruplets.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FourSum {

    private void loadResult(Set<Quad> ans, List<List<Integer>> result) {
        for (Quad x : ans) {
            List<Integer> y = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                y.add(x.a[i]);
            }
            result.add(y);
        }
    }

    class Quad {

        private int[] a;

        Quad(int w, int x, int y, int z) {
            a = new int[4];
            a[0] = w;
            a[1] = x;
            a[2] = y;
            a[3] = z;
            for (int i = 1; i < 4; i++) {
                for (int j = i; j >= 0 && a[j] < a[j - 1]; j--) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            Quad other = (Quad) o;
            if (this.a != null && other.a == null) {
                return false;
            }
            for (int i = 0; i < 4; i++) {
                if (a[i] != other.a[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return 323 + Arrays.hashCode(this.a);
        }
    }

    private Integer binarySearch(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (a[mid] == key) {
                return key;
            } else if (a[mid] > key) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return null;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Set<Quad> ans = new HashSet<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    Integer val = binarySearch(nums, target - (nums[i] + nums[j] + nums[k]), k + 1, n - 1);
                    if (val != null) {
                        Quad q = new Quad(nums[i], nums[j], nums[k], val);
                        ans.add(q);
                    }
                }
            }
        }
        loadResult(ans, result);
        return result;
    }
}
