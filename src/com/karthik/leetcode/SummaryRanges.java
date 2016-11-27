/*
 * Leetcode: https://leetcode.com/problems/summary-ranges/
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int n = nums.length;
        Stack<Integer> st = new Stack<>();
        st.push(nums[0]);
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] > nums[i] + 1) {
                int y = st.pop();
                String x = y == nums[i] ? y + "" : y + "->" + nums[i];
                result.add(x);
                st.push(nums[i + 1]);
            }
        }
        if (!st.isEmpty()) {
            int y = st.pop();
            String x = y == nums[n - 1] ? y + "" : y + "->" + nums[n - 1];
            result.add(x);
        }
        return result;
    }
}
