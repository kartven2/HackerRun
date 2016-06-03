/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/645/C
 *
 * Find the minimum possible distance between Farmer John's room and his farthest cow..
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class EnduringExodus {

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    class InputReader {

        private static final int INPUT_KB = 5 * 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }

            return tokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    private int n;
    private int k;
    private String inputStr;
    private int[] input;

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/enduringexodus3"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        k = sc.nextInt();
        inputStr = sc.readNext();
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = inputStr.charAt(i) == '0' ? 0 : 1;
        }
        int wl = 0;
        while (wl < n && input[wl] == 1) {
            wl++;
        }
        int wr = wl;
        int best = MAX_VALUE;
        int[][] bestIdx = new int[2][n];
        int bestPtr = 0;
        int zc = 0;
        for (; wr < n; wr++) {
            if (input[wr] == 0 && zc < k + 1) {
                zc++;
            }
            if (zc == k + 1) {
                if (best >= wr - wl + 1) {
                    if (best > wr - wl + 1) {
                        best = wr - wl + 1;
                    }
                    bestIdx[0][bestPtr] = wl;
                    bestIdx[1][bestPtr] = wr;
                    bestPtr++;
                }
                wl++;
                zc--;
                while (wl < wr && input[wl] == 1) {
                    wl++;
                }
            }
        }
        int fresult = MAX_VALUE;
        for (int m = 0; m < bestPtr; m++) {
            if (best == bestIdx[1][m] - bestIdx[0][m] + 1) {
                int bestOwnerRoom = bestIdx[0][m];
                while (Math.max(bestOwnerRoom - bestIdx[0][m], bestIdx[1][m] - bestOwnerRoom)
                        > Math.max(nextEmptyRoom(bestOwnerRoom) - bestIdx[0][m], bestIdx[1][m] - nextEmptyRoom(bestOwnerRoom))) {
                    bestOwnerRoom = nextEmptyRoom(bestOwnerRoom);
                }
                fresult = Math.min(Math.max(bestOwnerRoom - bestIdx[0][m], bestIdx[1][m] - bestOwnerRoom), fresult);
            }
        }
        fresult = fresult == MAX_VALUE ? 1 : fresult;
        System.out.println(fresult);
    }

    private int nextEmptyRoom(int fromIdx) {
        do {
            fromIdx++;
        } while (fromIdx < n && input[fromIdx] == 1);
        return fromIdx;
    }

    public static void main(String[] args) {
        EnduringExodus e = new EnduringExodus();
        e.compute();
    }
}
