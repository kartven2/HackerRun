/*
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
package com.karthik.leetcode;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FindDuplicateNumber {

    public static void main(String... args) {
        FindDuplicateNumber fdn = new FindDuplicateNumber();
        System.out.println(fdn.findDuplicate(new int[]{1, 2, 3, 4, 2}));
    }

    public int findDuplicate(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, s = 0, f = 0;
        do {
            s = a[s];
            f = a[a[f]];
        } while (s != f);
        f = 0;
        while (s != f) {
            s = a[s];
            f = a[f];
        }
        return f;
    }

    public int findDuplicate2(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2, cnt = 0;
            for (int x : a) {
                if (x <= m) {
                    cnt++;
                }
            }
            if (cnt <= m) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
