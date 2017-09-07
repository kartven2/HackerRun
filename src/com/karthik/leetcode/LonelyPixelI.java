/*
 * Leetcode: https://leetcode.com/problems/lonely-pixel-i/description/
 */
package com.karthik.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LonelyPixelI {

    public static void main(String... args) {
        LonelyPixelI lp = new LonelyPixelI();
        char[][] a = {{'B', 'B', 'B'}};
        lp.findLonelyPixel(a);
    }

    public int findLonelyPixel(char[][] a) {
        if (a == null || a.length == 0) {
            return 0;
        }
        int n = a.length, m = a[0].length, ans = 0;
        int[] arr = new int[m];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int ci = -1;
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 'B') {
                    ci = ci == -1 ? j : -2;
                    arr[j]++;
                }
            }
            if (ci > -1) {
                list.add(ci);
            }
        }
        for (int x : list) {
            if (arr[x] == 1) {
                ans++;
            }
        }
        return ans;
    }
}
