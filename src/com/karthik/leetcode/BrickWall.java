/*-
 * LeetCode Problem : https://leetcode.com/problems/brick-wall/description/
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks.
 * The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.
 * If your line go through the edge of a brick, then the brick is not considered as crossed.
 * You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.
 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 * The width sum of bricks in different rows are the same and won't exceed INT_MAX.
 * The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000].
 * Total number of bricks of the wall won't exceed 20,000.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BrickWall {

    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.isEmpty()) {
            return 0;
        }
        int n = wall.size(), ans = n, w = 0;
        for (int x : wall.get(0)) {
            w += x;
        }
        Map<Integer, Integer> dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < wall.get(i).size(); j++) {
                sum += wall.get(i).get(j);
                if (sum < w) {
                    Integer x = dp.get(sum);
                    x = x == null ? n - 1 : x - 1;
                    dp.put(sum, x);
                    if (ans > x) {
                        ans = x;
                    }
                }
            }
        }
        return ans;
    }
}
