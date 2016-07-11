/*-
 * Practice problem: Segment Tree.
 */
package com.karthik.codeforces;

import java.util.Arrays;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class SegmentTree {

    class Node {

        int sum;
        int min;
        int from;
        int to;
        Integer pendingValue = null;

        private int size() {
            return to - from + 1;
        }
    }

    private Node[] heap;
    private int[] array;
    private int size;

    public SegmentTree(int[] array) {
        int n = array.length;
        this.array = Arrays.copyOf(array, n);
        //size = 2 * 2 ^ ((log n base 2) + 1)
        this.size = (int) (2 * Math.pow(2.0, Math.floor((Math.log(n) / Math.log(2.0)) + 1)));
        this.heap = new Node[size];
        build(1, 0, n);
    }

    private void build(int v, int from, int size) {
        heap[v] = new Node();
        heap[v].from = from;
        heap[v].to = from + size - 1;

        if (size == 1) {
            heap[v].sum = array[from];
            heap[v].min = array[from];
        } else {
            build(2 * v, from, size / 2);
            build(2 * v + 1, from + size / 2, size - size / 2);
            heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            heap[v].min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
        }
    }

    public int rangeSum(int from, int to) {
        return rangeSum(1, from, to);
    }

    private int rangeSum(int v, int from, int to) {
        Node n = heap[v];

        if (n.pendingValue != null && contains(n.from, n.to, from, to)) {
            return (to - from + 1) * n.pendingValue;
        }

        if (contains(from, to, n.from, n.to)) {
            return heap[v].sum;
        }

        if (intersects(from, to, n.from, n.to)) {
            propogate(v);
            int leftSum = rangeSum(2 * v, from, to);
            int rightSum = rangeSum(2 * v + 1, from, to);
            return leftSum + rightSum;
        }

        return 0;
    }

    public int rangeMin(int from, int to) {
        return rangeMin(1, from, to);
    }

    private int rangeMin(int v, int from, int to) {
        Node n = heap[v];

        if (n.pendingValue != null && contains(n.from, n.to, from, to)) {
            return n.pendingValue;
        }

        if (contains(from, to, n.from, n.to)) {
            return heap[v].min;
        }

        if (intersects(from, to, n.from, n.to)) {
            propogate(v);
            int leftMin = rangeMin(2 * v, from, to);
            int rightMin = rangeMin(2 * v + 1, from, to);
            return Math.min(leftMin, rightMin);
        }
        return Integer.MAX_VALUE;
    }

    public void update(int from, int to, int value) {
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
            propogate(v);
            update(2 * v, from, to, value);
            update(2 * v + 1, from, to, value);
            n.sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            n.min = Math.min(heap[2 * v].min, heap[2 * v + 1].min);
        }
    }

    private void propogate(int v) {
        Node n = heap[v];
        if (n.pendingValue != null) {
            change(heap[2 * v], n.pendingValue);
            change(heap[2 * v + 1], n.pendingValue);
            n.pendingValue = null;
        }
    }

    private void change(Node n, int value) {
        n.pendingValue = value;
        n.sum = n.size() * value;
        n.min = value;
        array[n.from] = value;
    }

    //range1 contains range2
    private boolean contains(int from1, int to1, int from2, int to2) {
        return from1 <= from2 && to1 >= to2;
    }

    //range1 intersects range2
    private boolean intersects(int from1, int to1, int from2, int to2) {
        return (from1 <= from2 && to1 >= from2) || (from1 >= from2 && from1 <= to2);
    }
}
