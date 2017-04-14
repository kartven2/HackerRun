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

    public static void main(String... args) {
        StrongPasswordChecker sp = new StrongPasswordChecker();
        String s = "aaaabaaaaacaaaaaadA1aaabcdefghih";
        System.out.println(sp.strongPasswordChecker(s));
        System.out.println(sp.strongPasswordChecker2(s));
    }

    public int strongPasswordChecker2(String s) {

        char[] str = s.toCharArray();
        boolean isUpper = false, isLower = false, isDigit = false;
        int missinType = 3;
        for (char c : str) {
            if (!isUpper && Character.isUpperCase(c)) {
                isUpper = true;
                missinType -= 1;
            } //uppercase
            if (!isLower && Character.isLowerCase(c)) {
                isLower = true;
                missinType -= 1;
            } //lowercase
            if (!isDigit && Character.isDigit(c)) {
                isDigit = true;
                missinType -= 1;
            } //atleast one number

        }

        int totalChangeCnt = 0, OneChangeCnt = 0, TwoChangeCnt = 0, pos = 2;
        while (pos < s.length()) {
            if (str[pos] == str[pos - 1] && str[pos - 1] == str[pos - 2] && str[pos - 2] == str[pos]) {
                int length = 2;
                while (pos < s.length() && str[pos] == str[pos - 1]) {
                    length += 1;
                    pos += 1;
                }
                totalChangeCnt += length / 3;
                if (length % 3 == 0) {
                    OneChangeCnt += 1;
                } else if (length % 3 == 1) {
                    TwoChangeCnt += 1;
                }

            } else {
                pos = pos + 1;
            }
        }

        if (s.length() < 6) {
            return Math.max(missinType, 6 - s.length());
        } else if (s.length() <= 20) {
            return Math.max(missinType, totalChangeCnt);
        } else {
            int deleteCount = s.length() - 20;
            totalChangeCnt -= Math.min(deleteCount, OneChangeCnt * 1) / 1;
            totalChangeCnt -= Math.min(Math.max(deleteCount - OneChangeCnt, 0), TwoChangeCnt * 2) / 2;
            totalChangeCnt -= Math.max(deleteCount - OneChangeCnt - 2 * TwoChangeCnt, 0) / 3;

            return deleteCount + Math.max(missinType, totalChangeCnt);
        }
    }

    public int strongPasswordChecker(String s) {
        if (s == null || s.length() == 0) {
            return 6;
        }
        int n = s.length(), st = 1, need = 3, tp = 0, oc = 0, tc = 0;
        boolean lc = false, dc = false, uc = false;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int v = getCharVal(c);
            if (need > 0) {
                switch (v) {
                    case 0:
                        if (!lc) {
                            lc = true;
                            need--;
                        }
                        break;
                    case 1:
                        if (!uc) {
                            uc = true;
                            need--;
                        }
                        break;
                    case 2:
                        if (!dc) {
                            dc = true;
                            need--;
                        }
                        break;
                    default:
                        break;
                }
            }
            if (i == n - 1 && st > 1) {
                if (c == s.charAt(i - 1)) {
                    st++;
                    tp += (st / 3);
                    if (st % 3 == 0) {
                        oc++;
                    }
                    if (st % 3 == 1) {
                        tc++;
                    }
                }
            } else {
                if (i > 0 && c == s.charAt(i - 1)) {
                    st++;
                } else {
                    if (st > 2) {
                        tp += (st / 3);
                        if (st % 3 == 0) {
                            oc++;
                        }
                        if (st % 3 == 1) {
                            tc++;
                        }
                    }
                    st = 1;
                }
            }
        }

        if (n < 6) {
            return Math.max(6 - n, need);
        } else if (n >= 6 && n <= 20) {
            return Math.max(tp, need);
        } else {
            int delCount = n - 20;
            tp -= Math.min(delCount, oc * 1);
            tp -= Math.min(Math.max(delCount - oc, 0), tc * 2) / 2;
            tp -= Math.max(delCount - oc - 2 * tc, 0) / 3;
            return delCount + Math.max(tp, need);
        }
    }

    private int getCharVal(char c) {
        if (c >= 'a' && c <= 'z') {
            return 0;
        }
        if (c >= 'A' && c <= 'Z') {
            return 1;
        }
        if (c >= '0' && c <= '9') {
            return 2;
        }
        return -1;
    }
}
