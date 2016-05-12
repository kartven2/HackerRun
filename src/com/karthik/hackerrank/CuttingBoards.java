/*-
 * HackerRun problem :
 * https://www.hackerrank.com/challenges/board-cutting
 *
 * Find minimum cost of cutting board into 1X1.
 * 
 */
package com.karthik.hackerrank;

import com.karthik.hackerrank.CuttingBoards.MaxPq.Node;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CuttingBoards {

    private MaxPq vcut;

    private MaxPq hcut;

    private double mincutCost;

    private int m;

    private int n;

    private int noOfTest;

    private long vcomp;

    private long hcomp;

    private Scanner sc;

    private List<Double> output;

    private void input() {
        output = new ArrayList<>();
        //sc = new Scanner(System.in);
        try {
            sc = new Scanner(new File("./resources/cuttingboardinput"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file not found" + ex.getMessage());
        }
        noOfTest = sc.nextInt();
        for (int i = 0; i < noOfTest; i++) {
            m = sc.nextInt();
            n = sc.nextInt();
            vcomp = 0;
            hcomp = 0;
            mincutCost = 0;
            hcut = new MaxPq(m);
            vcut = new MaxPq(n);
            for (int j = 1; j < m; j++) {
                hcut.insert(j, sc.nextInt(), false);
            }
            for (int k = 1; k < n; k++) {
                vcut.insert(k, sc.nextInt(), true);
            }
            compute();
            output.add(mincutCost);
        }
    }

    private void compute() {
        while (!vcut.isEmpty() || !hcut.isEmpty()) {
            MaxPq.Node nextCut = getNextCutNode();
            long currentCutCost = nextCut.getCutCost();
            long affectedSegments;
            if (nextCut.isVcut()) {
                affectedSegments = hcomp + 1;
                vcomp++;
            } else {
                affectedSegments = vcomp + 1;
                hcomp++;
            }
            mincutCost = (mincutCost + (currentCutCost * affectedSegments)) % (Math.pow(10, 9) + 7);
        }
    }

    private MaxPq.Node getNextCutNode() {
        if (vcut.peek() > hcut.peek()) {
            return vcut.delMax();
        } else if (vcut.peek() < hcut.peek()) {
            return hcut.delMax();
        } else {
            return m > n ? vcut.delMax() : hcut.delMax();
        }
    }

    private void printResult() {
        for (double x : output) {
            System.out.println((long) x);
        }
    }

    public static void main(String[] args) {
        CuttingBoards c = new CuttingBoards();
        c.input();
        c.printResult();
    }

    static class MaxPq<Node> {

        static class Node {

            private boolean vcut;
            private int cutIdx;
            private long cutCost;

            public Node(int idx, long cost, boolean isvcut) {
                vcut = isvcut;
                cutIdx = idx;
                cutCost = cost;
            }

            public int getCutIdx() {
                return cutIdx;
            }

            public boolean isVcut() {
                return vcut;
            }

            public long getCutCost() {
                return cutCost;
            }
        }

        private MaxPq.Node[] e;
        private int sz;

        public MaxPq(int capacity) {
            e = new MaxPq.Node[capacity];
            sz = 0;
        }

        public void insert(int idx, int cost, boolean vcut) {
            e[++sz] = new Node(idx, cost, vcut);
            swim(sz);
        }

        private boolean greater(Node a, Node b) {
            return a.getCutCost() > b.getCutCost();
        }

        private void swim(int i) {
            while (i > 1 && greater(e[i], e[i / 2])) {
                exchange(i, i / 2);
                i = i / 2;
            }
        }

        private void exchange(int i, int j) {
            Node swp = e[i];
            e[i] = e[j];
            e[j] = swp;
        }

        public long peek() {
            if (sz <= 0) {
                return -1;
            }
            return e[1].getCutCost();
        }

        public boolean isEmpty() {
            return sz == 0;
        }

        public Node delMax() {
            if (sz <= 0) {
                return null;
            }
            Node result = e[1];
            exchange(1, sz);
            e[sz] = null;
            sz--;
            sink(1);
            return result;
        }

        private void sink(int i) {
            while (2 * i <= sz) {
                int j = 2 * i;
                if (j < sz && greater(e[j + 1], e[j])) {
                    j++;
                }
                if (greater(e[i], e[j])) {
                    break;
                }
                exchange(i, j);
                i = j;
            }
        }
    }
}
