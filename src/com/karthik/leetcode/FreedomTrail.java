/*
 * LeetCode: https://leetcode.com/problems/freedom-trail/#/description
 * In the video game Fallout 4, the quest "Road to Freedom" requires
 * players to reach a metal dial called the "Freedom Trail Ring",
 * and use the dial to spell a specific keyword in order to open the door.
 * Given a string ring, which represents the code engraved on the outer ring
 * and another string key, which represents the keyword needs to be spelled.
 * You need to find the minimum number of steps in order to spell all the
 * characters in the keyword.
 * Initially, the first character of the ring is aligned at 12:00 direction.
 * You need to spell all the characters in the string key one by one by rotating
 * the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00
 * direction and then by pressing the center button. 
 * At the stage of rotating the ring to spell the key character key[i]:
 * You can rotate the ring clockwise or anticlockwise one place,
 * which counts as 1 step.
 * The final purpose of the rotation is to align one of the string ring's
 * characters at the 12:00 direction, where this character must equal to the character key[i].
 * If the character key[i] has been aligned at the 12:00 direction, you need to press
 * the center button to spell, which also counts as 1 step.
 * After the pressing, you could begin to spell the next character in the key (next stage),
 * otherwise, you've finished all the spelling.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FreedomTrail {

    public int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
            return 0;
        }
        int n = ring.length(), m = key.length();
        Integer[][] dp = new Integer[m + 1][n];
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < n; j++) {
                if (key.charAt(i - 1) == ring.charAt(j)) {
                    for (int k = 0; k < n; k++) {
                        if (dp[i - 1][k] != null) {
                            int x = Math.min(Math.abs(k - j), n - Math.abs(k - j));
                            if (dp[i][j] == null || dp[i][j] > (x + dp[i - 1][k])) {
                                dp[i][j] = x + dp[i - 1][k];
                            }
                        }
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dp[m][i] != null && dp[m][i] < ans) {
                ans = dp[m][i];
            }
        }
        return ans + m;
    }
}
