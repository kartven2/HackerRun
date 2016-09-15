/*
 * https://leetcode.com/problems/word-ladder/
 *
 * Given two words (beginWord and endWord), and a dictionary's word list,
 * find the length of shortest transformation sequence from beginWord to endWord
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
public class WordLadder {

    class Graph {

        private final int V;
        private final List<Integer>[] adj;

        Graph(int V) {
            this.V = V;
            adj = (List<Integer>[]) new ArrayList[V];
        }

        private void addEdge(int v, int w) {
            if (adj[v] == null) {
                adj[v] = new ArrayList<>();
            }
            if (adj[w] == null) {
                adj[w] = new ArrayList<>();
            }
            adj[v].add(w);
            adj[w].add(v);
        }
    }

    class Bfs {

        private int[] edgeTo;
        private int dist;

        Bfs(Graph G) {
            edgeTo = new int[G.V];
            int d = G.V - 1;
            Arrays.fill(edgeTo, -1);
            edgeTo[0] = 0;
            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            while (!q.isEmpty() && edgeTo[d] < 0) {
                int v = q.remove();
                if (G.adj[v] != null) {
                    for (int w : G.adj[v]) {
                        if (edgeTo[w] == -1) {
                            edgeTo[w] = v;
                            q.add(w);
                        }
                    }
                }
            }
            if (edgeTo[d] >= 0) {
                for (int v = d; v != edgeTo[v]; v = edgeTo[v]) {
                    dist++;
                }
                dist++;
            }
        }
    }

    private boolean isOneCharTransform(String x, String y) {
        int count = 0;
        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) != y.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
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
        int v = wordList.size();
        String[] vertex = new String[v];
        wordList.remove(beginWord);
        wordList.remove(endWord);
        vertex[0] = beginWord;
        vertex[v - 1] = endWord;
        int idx = 1;
        for (String word : wordList) {
            vertex[idx++] = word;
        }
        Graph g = new Graph(v);
        for (int i = 0; i < v; i++) {
            for (int j = i + 1; j < v; j++) {
                if (isOneCharTransform(vertex[i], vertex[j])) {
                    g.addEdge(i, j);
                }
            }
        }
        Bfs bfs = new Bfs(g);
        return bfs.dist;
    }

    public static void main(String... args) {
        WordLadder wl = new WordLadder();
        Set<String> set = new HashSet<>();
        set.add("hot");
        set.add("dog");
        System.out.println(wl.ladderLength("hot", "dog", set));
    }

}
