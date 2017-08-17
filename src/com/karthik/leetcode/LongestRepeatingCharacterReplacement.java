/*
 * LeetCode problem : https://leetcode.com/problems/longest-repeating-character-replacement/description/
 * Given a string that consists of only uppercase English letters,
 * you can replace any letter in the string with another letter at most k times.
 * Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
 * Note:
 * Both the string's length and k will not exceed 10^4.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        int l = 0, maxCount = 0, maxLength = 0, n = s.length();
        int[] count = new int[26];
        for (int r = 0; r < n; r++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(r) - 'A']);
            while (r - l + 1 - maxCount > k) {
                count[s.charAt(l) - 'A']--;
                l++;
            }
            maxLength = Math.max(maxLength, r - l + 1);
        }
        return maxLength;
    }
}
