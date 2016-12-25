/*
 * LeetCode: https://leetcode.com/problems/additive-number/
 *
 * Additive number is a string whose digits can form additive sequence.
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * Follow up:
 * How would you handle overflow for very large input integers?
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class AdditiveNumber {

    public static void main(String... args) {
        AdditiveNumber an = new AdditiveNumber();
        System.out.println(an.isAdditiveNumber("198019823962"));
    }

    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) {
            return false;
        }
        int n = num.length();
        int n3 = n - n / 3;
        for (int i = 1; i < n3; i++) {
            for (int j = i + 1; j <= n3; j++) {
                String a = num.substring(0, i);
                String b = num.substring(i, j);
                String rest = num.substring(j);
                if (isAdditiveSum(a, b, rest)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAdditiveSum(String a, String b, String rest) {
        if ((a.length() > 1 && a.charAt(0) == '0') || (b.length() > 1 && b.charAt(0) == '0')) {
            return false;
        }
        int maxlen = Math.max(a.length(), b.length());
        if (rest.length() < maxlen) {
            return false;
        }
        String x = addTwoNum(a, b);
        if (!rest.startsWith(x)) {
            return false;
        }
        return rest.equals(x) || isAdditiveSum(b, x, rest.substring(x.length()));
    }

    private String addTwoNum(String a, String b) {
        int n = a.length() - 1, m = b.length() - 1, carry = 0, y = 0, x = 0;
        if (n < m) {
            int tmp = n;
            n = m;
            m = tmp;
            String tc = a;
            a = b;
            b = tc;
        }
        char digit = '0';
        StringBuilder sb = new StringBuilder();
        while (n >= 0 && m >= 0) {
            x = a.charAt(n--) - '0';
            y = b.charAt(m--) - '0';
            digit = (char) ((char) ((x + y + carry) >= 10 ? (x + y + carry) % 10 : x + y + carry) + '0');
            sb.insert(0, digit);
            carry = (x + y + carry) / 10;
        }
        while (n >= 0) {
            x = a.charAt(n--) - '0';
            digit = (char) ((char) ((x + carry) >= 10 ? (x + carry) % 10 : x + carry) + '0');
            sb.insert(0, digit);
            carry = (x + carry) / 10;
        }
        if (carry > 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }
}
