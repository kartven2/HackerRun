/*
 * LeetCode: https://leetcode.com/problems/rectangle-area/#/description
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Assume that the total area is never beyond the maximum possible value of int.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int rectArea = (((C - A) * (D - B)) + ((G - E) * (H - F)));
        int intersectArea = ((Math.min(G, C) - Math.max(A, E)) * (Math.min(D, H) - Math.max(F, B)));

    }
}
