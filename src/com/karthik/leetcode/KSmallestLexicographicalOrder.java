/*
 * LeetCode Problem: https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/#/description
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
 * Note: 1 ≤ k ≤ n ≤ 109.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class KSmallestLexicographicalOrder {

    public static void main(String... args) {
        KSmallestLexicographicalOrder ksl = new KSmallestLexicographicalOrder();
        System.out.println(ksl.findKthNumber2(1000, 12));
    }

    public int findKthNumber2(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = findSteps(n, curr, curr + 1);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                k--;
                curr *= 10;
            }
        }
        return curr;
    }

    private int findSteps(int n, long n1, long n2) {
        int steps = 0;
        while (n1 <= n) {
            if (n2 <= n) {
                steps += (n2 - n1);
            } else {
                steps += (n + 1 - n1);
            }
            n1 *= 10;
            n2 *= 10;
        }
        return steps;
    }

    class Node {

        private int val;
        private char c;
        private Node[] sub = new Node[10];
    }

    class Result {

        private int val;
        private int k;
    }

    public int findKthNumber(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        Node root = null;
        for (int i = 1; i <= n; i++) {
            String str = i + "";
            root = insert(root, str, 0, str.length());
        }
        Result res = new Result();
        res.k = k;
        for (int i = 1; i < 10 && res.k > 0 && root.sub[i] != null; i++) {
            res.k--;
            collect(res, root.sub[i]);
        }
        return res.val;
    }

    private void collect(Result res, Node x) {
        if (res.k == 0) {
            res.val = x.val;
            return;
        }
        for (int i = 0; i < 10 && res.k > 0 && x.sub[i] != null; i++) {
            res.k--;
            collect(res, x.sub[i]);
        }
    }

    private Node insert(Node x, String str, int d, int n) {
        if (x == null) {
            x = new Node();
        }
        if (d == n) {
            x.val = Integer.parseInt(str);
            return x;
        }
        char c = str.charAt(d);
        x.sub[c - '0'] = insert(x.sub[c - '0'], str, d + 1, n);
        return x;
    }

}
