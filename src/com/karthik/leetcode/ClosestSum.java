/*
 * Leetcode: https://leetcode.com/problems/3sum-closest/
 *
 * Given an array S of n integers,
 * find three integers in S such that the sum is closest to nums given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
package com.karthik.leetcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ClosestSum {

    class InputReader {

        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 1024);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }

            return tokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/closestsum"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        Arrays.sort(nums);
        int bestDist = Integer.MAX_VALUE;
        int i = 0, j = 0, k = 0, sum = 0, result = 0, dist = 0;

        for (; i < n - 2; i++) {
            j = i + 1;
            k = n - 1;
            while (k > j) {
                sum = nums[i] + nums[j] + nums[k];
                dist = target - sum;
                if (dist == 0) {
                    System.out.println(sum);
                    return;
                }
                if (bestDist > Math.abs(dist)) {
                    result = sum;
                    bestDist = Math.abs(dist);
                }
                if (dist < 0) {
                    k--;
                }
                if (dist > 0) {
                    j++;
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String... args) {
        ClosestSum cs = new ClosestSum();
        cs.compute();
    }
}
