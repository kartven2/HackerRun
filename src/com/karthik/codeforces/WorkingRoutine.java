/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/706/E
 *
 * Print n lines containing m integers each — the resulting matrix.
 */
package com.karthik.codeforces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class WorkingRoutine {

    private InputReader sc = new InputReader(System.in);
    private PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    class Node {

        private int v;
        private Node r, d;

        Node(int v) {
            this.v = v;
        }
    }

    private Node[][] nds;

    private Node find(int r, int c) {
        Node t = nds[0][0];
        for (int i = 0; i < r; i++) {
            t = t.d;
        }
        for (int i = 0; i < c; i++) {
            t = t.r;
        }
        return t;
    }

    private void setRightPt(Node x, Node y, int h) {
        for (int i = 0; i < h; i++) {
            x.r = y;
            x = x.d;
            y = y.d;
        }
    }

    private void setDownPt(Node x, Node y, int w) {
        for (int i = 0; i < w; i++) {
            x.d = y;
            x = x.r;
            y = y.r;
        }
    }

    private void compute() {
        try {
            sc = new InputReader(new FileInputStream("./resources/workingroutine"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        int n = sc.nextInt(), m = sc.nextInt(), q = sc.nextInt();
        nds = new Node[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            nds[i][0] = new Node(-1);
            nds[i][m + 1] = new Node(-1);
        }
        for (int i = 1; i < m + 1; i++) {
            nds[0][i] = new Node(-1);
            nds[n + 1][i] = new Node(-1);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                nds[i][j] = new Node(sc.nextInt());
            }
        }
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                nds[i][j].r = (j + 1) < (m + 2) ? nds[i][j + 1] : null;
                nds[i][j].d = (i + 1) < (n + 2) ? nds[i + 1][j] : null;
            }
        }
        for (; q > 0; q--) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt(), h = sc.nextInt(), w = sc.nextInt();
            final Node x = find(a, b), ux = find(a - 1, b), lx = find(a, b - 1), rx = find(a, b + w - 1), rxn = find(a, b + w), bx = find(a + h - 1, b), bxn = find(a + h, b);
            final Node y = find(c, d), uy = find(c - 1, d), ly = find(c, d - 1), ry = find(c, d + w - 1), ryn = find(c, d + w), by = find(c + h - 1, d), byn = find(c + h, d);
            setRightPt(lx, y, h);
            setDownPt(ux, y, w);
            setDownPt(bx, byn, w);
            setRightPt(rx, ryn, h);
            setRightPt(ly, x, h);
            setDownPt(uy, x, w);
            setDownPt(by, bxn, w);
            setRightPt(ry, rxn, h);
        }
        Node t = nds[1][0];
        for (int i = 0; i < n; i++) {
            Node l = t.r;
            for (int j = 0; j < m; j++) {
                pw.print(l.v + " ");
                l = l.r;
            }
            t = t.d;
            pw.println();
        }
        pw.flush();
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
        WorkingRoutine wr = new WorkingRoutine();
        wr.compute();
    }

}
