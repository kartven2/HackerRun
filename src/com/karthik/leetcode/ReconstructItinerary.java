/*
 * LeetCode: https://leetcode.com/problems/reconstruct-itinerary/
 * 
 * Given a list of airline tickets represented by pairs of departure
 * and arrival airports [from, to], reconstruct the itinerary in order.
 * All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary
 * that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 */
package com.karthik.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ReconstructItinerary {
    
    private static final int R = 26;
    private static final char FC = 'A';
    private static final String JFK = "JFK";
    private static final Map<String, Integer> MAP = new HashMap<>();
    private static String[] array;
    
    static class Lsd {
        
        private void sort(String[] a) {
            if (a == null || a.length == 0) {
                return;
            }
            int n = a.length;
            String[] aux = new String[n];
            for (int d = 2; d >= 0; d--) {
                int[] count = new int[R + 1];
                for (int i = 0; i < n; i++) {
                    count[a[i].charAt(d) - FC + 1]++;
                }
                for (int r = 0; r < R; r++) {
                    count[r + 1] += count[r];
                }
                for (int i = 0; i < n; i++) {
                    aux[count[a[i].charAt(d) - FC]++] = a[i];
                }
                for (int i = 0; i < n; i++) {
                    a[i] = aux[i];
                }
            }
        }
    }
    
    static class Domain {
        
        private int[] a;
        private int i;
        
        Domain(List<Integer> b) {
            int sz = b == null ? 0 : b.size();
            this.a = new int[sz];
            if (b == null) {
                return;
            }
            int k = 0;
            for (int x : b) {
                this.a[k++] = x;
            }
            Arrays.sort(a);
        }
        
        private boolean hasNext() {
            return i < a.length;
        }
        
        private int next() {
            return a[i++];
        }
    }
    
    private Set<String> getDistinctCitySet(int n, String[][] tickets) {
        final Set<String> src = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                src.add(tickets[i][j]);
            }
        }
        return src;
    }
    
    private void assignCityIdx(String[] citys) {
        int sz = 0;
        array = new String[citys.length];
        for (String city : citys) {
            MAP.put(city, sz);
            array[sz++] = city;
        }
    }
    
    private Domain[] getDomains(int n, int nc, String[][] tickets) {
        List<Integer>[] adj = (List<Integer>[]) new LinkedList[nc];
        for (int i = 0; i < n; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            int fromIdx = MAP.get(from);
            int toIdx = MAP.get(to);
            if (adj[fromIdx] == null) {
                adj[fromIdx] = new LinkedList<>();
            }
            adj[fromIdx].add(toIdx);
        }
        Domain[] domain = new Domain[nc];
        for (int i = 0; i < nc; i++) {
            domain[i] = new Domain(adj[i]);
        }
        return domain;
    }
    
    public List<String> findItinerary(String[][] tickets) {
        List<String> result = new LinkedList<>();
        if (tickets == null || tickets.length == 0) {
            return result;
        }
        int n = tickets.length;
        Set<String> citySet = getDistinctCitySet(n, tickets);
        if (!citySet.contains(JFK)) {
            return result;
        }
        int nc = citySet.size();
        String[] a = new String[nc];
        int sz = 0;
        for (String city : citySet) {
            a[sz++] = city;
        }
        Lsd lsd = new Lsd();
        lsd.sort(a);
        assignCityIdx(a);
        Domain[] domain = getDomains(n, nc, tickets);
        Stack<String> path = new Stack<>();
        Stack<Integer> stk = new Stack<>();
        stk.push(MAP.get(JFK));
        while (!stk.isEmpty()) {
            int v = stk.pop();
            while (domain[v].hasNext()) {
                stk.push(v);
                v = domain[v].next();
            }
            path.push(array[v]);
        }
        while (!path.isEmpty()) {
            result.add(path.pop());
        }
        return result;
    }
}
