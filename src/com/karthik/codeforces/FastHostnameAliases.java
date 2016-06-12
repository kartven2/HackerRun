/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/644/C
 *
 * Determine the groups of server names that correspond
 * to one website. 
 * Ignore groups consisting of the only server name.
 *
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class FastHostnameAliases {

    static class InputReader {

        private static final int inputkb = 1024;
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), inputkb);
        }

        private String readNext() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }
            return tokenizer.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/hostnamealiases2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt();
        Map<String, Set<String>> url = new HashMap<>();
        Map<Set<String>, List<String>> pathToServer = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String x = sc.readNext().substring(7);
            String server = x.indexOf('/') == -1 ? x.substring(0, x.length()) : x.substring(0, x.indexOf('/'));
            String path = x.indexOf('/') == -1 ? "-1" : x.substring(x.indexOf('/'));
            if (!url.containsKey(server)) {
                url.put(server, new HashSet<>());
            }
            url.get(server).add(path);
        }
        for (Entry<String, Set<String>> k : url.entrySet()) {
            Set<String> paths = k.getValue();
            if (!pathToServer.containsKey(paths)) {
                pathToServer.put(paths, new ArrayList<>());
            }
            pathToServer.get(paths).add(k.getKey());
        }
        List<String>[] output = new ArrayList[pathToServer.size()];
        int ptr = 0;
        for (List<String> servers : pathToServer.values()) {
            if (servers.size() > 1) {
                output[ptr++] = servers;
            }
        }
        System.out.println(ptr);
        for (int i = 0; i < ptr; i++) {
            for (int j = 0; j < output[i].size(); j++) {
                System.out.print("http://" + output[i].get(j) + " ");
            }
            System.out.println();
        }
    }
}
