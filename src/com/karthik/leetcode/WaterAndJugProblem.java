/*
 * Leetcode: https://leetcode.com/problems/water-and-jug-problem/
 *
 * You are given two jugs with capacities x and y litres.
 * There is an infinite amount of water supply available.
 * You need to determine whether it is possible to measure exactly z litres using these two jugs.
 * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
 * Operations allowed:
 * Fill any of the jugs completely with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WaterAndJugProblem {

    public boolean canMeasureWater(int x, int y, int z) {
        if (z == y || z == x || z == 0) {
            return true;
        }
        if (z > x + y) {
            return false;
        }
        int gcd = gcd(x, y);
        return z % gcd == 0;
    }

    private int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
