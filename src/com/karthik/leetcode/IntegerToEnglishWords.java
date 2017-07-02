/*
 * Leetcode: https://leetcode.com/problems/integer-to-english-words/
 *
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class IntegerToEnglishWords {

    private String[] lessThan20 = {"Zero ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ",
        "Eleven ", "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    private String[] lessThan100 = {"Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};
    private String[] greaterThan100 = {"Hundred ", "Thousand ", "Million ", "Billion "};

    private String lessThan1000ToWord(int num) {
        StringBuilder sb = new StringBuilder();
        int rem = num % 100, div = num / 100;
        if (div > 0) {
            sb.append(lessThan20[div]);
            sb.append(greaterThan100[0]);
        }
        if (rem == 0) {
            return sb.toString();
        }
        if (rem < 20) {
            sb.append(lessThan20[rem]);
            return sb.toString();
        }
        div = rem / 10;
        rem = rem % 10;
        sb.append(lessThan100[div - 2]);
        if (rem > 0) {
            sb.append(lessThan20[rem]);
        }
        return sb.toString();
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return lessThan20[0].trim();
        }
        String[] split = new String[4];
        int[] stck = new int[3];
        int pt = 2, spt = 3;
        while (num > 0) {
            stck[pt--] = num % 10;
            num /= 10;
            if (pt == -1 || num == 0) {
                split[spt--] = lessThan1000ToWord(Integer.parseInt(stck[0] + "" + stck[1] + "" + stck[2]));
                pt = 2;
                stck[0] = stck[1] = stck[2] = 0;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = ++spt; i < 4; i++) {
            boolean empty = split[i] == null || split[i].trim().length() == 0;
            if (!empty) {
                sb.append(split[i]);
                switch (i) {
                    case 0:
                        sb.append(greaterThan100[3]);
                        break;
                    case 1:
                        sb.append(greaterThan100[2]);
                        break;
                    case 2:
                        sb.append(greaterThan100[1]);
                        break;
                }
            }
        }
        return sb.toString().trim();
    }

    public static void main(String... args) {
        IntegerToEnglishWords iew = new IntegerToEnglishWords();
        System.out.println(iew.numberToWords(1000080007));
    }
}
