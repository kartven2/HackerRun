/*
 * LeetCode: https://leetcode.com/problems/range-sum-query-mutable/
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 */
package com.karthik.leetcode;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RangeSumQuery {

    class Node {

        int from, to;
        long sum;
        Integer propogate;

        Node(int from, int to) {
            this.from = from;
            this.to = to;
        }

        int size() {
            return to - from + 1;
        }
    }

    private int[] a;
    private int size;
    private Node[] heap;

    private boolean contains(int f1, int t1, int f2, int t2) {
        return f1 <= f2 && t2 <= t1;
    }

    private boolean intersects(int f1, int t1, int f2, int t2) {
        return f2 <= f1 && t2 >= f1 || f1 <= f2 && t1 >= f2;
    }

    public RangeSumQuery(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        this.a = Arrays.copyOf(nums, nums.length);
        int n = nums.length;
        int x = (int) Math.ceil((Math.log(n) / Math.log(2.0)) + 1);
        this.size = (int) (2 * Math.pow(2.0, x));
        this.heap = new Node[size];
        buildTree(1, 0, n);
    }

    private void buildTree(int v, int from, int size) {
        heap[v] = new Node(from, from + size - 1);
        if (size == 1) {
            heap[v].sum = a[from];
        } else {
            buildTree(2 * v, from, size / 2);
            buildTree(2 * v + 1, from + size / 2, size - size / 2);
            heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
        }
    }

    private long rsq(int from, int to) {
        return rsq(1, from, to);
    }

    private long rsq(int v, int from, int to) {
        Node n = heap[v];
        if (n.propogate != null && contains(n.from, n.to, from, to)) {
            return (to - from + 1) * n.propogate;
        }
        if (contains(from, to, n.from, n.to)) {
            return heap[v].sum;
        }
        if (intersects(from, to, n.from, n.to)) {
            propogate(v);
            return rsq(2 * v, from, to) + rsq(2 * v + 1, from, to);
        }
        return 0;
    }

    private void propogate(int v) {
        Node n = heap[v];
        if (n.propogate != null) {
            change(heap[2 * v], n.propogate);
            change(heap[2 * v + 1], n.propogate);
            n.propogate = null;
        }
    }

    private void change(Node n, int value) {
        n.propogate = value;
        n.sum = n.size() * value;
        a[n.from] = value;
    }

    private void update(int from, int to, int value) {
        update(1, from, to, value);
    }

    private void update(int v, int from, int to, int value) {
        Node n = heap[v];
        if (contains(from, to, n.from, n.to)) {
            change(n, value);
        }
        if (n.size() == 1) {
            return;
        }
        if (intersects(from, to, n.from, n.to)) {
            update(2 * v, from, to, value);
            update(2 * v + 1, from, to, value);
            heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
        }
    }

    void update(int i, int val) {
        update(i, i, val);
    }

    public int sumRange(int i, int j) {
        return (int) rsq(i, j);
    }
}
