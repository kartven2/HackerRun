/*
 * https://leetcode.com/problems/word-ladder/
 *
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord
 */
package com.karthik.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastWordLadder {

    class Info {

        private int dist;
        private boolean dir;

        Info(int dist, boolean dir) {
            this.dist = dist;
            this.dir = dir;
        }
    }

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

        Map<String, Info> nl = new HashMap<>();
        nl.put(beginWord, new Info(1, false));
        nl.put(endWord, new Info(1, true));

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int fd = 0;

        Queue<String> rq = new LinkedList<>();
        rq.add(endWord);
        int rd = 0;

        while (!q.isEmpty() && !rq.isEmpty()) {
            int qs = q.size();
            int rqs = rq.size();

            fd++;
            for (int i = 0; i < qs; i++) {
                char[] word = q.remove().toCharArray();
                for (int w = 0; w < word.length; w++) {
                    char o = word[w];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != o) {
                            word[w] = c;
                            String temp = new String(word);
                            if (temp.equals(endWord)) {
                                return fd + 1;
                            }
                            if (wordList.contains(temp)) {
                                if (nl.containsKey(temp)) {
                                    if (nl.get(temp).dir) {
                                        return nl.get(temp).dist + fd;
                                    }
                                } else {
                                    nl.put(temp, new Info(fd + 1, false));
                                    q.add(temp);
                                }
                            }
                        }
                    }
                    word[w] = o;
                }
            }

            rd++;
            for (int i = 0; i < rqs; i++) {
                char[] word = rq.remove().toCharArray();
                for (int w = 0; w < word.length; w++) {
                    char o = word[w];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != o) {
                            word[w] = c;
                            String temp = new String(word);
                            if (temp.equals(beginWord)) {
                                return rd + 1;
                            }
                            if (wordList.contains(temp)) {
                                if (nl.containsKey(temp)) {
                                    if (!nl.get(temp).dir) {
                                        return nl.get(temp).dist + rd;
                                    }
                                } else {
                                    nl.put(temp, new Info(rd + 1, true));
                                    rq.add(temp);
                                }
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
        FastWordLadder wl = new FastWordLadder();
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
