/*-
 * LeetCode Problem: https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode x) {
        List<List<Integer>> result = new ArrayList<>();
        if (x == null) {
            return result;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        q.add(x);
        col.add(0);
        int min = 0, max = 0;
        while (!q.isEmpty()) {
            TreeNode v = q.remove();
            int c = col.remove();
            List<Integer> list = map.get(c);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(v.val);
            map.put(c, list);
            if (v.left != null) {
                q.add(v.left);
                min = Math.min(min, c - 1);
                col.add(c - 1);
            }
            if (v.right != null) {
                q.add(v.right);
                max = Math.max(max, c + 1);
                col.add(c + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            List<Integer> list = map.get(i);
            if (list != null) {
                result.add(list);
            }
        }
        return result;
    }
}
