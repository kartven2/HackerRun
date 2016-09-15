/*
 * https://leetcode.com/problems/word-ladder/
 *
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null && endWord == null) {
            return 1;
        }
        if (beginWord == null || endWord == null) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (wordList == null || wordList.isEmpty()) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        wordList.remove(beginWord);
        int dist = 0;

        while (!q.isEmpty()) {
            dist++;
            int qsz = q.size();
            for (int i = 0; i < qsz; i++) {
                char[] word = q.remove().toCharArray();
                for (int w = 0; w < word.length; w++) {
                    char o = word[w];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != o) {
                            word[w] = c;
                            String temp = new String(word);
                            if (temp.equals(endWord)) {
                                return dist + 1;
                            }
                            if (wordList.contains(temp)) {
                                wordList.remove(temp);
                                q.add(temp);
                            }
                        }
                    }
                    word[w] = o;
                }
            }
        }
        return 0;
    }

    public static void main(String... args) {
        WordLadder wl = new WordLadder();
        Set<String> set = new HashSet<>();
        set.add("hit");
        set.add("hot");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
        set.add("cog");
        set.add("cig");
        set.add("cig");
        System.out.println(wl.ladderLength("hit", "cog", set));
    }
}
