/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/672/B
 *
 * Find the minimum number of characters to be replaced
 * in a string so that all substrings of it are unique.
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
public class DifferentIsGood {

    class InputReader {

        private static final int INPUT_KB = 1024;

        private BufferedReader bufferedReader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            bufferedReader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(bufferedReader.readLine());
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

    class CharSort {

        private final char[] text;

        CharSort(String input) {
            text = input.toCharArray();
            sort(0, input.length() - 1);
        }

        private void sort(int lo, int hi) {

            if (hi <= lo) {
                return;
            }

            int lt = lo, gt = hi;
            char v = text[lo];
            int i = lo + 1;
            while (i <= gt) {
                char t = text[i];
                if (t < v) {
                    exchange(lt++, i++);
                } else if (t > v) {
                    exchange(i, gt--);
                } else {
                    i++;
                }
            }

            sort(lo, lt - 1);
            sort(gt + 1, hi);
        }

        private void exchange(int i, int j) {
            char tmp = text[i];
            text[i] = text[j];
            text[j] = tmp;
        }

        private int duplicates() {
            int sum = 0;
            for (int i = 1; i < text.length; i++) {
                if (text[i] == text[i - 1]) {
                    sum++;
                }
            }
            return sum;
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/differentisgood2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        int n = sc.nextInt();
        int ans = -1;
        if (n < 27) {
            CharSort ss = new CharSort(sc.readNext());
            ans = ss.duplicates();
        }
        System.out.println(ans);
    }

    public static void main(String... args) {
        DifferentIsGood d = new DifferentIsGood();
        d.compute();
    }
}
