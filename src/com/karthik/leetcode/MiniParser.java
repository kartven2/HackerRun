/*
 * Leetcode: https://leetcode.com/problems/mini-parser/
 *
 * Given a nested list of integers represented as a string,
 * implement a parser to deserialize it.
 * Each element is either an integer, 
 * or a list -- whose elements may also be integers or other lists.
 * Note: You may assume that the string is well-formed:
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class MiniParser {

    class NestedInteger {

        int value;
        List<NestedInteger> list;

        boolean isInteger() {
            return list == null;
        }

        Integer getInteger() {
            return value;
        }

        void setInteger(int value) {
            this.value = value;
        }

        void add(NestedInteger ni) {
            list.add(ni);
        }

        List<NestedInteger> getList() {
            return list;
        }
    }

    public static void main(String... args) {
        MiniParser mp = new MiniParser();
        mp.deserialize("324");
    }

    public NestedInteger deserialize(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        char[] c = s.toCharArray();
        int n = s.length(), i = 0;
        NestedInteger root = new NestedInteger();
        Stack<NestedInteger> stk = new Stack<>();
        NestedInteger x = root;
        Integer num = null;
        boolean addToList = false;
        while (i < n) {
            if (c[i] == '-' || c[i] <= '9' && c[i] >= '0') {
                boolean sign = false;
                if (c[i] == '-') {
                    sign = true;
                    i++;
                }
                num = c[i] - '0';
                while (++i < n && c[i] <= '9' && c[i] >= '0') {
                    num = num * 10 + (c[i] - '0');
                }
                if (sign) {
                    num *= (-1);
                }
                if (addToList) {
                    List<NestedInteger> xlist = x.getList();
                    if (xlist == null) {
                        xlist = new ArrayList<>();
                    }
                    NestedInteger tmp = new NestedInteger();
                    tmp.setInteger(num);
                    xlist.add(tmp);
                    addToList = false;
                } else {
                    x.setInteger(num);
                    addToList = true;
                }
            } else if (c[i] == ',') {
                addToList = true;
                i++;
            } else if (c[i] == '[') {
                stk.push(x);
                if (addToList) {
                    List<NestedInteger> xlist = x.getList();
                    if (xlist == null) {
                        xlist = new ArrayList<>();
                    }
                    NestedInteger tmp = new NestedInteger();
                    xlist.add(tmp);
                    x = tmp;
                }
                addToList = true;
                i++;
            } else if (c[i] == ']' && !stk.isEmpty()) {
                x = stk.pop();
                i++;
            }
        }
        return root;
    }
}
