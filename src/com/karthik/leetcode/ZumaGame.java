/*
 * Leetcode: https://leetcode.com/problems/zuma-game/
 *
 * Think about Zuma Game. You have a row of balls on the table,
 * colored red(R), yellow(Y), blue(B), green(G), and white(W).
 * You also have several balls in your hand.
 * Each time, you may choose a ball in your hand,
 * and insert it into the row (including the leftmost place and rightmost place).
 * Then, if there is a group of 3 or more balls in the same color touching,
 * remove these balls. Keep doing this until no more balls can be removed.
 * Find the minimal balls you have to insert to remove all the balls on the table.
 * If you cannot remove all the balls, output -1.
 *
 * You may assume that the initial row of balls on the table won’t have any 3
 * or more consecutive balls with the same color.
 * The number of balls on the table won't exceed 20,
 * and the string represents these balls is called "board" in the input.
 * The number of balls in your hand won't exceed 5,
 * and the string represents these balls is called "hand" in the input.
 * Both input strings will be non-empty
 * and only contain characters 'R','Y','B','G','W'.
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ZumaGame {

    class Game {

        private String b;
        private String g;
        private int dist;
        //private int[] bcount;

        /*-
        Game(String b, String g, int dist, int[] bcount) {
            this.b = b;
            this.g = g;
            this.dist = dist;
            this.bcount = bcount;
        }
*/
        Game(String b, String g, int dist) {
            this.b = b;
            this.g = g;
            this.dist = dist;
        }
        
        
        @Override
        public int hashCode() {
            int h = 7;
            h = h * 79 + b == null ? 0 : b.hashCode();
            h = h * 79 + g == null ? 0 : g.hashCode();
            return h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Game other = (Game) o;
            if (this.b == null && other.b != null || this.g == null && other.g != null
                    || this.b != null && other.b == null || this.g != null && other.g == null) {
                return false;
            }
            if (this.b == null && other.b == null) {
                return (this.g).equals(other.g);
            }
            if (this.g == null && other.g == null) {
                return (this.b).equals(other.b);
            }
            return (this.b).equals(other.b) && (this.g).equals(other.g);
        }
    }
/*-
    private boolean isAllSameChars(int[] copyBcount) {
        int count = 0;
        for (int i = 0; i < copyBcount.length; i++) {
            if (copyBcount[i] > 0) {
                count++;
            }
        }
        return count == 1;
    }
*/
    
    private boolean isAllSame(String x) {
        char c= x.charAt(0);
        for(int i=1; i<x.length(); i++) {
            if(x.charAt(i) != c) {
                return false;
            }
        }
        return true;
    }
//    private int addNextStep(Queue<Game> q, Set<Game> set, String x, String y, int m, int dist, int[] bcount) {
       private int addNextStep(Queue<Game> q, Set<Game> set, String x, String y, int m, int dist) {
        String p = 'A' + x + 'A';
        char c = y.charAt(m);
        String nexty = y.substring(0, m) + y.substring(m + 1);
        StringBuilder sb = null;
        Game g = null;
        //int[] copyBcount = null;
        for (int i = 1; i < p.length(); i++) {
            int l = i - 1, r = i;
            sb = new StringBuilder();
            /*-copyBcount = new int[bcount.length];
            for (int j = 0; j < copyBcount.length; j++) {
                copyBcount[j] = bcount[j];
            }*/
            if (c == p.charAt(l) && c == p.charAt(r)) {
                while (l > 0 && p.charAt(l) == c) {
                    l--;
                }
                while (r < p.length() - 1 && p.charAt(r) == c) {
                    r++;
                }
                sb.append(p.substring(1, l + 1));
                //copyBcount[map.get(c)] = copyBcount[map.get(c)] - (i - 1 - l) - (r - i);
            } else {
                sb.append(p.substring(1, l + 1));
                sb.append(c);
                //copyBcount[map.get(c)]++;
            }
            sb.append(p.substring(r, p.length() - 1));
            String nextx = sb.toString();
            if (nextx.length() == 0 || (nextx.length() > 2 && isAllSame(nextx))) {
                return dist + 1;
            }
            if (nexty.length() == 0) {
                continue;
            }
           // g = new Game(sb.toString(), nexty, dist + 1, copyBcount);
            g = new Game(sb.toString(), nexty, dist + 1);
            if (!set.contains(g)) {
                set.add(g);
                q.add(g);
            }
        }
        return -1;
    }

    private boolean impossible(int[] bcount, String hand) {
        int[] hcount = new int[colors.length];
        for (int i = 0; i < hand.length(); i++) {
            hcount[map.get(hand.charAt(i))]++;
        }
        for (int i = 0; i < colors.length; i++) {
            if (bcount[map.get(colors[i])] > 0 && hcount[map.get(colors[i])] + bcount[map.get(colors[i])] < 3) {
                return true;
            }
        }
        return false;
    }

    private static final char[] colors = {'R', 'Y', 'B', 'G', 'W'};
    private static final Map<Character, Integer> map = new HashMap<>();

    public int findMinStep(String board, String hand) {
        if (board == null || board.trim().isEmpty()) {
            return 0;
        }
        for (int i = 0; i < colors.length; i++) {
            map.put(colors[i], i);
        }
        int[] bcount = new int[colors.length];
        for (int i = 0; i < board.length(); i++) {
            bcount[map.get(board.charAt(i))]++;
        }
        if (hand == null || hand.trim().isEmpty() || impossible(bcount, hand)) {
            return -1;
        }
        Queue<Game> q = new LinkedList<>();
        Game game = new Game(board, hand, 0);
        q.add(game);
        Set<Game> set = new HashSet<>();
        set.add(game);
        while (!q.isEmpty()) {
            Game v = q.remove();
            for (int i = 0; i < v.g.length(); i++) {
                //int res = addNextStep(q, set, v.b, v.g, i, v.dist, v.bcount);
                int res = addNextStep(q, set, v.b, v.g, i, v.dist);
                if (res > -1) {
                    return res;
                }
            }
        }
        return -1;
    }

    public static void main(String... args) {
        System.out.println(new ZumaGame().findMinStep("BGGRRYY", "BBYRG"));
    }
}
