/*
 * Leetcode: https://leetcode.com/problems/repeated-dna-sequences/
 *
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
 * for example: "ACGAATTCCG". When studying DNA,
 * it is sometimes useful to identify repeated sequences within the DNA.
 * Write a function to find all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule. * 
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RepeatedDNASequences {

    class SuffixArray {

        class Suffix implements Comparable<Suffix> {

            private String s;
            private int idx;

            Suffix(String s, int idx) {
                this.s = s;
                this.idx = idx;
            }

            public char charAt(int i) {
                return s.charAt(idx + i);
            }

            @Override
            public String toString() {
                return s.substring(idx);
            }

            public int length() {
                return s.length() - idx;
            }

            @Override
            public int compareTo(Suffix other) {
                if (this == other) {
                    return 0;
                }
                int al = this.length(), bl = other.length();
                int x = Math.min(al, bl);
                for (int i = 0; i < x; i++) {
                    if (this.charAt(i) < other.charAt(i)) {
                        return -1;
                    }
                    if (this.charAt(i) > other.charAt(i)) {
                        return 1;
                    }
                }
                return al - bl;
            }
        }

        private Suffix[] sfxArr;
        private int n;

        SuffixArray(String s) {
            n = s.length();
            sfxArr = new Suffix[n];
            for (int i = 0; i < n; i++) {
                sfxArr[i] = new Suffix(s, i);
            }
            Arrays.sort(sfxArr);
        }

        private Set<String> getResult() {
            Set<String> result = new HashSet<>();
            for (int i = 1; i < n; i++) {
                String item = lcp(i);
                if (item != null) {
                    result.add(item);
                }
            }
            return result;
        }

        private String lcp(int i) {
            int al = sfxArr[i].length(), bl = sfxArr[i - 1].length();
            if (al < 10 || bl < 10) {
                return null;
            }
            int minLen = Math.min(al, bl);
            int len = 0;
            for (int j = 0; j < minLen && len < 10; j++) {
                if (sfxArr[i].charAt(j) != sfxArr[i - 1].charAt(j)) {
                    return null;
                }
                len++;
            }
            return sfxArr[i].toString().substring(0, 10);
        }

    }

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.trim().isEmpty()) {
            return new ArrayList<>();
        }
        SuffixArray sa = new SuffixArray(s);
        List<String> result = new ArrayList<>();
        for (String x : sa.getResult()) {
            result.add(x);
        }
        return result;
    }
}
