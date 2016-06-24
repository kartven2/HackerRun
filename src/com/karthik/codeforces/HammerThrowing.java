/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/683/E
 *
 * The first line contains the positive integer n (1 ≤ n ≤ 1000) — the number of athletes.
 * The next line contains the sequence of integers a1, a2, ..., an (0 ≤ ai < n), where ai is equal to
 * the number of the athletes with identifiers larger than i, who should throw the hammer before the athlete with identifier i.
 * 
 */
package com.karthik.codeforces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class HammerThrowing {

    public static void main(String... args) {
        HammerThrowing ht = new HammerThrowing();
        ht.compute();
    }

    private void compute() {
        //Scanner sc = new Scanner(System.in);
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream("./resources/hammerthrowing"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int[] output = new int[n];
        int[] zcidx = new int[n + 1];
        Arrays.fill(zcidx, -1);
        int lastMaxKey = 0;
        for (int i = 1; i <= n; i++) {
            int totalzero = sc.nextInt();
            int j = 0;
            int countzero = 0;
            int zcbegin = -1;
            if (totalzero <= lastMaxKey) {
                lastMaxKey = totalzero;
            }
            zcbegin = zcidx[lastMaxKey];
            if (zcbegin > -1) {
                j = zcbegin + 1;
                countzero = lastMaxKey;
            }
            for (; j < n; j++) {
                if (output[j] == 0) {
                    if (countzero == totalzero) {
                        output[j] = i;
                        Arrays.fill(zcidx, countzero, zcidx.length, -1);
                        break;
                    } else {
                        zcidx[++countzero] = j;
                        lastMaxKey = countzero;
                    }
                }
            }
        }
        for (int x : output) {
            System.out.print(x + " ");
        }
    }
}
