/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/678/B
 *
 * Print the only integer y' — the next year after
 * y when the calendar will be the same. Note that
 * you should find the first year after y with the
 * same calendar.
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
public class SameCalendar {

    private InputReader sc = new InputReader(System.in);
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    private boolean isLeap(int y) {
        return y % 400 == 0 || (y % 4 == 0 && y % 100 > 0);
    }

    private void compute() {
        int n = sc.nextInt(), d = 0;
        boolean stleap = isLeap(n);
        for (;;) {
            d = isLeap(n++) ? (d + 2) % 7 : (d + 1) % 7;
            if (d == 0 && stleap == isLeap(n)) {
                break;
            }
        }
        pw.print(n);
        pw.flush();
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
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        SameCalendar sca = new SameCalendar();
        sca.compute();
    }
}