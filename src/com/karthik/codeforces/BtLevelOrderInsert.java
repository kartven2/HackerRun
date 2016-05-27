/*-
 * Level Order Binary Tree Insertion 
 */
package com.karthik.codeforces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Karthik Venkataraman
 * @email kafy83@gmail.com
 */
public class BtLevelOrderInsert {

    static class Bt {

        private Node root;

        private void insert(int i) {
            root = insert(root, i);
        }

        private Node insert(Node x, int value) {
            if (x == null) {
                x = new Node(value, 1);
                return x;
            }

            if (shouldGoLeft(x)) {
                x.left = insert(x.left, value);
            } else {
                x.right = insert(x.right, value);
            }

            x.size = size(x.left) + size(x.right) + 1;
            return x;
        }

        private int maxDepth(Node x) {
            return logBase2(size(x));
        }

        private int maxLeafNodes(Node x) {
            return (int) Math.pow(2, maxDepth(x));
        }

        private int size(Node x) {
            if (x == null) {
                return 0;
            }
            return x.size;
        }

        private boolean shouldGoLeft(Node x) {
            int maxLeafNodes = maxLeafNodes(x);
            int numLeafNodes = size(x) - maxLeafNodes(x) + 1;
            if (maxLeafNodes == numLeafNodes) {
                return true;
            }
            if (numLeafNodes < maxLeafNodes / 2) {
                return true;
            }
            return false;
        }

        private int logBase2(int n) {
            int result = 0;
            int value = 1;
            while (value < n) {
                value *= 2;
                result++;
            }
            if ((n & n - 1) != 0) {
                result--;
            }
            return result;
        }
    }

    static class Node<T extends Comparable<?>> {

        Node<T> left, right;
        T data;
        int size;

        public Node(T data, int size) {
            this.data = data;
            this.size = size;
        }
    }

    static class BTreePrinter {

        public static <T extends Comparable<?>> void printNode(Node<T> root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) {
                return;
            }

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node<T>> newNodes = new ArrayList<>();
            for (Node<T> node : nodes) {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null) {
                        System.out.print("/");
                    } else {
                        BTreePrinter.printWhitespaces(1);
                    }

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null) {
                        System.out.print("\\");
                    } else {
                        BTreePrinter.printWhitespaces(1);
                    }

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }
            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
            }
        }

        private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
            if (node == null) {
                return 0;
            }
            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Bt b = new Bt();
        int[] input = {5, 6, 7, 8, 4, 7, 8, 9, 1, 2, 5, 8, 9, 2, 3, 4, 5, 7, 8};
        for (int a : input) {
            b.insert(a);
        }
        BTreePrinter.printNode(b.root);
    }
}
