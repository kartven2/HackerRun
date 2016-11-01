/*
 * LeetCode: https://leetcode.com/problems/find-median-from-data-stream/
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindMedianDataStream {

    class Heap {

        private int[] heap;
        private boolean minHeap;
        private int capacity;
        private int n;

        Heap(int m, boolean mH) {
            capacity = m + 1;
            heap = new int[capacity];
            minHeap = mH;
        }

        private boolean isEmpty() {
            return n == 0;
        }

        private int size() {
            return n;
        }

        private void resize(int c) {
            Heap temp = new Heap(c, this.minHeap);
            for (int i = 0; i < this.n; i++) {
                temp.heap[i] = this.heap[i];
            }
            this.heap = temp.heap;
            this.minHeap = temp.minHeap;
            this.capacity = temp.capacity;
            this.n = temp.n;
        }

        private void insert(int x) {
            if (n == capacity) {
                resize(2 * n);
            }
            heap[++n] = x;
            swim(n);
        }

        private int removeTop() {
            exchange(1, n--);
            sink(1);
            return heap[n + 1];
        }

        private int peek() {
            return heap[1];
        }

        private boolean less(int i, int j) {
            return heap[i] < heap[j];
        }

        private void exchange(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void swim(int k) {
            while (k > 1) {
                if ((minHeap && less(k, k / 2)) || (!minHeap && less(k / 2, k))) {
                    exchange(k, k / 2);
                    k = k >> 1;
                } else {
                    break;
                }
            }
        }

        private void sink(int k) {
            while (k << 1 <= n) {
                int j = k << 1;
                if (j < n) {
                    if ((minHeap && less(j + 1, j)) || (!minHeap && less(j, j + 1))) {
                        j++;
                    }
                }
                if ((minHeap && less(k, j)) || (!minHeap && less(j, k))) {
                    break;
                }
                exchange(k, j);
                k = j;
            }
        }
    }

    private Heap minHeap = new Heap((int) 1e5, true);
    private Heap maxHeap = new Heap((int) 1e5, false);

    public void addNum(int num) {
        maxHeap.insert(num);
        minHeap.insert(maxHeap.removeTop());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.insert(minHeap.removeTop());
        }
    }

    public double findMedian() {
        int a = maxHeap.size(), b = minHeap.size();
        if (a == b) {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }
}
