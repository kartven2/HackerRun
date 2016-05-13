/*-
 * HackerRun problem :
 * https://www.hackerrank.com/challenges/chocolate-in-box
 *
 * Find the count of chocolate boxes to pick in order to win.
 * 
 */
package com.karthik.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class ChocolateBox {

    private long cbox[];

    private long wincount;

    private Scanner sc;

    private long xorSum;

    private void compute() {
        for (long x : cbox) {
            xorSum ^= x;
        }

        for (long x : cbox) {
            long y = xorSum ^ x;
            if (y < x) {
                wincount++;
            }
        }
    }

    private void input() {
        //sc = new Scanner(System.in);
        try {
            sc = new Scanner(new File("./resources/chocbox"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Input file not found" + ex.getMessage());
        }

        cbox = new long[sc.nextInt()];
        for (int i = 0; i < cbox.length; i++) {
            cbox[i] = sc.nextLong();
        }
    }

    private void output() {
        System.out.println(wincount);
    }

    public static void main(String[] args) {
        ChocolateBox cbox = new ChocolateBox();
        cbox.input();
        cbox.compute();
        cbox.output();
    }
}
