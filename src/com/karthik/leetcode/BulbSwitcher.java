/*
 * Leetcode: https://leetcode.com/problems/bulb-switcher/#/description
 *
 * There are n bulbs that are initially off. You first turn on all the bulbs.
 * Then, you turn off every second bulb. On the third round, you toggle every
 * third bulb (turning on if it's off or turning off if it's on). For the ith round,
 * you toggle every i bulb. For the nth round, you only toggle the last bulb.
 * Find how many bulbs are on after n rounds.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BulbSwitcher {

    public int bulbSwitch(int n) {
        if (n <= 0) {
            return 0;
        }
        return (int) Math.sqrt(n);
    }
}
