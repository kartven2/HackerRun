/*
 * Leetcode: https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class NumMatrix {

    private SegmentTree[] st;

    class Node {

        private int sum;
        private int from;
        private int to;
        private Integer pendingVal = null;

        private int size() {
            return to - from + 1;
        }
    }

    class SegmentTree {

        private Node[] heap;
        private int[] array;
        private int size;

        SegmentTree(int[] array) {
            double n = (double) array.length;
            //this.array = Arrays.copyOf(array, array.length);
            this.array = array;
            this.size = (int) (2 * Math.pow(2.0, Math.floor((Math.log(n) / Math.log(2.0)) + 1)));
            this.heap = new Node[size];
            build(1, 0, array.length);
        }

        private int size() {
            return array.length;
        }

        private void build(int v, int from, int sz) {
            heap[v] = new Node();
            heap[v].from = from;
            heap[v].to = from + sz - 1;
            if (sz == 1) {
                heap[v].sum = array[from];
            } else {
                build(2 * v, from, sz / 2);
                build(2 * v + 1, from + sz / 2, sz - sz / 2);
                heap[v].sum = heap[2 * v].sum + heap[2 * v + 1].sum;
            }
        }

        private int rsq(int from, int to) {
            return rsq(1, from, to);
        }

        private int rsq(int v, int from, int to) {
            Node n = heap[v];

            //if (n.pendingVal != null && contains(n.from, n.to, from, to)) {
            //return (to - from + 1) * n.pendingVal;
            //}
            if (contains(from, to, n.from, n.to)) {
                return n.sum;
            }

            if (intersects(from, to, n.from, n.to)) {
                //propogate(v);
                int lsum = rsq(2 * v, from, to);
                int rsum = rsq(2 * v + 1, from, to);
                return lsum + rsum;
            }
            return 0;
        }

        private void propogate(int v) {
            Node n = heap[v];
            if (n.pendingVal != null) {
                change(heap[2 * v], n.pendingVal);
                change(heap[2 * v + 1], n.pendingVal);
                n.pendingVal = null;
            }
        }

        private void change(Node n, int value) {
            n.pendingVal = value;
            n.sum = n.size() * value;
            array[n.from] = value;
        }

        private boolean contains(int from1, int to1, int from2, int to2) {
            return from1 <= from2 && to2 <= to1;
        }

        private boolean intersects(int from1, int to1, int from2, int to2) {
            return from1 <= from2 && to1 >= from2 || from1 >= from2 && from1 <= to2;
        }
    }

    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        /*-
         int rl = matrix.length;
         if (rl > 0) {
         int cl = matrix[0].length;
         st = new SegmentTree[rl];
         for (int i = 0; i < rl; i++) {
         st[i] = new SegmentTree(matrix[i]);
         }
         }
         */
        int rl = matrix.length;
        if (rl > 0) {
            int cl = matrix[0].length;
            sum = new int[rl + 1][cl + 1];
            for (int i = 1; i <= rl; i++) {
                for (int j = 1; j <= cl; j++) {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        /*-
         int result = 0;
         for (int i = row1; i <= row2; i++) {
         result += st[i].rsq(col1, col2);
         }
         return result;
         */
        return sum[row1][col1]
                + sum[row2 + 1][col2 + 1]
                - sum[row1][col2 + 1]
                - sum[row2 + 1][col1];
    }

    public static void main(String... args) {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix nm = new NumMatrix(matrix);
        System.out.println(nm.sumRegion(2, 1, 4, 3));
        System.out.println(nm.sumRegion(1, 1, 2, 2));
        System.out.println(nm.sumRegion(1, 2, 2, 4));
    }
}
