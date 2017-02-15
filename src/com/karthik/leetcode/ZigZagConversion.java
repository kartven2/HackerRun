/*
 * LeetCode problem :
 * https://leetcode.com/problems/zigzag-conversion/
 * The string "PAYPALISHIRING" is written in a zigzag pattern
 * on a given number of rows like this: (you may want to display this pattern
 * in a fixed font for better legibility)
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make
 * this conversion given a number of rows:
 *
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ZigZagConversion {

    public String convert(String s, int nr) {
        if (s == null || s.trim().length() == 0 || nr < 2) {
            return s;
        }
        StringBuilder[] sb = new StringBuilder[nr];
        int n = s.length(), i = 0, j = 0;
        while (i < n) {
            while (i < n && j < nr) {
                if (sb[j] == null) {
                    sb[j] = new StringBuilder();
                }
                sb[j].append(s.charAt(i));
                i++;
                j++;
            }
            if (j >= nr) {
                j = nr - 2;
            }
            while (i < n && j >= 0) {
                if (sb[j] == null) {
                    sb[j] = new StringBuilder();
                }
                sb[j].append(s.charAt(i));
                i++;
                j--;
            }
            if (j < 0) {
                j = 1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int k = 0; k < nr; k++) {
            if (sb[k] != null) {
                result.append(sb[k]);
            }
        }
        return result.toString();
    }

    public static void main(String... args) {
        ZigZagConversion zc = new ZigZagConversion();
        System.out.println(zc.convert("PAYPALISHIRING", 3));
    }

}
