/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/670/F
 *
 * Print the smalles integer n which Vasya could pass to Kate.
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
public class RestoreANumber {

    class StrCustom implements Comparable {

        private static final int MAX = (int) 1e6 + 5;
        private char[] s;
        private int f;
        private int r;
        private int n;
        private int c;

        StrCustom() {
            s = new char[MAX];
            f = 1;
            c = MAX - 1;
        }

        private void init(char[] v) {
            for (int i = 0; i < v.length; i++) {
                inLast(v[i]);
            }
        }

        private boolean isEmpty() {
            return n == 0;
        }

        private String getStr() {
            StringBuilder sb = new StringBuilder();
            int m = n;
            int fp = f;
            while (m-- > 0) {
                sb.append(s[((fp - 1) % c + c) % c]);
                fp--;
            }
            return sb.toString();
        }

        private char first() {
            return s[((f - 1) % c + c) % c];
        }

        private void inLast(char v) {
            n++;
            s[r--] = v;
            r = (r % c + c) % c;
        }

        private void fillLast(int x, char v) {
            if (x == 0) {
                return;
            }
            for (int i = 0; i < x; i++) {
                inLast(v);
            }
        }

        @Override
        public int compareTo(Object o) {
            StrCustom other = (StrCustom) o;
            return this.getStr().compareTo(other.getStr());
        }
    }

    private int digitCount(int n) {
        int s = 0;
        for (; n > 0; n /= 10) {
            s++;
        }
        return s;
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/restoreanumber"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }

        int[] cntdigits = new int[10];
        char[] a = sc.readNext();
        char[] b = sc.readNext();

        int n = a.length;
        int m = b.length;

        //Increment count for each digit in the text
        for (int i = 0; i < n; i++) {
            cntdigits[a[i] - '0']++;
        }

        //Reduce count for digits in the pattern
        for (int i = 0; i < m; i++) {
            cntdigits[b[i] - '0']--;
        }

        //n=10,digitcntofn=2,digitcnt=8,2!=1 -> digitcnt=9
        //n=12,digitcntofn=2,digitcnt=10,2==2 -> digitcnt=10
        int digitcntofn = digitCount(n);
        int digitcnt = n - digitcntofn;
        if (digitcntofn != digitCount(digitcnt)) {
            digitcnt++;
        }

        //Reduce count for digits in digitcnt
        for (; digitcnt > 0; digitcnt /= 10) {
            cntdigits[digitcnt % 10]--;
        }

        //Smallest possible pattern preceding number valid/invalid with available digits
        StrCustom y = new StrCustom();
        y.init(b);
        for (char q = '0'; q <= '9'; q++) {
            y.fillLast(cntdigits[q - '0'], q);
        }

        StrCustom x = new StrCustom();
        char msbchar = '1';
        int patternidx = 0;
        char breakcharpattern = b[0];

        //Find the smallest MSB character for x
        for (; msbchar <= '9' && cntdigits[msbchar - '0'] == 0; msbchar++);

        //No digits found or only 0's exists
        //0000454312911
        //9213544
        //0000
        if (msbchar > '9') {
            x.init(b);
            x.fillLast(cntdigits[0], '0');
        } else {

            //Reduce count for MSB character in cntdigits
            //Make the first digit in x as the smallest MSB character
            cntdigits[msbchar - '0']--;
            x.inLast(msbchar);

            //Find the next digit different from the MSB character digit in the pattern
            //Reduce the break character if the pattern is decreasing sequence
            //3116481,118 ->  111834
            //3716488,881 ->  347881
            for (patternidx = 0; patternidx < m && b[patternidx] == b[0]; patternidx++);
            if (patternidx < m && b[patternidx] < b[0]) {
                breakcharpattern--;
            }

            //Construct x using cntdigits like ['0','1','2'..breakchar]
            //pattern itself and then [breakchar+1, '9']
            for (char q = '0'; q <= breakcharpattern; q++) {
                x.fillLast(cntdigits[q - '0'], q);
            }
            for (int i = 0; i < b.length; i++) {
                x.inLast(b[i]);
            }
            breakcharpattern++;
            for (char q = breakcharpattern; q <= '9'; q++) {
                x.fillLast(cntdigits[q - '0'], q);
            }
        }

        //Smallest valid number between x and y
        StrCustom result = x.compareTo(y) < 0 || y.first() == '0' ? x : y;
        System.out.print(result.getStr());
    }

    class InputReader {

        private static final int INPUT_KB = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), INPUT_KB);
        }

        char[] readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalArgumentException(ex);
                }
            }
            return tokenizer.nextToken().toCharArray();
        }
    }

    public static void main(String... args) {
        RestoreANumber ran = new RestoreANumber();
        ran.compute();
    }
}
