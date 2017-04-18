/*
 * LeetCode Problem: https://leetcode.com/problems/letter-combinations-of-a-phone-number/#/description
 * Given a digit string, return all possible letter combinations that the
 * number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LetterCombinationsPhoneNumber {

    public static void main(String... args) {
        LetterCombinationsPhoneNumber lcp = new LetterCombinationsPhoneNumber();
        lcp.letterCombinations("23");
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        build(result, map, digits, 0, "");
        return result;
    }

    private void build(List<String> result, String[] map, String digits, int i, String str) {
        if (i == digits.length()) {
            result.add(str);
            return;
        }
        String x = map[digits.charAt(i) - '2'];
        for (int j = 0; j < x.length(); j++) {
            build(result, map, digits, i + 1, str + x.charAt(j));
        }
    }

}
