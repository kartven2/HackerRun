/*
 * Leetcode: https://leetcode.com/problems/can-i-win/
 * In the "100 game," two players take turns adding, to a running total,
 * any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.
 * What if we change the game so that players cannot re-use integers?
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players play optimally.
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.
 */
package com.karthik.leetcode;

import java.util.BitSet;
import java.util.HashMap;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CanIWin {

    public boolean canIWin(int n, int t) {
        int n1 = n + 1;
        if (((n * n1) >> 1) < t || n1 == t) {
            return false;
        }
        if (t <= n) {
            return true;
        }
        BitSet bs = new BitSet(n);
        HashMap<BitSet, Boolean> mem = new HashMap<>();
        return canIWin(bs, mem, n, t);
    }

    private boolean canIWin(BitSet bs, HashMap<BitSet, Boolean> mem, int n, int t) {
        if (mem.containsKey(bs)) {
            return mem.get(bs);
        }
        for (int i = n; i >= 1; i--) {
            if (!bs.get(i - 1)) {
                bs.set(i - 1);
                if (i >= t || !canIWin(bs, mem, n, t - i)) {
                    bs.clear(i - 1);
                    mem.put(bs, Boolean.TRUE);
                    return true;
                }
                bs.clear(i - 1);
            }
        }
        mem.put(bs, Boolean.FALSE);
        return false;
    }
}
