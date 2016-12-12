/*
 * Leetcode: https://leetcode.com/problems/word-search-ii/
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent"
 * cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once in a word.
 */
package com.karthik.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WordSearchII {

    private Trie trie;
    private boolean[][] marked;
    private char[][] boardCopy;
    private String[] wordsCopy;
    private List<String> result;
    private Set<String> setResult;
    private int n;
    private int m;

    class Node {

        private char c;
        private Node l, m, r;
        private boolean isWord;
    }

    class Trie {

        private Node root;

        private void put(String key) {
            root = put(root, key, 0);
        }

        private Node put(Node x, String key, int d) {
            char c = key.charAt(d);
            if (x == null) {
                x = new Node();
                x.c = c;
            }
            if (x.c > c) {
                x.l = put(x.l, key, d);
            } else if (x.c < c) {
                x.r = put(x.r, key, d);
            } else if (d < key.length() - 1) {
                x.m = put(x.m, key, d + 1);
            } else {
                x.isWord = true;
            }
            return x;
        }

        private boolean exists(String key) {
            Node x = get(root, key, 0);
            return x == null ? false : x.isWord;
        }

        private boolean prefixExists(String key) {
            Node x = get(root, key, 0);
            return x != null;
        }

        private Node get(Node x, String key, int d) {
            if (x == null) {
                return null;
            }
            char c = key.charAt(d);
            if (x.c > c) {
                return get(x.l, key, d);
            } else if (x.c < c) {
                return get(x.r, key, d);
            } else if (d < key.length() - 1) {
                return get(x.m, key, d + 1);
            } else {
                return x;
            }
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        boardCopy = board;
        wordsCopy = words;
        result = new LinkedList<>();
        if (boardCopy == null || boardCopy.length == 0 || wordsCopy == null || wordsCopy.length == 0) {
            return result;
        }
        setResult = new HashSet<>();
        trie = new Trie();
        for (int i = 0; i < wordsCopy.length; i++) {
            trie.put(words[i]);
        }
        n = boardCopy.length;
        m = boardCopy[0].length;
        marked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs("", i, j);
            }
        }
        result = new LinkedList<>(setResult);
        return result;
    }

    private void dfs(String str, int i, int j) {
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return;
        }
        if (marked[i][j]) {
            return;
        }
        str += boardCopy[i][j];
        if (!trie.prefixExists(str)) {
            return;
        }
        if (trie.exists(str)) {
            setResult.add(str);
        }
        marked[i][j] = true;
        dfs(str, i - 1, j);
        dfs(str, i + 1, j);
        dfs(str, i, j - 1);
        dfs(str, i, j + 1);
        marked[i][j] = false;
    }

    public static void main(String... args) {
        WordSearchII ws = new WordSearchII();
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> answer = ws.findWords(board, words);
        for (String x : answer) {
            System.out.println(x);
        }
    }
}
