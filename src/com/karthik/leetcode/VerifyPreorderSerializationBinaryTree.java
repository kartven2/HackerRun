/*-
 * LeetCode problem :
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/#/description
 *
 * One way to serialize a binary tree is to use pre-order traversal.
 * When we encounter a non-null node, we record the node's value.
 * If it is a null node, we record using a sentinel value such as #.
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 * where # represents a null node.
 * Given a string of comma separated values, verify whether it is a correct preorder
 * traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class VerifyPreorderSerializationBinaryTree {

    public static void main(String... args) {
        VerifyPreorderSerializationBinaryTree vp = new VerifyPreorderSerializationBinaryTree();
        vp.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }

    public boolean isValidSerialization(String a) {
        if (a == null || a.trim().length() == 0) {
            return false;
        }
        String[] s = a.split(",");
        if (s.length == 1) {
            return s[0].equals("#");
        } else if (s[0].equals("#")) {
            return false;
        }
        Stack<String> stk = new Stack<>();
        stk.push("-1");
        int n = s.length, i = 0;
        while (i < n) {
            String c = s[i++];
            if (stk.isEmpty()) {
                return false;
            } else if (c.equals("#")) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.isEmpty();
    }
}
