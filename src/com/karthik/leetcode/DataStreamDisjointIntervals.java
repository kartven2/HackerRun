/*
 * LeetCode: https://leetcode.com/problems/data-stream-as-disjoint-intervals/#/description
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize
 * the numbers seen so far as a list of disjoint intervals.
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DataStreamDisjointIntervals {

    static class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    static class SummaryRanges {

        static class Node {

            private int[] v;
            Node left, right;

            Node(int[] v) {
                this.v = v;
            }
        }
        private Node root;
        private List<Interval> result;

        public SummaryRanges() {
            result = new ArrayList<>();
            root = null;
        }

        public List<Interval> getIntervals() {
            result.clear();
            inorder(root);
            return result;
        }

        private void inorder(Node x) {
            if (x == null) {
                return;
            }
            inorder(x.left);
            result.add(new Interval(x.v[0], x.v[1]));
            inorder(x.right);
        }

        public void addNum(int val) {
            root = build(root, val);
        }

        private Node build(Node x, int val) {
            if (x == null) {
                int[] arr = {val, val};
                x = new Node(arr);
            }
            if (val >= x.v[0] && val <= x.v[1]) {
                return x;
            } else if (val + 1 < x.v[0]) {
                x.left = build(x.left, val);
            } else if (val - 1 > x.v[1]) {
                x.right = build(x.right, val);
            } else if (val + 1 == x.v[0]) {
                x.v[0] = val;
                Node y = find(root, val - 1, 1);
                if (y == null) {
                    return x;
                }
                int key = y.v[0];
                delete(key);
                x.v[0] = key;
            } else if (val - 1 == x.v[1]) {
                x.v[1] = val;
                Node y = find(root, val + 1, 0);
                if (y == null) {
                    return x;
                }
                int key = y.v[1];
                delete(y.v[0]);
                x.v[1] = key;
            }
            return x;
        }

        private Node find(Node x, int key, int idx) {
            if (x == null) {
                return null;
            }
            if (key == x.v[idx]) {
                return x;
            } else if (key > x.v[idx]) {
                return find(x.right, key, idx);
            } else {
                return find(x.left, key, idx);
            }
        }

        private Node min(Node x) {
            if (x == null) {
                return null;
            }
            while (x.left != null) {
                x = x.left;
            }
            return x;
        }

        private Node delMin(Node x) {
            if (x == null) {
                return null;
            }
            if (x.left == null) {
                return x.right;
            }
            x.left = delMin(x.left);
            return x;
        }

        private void delete(int key) {
            if (root == null) {
                return;
            }
            root = delete(key, root);
        }

        private Node delete(int key, Node x) {
            if (x == null) {
                return null;
            }
            if (x.v[0] < key) {
                x.right = delete(key, x.right);
            } else if (x.v[0] > key) {
                x.left = delete(key, x.left);
            } else {
                if (x.left == null) {
                    return x.right;
                }
                if (x.right == null) {
                    return x.left;
                }
                Node t = x;
                x = min(t.right);
                x.right = delMin(t.right);
                x.left = t.left;
            }
            return x;
        }
    }
}
