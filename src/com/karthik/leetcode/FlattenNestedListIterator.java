/*
 * LeetCode: https://leetcode.com/problems/flatten-nested-list-iterator/#/description
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 * Given the list [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,4,6].
 */
package com.karthik.leetcode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FlattenNestedListIterator {

    interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    class NestedIterator implements Iterator<Integer> {

        private Stack<NestedInteger> stk;

        public NestedIterator(List<NestedInteger> x) {
            stk = new Stack<>();
            if (x == null || x.isEmpty()) {
                return;
            }
            for (int i = x.size() - 1; i >= 0; i--) {
                stk.push(x.get(i));
            }
        }

        @Override
        public Integer next() {
            return stk.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stk.isEmpty()) {
                NestedInteger nestedInteger = stk.peek();
                if (nestedInteger.isInteger()) {
                    return true;
                }
                stk.pop();
                List<NestedInteger> nestedList = nestedInteger.getList();
                for (int i = nestedList.size() - 1; i >= 0; i--) {
                    stk.push(nestedList.get(i));
                }
            }
            return false;
        }
    }
}
