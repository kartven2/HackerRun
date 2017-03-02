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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        Set<Integer> valid = new HashSet<>();
        Set<Integer> dup = new HashSet<>();
        int[] ch = new int[26];
        ch['C' - 'A'] = 1;
        ch['G' - 'A'] = 2;
        ch['T' - 'A'] = 3;
        int n = s.length();
        for (int i = 0; i < n - 9; i++) {
            int c = 0;
            for (int j = i; j < i + 10; j++) {
                c |= ch[s.charAt(j) - 'A'];
                c <<= 2;
            }
            if (!valid.add(c) && dup.add(c)) {
                result.add(s.substring(i, i + 10));
            }
        }
        return result;
    }
}
