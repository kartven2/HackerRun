/*
 * LeetCode Problem: https://leetcode.com/problems/frog-jump/
 * A frog is crossing a river.
 * The river is divided into x units and at each unit there may or may not exist a stone.
 * The frog can jump on a stone, but it must not jump into the water.
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone.
 * Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
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
public class FrogJump {

    public boolean canCross(int[] stones) {
        int n = stones.length, max = 0;
        int[] maxSteps = new int[n];
        Map<Integer, Set<Integer>> path = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        set.add(0);
        path.put(0, set);
        for (int i = 1; i < n; i++) {
            Set<Integer> currentSet = new HashSet<>();
            for (int j = i - 1; j >= 0; j--) {
                int steps = stones[i] - stones[j];
                if (steps > maxSteps[j] + 1) {
                    break;
                }
                Set<Integer> prev = path.get(j);
                if (prev.contains(steps) || prev.contains(steps - 1) || prev.contains(steps + 1)) {
                    currentSet.add(steps);
                    max = Math.max(max, steps);
                }
            }
            path.put(i, currentSet);
            maxSteps[i] = max;
        }
        return path.get(n - 1).size() > 0;
    }
}
