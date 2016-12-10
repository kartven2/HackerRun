/*
 * LeetCode: https://leetcode.com/problems/restore-ip-addresses/
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * For example:
 * Given "25525511135",
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class RestoreIPAddresses {

    public static void main(String... args) {
        RestoreIPAddresses rp = new RestoreIPAddresses();
        System.out.println(rp.restoreIpAddresses("19216811"));
    }

    private void validIp(List<String> result, String[] octects) {
        for (int i = 0; i < 4; i++) {
            if (octects[i].length() > 3 || (octects[i].charAt(0) == '0' && octects[i].length() > 1)) {
                return;
            }
            if (Integer.parseInt(octects[i]) > 255) {
                return;
            }
        }
        result.add(octects[0] + "." + octects[1] + "." + octects[2] + "." + octects[3]);
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new LinkedList<>();
        if (s == null || s.trim().isEmpty() || s.length() < 4 || s.length() > 12) {
            return result;
        }
        for (int i = 1; i < 4; i++) {
            if (s.substring(i).length() >= 3 && s.substring(i).length() <= 9) {
                for (int j = i + 1; j - i < 4; j++) {
                    if (s.substring(j).length() >= 2 && s.substring(j).length() <= 6) {
                        for (int k = j + 1; k < s.length() && k - j < 4; k++) {
                            if (s.substring(k).length() >= 1 && s.substring(k).length() <= 3) {
                                String[] octects = new String[4];
                                octects[0] = s.substring(0, i);
                                octects[1] = s.substring(i, j);
                                octects[2] = s.substring(j, k);
                                octects[3] = s.substring(k);
                                validIp(result, octects);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
