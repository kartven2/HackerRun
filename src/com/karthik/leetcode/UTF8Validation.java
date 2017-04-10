/*
 * LeetCode Problem: https://leetcode.com/problems/utf-8-validation/#/description
 * A character in UTF8 can be from 1 to 4 bytes long,
 * subjected to the following rules:
 * For 1-byte character, the first bit is a 0, followed by its unicode code.
 * For n-bytes character, the first n-bits are all one's, the n+1 bit is 0,
 * followed by n-1 bytes with most significant 2 bits being 10.
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class UTF8Validation {

    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        int n = data.length, cnt = 0;
        for (int i = 0; i < n; i++) {
            data[i] &= 255;
        }
        for (int i = 0; i < n; i++) {
            if (cnt > 0) {
                int mask = 3 << 6;
                if ((data[i] & mask) != (1 << 7)) {
                    return false;
                }
                cnt--;
            } else if ((data[i] & (1 << 7)) > 0) {
                for (int j = 0; j < 5 && (data[i] & (1 << (7 - j))) > 0; j++) {
                    cnt++;
                }
                if (cnt == 5 || cnt == 1) {
                    return false;
                }
                cnt--;
            }
        }
        return cnt == 0;
    }

    public static void main(String... args) {
        UTF8Validation uv = new UTF8Validation();
        int[] data = {197, 130, 1};
        uv.validUtf8(data);
    }
}
