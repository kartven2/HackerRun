/*
 * Leetcode: https://leetcode.com/problems/simplify-path/
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) {
            return path;
        }
        Stack<String> st = new Stack<>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < path.length()) {
            while (i < path.length() && path.charAt(i) != '/') {
                sb.append(path.charAt(i++));
            }
            if (sb.toString().equals(".")) {
            } else if (sb.toString().equals("..")) {
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else if (sb.length() > 0) {
                st.push(sb.toString());
            }
            sb = new StringBuilder();
            i++;
        }
        while (!st.isEmpty()) {
            sb.insert(0, st.pop());
            sb.insert(0, "/");
        }
        if (sb.length() == 0) {
            sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String... args) {
        SimplifyPath sp = new SimplifyPath();
        System.out.println(sp.simplifyPath("/.."));
    }
}
