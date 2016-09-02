/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/691/B
 *
 * Print "TAK" if the string s is "s-palindrome" and "NIE" otherwise.
 * 
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class Spalindrome {

    private static final int[] mi = new int[125];

    static {
        mi['A'] = 'A';
        mi['b'] = 'd';
        mi['d'] = 'b';
        mi['H'] = 'H';
        mi['I'] = 'I';
        mi['M'] = 'M';
        mi['O'] = 'O';
        mi['o'] = 'o';
        mi['q'] = 'p';
        mi['p'] = 'q';
        mi['T'] = 'T';
        mi['U'] = 'U';
        mi['V'] = 'V';
        mi['v'] = 'v';
        mi['W'] = 'W';
        mi['w'] = 'w';
        mi['X'] = 'X';
        mi['x'] = 'x';
        mi['Y'] = 'Y';
    }

    private void compute() {
        InputReader sc = new InputReader(System.in);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String input = sc.readNext();
        int m = input.length();
        int mby2 = (m & 1) == 0 ? m >> 1 : (m >> 1) + 1;
        for (int i = 0; i < mby2; i++) {
            if (mi[input.charAt(i)] != input.charAt(m - 1 - i)) {
                System.out.print("NIE");
                return;
            }
        }
        System.out.print("TAK");
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken();
        }
    }

    public static void main(String... args) {
        Spalindrome sp = new Spalindrome();
        sp.compute();
    }
}
