/*
 * https://leetcode.com/problems/validate-ip-address/
 *
 * In this problem, your job to write a function to check
 * whether a input string is a valid IPv4 address or IPv6 address or neither.
 * IPv4 addresses are canonically represented in dot-decimal notation,
 * which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 * Besides, you need to keep in mind that leading zeros in the IPv4 is illegal.
 * For example, the address 172.16.254.01 is illegal.
 * IPv6 addresses are represented as eight groups of four hexadecimal digits,
 * each group representing 16 bits. The groups are separated by colons (":").
 * For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal one.
 * Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters
 * in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).
 * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity.
 * For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
 * Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal.
 * For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.
 * Note: You could assume there is no extra space in the test cases and there may some
 * special characters in the input string.
 */
package com.karthik.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ValidateIpAddress {

    String[] ans = {"Neither", "IPv4", "IPv6"};

    public String validIPAddress(String ip) {
        if (ip == null || ip.length() == 0) {
            return ans[0];
        }
        char[] iparr = ip.toCharArray();
        int n = iparr.length, isIpv4 = 0, isIpv6 = 0;
        List<List<Character>> chars = new LinkedList<>();
        List<Character> seg = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (iparr[i] == '.') {
                isIpv4++;
                chars.add(seg);
                seg = new LinkedList<>();
            } else if (iparr[i] == ':') {
                isIpv6++;
                chars.add(seg);
                seg = new LinkedList<>();
            } else {
                seg.add(iparr[i]);
            }
            if (isIpv4 > 0 && isIpv6 > 0) {
                return ans[0];
            }
        }
        chars.add(seg);
        if ((isIpv4 == 0 && isIpv6 == 0)
                || isIpv4 > 0 && (isIpv4 != 3 || n < 7 || n > 15)
                || isIpv6 > 0 && (isIpv6 != 7 || n < 15 || n > 39)) {
            return ans[0];
        }
        if (isIpv4 == 3) {
            for (List<Character> chs : chars) {
                if (chs.size() > 3 || chs.isEmpty()) {
                    return ans[0];
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < chs.size(); i++) {
                    char cp = chs.get(i);
                    if (i < chs.size() - 1 && cp == '0') {
                        return ans[0];
                    }
                    if (cp < '0' || cp > '9') {
                        return ans[0];
                    }
                    sb.append(cp);
                }
                if (Integer.parseInt(sb.toString()) > 255) {
                    return ans[0];
                }
            }
            return ans[1];
        }
        for (List<Character> chs : chars) {
            if (chs.size() > 4 || chs.isEmpty()) {
                return ans[0];
            }
            for (int i = 0; i < chs.size(); i++) {
                char cp = chs.get(i);
                if (!((cp >= '0' && cp <= '9') || (cp >= 'a' && cp <= 'f') || (cp >= 'A' && cp <= 'F'))) {
                    return ans[0];
                }
            }
        }
        return ans[2];
    }

    public static void main(String... args) {
        ValidateIpAddress vip = new ValidateIpAddress();
        System.out.println(vip.validIPAddress("1.1.1.01"));
    }
}
