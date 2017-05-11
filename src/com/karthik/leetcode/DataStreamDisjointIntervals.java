/*
 * LeetCode: https://leetcode.com/problems/data-stream-as-disjoint-intervals/#/description
 * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize
 * the numbers seen so far as a list of disjoint intervals.
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class DataStreamDisjointIntervals {

    class Interval {

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

    class SummaryRanges {

        class Node {

            private Interval intvl;
            private Node left, right;

            Node(Interval intvl) {
                this.intvl = intvl;
            }
        }

        private Node root;

        public SummaryRanges() {

        }

        public void addNum(int val) {
            root = buildTree(root, val);
        }

        private Node buildTree(Node x, int v) {
            if (x == null) {
                x = new Node(new Interval(v, v));
            }
            if (v >= x.intvl.start && v <= x.intvl.end) {
                return x;
            } else if (v + 1 < x.intvl.start) {
                x.left = buildTree(x.left, v);
            } else if (v - 1 > x.intvl.end) {
                x.right = buildTree(x.right, v);
            } else if (v + 1 == x.intvl.start) {
                x.intvl.start = v;
                x.left = merge(x, x.left);
            } else if (v - 1 == x.intvl.end) {
                x.intvl.end = v;
                x.right = merge(x, x.right);
            }
            return x;
        }

        private void removeMin() {
            if (root == null) {
                return;
            }
            root = removeMin(root);
        }

        private Node removeMin(Node x) {
            if (x.left == null) {
                return x.right;
            }
            x.left = removeMin(x.left);
            return x;
        }

        private void delMin() {
            if (root == null) {
                return;
            }
            if (root.left == null) {
                root = root.right;
                return;
            }
            delMin(root);
        }

        private void delMin(Node x) {
            Node p = null;
            while (x.left != null) {
                p = x;
                x = x.left;
            }
            if (x.right == null) {
                p.left = null;
                x = null;
                return;
            }
            p.left = x.right;
            x = null;
        }

        public List<Interval> getIntervals() {

        }
    }
}
