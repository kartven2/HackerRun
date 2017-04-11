/*
 * Leetcode: https://leetcode.com/problems/strong-password-checker/#/description
 *
 * A password is considered strong if below conditions are all met:
 *
 * It has at least 6 characters and at most 20 characters.
 * It must contain at least one lowercase letter, at least one uppercase letter,
 * and at least one digit.
 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
 * Write a function strongPasswordChecker(s),
 * that takes a string s as input, and return the MINIMUM change required to make s a strong password.
 * If s is already strong, return 0.
 * Insertion, deletion or replace of any one character are all considered as one change.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class StrongPasswordChecker {

    public int strongPasswordChecker(String s) {
        if (s == null || s.length() == 0) {
            return 6;
        }
        int n = s.length(), ans = n < 6 ? 6 - n : n > 20 ? n - 20 : 0;
        boolean lc = false, uc = false, dc = false;
        int triples = 0, st = 1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                lc = true;
            } else if (c >= 'A' && c <= 'Z') {
                uc = true;
            } else if (c >= '0' && c <= '9') {
                dc = true;
            }
            if (i > 0 && c == s.charAt(i - 1)) {
                st++;
            } else {
                if (st > 3) {
                    triples += (st / 3);
                }
                st = 1;
            }
        }
        int need = triples;
        if (!lc) {
            need++;
        }
        if (!uc) {
            need++;
        }
        if (!dc) {
            need++;
        }
        if(n>=6 && n<=20) {
          return need;            
        } else if(n<6) {
            return ans;
        }
        
        
    }
}
