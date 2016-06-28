/*-
 * Codeforces problem :
 * http://codeforces.com/problemset/problem/676/D
 *
 * If Theseus is not able to get to Minotaur, then print -1 in the only line of the output.
 * Otherwise, print the minimum number of minutes required to get to the block where Minotaur is hiding.
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
public class TheseusLabyrinth {

    private static final String TOP = "+|^LRD";
    private static final String BOTTOM = "+|vLRU";
    private static final String LEFT = "+-<RUD";
    private static final String RIGHT = "+->LUD";

    private int n;
    private int m;
    private char[][][] vertexType;
    private boolean[][][] marked;

    class Coord {

        private int x;
        private int y;
        private int orientation;
        private int distance;

        Coord(int x, int y, int orientation, int distance) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
            this.distance = distance;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 11 * hash + this.x;
            hash = 11 * hash + this.y;
            hash = 11 * hash + this.orientation;
            hash = 11 * hash + this.distance;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Coord other = (Coord) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            if (this.orientation != other.orientation) {
                return false;
            }
            if (this.distance != other.distance) {
                return false;
            }
            return true;
        }
    }

    private void compute() {
        //InputReader sc = new InputReader(System.in);
        InputReader sc = null;
        try {
            sc = new InputReader(new FileInputStream("./resources/theseuslabyrinth2"));
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException(ex);
        }
        n = sc.nextInt();
        m = sc.nextInt();
        vertexType = new char[n][m][4];
        marked = new boolean[n][m][4];

        for (int i = 0; i < n; i++) {
            char[] chars = sc.readNext().toCharArray();
            for (int j = 0; j < m; j++) {
                vertexType[i][j][0] = chars[j];
                for (int k = 1; k < 4; k++) {
                    switch (vertexType[i][j][k - 1]) {
                        case '+':
                            vertexType[i][j][k] = '+';
                            break;
                        case '-':
                            vertexType[i][j][k] = '|';
                            break;
                        case '|':
                            vertexType[i][j][k] = '-';
                            break;
                        case '^':
                            vertexType[i][j][k] = '>';
                            break;
                        case '>':
                            vertexType[i][j][k] = 'v';
                            break;
                        case '<':
                            vertexType[i][j][k] = '^';
                            break;
                        case 'v':
                            vertexType[i][j][k] = '<';
                            break;
                        case 'L':
                            vertexType[i][j][k] = 'U';
                            break;
                        case 'R':
                            vertexType[i][j][k] = 'D';
                            break;
                        case 'U':
                            vertexType[i][j][k] = 'R';
                            break;
                        case 'D':
                            vertexType[i][j][k] = 'L';
                            break;
                        case '*':
                            vertexType[i][j][k] = '*';
                            break;
                        default:
                            throw new IllegalArgumentException("invalid input");
                    }
                }
            }
        }

        Graph g = new Graph(new Coord(sc.nextInt() - 1, sc.nextInt() - 1, 0, 0),
                new Coord(sc.nextInt() - 1, sc.nextInt() - 1, 0, 0));
        g.simulate();
    }

    class Graph {

        private Coord source;
        private Coord dest;

        Graph(Coord source, Coord dest) {
            if (source == dest) {
                System.out.println(0);
                System.exit(0);
            }
            this.source = source;
            this.dest = dest;
        }

        private void simulate() {
            SimpleQueue<Coord> q = new SimpleQueue<>();
            q.add(source);
            while (!q.isEmpty()) {
                Coord v = q.remove();

                if (marked[v.x][v.y][v.orientation]) {
                    continue;
                }

                marked[v.x][v.y][v.orientation] = true;

                if (v.x == dest.x && v.y == dest.y) {
                    System.out.println(v.distance);
                    System.exit(0);
                }

                if (!marked[v.x][v.y][(v.orientation + 1) % 4]) {
                    q.add(new Coord(v.x, v.y, (v.orientation + 1) % 4, v.distance + 1));
                }

                if (v.x - 1 >= 0 && !marked[v.x - 1][v.y][v.orientation]
                        && TOP.indexOf(vertexType[v.x][v.y][v.orientation]) != -1
                        && BOTTOM.indexOf(vertexType[v.x - 1][v.y][v.orientation]) != -1) {
                    q.add(new Coord(v.x - 1, v.y, v.orientation, v.distance + 1));
                }

                if (v.x + 1 < n && !marked[v.x + 1][v.y][v.orientation]
                        && BOTTOM.indexOf(vertexType[v.x][v.y][v.orientation]) != -1
                        && TOP.indexOf(vertexType[v.x + 1][v.y][v.orientation]) != -1) {
                    q.add(new Coord(v.x + 1, v.y, v.orientation, v.distance + 1));
                }

                if (v.y - 1 >= 0 && !marked[v.x][v.y - 1][v.orientation]
                        && LEFT.indexOf(vertexType[v.x][v.y][v.orientation]) != -1
                        && RIGHT.indexOf(vertexType[v.x][v.y - 1][v.orientation]) != -1) {
                    q.add(new Coord(v.x, v.y - 1, v.orientation, v.distance + 1));
                }

                if (v.y + 1 < m && !marked[v.x][v.y + 1][v.orientation]
                        && RIGHT.indexOf(vertexType[v.x][v.y][v.orientation]) != -1
                        && LEFT.indexOf(vertexType[v.x][v.y + 1][v.orientation]) != -1) {
                    q.add(new Coord(v.x, v.y + 1, v.orientation, v.distance + 1));
                }
            }
            System.out.println(-1);
            System.exit(0);
        }
    }

    class SimpleQueue<Key> {

        private Key[] q;
        private int size;
        private int first;
        private int last;

        SimpleQueue() {
            q = (Key[]) new Object[n * m * 4];
            size = first = last = 0;
        }

        private boolean isEmpty() {
            return size == 0;
        }

        private void add(Key key) {
            q[last++] = key;
            size++;
            if (last == q.length) {
                last = 0;
            }
        }

        private Key remove() {
            Key key = q[first];
            q[first++] = null;
            size--;
            if (first == q.length) {
                first = 0;
            }
            return key;
        }
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

        private int nextInt() {
            return Integer.parseInt(readNext());
        }
    }

    public static void main(String... args) {
        TheseusLabyrinth tl = new TheseusLabyrinth();
        tl.compute();
    }
}
