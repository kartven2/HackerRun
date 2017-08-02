/*-
 * LeetCode problem: https://leetcode.com/problems/replace-words/description/
 *
 * In English, we have a concept called root, which can be followed by some other
 * words to form another longer word - let's call this word successor.
 * For example, the root an, followed by other, which can form another word another.
 * Now, given a dictionary consisting of many roots and a sentence.
 * You need to replace all the successor in the sentence with the root forming it.
 * If a successor has many roots can form it, replace it with the root with the shortest length.
 */
package com.karthik.leetcode;

import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReplaceWords {

    private Node root;

    class Node {

        boolean endWord;
        Node[] node = new Node[26];
    }

    private void insert(String s) {
        if (s == null || s.trim().isEmpty()) {
            return;
        }
        root = build(root, s, 0, s.length());
    }

    private Node build(Node x, String s, int d, int n) {
        if (x == null) {
            x = new Node();
        }
        if (d < n) {
            char c = s.charAt(d);
            x.node[c - 'a'] = build(x.node[c - 'a'], s, d + 1, n);
        }
        if (d == n) {
            x.endWord = true;
        }
        return x;
    }

    private String search(String s) {
        if (s == null || s.trim().isEmpty()) {
            return s;
        }
        return get(root, s, 0, s.length(), "");
    }

    private String get(Node x, String s, int d, int n, String prefix) {
        if (x == null || d >= n || x.endWord) {
            return prefix.length() == 0 ? null : prefix;
        }
        char c = s.charAt(d);
        if (x.node[c - 'a'] == null) {
            return null;
        }
        return get(x.node[c - 'a'], s, d + 1, n, prefix + c);
    }

    public String replaceWords(List<String> list, String s) {
        if (s == null || s.trim().length() == 0 || list == null || list.isEmpty()) {
            return s;
        }
        for (String x : list) {
            insert(x);
        }
        StringBuilder sb = new StringBuilder();
        String splt[] = s.split("\\s+");
        for (int i = 0; i < splt.length; i++) {
            String wrd = search(splt[i]);
            if (wrd == null) {
                wrd = splt[i];
            }
            sb.append(wrd);
            if (i < splt.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
