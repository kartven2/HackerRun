/*
 * LeetCode Problem : https://leetcode.com/problems/trapping-rain-water/
 * Given an m x n matrix of positive integers representing the height of
 * each unit cell in a 2D elevation map, compute the volume of water it is
 * able to trap after raining.
 * Note:
 * Both m and n are less than 110.
 * The height of each unit cell is greater than 0 and is less than 20,000.
 * After the rain, water are trapped between the blocks.
 * The total volume of water trapped is 4.
 */
package com.karthik.leetcode;

import java.util.PriorityQueue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class TrappingRainWaterII {

    private int ans = 0;

    private void visit(Cell c, boolean[][] mark, int[][] heightMap, int max, int n, int m, PriorityQueue<Cell> pq) {
        max = Math.max(max, c.val);
        if (c.r < n - 1 && !mark[c.r + 1][c.c]) {
            mark[c.r + 1][c.c] = true;
            Cell down = new Cell(c.r + 1, c.c, heightMap[c.r + 1][c.c]);
            if (down.val >= max) {
                pq.add(down);
            } else {
                ans += max - down.val;
                visit(down, mark, heightMap, max, n, m, pq);
            }
        }
        if (c.r > 0 && !mark[c.r - 1][c.c]) {
            mark[c.r - 1][c.c] = true;
            Cell up = new Cell(c.r - 1, c.c, heightMap[c.r - 1][c.c]);
            if (up.val >= max) {
                pq.add(up);
            } else {
                ans += max - up.val;
                visit(up, mark, heightMap, max, n, m, pq);
            }
        }
        if (c.c > 0 && !mark[c.r][c.c - 1]) {
            mark[c.r][c.c - 1] = true;
            Cell left = new Cell(c.r, c.c - 1, heightMap[c.r][c.c - 1]);
            if (left.val >= max) {
                pq.add(left);
            } else {
                ans += max - left.val;
                visit(left, mark, heightMap, max, n, m, pq);
            }
        }
        if (c.c < m - 1 && !mark[c.r][c.c + 1]) {
            mark[c.r][c.c + 1] = true;
            Cell right = new Cell(c.r, c.c + 1, heightMap[c.r][c.c + 1]);
            if (right.val >= max) {
                pq.add(right);
            } else {
                ans += max - right.val;
                visit(right, mark, heightMap, max, n, m, pq);
            }
        }
    }

    class Cell implements Comparable<Cell> {

        private int r, c, val;

        Cell(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }

        @Override
        public int compareTo(Cell other) {
            return this.val - other.val;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0) {
            return 0;
        }
        int n = heightMap.length, m = heightMap[0].length;
        boolean[][] mark = new boolean[n][m];
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(new Cell(i, 0, heightMap[i][0]));
            mark[i][0] = true;
            pq.add(new Cell(i, m - 1, heightMap[i][m - 1]));
            mark[i][m - 1] = true;
        }
        for (int j = 0; j < m; j++) {
            pq.add(new Cell(0, j, heightMap[0][j]));
            mark[0][j] = true;
            pq.add(new Cell(n - 1, j, heightMap[n - 1][j]));
            mark[n - 1][j] = true;
        }
        while (!pq.isEmpty()) {
            Cell c = pq.remove();
            visit(c, mark, heightMap, Integer.MIN_VALUE, n, m, pq);
        }
        return ans;
    }

    public static void main(String... args) {
        TrappingRainWaterII trw = new TrappingRainWaterII();
        int[][] ip = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
        System.out.println(trw.trapRainWater(ip));
    }
}
