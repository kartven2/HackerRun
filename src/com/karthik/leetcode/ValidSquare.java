/*
 * https://leetcode.com/problems/valid-square/#/description
 *
 * Given the coordinates of four points in 2D space,
 * return whether the four points could construct a square.
 * The coordinate (x,y) of a point is represented by an integer array with two integers.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ValidSquare {

    private double computeLen(int[] p1, int[] p2) {
        int diffx = p2[0] - p1[0];
        int diffy = p2[1] - p1[1];
        return Math.sqrt((diffx * diffx) + (diffy * diffy));
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1 == null || p2 == null || p3 == null || p4 == null || p1.length != 2 || p2.length != 2 || p3.length != 2 || p4.length != 2) {
            return false;
        }
        double[] ans = new double[6];
        ans[0] = computeLen(p1, p2);
        ans[1] = computeLen(p1, p3);
        ans[2] = computeLen(p1, p4);
        ans[3] = computeLen(p2, p3);
        ans[4] = computeLen(p2, p4);
        ans[5] = computeLen(p3, p4);
        Map<Double, Integer> map = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            Integer x = map.get(ans[i]);
            if (x == null) {
                x = 0;
            }
            map.put(ans[i], x + 1);
        }
        if (map.size() != 2) {
            return false;
        }
        Double side = null, diag = null;
        for (Double x : map.keySet()) {
            if (side == null) {
                side = x;
            } else if (diag == null && x < side) {
                diag = side;
                side = x;
            } else {
                diag = x;
            }
        }
        return (map.get(diag) == 2 && map.get(side) == 4);
    }
}
