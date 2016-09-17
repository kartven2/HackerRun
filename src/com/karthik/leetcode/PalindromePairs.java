/*
 * https://leetcode.com/problems/palindrome-pairs/
 *
 * Given a list of unique words.
 * Find all pairs of distinct indices (i, j) in the given list, 
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class PalindromePairs {

    class Node {

        private char c;
        private Node l, m, r;
        private int id;

        Node(char c) {
            this.c = c;
            this.id = -1;
        }
    }

    class Tst {

        private Node root;

        private List<Integer> collectPalindromeShorterThanOrEqualToWord(String key, int iid) {
            List<Integer> result = new LinkedList<>();
            collectPalindromeShorterThanOrEqualToWord(root, key, 0, result, new StringBuilder(), iid);
            return result;
        }

        private void collectPalindromeShorterThanOrEqualToWord(Node x, String key, int d, List<Integer> result, StringBuilder sb, int iid) {
            if (x == null) {
                return;
            }
            char c = key.charAt(d);
            if (c < x.c) {
                collectPalindromeShorterThanOrEqualToWord(x.l, key, d, result, sb, iid);
                return;
            }
            if (c > x.c) {
                collectPalindromeShorterThanOrEqualToWord(x.r, key, d, result, sb, iid);
                return;
            }
            sb.append(x.c);
            if (x.id >= 0 && iid != x.id && isPalindrome(key + revStr(sb.toString()))) {
                result.add(x.id);
            }
            if (d < key.length() - 1) {
                collectPalindromeShorterThanOrEqualToWord(x.m, key, d + 1, result, sb, iid);
            }
        }

        private List<Integer> collectPalindromeLargerThanWord(String key) {
            List<Integer> result = new LinkedList<>();
            Node x = getNode(root, key, 0);
            if (x == null) {
                return result;
            }
            collectPalindromeLargerThanWord(x.m, result, new StringBuilder());
            return result;
        }

        private void collectPalindromeLargerThanWord(Node x, List<Integer> result, StringBuilder sb) {
            if (x == null) {
                return;
            }
            collectPalindromeLargerThanWord(x.l, result, sb);
            sb.append(x.c);
            if (x.id >= 0 && isPalindrome(sb.toString())) {
                result.add(x.id);
            }
            collectPalindromeLargerThanWord(x.m, result, sb);
            sb.deleteCharAt(sb.length() - 1);
            collectPalindromeLargerThanWord(x.r, result, sb);

        }

        private Node getNode(Node x, String key, int d) {
            if (x == null) {
                return null;
            }
            char c = key.charAt(d);
            if (c < x.c) {
                return getNode(x.l, key, d);
            }
            if (c > x.c) {
                return getNode(x.r, key, d);
            }
            if (d < key.length() - 1) {
                return getNode(x.m, key, d + 1);
            }
            return x;
        }

        private void put(String key, int idx) {
            root = put(root, key, idx, 0);
        }

        private Node put(Node x, String key, int idx, int d) {
            char c = key.charAt(d);
            if (x == null) {
                x = new Node(c);
            }
            if (c < x.c) {
                x.l = put(x.l, key, idx, d);
            } else if (c > x.c) {
                x.r = put(x.r, key, idx, d);
            } else if (d < key.length() - 1) {
                x.m = put(x.m, key, idx, d + 1);
            } else {
                x.id = idx;
            }
            return x;
        }
    }

    private boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private String revStr(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }
        List<List<Integer>> result = new LinkedList<>();
        Tst tst = new Tst();
        int emptyIdx = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("")) {
                emptyIdx = i;
                continue;
            }
            tst.put(revStr(words[i]), i);
        }

        for (int i = 0; i < words.length; i++) {
            if (i != emptyIdx) {
                List<Integer> slist = tst.collectPalindromeShorterThanOrEqualToWord(words[i], i);
                for (int x : slist) {
                    List temp = new LinkedList();
                    temp.add(i);
                    temp.add(x);
                    result.add(temp);
                }
                List<Integer> glist = tst.collectPalindromeLargerThanWord(words[i]);
                for (int x : glist) {
                    List temp = new LinkedList();
                    temp.add(i);
                    temp.add(x);
                    result.add(temp);
                }
            }
        }

        if (emptyIdx > -1) {
            for (int i = 0; i < words.length; i++) {
                if (i != emptyIdx && isPalindrome(words[i])) {
                    List temp = new LinkedList();
                    temp.add(i);
                    temp.add(emptyIdx);
                    result.add(temp);
                    temp = new LinkedList();
                    temp.add(emptyIdx);
                    temp.add(i);
                    result.add(temp);
                }
            }
        }
        return result;
    }

    public static void main(String... args) {
        PalindromePairs pp = new PalindromePairs();
        List<List<Integer>> res = pp.palindromePairs(new String[]{"a", "abc", "aba"});
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).get(0) + "," + res.get(i).get(1));
        }
    }
}
