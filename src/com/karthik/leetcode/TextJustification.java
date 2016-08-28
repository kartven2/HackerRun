/*
 * Leetcode: https://leetcode.com/problems/text-justification/
 *
 * Given an array of words and a length L, 
 * format the text such that each line has exactly L characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; 
 * that is, pack as many words as you can in each line.
 * Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible.
 * If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class TextJustification {

    public static void main(String... args) {
        TextJustification tj = new TextJustification();
        String[] ip = {"Listen", "to", "many", "speak", "to", "a", "few."};
        List<String> ans = tj.fullJustify(ip, 6);
        for (String x : ans) {
            System.out.println(x);
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || maxWidth < 0) {
            return result;
        }
        if (maxWidth == 0) {
            result.add("");
            return result;
        }
        addText(0, words, result, maxWidth);
        return result;
    }

    private void addText(int idx, String[] words, List<String> result, int limit) {
        if (idx >= words.length) {
            return;
        }

        int count = 0;
        int len = 0;
        int next = -1;
        int i = idx;

        for (; i < words.length && count < limit; count++, i++) {
            count += words[i].length();
            if (count > limit) {
                next = i;
                break;
            }
            len += words[i].length();
        }

        if (next == -1) {
            next = i;
        }

        addList(words, idx, next, result, len, limit);
        addText(next, words, result, limit);
    }

    private void addList(String[] words, int start,
            int next, List<String> result, int len, int limit) {
        int slots = next - start - 1;
        StringBuilder sb = new StringBuilder();
        if (slots == 0 || next == words.length) {
            for (int i = start; i < next; i++) {
                sb.append(words[i]);
                if (i < next - 1) {
                    sb.append(" ");
                }
            }

            int trailingSpace = limit - len - slots;
            for (int i = 0; i < trailingSpace; i++) {
                sb.append(" ");
            }
        } else {
            int avspace = (limit - len) / slots;
            int exspace = (limit - len) % slots;
            for (int i = start; i < next; i++) {
                sb.append(words[i]);
                if (i < next - 1) {
                    for (int j = 0; j < avspace; j++) {
                        sb.append(" ");
                    }
                    if (exspace > 0) {
                        sb.append(" ");
                        exspace--;
                    }
                }
            }
        }
        result.add(sb.toString());
    }
}
