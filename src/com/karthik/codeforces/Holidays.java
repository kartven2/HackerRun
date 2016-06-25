/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/670/A
 *
 * The first line of the input contains a positive integer n (1 ≤ n ≤ 1 000 000) — 
 * the number of days in a year on Mars.
 * Print two integers — the minimum possible and the maximum possible
 * number of days off per year on Mars.
 */
package com.karthik.codeforces;

import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Holidays {

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int div = n / 7;
        int rem = n % 7;
        int add = rem;
        if (rem > 2) {
            add = 2;
        }
        int mn = 2 * div;
        int mx = mn + add;
        if (rem == 6) {
            mn++;
        }
        System.out.println(mn + " " + mx);
    }
}
