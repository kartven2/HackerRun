/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/677/C
 *
 * Find the the number of possible pairs of words, 
 * such that their bitwise AND is equal to string s modulo 109 + 7.
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
public class VanyaLabel {

    private String findBitString(char c) {
        if (c >= '0' && c <= '9') {
            return bitString(((int) c) - '0');
        }
        if (c >= 'A' && c <= 'Z') {
            return bitString(((int) c) - 'A' + 10);
        }
        if (c >= 'a' && c <= 'z') {
            return bitString(((int) c) - 'a' + 36);
        }
        if (c == '-') {
            return bitString(62);
        }
        if (c == '_') {
            return bitString(63);
        }
        return null;
    }

    private String bitString(int c) {
        StringBuilder sb = new StringBuilder();
        while (c > 0) {
            int r = c % 2;
            sb.insert(0, r);
            c /= 2;
        }
        int res = 6 - sb.length();
        for (int i = 0; i < res; i++) {
            sb.insert(0, 0);
        }
        return sb.toString();
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
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
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/vanyanlabel2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        long output = 1;
        String text = sc.readNext();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String bitString = findBitString(c);
            for (int j = 0; j < bitString.length(); j++) {
                if (bitString.charAt(j) == '0') {
                    output *= 3;
                    output %= (1e9 + 7);
                }
            }
        }
        System.out.println(output);
    }

    public static void main(String... args) {
        VanyaLabel va = new VanyaLabel();
        va.compute();
    }
}
