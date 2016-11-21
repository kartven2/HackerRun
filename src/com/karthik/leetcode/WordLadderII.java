/*
 * LeetCode Problem: https://leetcode.com/problems/word-ladder-ii/
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class WordLadderII {

    private Set<String> visited = new HashSet<>();
    private Set<String> unvisited = null;
    private Queue<Node> queue = new LinkedList<>();
    private List<List<String>> result = new ArrayList<>();
    private int level = 0, minDist = Integer.MAX_VALUE;

    class Node {

        private String word;
        private Node parent;
        private int dist;

        Node(String w, Node p, int d) {
            word = w;
            parent = p;
            dist = d;
        }
    }

    private void addSameLevelNodes(Node currentNode) {
        char[] currWord = currentNode.word.toCharArray();
        for (int i = 0; i < currWord.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != currWord[i]) {
                    char tmp = currWord[i];
                    currWord[i] = c;
                    String newWord = new String(currWord);
                    if (unvisited.contains(newWord)) {
                        Node n = new Node(newWord, currentNode, currentNode.dist + 1);
                        queue.add(n);
                        visited.add(newWord);
                    }
                    currWord[i] = tmp;
                }
            }
        }
    }

    private void addPath(Node currentNode) {
        String[] path = new String[currentNode.dist + 1];
        int pathptr = currentNode.dist;
        while (currentNode != null) {
            path[pathptr--] = currentNode.word;
            currentNode = currentNode.parent;
        }
        result.add(new ArrayList<>(Arrays.asList(path)));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return null;
        }
        unvisited = wordList;
        visited.add(beginWord);
        unvisited.add(endWord);
        Node start = new Node(beginWord, null, 0);
        queue.add(start);
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            if (currentNode.word.equals(endWord) && currentNode.dist <= minDist) {
                minDist = currentNode.dist;
                addPath(currentNode);
            }

            if (currentNode.dist > minDist) {
                break;
            }

            if (currentNode.dist > level) {
                level = currentNode.dist;
                unvisited.removeAll(visited);
            }

            addSameLevelNodes(currentNode);
        }
        return result;
    }
}
