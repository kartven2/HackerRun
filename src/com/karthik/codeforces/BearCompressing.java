/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/653/B
 *
 * Find the number of strings of length n that Limak
 * will be able to transform to string "a" by applying only operations given in the input.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BearCompressing {

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

    Map<Integer, List<String>> input = new TreeMap<>();
    Set<String> output = new HashSet<>();

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/bearcompression2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        int m = sc.nextInt();
        while (m-- > 0) {
            String twoChar = sc.readNext();
            String keyStr = sc.readNext();
            int key = keyStr.toCharArray()[0];
            List<String> twoCharList = input.get(key);
            if (twoCharList == null) {
                twoCharList = new ArrayList<>();
            }
            twoCharList.add(twoChar);
            input.put(key, twoCharList);
        }
        int dest = 'a';
        List<String> toDest = input.get(dest);
        if (toDest != null && !toDest.isEmpty()) {
            if (n == 2) {
                for (String x : toDest) {
                    output.add(x);
                }
            } else if (n > 2) {
                for (int j = 0; j < toDest.size(); j++) {
                    String x = toDest.get(j);
                    countAllStringsOfLengthN(n, new StringBuilder(x), 0, true);
                    //countAllStringsOfLengthN(n, new StringBuilder(x), 1, false);
                }
            }
        }
        System.out.println(output.size());
    }

    private void countAllStringsOfLengthN(int maxLen, StringBuilder prefix, int idx, boolean isPrefixExp) {
        if (maxLen == prefix.length()) {
            //System.out.println(prefix.toString());
            output.add(prefix.toString());
            return;
        }
        List<String> toDest = input.get((int) prefix.charAt(idx));
        if (toDest == null || toDest.isEmpty()) {
            return;
        }
        for (String x : toDest) {
            StringBuilder tmp = new StringBuilder(prefix);
            if (isPrefixExp) {
                tmp.deleteCharAt(0);
                tmp.insert(0, x);
            } else {
                tmp.deleteCharAt(tmp.length() - 1);
                tmp.append(x);
            }
            countAllStringsOfLengthN(maxLen, tmp, idx, isPrefixExp);
        }
    }

    public static void main(String... args) {
        BearCompressing bc = new BearCompressing();
        bc.compute();
    }
}
