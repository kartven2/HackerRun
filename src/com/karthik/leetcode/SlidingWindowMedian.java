/*
 * LeetCode Problem: https://leetcode.com/problems/sliding-window-median/
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * Examples: 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * Given an array nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * Your job is to output the median array for each window in the original array.
 */
package com.karthik.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SlidingWindowMedian {

    PriorityQueue<Double> minPq = new PriorityQueue<>();
    PriorityQueue<Double> maxPq = new PriorityQueue<>(16, new Comparator<Double>() {
        @Override
        public int compare(Double a, Double b) {
            return b.compareTo(a);
        }
    });

    private void insert(double key) {
        minPq.add(key);
        maxPq.add(minPq.remove());
        if (minPq.size() < maxPq.size()) {
            minPq.add(maxPq.remove());
        }
    }

    private void delete(double key, boolean minq) {
        if (minq) {
            minPq.remove(key);
        } else {
            maxPq.remove(key);
        }
        if (minPq.size() < maxPq.size()) {
            minPq.add(maxPq.remove());
        }
    }

    private double findMedian() {
        if (minPq.size() == maxPq.size()) {
            return (minPq.peek() + maxPq.peek()) / 2;
        }
        return minPq.peek();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int n = nums.length, j = 0;
        double[] result = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            if (i < k) {
                insert(nums[i]);
                continue;
            }
            result[j] = findMedian();
            delete(nums[i - k], nums[i - k] >= result[j]);
            j++;
            insert(nums[i]);
        }
        result[j] = findMedian();
        return result;
    }

    public static void main(String... args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] num = {1, 3, -1, -3, 5, 3, 6, 7};
        swm.medianSlidingWindow(num, 3);
    }
}
