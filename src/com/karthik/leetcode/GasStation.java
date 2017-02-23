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
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int n = gas.length, s = n - 1, e = 0, sum = gas[s] - cost[s];
        while (s > e) {
            if (sum >= 0) {
                sum += gas[e] - cost[e];
                e++;
            } else {
                s--;
                sum += gas[s] - cost[s];
            }
        }
        return sum >= 0 ? s : -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        int n = gas.length, tank = 0, start = 0, net = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            net += gas[i] - cost[i];
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }
        return net < 0 ? -1 : start;
    }
    
    public static void main(String...args) {
        GasStation gs = new GasStation();
        int[] gas = {3,3,4,5};
        int[] cost = {3,2,4,5};
        System.out.println(gs.canCompleteCircuit(gas, cost));
        System.out.println(gs.canCompleteCircuit2(gas, cost));
    }
}
