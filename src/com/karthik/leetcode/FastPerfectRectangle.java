/*
 * Leetcode: https://leetcode.com/problems/perfect-rectangle/
 *
 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
 * Return false. Because there is a gap between the two rectangular regions.
 * Return false. Because there is a gap in the top center.
 * Return false. Because two of the rectangles overlap with each other.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastPerfectRectangle {

    private static final String COMMA = ",";
    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;
    private Map<String, Integer> map = new HashMap<>();

    private boolean isOverlap(String x, int type) {
        Integer valtype = map.get(x);
        if (valtype == null) {
            map.put(x, type);
            return false;
        }
        if ((valtype & type) > 0) {
            return true;
        }
        valtype |= type;
        map.put(x, valtype);
        return false;
    }

    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) {
            return false;
        }
        long area = 0;
        int l = MAX, b = l, r = MIN, t = r;
        for (int[] coord : rectangles) {
            if (isOverlap(coord[0] + COMMA + coord[1], 1)) {
                return false;
            }
            if (isOverlap(coord[0] + COMMA + coord[3], 2)) {
                return false;
            }
            if (isOverlap(coord[2] + COMMA + coord[1], 4)) {
                return false;
            }
            if (isOverlap(coord[2] + COMMA + coord[3], 8)) {
                return false;
            }
            area += (coord[2] - coord[0]) * (coord[3] - coord[1]);
            l = Math.min(l, coord[0]);
            b = Math.min(b, coord[1]);
            r = Math.max(r, coord[2]);
            t = Math.max(t, coord[3]);
        }
        int corners = 0;
        for (int x : map.values()) {
            if (x != 3 && x != 5 && x != 6 && x != 9 && x != 10 && x != 12 && x != 15) {
                corners++;
            }
        }
        return corners == 4 && area == (r - l) * (t - b);
    }
}
