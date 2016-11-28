/*
 * LeetCode: https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or .. A . means it can represent any one letter.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AddSearchWord {

    class Trie {

        private Node root;
        private boolean hasNull = false, hasEmpty = false;

        class Node {

            private Node[] ch;
            private boolean end;

            Node() {
                ch = new Node[26];
            }
        }

        private void insert(String word) {
            if (word == null) {
                hasNull = true;
                return;
            }
            if (word.trim().isEmpty()) {
                hasEmpty = true;
                return;
            }
            root = insert(root, word, word.length(), 0);
        }

        private Node insert(Node x, String word, int n, int d) {
            if (x == null) {
                x = new Node();
            }
            if (d == n) {
                x.end = true;
                return x;
            }
            int idx = word.charAt(d) - 'a';
            x.ch[idx] = insert(x.ch[idx], word, n, d + 1);
            return x;
        }

        private boolean search(String word) {
            if (word == null) {
                return hasNull;
            }
            if (word.trim().isEmpty()) {
                return hasEmpty;
            }
            return search(root, word, word.length(), 0);
        }

        private boolean search(Node x, String word, int n, int d) {
            if (x == null) {
                return false;
            }
            if (n == d) {
                return x.end;
            }
            char c = word.charAt(d);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (x.ch[i] != null && search(x.ch[i], word, n, d + 1)) {
                        return true;
                    }
                }
                return false;
            }
            return search(x.ch[c - 'a'], word, n, d + 1);
        }
    }

    private Trie trie = new Trie();

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }
}
