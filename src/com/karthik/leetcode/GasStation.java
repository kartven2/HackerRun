/*
 * Leetcode: https://leetcode.com/problems/gas-station/
 *
 * There are N gas stations along a circular route,
 * where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs
 * cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the
 * circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0
                || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int n = gas.length;
        int[] diff = new int[n];
        long net = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = gas[i] - cost[i];
            net += diff[i];
        }
        if (net < 0) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            if (diff[i] < 0) {
                continue;
            }
            int bal = 0;
            boolean complete = true;
            for (int j = i, k=0; k<n; j = (j + 1) % n, k++) {
                if (diff[j] >= 0 || bal + diff[j] >= 0) {
                    bal += diff[j];
                } else {
                    complete = false;
                    break;
                }
            }
            if (complete) {
                return i;
            }
        }
        return -1;
    }
}
