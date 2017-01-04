/*
 * LeetCode: https://leetcode.com/problems/implement-trie-prefix-tree/
 * Implement a trie with insert, search, and startsWith methods.
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
class TrieNode {

    boolean eow;
    char c;
    TrieNode l, m, r;

    // Initialize your data structure here.
    public TrieNode() {
    }
}

public class Trie {

    private TrieNode root;

    public Trie() {
    }

    private TrieNode put(TrieNode x, String word, int d, int n) {
        char c = word.charAt(d);
        if (x == null) {
            x = new TrieNode();
            x.c = c;
        }
        if (x.c > c) {
            x.l = put(x.l, word, d, n);
        } else if (x.c < c) {
            x.r = put(x.r, word, d, n);
        } else if (d < n - 1) {
            x.m = put(x.m, word, d + 1, n);
        } else {
            x.eow = true;
        }
        return x;
    }

    private TrieNode get(TrieNode x, String word, int d, int n) {
        if (x == null) {
            return x;
        }
        char c = word.charAt(d);
        if (x.c > c) {
            return get(x.l, word, d, n);
        } else if (x.c < c) {
            return get(x.r, word, d, n);
        } else if (d < n - 1) {
            return get(x.m, word, d + 1, n);
        } else {
            return x;
        }
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        root = put(root, word, 0, word.length());
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        TrieNode x = get(root, word, 0, word.length());
        return x == null ? false : x.eow;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        TrieNode x = get(root, prefix, 0, prefix.length());
        if (x == null) {
            return false;
        }
        return (x.l != null || x.m != null || x.r != null || x.eow);
    }
}
