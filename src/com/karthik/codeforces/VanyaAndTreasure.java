/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/677/D
 *
 * Find the minimum possible total distance Vanya
 * has to walk in order to get the treasure from the chest of type p.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class VanyaAndTreasure {

    private static final int MAX = Integer.MAX_VALUE;

    class Node implements Comparable {

        private int x;
        private int y;
        private int d;

        Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Object o) {
            Node that = (Node) o;
            return this.d - that.d;
        }
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        private Integer nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private Map<Integer, List<Node>> dp;
    private int n;
    private int m;
    private int p;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/vanyantreasure3"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        m = sc.nextInt();
        p = sc.nextInt();
        dp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int type = sc.nextInt();
                int d = type == 1 ? (i + j) : MAX;
                List<Node> typeList = dp.get(type);
                if (typeList == null) {
                    typeList = new ArrayList<>();
                }
                typeList.add(new Node(i, j, d));
                dp.put(type, typeList);
            }
        }

        for (int i = 2; i <= p; i++) {
            List<Node> typeList = dp.get(i);
            List<Node> prevTypeList = dp.get(i - 1);
            Collections.sort(prevTypeList);
            int limit = Math.min(prevTypeList.size(), m + n);
            for (int j = 0; j < typeList.size(); j++) {
                for (int k = 0; k < limit; k++) {
                    typeList.get(j).d = Math.min(typeList.get(j).d, prevTypeList.get(k).d + dist(prevTypeList.get(k), typeList.get(j)));
                }
            }
        }

        int result = MAX;
        List<Node> resultrow = dp.get(p);
        for (int i = 0; i < resultrow.size(); i++) {
            result = Math.min(result, resultrow.get(i).d);
        }
        System.out.println(result);
    }

    private int dist(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public static void main(String... args) {
        VanyaAndTreasure t = new VanyaAndTreasure();
        t.compute();
    }
}
