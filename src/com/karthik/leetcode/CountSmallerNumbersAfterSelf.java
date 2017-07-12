/*-
* LeetCode Problem: https://leetcode.com/problems/count-of-smaller-numbers-after-self/#/description
*
* You are given an integer array nums and you have to return a new counts array.
* The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
*/
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CountSmallerNumbersAfterSelf {

    class Node {

        private int key, lcount, dup;
        private Node left, right;
    }

    private Node insert(Node x, int[] count, int cp, int key) {
        if (x == null) {
            x = new Node();
            x.key = key;
        }
        if (x.key > key) {
            x.lcount++;
            x.left = insert(x.left, count, cp, key);
        } else if (x.key < key) {
            count[cp] += x.dup + x.lcount;
            x.right = insert(x.right, count, cp, key);
        } else {
            count[cp] += x.lcount;
            x.dup++;
        }
        return x;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        int n = nums.length;
        int[] ans = new int[n];
        Node root = null;
        for (int i = n - 1; i >= 0; i--) {
            root = insert(root, ans, i, nums[i]);
        }
        for (int x : ans) {
            list.add(x);
        }
        return list;
    }
}