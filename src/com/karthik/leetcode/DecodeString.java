/*-
 * LeetCode Problem : https://leetcode.com/problems/decode-string/#/description
 *
 * Given an encoded string, return it's decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square
 * brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
 * For example, there won't be input like 3a or 2[4].
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
package com.karthik.leetcode;

import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DecodeString {

    public static void main(String... args) {
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString("3[a]2[bc]"));
    }

    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        Stack<Integer> val = new Stack<>();
        Stack<String> expr = new Stack<>();
        int n = s.length();
        char[] c = s.toCharArray();
        for (int i = 0; i < n;) {
            int num = 0;
            if (c[i] > '0' && c[i] <= '9') {
                while (i < n && (c[i] >= '0' && c[i] <= '9')) {
                    num = num * 10 + (c[i] - '0');
                    i++;
                }
                val.push(num);
            } else if (c[i] == '[') {
                expr.push("[");
                i++;
            } else if (c[i] == ']') {
                StringBuilder sb = new StringBuilder();
                while (!expr.isEmpty() && !expr.peek().equals("[")) {
                    sb.insert(0, expr.pop());
                }
                num = val.pop();
                while (num-- > 0) {
                    ans.append(sb);
                }
                expr.push(ans.toString());
                i++;
            } else {
                StringBuilder str = new StringBuilder();
                while (i < n && ((c[i] >= 'a' && c[i] <= 'z')
                        || (c[i] >= 'A' && c[i] <= 'Z'))) {
                    str.append(c[i]);
                    i++;
                }
                expr.push(str.toString());
            }
        }
        return ans.toString();
    }
}
