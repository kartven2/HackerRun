/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/688/B
 *
 * Print the n-th even-length palindrome number.
 */
package com.karthik.codeforces;

import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class LovelyPalindromes {

    private void compute() {
        StringBuilder sb = new StringBuilder();
        String n = new Scanner(System.in).next();
        sb.append(n);
        char[] narr = n.toCharArray();
        int j = narr.length - 1;
        while (j >= 0) {
            sb.append(narr[j--]);
        }
        System.out.print(sb.toString());
    }

    public static void main(String... args) {
        LovelyPalindromes lp = new LovelyPalindromes();
        lp.compute();
    }
}
