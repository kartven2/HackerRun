/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/651/B
 *
 * Find the maximum possible number of indices i (1 ≤ i ≤ n - 1), 
 * such that ai + 1 > ai.
 */
package com.karthik.codeforces;

import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastBeautifulPaintings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count[] = new int[10001];
        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, ++count[sc.nextInt()]);
        }
        System.out.println(n - max);
    }
}
