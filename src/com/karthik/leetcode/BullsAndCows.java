/*
 * LeetCode: https://leetcode.com/problems/bulls-and-cows/#/description
 * You are playing the following Bulls and Cows game with your friend:
 * You write down a number and ask your friend to guess what the number
 * is. Each time your friend makes a guess, you provide a hint that
 * indicates how many digits in said guess match your secret number
 * exactly in both digit and position (called "bulls") and
 * how many digits match the secret number but locate in the wrong position (called "cows").
 * Your friend will use successive guesses and hints to eventually derive the secret number.
 *
 * For example:
 *
 * Secret number:  "1807"
 * Friend's guess: "7810"
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BullsAndCows {

    public String getHint2(String secret, String guess) {
        if (secret == null || secret.trim().isEmpty() || guess == null || guess.trim().isEmpty()
                || secret.length() != guess.length()) {
            return "0A0B";
        }
        int n = secret.length(), a = 0, b = 0;
        int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                if (count[secret.charAt(i) - '0'] > 0) {
                    b++;
                }
                if (count[guess.charAt(i) - '0'] < 0) {
                    b++;
                }
                count[secret.charAt(i) - '0']--;
                count[guess.charAt(i) - '0']++;
            }
        }
        return a + "A" + b + "B";
    }

    public String getHint(String secret, String guess) {
        if (secret == null || secret.trim().isEmpty() || guess == null || guess.trim().isEmpty()
                || secret.length() != guess.length()) {
            return "0A0B";
        }
        int n = secret.length(), a = 0, b = 0;
        Map<Character, Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                Integer x = map.get(guess.charAt(i));
                x = (x == null) ? 1 : x + 1;
                map.put(guess.charAt(i), x);
                list.add(secret.charAt(i));
            }
        }

        for (char c : list) {
            if (map.get(c) != null) {
                int val = map.get(c);
                if (val == 1) {
                    map.remove(c);
                } else {
                    map.put(c, val - 1);
                }
                b++;
            }
        }
        return a + "A" + b + "B";
    }
}
