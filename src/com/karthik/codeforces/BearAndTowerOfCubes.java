/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/679/B
 *
 * Print two integers — the maximum number of blocks in the tower
 * and the maximum required total volume X, resulting in the maximum number of blocks.
 */
package com.karthik.codeforces;

import java.util.Scanner;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BearAndTowerOfCubes {

    long maxblocks = 0;
    long maxvolume = -1;

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        long m = sc.nextLong();
        BearAndTowerOfCubes bt = new BearAndTowerOfCubes();
        bt.compute(m, 0, 0);
        System.out.println(bt.maxblocks + " " + bt.maxvolume);
    }

    private void compute(long m, long blocks, long volume) {
        if (m == 0) {
            if (blocks > maxblocks) {
                maxblocks = blocks;
                maxvolume = volume;
            }
            return;
        }
        long a = 1;
        while ((a + 1) * (a + 1) * (a + 1) <= m) {
            a++;
        }
        long a3 = a * a * a;
        compute(m - a3, blocks + 1, volume + a3);
        long aminus13 = (a - 1) * (a - 1) * (a - 1);
        compute(a3 - 1 - aminus13, blocks + 1, volume + aminus13);
    }
}
