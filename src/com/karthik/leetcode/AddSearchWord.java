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

    private Trie trie = new Trie();

    class Trie {

        private Node root;
        private boolean hasNull = false, hasEmpty = false;

        class Node {

            private Node[] nlist;
            private boolean end;

            Node() {
                nlist = new Node[26];
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
            root = insert(word, root, 0);
        }

        private Node insert(String word, Node x, int d) {
            if (x == null) {
                x = new Node();
            }
            if (d == word.length()) {
                x.end = true;
                return x;
            }
            char c = word.charAt(d);
            x.nlist[c - 'a'] = insert(word, x.nlist[c - 'a'], d + 1);
            return x;
        }

        private boolean search(String word) {
            if (word == null) {
                return hasNull;
            }
            if (word.trim().isEmpty()) {
                return hasEmpty;
            }
            return search(root, word, 0);
        }

        private boolean search(Node x, String word, int d) {
            if (x == null) {
                return false;
            }
            if (d == word.length()) {
                return x.end;
            }
            char c = word.charAt(d);
            if (c == '.') {
                for (int i = 0; i < 26; i++) {
                    if (search(x.nlist[i], word, d + 1)) {
                        return true;
                    }
                }
                return false;
            }
            return search(x.nlist[c - 'a'], word, d + 1);
        }

    }

    public void addWord(String word) {
        trie.insert(word);
    }

    public boolean search(String word) {
        return trie.search(word);
    }
}
