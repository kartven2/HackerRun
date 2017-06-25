/*
 * LeetCode: https://leetcode.com/problems/house-robber-iii/#/description
 *
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class HouseRobberIII {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int rob2(TreeNode x) {
        int[] result = dfs(x);
        return max(result[0], result[1]);
    }

    private int[] dfs(TreeNode x) {
        int[] res = new int[2];
        if (x == null) {
            return res;
        }
        int[] l = dfs(x.left);
        int[] r = dfs(x.right);
        res[0] = x.val + l[1] + r[1];
        res[1] = max(l[0], l[1]) + max(r[0], r[1]);
        return res;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int rob(TreeNode x) {
        if (x == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        q.add(x);
        map.put(x, null);
        while (!q.isEmpty()) {
            TreeNode v = q.remove();
            if (v.left != null) {
                q.add(v.left);
                map.put(v.left, v);
            }
            if (v.right != null) {
                q.add(v.right);
                map.put(v.right, v);
            }
        }
        return walk(map, x, new HashSet<>(), 0, new HashMap<>());
    }

    private int walk(Map<TreeNode, TreeNode> map, TreeNode x, Set<TreeNode> set, int sum, Map<TreeNode, Integer> dp) {
        if (x == null) {
            return sum;
        }
        int withx = 0, withoutx = 0;
        if (map.get(x) == null || !set.contains(map.get(x))) {
            Integer y = dp.get(x);
            if (y == null) {
                set.add(x);
                withx = walk(map, x.left, set, sum, dp) + x.val + walk(map, x.right, set, sum, dp);
                dp.put(x, withx);
                set.remove(x);
            } else {
                withx = y;
            }
        }
        withoutx = walk(map, x.left, set, sum, dp) + walk(map, x.right, set, sum, dp);
        int ans = max(withx, withoutx);
        return ans;
    }
}
