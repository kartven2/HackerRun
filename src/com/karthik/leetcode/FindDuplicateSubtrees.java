/*-
 * LeetCode Problem: https://leetcode.com/problems/find-duplicate-subtrees/description/
 * Given a binary tree, return all duplicate subtrees.
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with same node values.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindDuplicateSubtrees {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private String collect(TreeNode x, Map<String, Integer> map, List<TreeNode> result) {
        if (x == null) {
            return "#";
        }
        String key = x.val + "," + collect(x.left, map, result) + "," + collect(x.right, map, result);
        Integer cnt = map.get(key);
        if (cnt != null && cnt == 1) {
            result.add(x);
        }
        int value = cnt == null ? 1 : cnt + 1;
        map.put(key, value);
        return key;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode x) {
        List<TreeNode> result = new LinkedList<>();
        if (x == null) {
            return result;
        }
        collect(x, new HashMap<String, Integer>(), result);
        return result;
    }
}