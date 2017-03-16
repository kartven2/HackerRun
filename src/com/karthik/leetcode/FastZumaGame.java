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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastZumaGame {

    private static final int MAX = Integer.MAX_VALUE;

    public int findMinStep(String board, String hand) {
        List<Character> brd = new LinkedList<>();
        for (int i = 0; i < board.length(); i++) {
            brd.add(board.charAt(i));
        }
        Map<Character, Integer> hnd = new HashMap<>();
        char[] allChars = {'R', 'Y', 'B', 'G', 'W'};
        for (char c : allChars) {
            hnd.put(c, 0);
        }
        for (int i = 0; i < hand.length(); i++) {
            char c = hand.charAt(i);
            hnd.put(c, hnd.get(c) + 1);
        }
        return findMin(brd, hnd, false, 0);
    }

    private int findMin(List<Character> board, Map<Character, Integer> hand, boolean compress, int steps) {
        if (compress) {
            compress(board);
        }
        if (board.isEmpty()) {
            return steps;
        }
        if (empty(hand)) {
            return -1;
        }
        int n = board.size(), count = 0, min = MAX;
        for (int i = 0; i < n; i++) {
            char c = board.get(i);
            count++;
            if (i == n - 1 || c != board.get(i + 1)) {
                int need = 3 - count;
                int have = hand.get(c);
                if (have >= need) {
                    hand.put(c, have - need);
                    List<Character> nextLevel = new LinkedList<>(board);
                    for (int j = 0; j < count; j++) {
                        nextLevel.remove(i - j);
                    }
                    Integer result = findMin(nextLevel, hand, true, steps + need);
                    if (result != -1) {
                        min = Math.min(min, result);
                    }
                    hand.put(c, have);
                }
                count = 0;
            }
        }
        return min == MAX ? -1 : min;
    }

    private boolean empty(Map<Character, Integer> hand) {
        for (Character c : hand.keySet()) {
            if (hand.get(c) > 0) {
                return false;
            }
        }
        return true;
    }

    private void compress(List<Character> board) {
        int count = 0, n = board.size();
        for (int i = 0; i < board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size() - 1 || c != board.get(i + 1)) {
                if (count >= 3) {
                    for (int j = 0; j < count; j++) {
                        board.remove(i - j);
                    }
                }
                count = 0;
            }
        }
        int compSz = board.size();
        if (compSz == n || compSz == 0) {
            return;
        }
        compress(board);
    }

    public static void main(String... args) {
        System.out.println(new FastZumaGame().findMinStep("GGRRGRRG", "RRYBB"));
    }
}
