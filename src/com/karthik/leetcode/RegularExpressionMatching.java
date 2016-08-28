/*
 * Leetcode: https://leetcode.com/problems/regular-expression-matching/
 *
 * Implement regular expression matching with support for '.' and '*'.
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RegularExpressionMatching {

    class Digraph {

        private int V;
        private List<Integer>[] adj;

        Digraph(int V) {
            this.V = V;
            this.adj = (List<Integer>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<>();
            }
        }

        private void addEdge(int v, int w) {
            adj[v].add(w);
        }

        private Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    class Dfs {

        private boolean[] marked;
        private List<Integer> visited;

        private void initialize(int x) {
            marked = new boolean[x];
            visited = new ArrayList<>();
        }

        Dfs(Digraph G, int s) {
            initialize(G.V);
            dfs(G, s);
        }

        Dfs(Digraph G, Iterable<Integer> sources) {
            initialize(G.V);
            for (int s : sources) {
                if (!marked[s]) {
                    dfs(G, s);
                }
            }
        }

        private void dfs(Digraph G, int v) {
            marked[v] = true;
            visited.add(v);
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
        }
    }

    class NFA {

        private String regex;
        private Digraph g;
        private int m;

        NFA(String regex) {
            this.regex = regex;
            this.m = regex.length();
            this.g = new Digraph(m + 1);
            Stack<Integer> ops = new Stack<>();
            for (int i = 0; i < m; i++) {
                int lp = i;
                if (regex.charAt(i) == '(') {
                    ops.push(i);
                } else if (regex.charAt(i) == ')') {
                    lp = ops.pop();
                }

                if (i < m - 1 && regex.charAt(i + 1) == '*') {
                    g.addEdge(lp, i + 1);
                    g.addEdge(i + 1, lp);
                }

                char c = regex.charAt(i);
                if (c == '(' || c == '*' || c == ')') {
                    g.addEdge(i, i + 1);
                }
            }
        }

        private boolean recognizeText(String text) {
            Dfs dfs = new Dfs(g, 0);
            for (int i = 0; i < text.length(); i++) {
                List<Integer> match = new ArrayList<>();
                for (int v : dfs.visited) {
                    if (v != m) {
                        if (regex.charAt(v) == text.charAt(i) || regex.charAt(v) == '.') {
                            match.add(v + 1);
                        }
                    }
                }
                dfs = new Dfs(g, match);
                if (dfs.visited.isEmpty()) {
                    return false;
                }
            }
            for (int v : dfs.visited) {
                if (v == m) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        if (p.length() == 0) {
            return s.length() == 0;
        }
        NFA nfa = new NFA("(" + p + ")");
        return nfa.recognizeText(s);
    }
}
