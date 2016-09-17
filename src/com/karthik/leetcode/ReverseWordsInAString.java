/*
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * Given an input string, reverse the string word by word.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReverseWordsInAString {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int n = s.length(), swptr = 0, wordptr = 0;
        char[] sw = new char[n];
        String[] word = new String[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                if (swptr > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < swptr; j++) {
                        sb.append(sw[j]);
                    }
                    word[wordptr++] = sb.toString();
                    swptr = 0;
                }
            } else {
                sw[swptr++] = s.charAt(i);
            }
        }
        if (swptr > 0) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < swptr; j++) {
                sb.append(sw[j]);
            }
            word[wordptr++] = sb.toString();
        }
        StringBuilder result = new StringBuilder();
        --wordptr;
        for (; wordptr > -1;) {
            result.append(word[wordptr--]);
            if (wordptr > -1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    public static void main(String... args) {
        ReverseWordsInAString rw = new ReverseWordsInAString();
        System.out.println(rw.reverseWords("the sky is blue"));
    }
}
