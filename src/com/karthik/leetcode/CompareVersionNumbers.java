/*
 * LeetCode: https://leetcode.com/problems/compare-version-numbers/
 *
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1,
 * otherwise return 0.
 * You may assume that the version strings are non-empty and contain
 * only digits and the . character. The . character does not represent
 * a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class CompareVersionNumbers {

    public int compareVersion2(String v1, String v2) {
        String[] a = v1.split("\\.");
        String[] b = v2.split("\\.");
        for (int i = 0; (i < a.length || i < b.length); i++) {
            if (i < a.length && i < b.length) {
                int ai = Integer.parseInt(a[i]);
                int bi = Integer.parseInt(b[i]);
                if (ai == bi) {
                    continue;
                }
                return ai > bi ? 1 : -1;
            } else if (i < a.length && Integer.parseInt(a[i]) != 0) {
                return 1;
            } else if (i < b.length && Integer.parseInt(b[i]) != 0) {
                return -1;
            }
        }
        return 0;
    }

    public int compareVersion(String v1, String v2) {
        if ((v1 == null && v2 == null) || (v1.trim().isEmpty() && v2.trim().isEmpty())) {
            return 0;
        }
        if (v1 == null || v1.trim().isEmpty()) {
            return -1;
        }
        if (v2 == null || v2.trim().isEmpty()) {
            return 1;
        }
        String[] voc1 = removeZero(v1);
        String[] voc2 = removeZero(v2);
        int minLen = voc1.length < voc2.length ? voc1.length : voc2.length;
        boolean isEqLen = voc1.length == voc2.length;
        for (int i = 0; i < minLen; i++) {
            if (voc1[i].length() == voc2[i].length()) {
                for (int j = 0; j < voc1[i].length(); j++) {
                    if ((voc1[i].charAt(j) - '0') > (voc2[i].charAt(j) - '0')) {
                        return 1;
                    }
                    if ((voc1[i].charAt(j) - '0') < (voc2[i].charAt(j) - '0')) {
                        return -1;
                    }
                }
            } else {
                return voc1[i].length() > voc2[i].length() ? 1 : -1;
            }
        }
        if (isEqLen) {
            return 0;
        }
        return voc1.length > minLen ? findMax(voc1, minLen, 1) : findMax(voc2, minLen, -1);
    }

    private String removeLeadingZero(String x) {
        String y = "0";
        for (int j = 1; j < x.length(); j++) {
            if (x.charAt(j) > '0') {
                y = x.substring(j);
                return y;
            }
        }
        return y;
    }

    private String[] removeZero(String version) {
        String[] result = null;
        if (!version.contains(".")) {
            result = new String[1];
            result[0] = version.charAt(0) == '0' ? removeLeadingZero(version) : version;
            return result;
        }
        result = version.split("\\.");
        int n = result.length;
        for (int i = 0; i < n; i++) {
            if (result[i] == null || result[i].trim().isEmpty()) {
                result[i] = "0";
            } else if (result[i].charAt(0) == '0') {
                result[i] = removeLeadingZero(result[i]);
            }
        }
        return result;
    }

    private int findMax(String[] voc, int start, int res) {
        for (int i = start; i < voc.length; i++) {
            for (int j = 0; j < voc[i].length(); j++) {
                if (voc[i].charAt(j) > '0') {
                    return res;
                }
            }
        }
        return 0;
    }

    public static void main(String... args) {
        CompareVersionNumbers cvn = new CompareVersionNumbers();
        System.out.println(cvn.compareVersion("1.1", "1.0"));
    }
}
